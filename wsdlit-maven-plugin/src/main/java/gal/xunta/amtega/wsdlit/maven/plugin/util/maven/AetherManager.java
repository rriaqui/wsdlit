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

import gal.xunta.amtega.wsdlit.maven.plugin.DownloadException;
import gal.xunta.amtega.wsdlit.maven.plugin.Messages;
import org.apache.maven.plugin.logging.Log;
import org.eclipse.aether.RepositoryException;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.*;

import java.util.List;

/**
 * Aether Eclipse Artifact utils.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class AetherManager {
    private final RepositorySystem repositorySystem;

    private final RepositorySystemSession session;

    /**
     * The list of repositories to be used to resolve the artifacts.
     */
    private final List<RemoteRepository> remoteRepositories;

    private final Log log;

    /**
     * Constructor that initializes the manager from specific values of
     * repository system, Maven session and the list of remote repositories.
     *
     * @param repositorySystem the entry point to Aether.
     * @param session the current Maven repository/network configuration.
     * @param remoteRepositories the remote repositories that can be used to resolve the artifact.
     * @param log Maven logger.
     */
    public AetherManager( final RepositorySystem repositorySystem,
                          final RepositorySystemSession session,
                          final List<RemoteRepository> remoteRepositories,
                          final Log log ) {

        this.log = log;
        this.repositorySystem = repositorySystem;
        this.session = session;
        this.remoteRepositories = remoteRepositories;
    }

    /**
     * Downloads the specified artifact from a repository.
     * <p>
     * Relocations are assumed to have been resolved.
     *
     * @param coordinates the coordinates of the specified artifact to be downloaded.
     *
     * @return the {@link Artifact} resolved.
     * @throws DownloadException if any error occurs.
     */
    public Artifact find( final String coordinates )
    throws DownloadException {
        Artifact artifact;
        Artifact resolvedArtifact = null;

        try {
            artifact = new DefaultArtifact( coordinates );
            resolvedArtifact = resolveArtifact( artifact );

            final ArtifactRequest artifactRequest = new ArtifactRequest();
            artifactRequest.setArtifact( resolvedArtifact );
            artifactRequest.setRepositories( remoteRepositories );

            final ArtifactResult artifactResult = repositorySystem.resolveArtifact( session, artifactRequest );
            return artifactResult.getArtifact();

        } catch ( final IllegalArgumentException cause ) {
            throw new DownloadException(
                    Messages.I18N.format( Messages.INVALID_ARTIFACT_COORDINATES_ERROR_KEY, coordinates ),
                    cause
            );
        } catch ( final ArtifactResolutionException cause ) {
            throw new DownloadException( resolvedArtifact, cause );
        }
    }

    /**
     * Resolve a Maven artifact version of the specified artifact based on its version type.
     * <p>
     *      Maven supports ranges in the version of an artifact,
     *      so resolving an artifact means figuring out which version
     *      of those existing in the repository, meets our requirements.
     * </p>
     *
     * @param artifact the specified artifact.
     * @return a {@code Artifact} with the resolved version.
     * @throws DownloadException if any error occurs, such as not finding the artifact in the repository.
     */
    public Artifact resolveArtifact( final Artifact artifact )
    throws DownloadException {
        final String versionNormalized = VersionUtil.normalizeVersion( artifact.getVersion() );
        final boolean range = versionNormalized.startsWith( "[" ) || versionNormalized.startsWith( "(" );
        final Artifact resolveArtifact = artifact.setVersion( versionNormalized );
        final Artifact resolvedArtifact;

        try {
            if ( range ) {
                final VersionRangeRequest versionRangeRequest = new VersionRangeRequest( resolveArtifact, remoteRepositories, "");
                final VersionRangeResult versionRangeResult = repositorySystem.resolveVersionRange( session, versionRangeRequest );
                resolvedArtifact = resolveArtifact.setVersion( versionRangeResult.getHighestVersion().toString() );

            } else {
                final VersionRequest versionRequest = new VersionRequest( resolveArtifact, remoteRepositories, "" );
                final VersionResult versionResult = repositorySystem.resolveVersion( session, versionRequest );
                resolvedArtifact = resolveArtifact.setVersion( versionResult.getVersion() );
            }
        } catch ( final RepositoryException cause ) {
            throw new DownloadException( Messages.I18N.format( Messages.ARTIFACT_VERSION_ERROR_KEY, artifact ), cause );
        }
        log.info( Messages.I18N.format( Messages.ARTIFACT_RESOLVED_KEY, resolvedArtifact ) );
        return resolvedArtifact;
    }
}
