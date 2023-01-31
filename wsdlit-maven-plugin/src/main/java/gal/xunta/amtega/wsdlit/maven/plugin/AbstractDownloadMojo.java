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

import gal.xunta.amtega.wsdlit.maven.plugin.util.UnzipUtil;
import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.AetherManager;
import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.ArtifactUtil;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.repository.RemoteRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *Abstract Mojo for downloading artifacts.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractDownloadMojo
extends AbstractWsdlITMojo {
    private static final String UNPACKED_DIRECTORY_DOES_NOT_EXISTS = "O directorio de desempaquetado do artefacto non existe.";

    /**
     * Maven's current repository/network configuration.
     *
     * @since 1.0.0
     */
    @Parameter(readonly = true, defaultValue="${repositorySystemSession}")
    protected RepositorySystemSession repoSession;

    /**
     * The entry point to Aether, the component that does the hard work.
     *
     * @since 1.0.0
     */
    @Component
    protected RepositorySystem repositorySystem;

    /**
     * The remote repositories used to download the themes and artifacts.
     *
     * @since 1.0.0
     */
    @Parameter(readonly=true, defaultValue="${project.remoteProjectRepositories}")
    protected List<RemoteRepository> remoteRepositories;

    /**
     * Downloads the artifacts specified by Maven coordinates, returning the list of downloaded artifacts.
     *
     * <p>
     *      The coordinates can specify the version as a range or as a metaversion (RELEASE - SNAPSHOT),
     *      and the list of artifacts returned will contain the resolved version of each one.
     * </p>
     *
     * @param coordinatesList list of artifact coordinates to download.
     * @return the list of downloaded artifacts.
     * @throws DownloadException if any error occurs.
     *
     * @since 1.0.0
     */
    protected List<Artifact> artifactsDownload( final List<String> coordinatesList )
    throws DownloadException {
        getLog().info( Messages.I18N.getString( Messages.ARTIFACT_DOWNLOAD_KEY) );
        final long startedAt = System.currentTimeMillis();
        if ( coordinatesList.isEmpty() ) {
            getLog().warn( Messages.I18N.getString( Messages.ARTIFACT_DOWNLOAD_REQUIRED_ERROR_KEY) );
            return Collections.emptyList();
        }
        final List<Artifact> artifacts = new ArrayList<>();
        final AetherManager aetherManager = createAetherManager();

        for( final String coordinates : coordinatesList ) {
            final Artifact artifact = artifactDownload( coordinates, aetherManager );
            artifacts.add( artifact );
        }
        final long duration = System.currentTimeMillis() - startedAt;
        getLog().info( Messages.I18N.format( Messages.ARTIFACT_DOWNLOAD_END_KEY, duration ) );
        return Collections.unmodifiableList( artifacts );
    }

    /**
     * Downloads an artifact by its coordinates.
     *
     * @param coordinates the coordinates of the artifact to download.
     * @param aether the Aether manager used for downloading artifacts.
     * @return the downloaded artifact.
     * @throws DownloadException if any error occurs.
     *
     * @since 1.0.0
     */
    protected Artifact artifactDownload( final String coordinates, final AetherManager aether )
    throws DownloadException {
        return aether.find( coordinates );
    }

    /**
     * Unpacks the wsdl directory of a Resource for Consumers artifact.
     *
     * <p>
     *     <b>r4c</b> is the acronym for <i>Resources For Consumers</i>.
     * </p>
     *
     * @param artifact the artifact to unzip.
     * @return the directory in which the artifact will be unzipped.
     * @throws IOException if any error occurs.
     *
     * @since 1.0.0
     */
    public File unpackR4C( final Artifact artifact )
    throws IOException {
        return unpackR4C( artifact, null );
    }

    /**
     * Unpacks the wsdl directory of a Resource for Consumers artifact,
     * specifying a suffix for the directory to be unpacked into.
     *
     * @param artifact the artifact to unpack.
     * @param suffix the specified suffix to add to the directory to be unpacked into (if specified).
     * @return the directory the artifact was unpacked into.
     * @throws IOException if any error occurs.
     *
     * @since 1.0.0
     */
    public File unpackR4C( final Artifact artifact, final String suffix )
    throws IOException {
        final File outputDirectory = ArtifactUtil.getOutputDirectory( this.sourcesDirectory, artifact, suffix );

        getLog().info( Messages.I18N.format( Messages.DEFLATING_FILE, artifact.toString(), outputDirectory.getPath() ) );

        UnzipUtil.unzip( artifact.getFile(), outputDirectory);
        if ( ! outputDirectory.exists() || !outputDirectory.isDirectory() ) {
            throw new IOException( UNPACKED_DIRECTORY_DOES_NOT_EXISTS );
        }
        getLog().info( Messages.I18N.format( Messages.DEFLATING_FILE_END, artifact.toString() ) );
        return outputDirectory;
    }

    /**
     * Creates the instance of {@code AetherManager} to be used to download artifacts.
     * <p>
     *     This method simplifies tests writted for this class.
     * </p>
     *
     * @return the instance of {@code AetherManager} to be used to download artifacts.
     */
    protected AetherManager createAetherManager() {
        return new AetherManager( this.repositorySystem, this.repoSession, this.remoteRepositories, getLog() );
    }
}
