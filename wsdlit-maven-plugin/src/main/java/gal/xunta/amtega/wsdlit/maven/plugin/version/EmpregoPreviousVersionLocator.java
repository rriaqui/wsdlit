package gal.xunta.amtega.wsdlit.maven.plugin.version;

/*-
 * #%L
 * AMTEGA WsdlIT Maven Plugin
 * %%
 * Copyright (C) 2021 - 2022 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
 * %%
 * This file is part of "wsdlit".
 *
 * "wsdlit" is free software: you can redistribute it and/or modify
 * it under the terms of:
 * European Union Public License, either Version 1.2 or – as soon
 * they will be approved by the European Commission - subsequent versions of
 * the EUPL;
 *
 * "wsdlit" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * European Union Public License for more details.
 *
 * You may obtain a copy of tce European Union Public Licence at:
 * http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 * #L%
 */

import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.VersionUtil;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for locating and managing previous version of an artifact,
 * implementing the following Emprego business rules for the specified artifacts coordinates:
 *
 * <ul>
 *     <li>The version of the located artifacts is always lower than the version of the specified artifact.</li>
 *     <li>The name of the located artifacts matches the name of the specified artifact name.</li>
 *     <li>The name of the located artifacts matches the regular expression V\d+CA.</li>
 * </ul>
 *
 * <p>
 *      The list of returned artifacts can contains:
 * </p>
 *
 * <ul>
 *      <li>The one corresponding to the artifacts with the same {@code artifactId}, but lower in version.
 *      <li>The one corresponding to previous versioned artifacts, but lower in version.
 * </ul>
 *
 * <p>
 *      An artifact with the coordinates {@code emprego:artifactV3CA:2.0.0} will correspond to the following coordinates:
 * </p>
 *
 * <ul>
 * <li>{@code emprego:artifactV3CA:[,2.0.0)}
 * <li>{@code emprego:artifactV2CA:[,2.0.0)}
 * </ul>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class EmpregoPreviousVersionLocator
implements PreviousArtifactLocator {
    // We limit to a max of 6 repetitions to avoid catastrophic backtracking.
    private static final Pattern NAMING_CONVENTION_PATTERN = Pattern.compile( "^(.*V)(\\d{1,6})(.*CA)$" );

    @Override
    public List<Artifact> listOfPossibleArtifacts( final Artifact artifact ) {
        final List<Artifact> candidates = new ArrayList<>();
        final Matcher matcher = NAMING_CONVENTION_PATTERN.matcher( artifact.getArtifactId() );
        if ( matcher.matches() ) {
            final Artifact previousNamingVersionArtifact = previousNamingVersion( artifact, matcher );
            if( previousNamingVersionArtifact != null ) {
                candidates.add( previousNamingVersionArtifact );
            }
            candidates.add( 0, VersionUtil.artifactWithVersionRangeOpenedClosed( artifact ) );
            return Collections.unmodifiableList( candidates );
        }
        return Collections.emptyList();
    }

    @Override
    public boolean appliesTo( final Artifact artifact ) {
        return artifact != null && NAMING_CONVENTION_PATTERN
            .matcher( artifact.getArtifactId() ).matches();
    }

    private static Artifact previousNamingVersion( final Artifact artifact, final Matcher matcher ) {
        final int candidateVersionNumber = Integer.parseInt( matcher.group( 2 ) ) - 1;
        if ( candidateVersionNumber > 0 ) {
            final String candidateArtifactId = matcher.group( 1 ) + candidateVersionNumber + matcher.group( 3 );
            final Artifact previousArtifact = new DefaultArtifact(
                    artifact.getGroupId(),
                    candidateArtifactId,
                    artifact.getClassifier(),
                    artifact.getExtension(),
                    artifact.getVersion()
            );
            return VersionUtil.artifactWithVersionRangeOpenedOpened( previousArtifact );
        }
        return null;
    }
}
