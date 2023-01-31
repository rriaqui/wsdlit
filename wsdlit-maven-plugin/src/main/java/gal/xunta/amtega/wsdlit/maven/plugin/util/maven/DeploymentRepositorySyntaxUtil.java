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
import org.apache.maven.artifact.ArtifactUtils;
import org.eclipse.aether.repository.RemoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that allows you to easily obtain the remote repository based on the version of a project,
 * and to the SNAPSHOT, FINAL, and common deployment repositories.
 *
 * @author rriaqui
 * @since 2.0
 */
public final class DeploymentRepositorySyntaxUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger( DeploymentRepositorySyntaxUtil.class );
    private static final Pattern ALT_REPO_SYNTAX_PATTERN = Pattern.compile( "^(.+?)::(.+)$" );

    private DeploymentRepositorySyntaxUtil() {}

    /**
     * Returns the remote deployment repository based on the specified version,
     * the alternate development repository,
     * the alternate release repository,
     * and or a common repository for development and release,
     * following the following algorithm:
     *
     * <ol>
     *      <li>
     *          <b>If version is {@code SNAPSHOT}</b> and {@code altSnapshotDeploymentRepository} has been defined,
     *          the remote repository defined by this property is returned.
     *      </li>
     *      <li>
     *          <b>If the version is {@code final}</b> and {@code altReleaseDeploymentRepository} has been defined,
     *          the remote repository indicated by this property is returned.
     *      </li>
     *      <li>If it could not be established according to the above criteria,
     *          the remote repository pointed to by {@code deploymentRepository} is returned.
     *      </li>
     * </ol>
     *
     * @param version The specified version.
     * @param altSnapshotDeploymentRepository the specified alternate development repository, in {@code id::url} format.
     * @param altReleaseDeploymentRepository the specified alternate release repository, in {@code id::url} format.
     * @param deploymentRepository the specified common repository, in {@code id::url} format.
     * @return a {@code RemoteRepository} initialized according to the target repository selected by the algorithm.
     */
    public static RemoteRepository getDeploymentRepository(
        final String version,
        final String altSnapshotDeploymentRepository,
        final String altReleaseDeploymentRepository,
        final String deploymentRepository
    ) {
        String repository;
        if ( ArtifactUtils.isSnapshot( version ) && altSnapshotDeploymentRepository != null ) {
            repository = altSnapshotDeploymentRepository;
        } else if ( ! ArtifactUtils.isSnapshot( version ) && altReleaseDeploymentRepository != null ) {
            repository = altReleaseDeploymentRepository;
        } else {
            repository = deploymentRepository;
        }

        if ( repository != null ) {
            LOGGER.info( Messages.I18N.format( Messages.DEPLOYMENT_REPOSITORY_KEY, repository ) );
            final Matcher matcher = ALT_REPO_SYNTAX_PATTERN.matcher( repository );
            if ( matcher.matches() ) {
                final String id = matcher.group( 1 ).trim();
                final String url = matcher.group( 2 ).trim();

                return new RemoteRepository.Builder( id, "default", url ).build();
            }
        }
        return new RemoteRepository.Builder( "", "default", "" ).build();
    }
}
