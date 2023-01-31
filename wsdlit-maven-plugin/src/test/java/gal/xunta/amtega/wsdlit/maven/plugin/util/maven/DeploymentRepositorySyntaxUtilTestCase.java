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

import gal.xunta.amtega.wsdlit.maven.plugin.AssertMessages;
import org.eclipse.aether.repository.RemoteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class DeploymentRepositorySyntaxUtilTestCase {
    private final String version;
    private final MavenTestRepository altSnapshotDeploymentRepository;
    private final MavenTestRepository altReleaseDeploymentRepository;
    private final MavenTestRepository deploymentRepository;
    private final MavenTestRepository repositoryExpected;

    public DeploymentRepositorySyntaxUtilTestCase(
        final String version,
        final MavenTestRepository altSnapshotRepository,
        final MavenTestRepository altReleasesRepository,
        final MavenTestRepository repository,
        final MavenTestRepository repositoryExpected
    ) {
        this.version = version;
        this.altSnapshotDeploymentRepository = altSnapshotRepository;
        this.altReleaseDeploymentRepository = altReleasesRepository;
        this.deploymentRepository = repository;
        this.repositoryExpected = repositoryExpected;
    }

    @Test
    public void expectedIdTest() {
        final RemoteRepository repository = getRemoteRepository();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.repositoryExpected.id, repository.getId() );
    }

    @Test
    public void expectedUrlTest() {
        final RemoteRepository repository = getRemoteRepository();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.repositoryExpected.url, repository.getUrl() );
    }

    public RemoteRepository getRemoteRepository() {
        return DeploymentRepositorySyntaxUtil.getDeploymentRepository(
            this.version,
            ( this.altSnapshotDeploymentRepository != null ) ? this.altSnapshotDeploymentRepository.deploymentRepository : null,
            ( this.altReleaseDeploymentRepository != null ) ? this.altReleaseDeploymentRepository.deploymentRepository : null,
            ( this.deploymentRepository != null ) ? this.deploymentRepository.deploymentRepository : null
        );

    }

    @Parameterized.Parameters
    public static Collection< Object[] > data() {
        return
            Arrays.asList(
                new Object[][] {
                    // SNAPSHOT DEPLOYMENT REPOSITORY
                    {
                        SNAPSHOT_VERSION,
                        ALT_SNAPSHOT_DEPLOYMENT_REPOSITORY,
                        null,
                        null,
                        ALT_SNAPSHOT_DEPLOYMENT_REPOSITORY
                    },
                    {
                        SNAPSHOT_VERSION,
                        null,
                        null,
                        DEPLOYMENT_REPOSITORY,
                        DEPLOYMENT_REPOSITORY
                    },
                    {
                        SNAPSHOT_VERSION,
                        null,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY,
                        DEPLOYMENT_REPOSITORY,
                        DEPLOYMENT_REPOSITORY
                    },
                    {
                        SNAPSHOT_VERSION,
                        ALT_SNAPSHOT_DEPLOYMENT_REPOSITORY,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY,
                        DEPLOYMENT_REPOSITORY,
                        ALT_SNAPSHOT_DEPLOYMENT_REPOSITORY
                    },
                    // RELEASE DEPLOYMENT REPOSITORY
                    {
                        RELEASE_VERSION,
                        null,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY,
                        null,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY
                    },
                    {
                        RELEASE_VERSION,
                        null,
                        null,
                        DEPLOYMENT_REPOSITORY,
                        DEPLOYMENT_REPOSITORY
                    },
                    {
                        RELEASE_VERSION,
                        null,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY,
                        DEPLOYMENT_REPOSITORY,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY
                    },
                    {
                        RELEASE_VERSION,
                        ALT_SNAPSHOT_DEPLOYMENT_REPOSITORY,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY,
                        DEPLOYMENT_REPOSITORY,
                        ALT_RELEASE_DEPLOYMENT_REPOSITORY
                    },
                    {
                        RELEASE_VERSION,
                        null,
                        null,
                        null,
                        EMPTY_DEPLOYMENT_REPOSITORY
                    }
                }
            );
    }

    private static final MavenTestRepository ALT_SNAPSHOT_DEPLOYMENT_REPOSITORY = new MavenTestRepository( "altSnapshot" );
    private static final MavenTestRepository ALT_RELEASE_DEPLOYMENT_REPOSITORY = new MavenTestRepository( "altRelease" );
    private static final MavenTestRepository DEPLOYMENT_REPOSITORY = new MavenTestRepository( "common" );

    private static final MavenTestRepository EMPTY_DEPLOYMENT_REPOSITORY = new MavenTestRepository( null );

    private static final String SNAPSHOT_VERSION = "1.0.0-SNAPSHOT";
    private static final String RELEASE_VERSION = "1.0.0";

    static class MavenTestRepository {
        protected final String id;
        protected final String url;
        protected final String deploymentRepository;

        public MavenTestRepository( final String id ) {
            if ( id == null ) {
                this.id = "";
                this.url = "";
                this.deploymentRepository = "";
            } else {
                this.id = id + "Id";
                this.url = "https://localhost.localdomain/repository/" + id;
                this.deploymentRepository = this.id + "::" + this.url;
            }
        }
    }
}
