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

import org.eclipse.aether.artifact.Artifact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractDownloadMojoUnpackR4CTestCase {
    protected static final File TARGET_DIR = new File( System.getProperty( "user.dir" ), "target/test-classes/" + AbstractDownloadMojoUnpackR4CTestCase.class.getCanonicalName().replace('.', '/') );
    private static final String ARTIFACT_GROUP_ID = "groupId";
    private static final String ARTIFACT_ARTIFACT_ID = "helloworld";
    private static final String ARTIFACT_VERSION = "1.0.0";
    private static final String ARTIFACT_PACKAGING = "zip";

    private static final String EMPTY_ARTIFACT_ARTIFACT_ID = "helloworld-empty";

    private final Artifact artifact = createArtifact( ARTIFACT_ARTIFACT_ID );
    private final Artifact emptyArtifact = createArtifact( EMPTY_ARTIFACT_ARTIFACT_ID );

    private final AbstractDownloadMojo mojo = new AbstractDownloadMojo() {
        @Override
        public void execute() {

        }
    };
    private final AbstractDownloadMojo spied = Mockito.spy( mojo );

    @Before
    public void before()
    throws IOException {
        this.spied.sourcesDirectory = Files
            .createTempDirectory( TARGET_DIR.toPath(), AbstractDownloadMojoUnpackR4CTestCase.class.getSimpleName() )
            .toFile();
    }

    @Test
    public void unpackTest() throws IOException {
        final File expected = new File( spied.sourcesDirectory, "helloworld/m/hello.txt" );

        spied.unpackR4C( artifact );

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, expected.exists() && expected.isFile() );
    }

    @Test( expected = IOException.class )
    public void unpackThrowsIOExceptionWhenZipIsEmptyTest()
    throws IOException {
        spied.unpackR4C( emptyArtifact );
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
