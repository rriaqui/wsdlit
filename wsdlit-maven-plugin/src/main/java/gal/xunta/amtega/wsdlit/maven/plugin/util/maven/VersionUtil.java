package gal.xunta.amtega.wsdlit.maven.plugin.util.maven;

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

import gal.xunta.amtega.wsdlit.maven.plugin.Messages;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.version.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Utilities related to {@link Version}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class VersionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger( VersionUtil.class );

    public static final Comparator< Artifact > ARTIFACT_COMPARATOR = ( o1, o2 ) -> {
        if ( o1 == null || o2 == null ) {
            final String message = Messages.I18N.getString( Messages.NULL_ARTIFACTS_ARE_NOT_ALLOWED_KEY );
            LOGGER.error( message );
            throw new NullPointerException( message );
        }
        final ComparableVersion v1 = new ComparableVersion( o1.getVersion() );
        final ComparableVersion v2 = new ComparableVersion( o2.getVersion() );
        return v1.compareTo( v2 );
    };


    private VersionUtil() {}

    /**
     * Returns {@code true} if the specified version is a development version.
     *
     * @param version the specified version to be tested
     * @return {@code true} if the specified version is a {@code SNAPSHOT}.
     */
    public static boolean isSnapshot( final Version version ) {
        return VersionType.DEVELOPMENT.appliesTo( version.toString() );
    }

    /**
     * Returns {@code true} if the specified version is not a development version.
     *
     * @param version the specified version to be tested
     * @return {@code true} if the specified version is not a {@code SNAPSHOT}.
     */
    public static boolean isNotSnapshot( final Version version ) {
        return !isSnapshot( version );
    }

    /**
     * Returns an artifact initialized from the specificied artifact,
     * where the version is a range with the limit of the specified artifact
     * version: @{code [, artifact.getVersion())}.
     *
     * @param artifact the specified artifact.
     * @return the artifact initialized from the specified artifact where the version is a range with the limit of the specified artifact version.
     */
    public static Artifact artifactWithVersionRangeOpenedClosed( final Artifact artifact ) {
        final String version = "[," + artifact.getVersion() + ")";
        return artifact.setVersion( version );
    }

    /**
     * Returns an artifact initialized from the specificied artifact,
     * where the version is an opened range with the limit of the specified artifact
     * version: @{code [, artifact.getVersion()]}.
     *
     * @param artifact the specified artifact.
     * @return the artifact initialized from the specified artifact where the version is an opened range with the limit of the specified artifact version.
     */
    public static Artifact artifactWithVersionRangeOpenedOpened( final Artifact artifact ) {
        final String version = "[," + artifact.getVersion() + "]";
        return artifact.setVersion( version );
    }

    /**
     * Normalize the specified version, removing non-significant spaces.
     *
     * @param version the specified version.
     * @return the normalized version.
     */
    public static String normalizeVersion( final String version ) {
        return version.trim();
    }

    /**
     * Returns an artifact initialized from the specificied artifact,
     * where the version is a range with the limit of the specified artifact
     * version: @{code [, artifact.getVersion())}.
     *
     * @param artifact the specified artifact.
     * @return the artifact initialized from the specified artifact where the version is a range with the limit of the specified artifact version.
     */
    public static Artifact versionsLowerThan( final Artifact artifact ) {
        return VersionUtil.artifactWithVersionRangeOpenedClosed( artifact );
    }

    /**
     * Finds the highest version for the specified candidate artifact that matches the specified version type.
     *
     * @param candidates the specified candidate.
     * @param versionType the specified version type.
     * @return the highest version of an {@code Optional<Artifact>} that meets the specified version type.
     */
    public static Optional< Artifact > findHighestArtifactFromVersion( final List< Artifact > candidates, final VersionType versionType) {
        if ( candidates == null || candidates.isEmpty() ) {
            LOGGER.warn( Messages.I18N.getString( Messages.CANDIDATES_LIST_IS_EMPTY_KEY ) );
            return Optional.empty();
        }

        return candidates
            .stream()
            .filter( versionType::appliesTo )
            .max( ARTIFACT_COMPARATOR );
    }

    /**
     * Finds in the specified list of candidates the highest version artifact that matches production versions.
     *
     * @param candidates the specified list of candidates.
     * @return the highest version of an {@code Optional<Artifact>} that meets the production version type.
     */
    public static Optional< Artifact > findHighestArtifactFromVersion( final List<Artifact> candidates ) {
        return findHighestArtifactFromVersion( candidates, VersionType.PRODUCTION );
    }
}
