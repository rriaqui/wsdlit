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

import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import gal.xunta.amtega.wsdlit.maven.plugin.Messages;
import org.eclipse.aether.artifact.Artifact;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Utilities related to Maven artifacts.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ArtifactUtil {
    private static final Pattern ARTIFACT_NAME_NORMALIZER_RE = Pattern.compile("[^\\w.-]");
    private static final Pattern ARTIFACT_NAME_AT_END_REMOVER_RE = Pattern.compile( "[_.-]+$" );
    private static final String ARTIFACT_SHOULD_BE_NOT_NULL = "O artefacto non pode ser nulo";

    private ArtifactUtil() {}

    /**
     * Calculates the normalized name of the specified artifact,
     * removing any characters not found in '[\\w.-]'.
     *
     * @param artifact the specified artifact.
     * @return the normalized name of the specified artifact, after removing any characters not found in '[\\w.-]'.
     */
    public static String normalizedArtifactName( final Artifact artifact ) {
        Objects.requireNonNull( artifact, ARTIFACT_SHOULD_BE_NOT_NULL );
        final String value = ARTIFACT_NAME_NORMALIZER_RE.matcher( artifact.getArtifactId() ).replaceAll( "" );
        return ARTIFACT_NAME_AT_END_REMOVER_RE.matcher( value ).replaceAll( "" );
    }

    /**
     * Calculates the normalized name of the specified artifact,
     * removing any characters not found in '[\\w.-]',
     * and adding the specified suffix to the name calculated.
     *
     * @param artifact the specified artifact.
     * @param suffix the specified suffix to add to the normalized name.
     * @return the normalized name of the artifact.
     */
    public static String normalizedArtifactName( final Artifact artifact, final String suffix ) {
        Objects.requireNonNull( artifact, ARTIFACT_SHOULD_BE_NOT_NULL );
        final String normalizedName = ArtifactUtil.normalizedArtifactName( artifact );
        if ( suffix != null ) {
            return normalizedName + suffix;
        }
        return normalizedName;
    }

    /**
     * Returns the absolute working directory of the specified artifact,
     * from the specified base directory and an optional suffix to be appended to the absolute working directory.
     *
     * @param baseDirectory the base directory against which the relative working directory is resolved.
     * @param artifact the specified artifact.
     * @param suffix an optional suffix to be appended to the absolute working directory.
     * @return an absolute {@code File} pointing to the absolute working directory of the artifact.
     */
    public static File getOutputDirectory( final File baseDirectory, final Artifact artifact, final String suffix ) {
        Objects.requireNonNull( artifact, ARTIFACT_SHOULD_BE_NOT_NULL );
        final String normalizedName = ArtifactUtil.normalizedArtifactName( artifact, suffix );
        return new File( baseDirectory, normalizedName );
    }

    /**
     * Convert coordinates in standard format to coordinates in the format used in maven-dependency-plugin.
     *
     * <p>
     *      Coordinates in standard Maven format follow the format:
     *      {@code groupId:artifactId[:extension[:classifier]]:version}
     * </p>
     * <p>
     *      Coordinates in maven-dependency-plugin format follow the format:
     *      {@code groupId:artifactId:version[:package[:classifier]]}
     * </p>
     *
     * @param coordinates standard Maven coordinates
     * @return coordinates expressed in maven-dependency-plugin format
     */
    public static String toMavenDependencyPluginCoordinates( final String coordinates ) {
        final String[] splited = StringUtil.split( ":", coordinates );
        int length = splited.length;

        if ( length >= 3 && length <= 5 ) {
            final StringBuilder fixed = new StringBuilder( length );
            fixed
                .append( splited[0] )
                .append( ":" )
                .append( splited[1] )
                .append( ":" )
                .append( splited[length - 1] );

            if ( length == 5 ) {
                fixed
                    .append( ":" )
                    .append( splited[2] )
                    .append( ":" )
                    .append( splited[3] );
            } else {
                if ( length == 4 ) {
                    fixed
                        .append( ":" )
                        .append( splited[2] );
                }
            }
            return fixed.toString();
        } else {
            throw new IllegalArgumentException(
                    Messages.I18N.format( Messages.INVALID_ARTIFACT_COORDINATES_ERROR_KEY, coordinates )
            );
        }
    }
}
