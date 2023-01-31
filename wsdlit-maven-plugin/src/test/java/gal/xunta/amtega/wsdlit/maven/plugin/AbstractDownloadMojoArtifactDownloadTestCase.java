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

import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.AetherManager;
import org.eclipse.aether.artifact.Artifact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractDownloadMojoArtifactDownloadTestCase {
    protected static final File TARGET_DIR = new File( System.getProperty( "user.dir" ), "target/test-classes/" + AbstractDownloadMojoArtifactDownloadTestCase.class.getCanonicalName().replace('.', '/') );
    private static final String ARTIFACT_GROUP_ID = "groupId";
    private static final String ARTIFACT_ARTIFACT_ID = "helloworld";
    private static final String ARTIFACT_VERSION = "1.0.0";
    private static final String ARTIFACT_PACKAGING = "zip";

    private static final String EMPTY_ARTIFACT_ARTIFACT_ID = "helloworld-empty";

    private static final String NOT_FOUND_ARTIFACT_ARTIFACT_ID = "helloworld-notfound";

    private final Artifact artifact = createArtifact( ARTIFACT_ARTIFACT_ID );
    private final Artifact emptyArtifact = createArtifact( EMPTY_ARTIFACT_ARTIFACT_ID );

    private final Artifact notFoundArtifact = createArtifact( NOT_FOUND_ARTIFACT_ARTIFACT_ID );

    private static final DownloadException DOWNLOAD_EXCEPTION = new DownloadException( "Coordenadas non válidas" );


    private final AbstractDownloadMojo mojo = new AbstractDownloadMojo() {
        @Override
        public void execute() {

        }
    };
    private final AbstractDownloadMojo spied = Mockito.spy( mojo );

    private final AetherManager aether = Mockito.mock( AetherManager.class );

    @Before
    public void before()
    throws DownloadException {
        final String c = artifact.toString();
        final String c2 = emptyArtifact.toString();
        final String c3 = notFoundArtifact.toString();
        Mockito
            .when( aether.find( artifact.toString() ) )
            .thenReturn( artifact );
        Mockito
            .when( aether.find( emptyArtifact.toString() ) )
            .thenReturn( emptyArtifact );
        Mockito
            .when( aether.find( notFoundArtifact.toString() ) )
            .thenThrow( DOWNLOAD_EXCEPTION );
        Mockito
            .when( aether.resolveArtifact( artifact ) )
            .thenReturn( artifact );
        Mockito
            .when( aether.resolveArtifact( emptyArtifact ) )
            .thenReturn( emptyArtifact );
        Mockito
            .when( aether.resolveArtifact( notFoundArtifact ) )
            .thenThrow( DOWNLOAD_EXCEPTION );

        Mockito
            .doReturn( aether )
            .when( spied ).createAetherManager();
    }

    @Test
    public void artifactDownloadTest()
    throws DownloadException {
        final Artifact downloaded = spied.artifactDownload( artifact.toString(), aether );

        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, downloaded );
    }

    @Test( expected = DownloadException.class )
    public void artifactDownloadThrowsDownloadExceptionTest()
    throws DownloadException {
        spied.artifactDownload( notFoundArtifact.toString(), aether );
    }

    @Test
    public void artifactsDownloadTest()
    throws DownloadException {
        final List< String > coordinates = Arrays.asList(
            artifact.toString(),
            emptyArtifact.toString()
        );

        final List< Artifact > downloaded = spied.artifactsDownload( coordinates );
        final List< Artifact > expected = Arrays.asList( artifact, emptyArtifact );

        Assert.assertEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, downloaded );
    }

    @Test
    public void artifactsDownloadWhenCoordinatesListIsEmptyTest()
        throws DownloadException {
        final List< String > coordinates = Collections.emptyList();

        final List< Artifact > downloaded = spied.artifactsDownload( coordinates );
        final List< Artifact > expected = Collections.emptyList();

        Assert.assertEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, downloaded );
    }

    private static Artifact createArtifact( final String artifactId ) {
        final Artifact artifact = Mockito.mock( Artifact.class );
        final String artifactFile = artifactId + "-" + ARTIFACT_VERSION + "." + ARTIFACT_PACKAGING;
        Mockito.when( artifact.getGroupId() ).thenReturn( ARTIFACT_GROUP_ID );
        Mockito.when( artifact.getArtifactId() ).thenReturn( artifactId );
        Mockito.when( artifact.getVersion() ).thenReturn( ARTIFACT_VERSION );
        Mockito.when( artifact.getExtension() ).thenReturn( ARTIFACT_PACKAGING );
        Mockito.when( artifact.getFile() ).thenReturn( new File( TARGET_DIR, artifactFile ) );

        final String coordinates = artifact.getGroupId() + ":" + artifact.getArtifactId() + ":" + artifact.getExtension() + ":" + artifact.getVersion();
        Mockito
            .when( artifact.toString() )
            .thenReturn( coordinates );
        return artifact;
    }
}
