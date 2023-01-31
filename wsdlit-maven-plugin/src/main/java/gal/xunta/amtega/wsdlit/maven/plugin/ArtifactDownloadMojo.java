package gal.xunta.amtega.wsdlit.maven.plugin;

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

import gal.xunta.amtega.wsdlit.maven.plugin.diff.DiffActionOnError;
import gal.xunta.amtega.wsdlit.maven.plugin.diff.DiffVersionPersistence;
import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.VersionUtil;
import gal.xunta.amtega.wsdlit.maven.plugin.version.ArtifactRelations;
import gal.xunta.amtega.wsdlit.maven.plugin.version.DefaultPreviousVersionLocator;
import gal.xunta.amtega.wsdlit.maven.plugin.version.PreviousArtifactLocator;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.aether.artifact.Artifact;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Download the artifacts that contain the resources needed to generate the documentation.
 *
 * <p>
 *      Artifacts are configured using Maven coordinates.
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
@Mojo( name="artifact-download", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class ArtifactDownloadMojo
extends AbstractDownloadMojo {
    /**
     * Coordinates of the artifacts that contain the wsdl files.
     */
    @Parameter( property="artifacts" )
    protected List<String> artifacts;

    @Override
    public void execute()
    throws MojoExecutionException {
        if ( this.artifacts.isEmpty() ) {
            getLog().info( Messages.I18N.getString( Messages.ARTIFACT_REQUIRED_ERROR_KEY ) );
        } else {
            String coordinates = "";
            try {
                final List< Artifact > artifactsDownloaded = this.artifactsDownload( this.artifacts );
                for ( final Artifact artifact : artifactsDownloaded ) {
                    coordinates = artifact.toString();
                    this.unpackR4C( artifact );
                }
                if ( !this.diffSkip ) {
                    final ArtifactRelations relations = downloadAndUnpackPreviousVersionOfArtifacts( artifactsDownloaded );
                    final DiffVersionPersistence persistence = new DiffVersionPersistence( this.diffOnError );
                    persistence.writePreviousVersionPropertiesFile( relations, this.diffPropertiesFile );
                }
                copyAdditionalFiles();
            } catch ( final DownloadException cause ) {
                throw new MojoExecutionException( cause.getMessage(), cause );
            } catch ( final IOException cause ) {
                final String message = Messages.I18N.format( Messages.DEFLATING_ERROR_KEY, coordinates );
                getLog().error( message, cause );
                throw new MojoExecutionException( message );
            }
        }
    }

    /**
     * Downloads the previous version of the artifacts and extract it to a directory ending in {@code .diff},
     * in order to be able to make comparisons between versions.
     *
     * <p>
     *      For now we will compare files with the same name,
     *      unless it ends in {@code }V\d+},
     *      trying to convert the version above when the same wsdl name is not found in the {@code .diff} directory.
     * </p>
     *
     * @param artifacts list of artifacts to download from Maven repositories and unzip them.
     * @return an object that encapsulates the relationships between the downloaded artifact and its previous version,
     * useful for creating the change report.
     * @throws DownloadException if any error is encountered while downloading the artifacts.
     */
    protected ArtifactRelations downloadAndUnpackPreviousVersionOfArtifacts( final List<Artifact> artifacts )
    throws DownloadException {
        final PreviousArtifactLocator locator = createPreviousArtifactLocator();
        final ArtifactRelations relations = new ArtifactRelations();
        for( final Artifact artifact : artifacts ) {
            final List<Artifact> candidates = locator.listOfPossibleArtifacts( artifact );
            getLog().info(
                    Messages.I18N.format(
                        Messages.PREVIOUS_VERSION_CANDIDATES_LIST_KEY,
                        artifact.toString(),
                        candidates.toString()
                    )
            );

            try {
                final Optional< Artifact > candidate = VersionUtil.findHighestArtifactFromVersion( candidates );
                if ( candidate.isPresent() ) {
                    this.unpackR4C( candidate.get(), DIFF_DIRECTORY_SUFFIX );
                    relations.setRelation( artifact, candidate.get() );
                }

            } catch ( final IOException cause ) {
                if ( this.diffOnError == DiffActionOnError.BUILD_BREAK) {
                    final String message = Messages.I18N.format( Messages.PREVIOUS_VERSION_CANDIDATES_MISSING_KEY, artifact );
                    getLog().error( message );
                    throw new DownloadException( message, cause );
                } else {
                    final String message = Messages.I18N.format( Messages.DEFLATING_ERROR_KEY, artifact );
                    getLog().warn( message );
                }
            }
        }
        return relations;
    }

    protected PreviousArtifactLocator createPreviousArtifactLocator() {
        return new DefaultPreviousVersionLocator();
    }
}
