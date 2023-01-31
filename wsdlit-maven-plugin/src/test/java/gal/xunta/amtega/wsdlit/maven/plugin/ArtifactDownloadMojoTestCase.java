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
import gal.xunta.amtega.wsdlit.maven.plugin.test.EnhancedMavenLog;
import gal.xunta.amtega.wsdlit.maven.plugin.test.TestContext;
import gal.xunta.amtega.wsdlit.maven.plugin.version.ArtifactRelations;
import gal.xunta.amtega.wsdlit.maven.plugin.version.PreviousArtifactLocator;
import org.apache.maven.plugin.MojoExecutionException;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ArtifactDownloadMojoTestCase {
    @Test
    public void executeWhenArtifactsDownloadReturnsEmptyListAndDiffSkipIsFalseTest()
    throws MojoExecutionException, DownloadException, IOException {
        final EnhancedMavenLog enhancedLogger = new EnhancedMavenLog( null );
        final TestContext testContext = new TestContext( ArtifactDownloadMojoTestCase.class, "executeWhenArtifactsDownloadReturnsEmptyList" ).init();
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final ArtifactDownloadMojo spied;
        mojo.artifacts = Collections.singletonList( "groupId:artifactId:1.0.0" );
        mojo.diffSkip = false;
        mojo.sourcesDirectory = testContext.getSourcesDirectory().toFile();
        mojo.diffPropertiesFile = new File( testContext.getOutputPath().toFile(), "diffProperties.properties" );
        spied = Mockito.spy( mojo );

        Mockito
            .doReturn( enhancedLogger )
            .when( spied ).getLog();

        Mockito
            .doReturn( Collections.emptyList() )
            .when( spied ).artifactsDownload( mojo.artifacts );

        Mockito
            .doReturn( new ArtifactRelations() )
            .when( spied ).downloadAndUnpackPreviousVersionOfArtifacts( Mockito.anyList() );

        spied.execute();
        Assert.assertTrue( String.format( AssertMessages.FILE_EXPECTED, mojo.diffPropertiesFile ), mojo.diffPropertiesFile.isFile() );
    }

    @Test
    public void executeWhenArtifactsIsEmptyAndDiffSkipIsTrueTest()
    throws MojoExecutionException, DownloadException, IOException {
        final EnhancedMavenLog enhancedLogger = new EnhancedMavenLog( null );
        final TestContext testContext = new TestContext( ArtifactDownloadMojoTestCase.class, "executeWhenArtifactsDownloadReturnsEmptyList" ).init();
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final ArtifactDownloadMojo spied;
        mojo.artifacts = Collections.singletonList( "groupId:artifactId:1.0.0" );
        mojo.diffSkip = true;
        mojo.sourcesDirectory = testContext.getSourcesDirectory().toFile();

        spied = Mockito.spy( mojo );

        Mockito
            .doReturn( enhancedLogger )
            .when( spied ).getLog();

        Mockito
            .doReturn( Collections.emptyList() )
            .when( spied ).artifactsDownload( mojo.artifacts );

        spied.execute();

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED,
            enhancedLogger.getWarningMessage().isEmpty()
        );
    }

    @Test
    public void executeWhenArtifactsIsEmptyTest()
    throws MojoExecutionException {
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final EnhancedMavenLog enhancedLogger = new EnhancedMavenLog( null );
        final ArtifactDownloadMojo spied;
        mojo.artifacts = Collections.emptyList();
        spied = Mockito.spy( mojo );

        Mockito
            .doReturn( enhancedLogger )
            .when( spied ).getLog();
        spied.execute();

        final String expected = Messages.I18N.getString( Messages.ARTIFACT_REQUIRED_ERROR_KEY );

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, enhancedLogger.infoMessageContains( expected ) );
    }

    @Test( expected = MojoExecutionException.class )
    public void executeWhenArtifactsDownloadThrowsDownloadExceptionTest()
    throws MojoExecutionException, DownloadException {
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final ArtifactDownloadMojo spied;
        mojo.artifacts = Collections.singletonList( "groupId:artifactId:1.0.0" );
        spied = Mockito.spy( mojo );

        Mockito
            .doThrow( new DownloadException( "Artefactos non atopados" ) )
            .when( spied ).artifactsDownload( mojo.artifacts );
        spied.execute();
    }

    @Test( expected = MojoExecutionException.class )
    public void executeWhenUnpackR4CThrowsIOExceptionTest()
    throws MojoExecutionException, DownloadException, IOException {
        final List< Artifact > artifacts = Collections.singletonList( new DefaultArtifact( "groupId:artifactId:zip:1.0.0" ) );
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final ArtifactDownloadMojo spied;
        mojo.artifacts = Collections.singletonList( "groupId:artifactId:1.0.0" );
        mojo.diffSkip = true;
        spied = Mockito.spy( mojo );

        Mockito
            .doReturn( artifacts )
            .when( spied ).artifactsDownload( mojo.artifacts );

        Mockito
            .doThrow( new IOException( "" ) )
            .when( spied ).unpackR4C( artifacts.get( 0 ) );
        spied.execute();
    }

    @Test
    public void executeWhenUnpackR4CTest()
    throws MojoExecutionException, DownloadException, IOException {
        final TestContext testContext = new TestContext( ArtifactDownloadMojoTestCase.class, "executeWhenUnpackR4C" ).init();
        final File artifactFile = new File( testContext.getResourcesPath().toFile(), "repository/groupId/artifactId/1.0.0/artifactId-1.0.0.zip" );
        final Artifact artifact = new DefaultArtifact( "groupId:artifactId:zip:1.0.0" );
        final List< Artifact > artifacts = Collections.singletonList( artifact.setFile( artifactFile ) );
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final ArtifactDownloadMojo spied;

        mojo.artifacts = Collections.singletonList( "groupId:artifactId:zip:1.0.0" );
        mojo.diffSkip = true;
        mojo.sourcesDirectory = testContext.getSourcesDirectory().toFile();
        spied = Mockito.spy( mojo );

        Mockito
            .doReturn( artifacts )
            .when( spied ).artifactsDownload( mojo.artifacts );

        spied.execute();

        final File expected = new File(  testContext.getSourcesDirectory().toFile(), "artifactId/m/hello.txt" );

        Assert.assertTrue( String.format( AssertMessages.FILE_EXPECTED, expected ), expected.isFile() );
    }

    @Test
    public void downloadAndUnpackPreviousVersionOfArtifactsWhenListIsEmptyTest()
    throws DownloadException {
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final List< Artifact > artifacts = Collections.emptyList();
        final ArtifactRelations relations = mojo.downloadAndUnpackPreviousVersionOfArtifacts( artifacts );

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, relations.isEmpty() );
    }

    @Test
    public void downloadAndUnpackPreviousVersionOfArtifactsWhenListIsNotEmptyTest()
    throws DownloadException, IOException {
        final TestContext testContext = new TestContext( ArtifactDownloadMojoTestCase.class, "downloadAndUnpackPreviousVersionOfArtifactsWhenListIsNotEmpty" ).init();

        final PreviousArtifactLocator locator = new PreviousArtifactLocator() {
            @Override
            public List< Artifact > listOfPossibleArtifacts( final Artifact artifact ) {
                final File file = new File( testContext.getResourcesPath().toFile(), "repository/groupId/artifactId/1.0.0/artifactId-1.0.0.zip" );
                return Collections.singletonList( artifact.setVersion( "1.0.0" ).setFile( file ) );
            }

            @Override
            public boolean appliesTo( Artifact artifact ) {
                return true;
            }
        };
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final ArtifactDownloadMojo spied;
        final List< Artifact > artifacts = Collections.singletonList( new DefaultArtifact( "groupId:artifactId:2.0.0" ) );
        final ArtifactRelations relations;

        mojo.sourcesDirectory = testContext.getSourcesDirectory().toFile();
        spied = Mockito.spy( mojo );
        Mockito
            .doReturn( locator )
            .when( spied ).createPreviousArtifactLocator();
        relations = spied.downloadAndUnpackPreviousVersionOfArtifacts( artifacts );

        final String relationAsString = relations.getRelation( "groupId:artifactId:jar:2.0.0" );
        final String expected = "groupId:artifactId:jar:1.0.0";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, relationAsString );
    }

    @Test( expected = DownloadException.class )
    public void downloadAndUnpackPreviousVersionOfArtifactsWhenListIsNotEmptyThenThrowsDownloadExceptionTest()
    throws DownloadException, IOException {
        final TestContext testContext = new TestContext( ArtifactDownloadMojoTestCase.class, "downloadAndUnpackPreviousVersionOfArtifactsWhenListIsNotEmptyThenThrowsDownloadException" ).init();

        final PreviousArtifactLocator locator = new PreviousArtifactLocator() {
            @Override
            public List< Artifact > listOfPossibleArtifacts( final Artifact artifact ) {
                final File file = new File( testContext.getResourcesPath().toFile(), "repository/groupId/artifactId/1.0.0/artifactId-1.0.0.zip" );
                return Collections.singletonList( artifact.setVersion( "1.0.0" ).setFile( file ) );
            }

            @Override
            public boolean appliesTo( Artifact artifact ) {
                return true;
            }
        };
        final ArtifactDownloadMojo mojo = new ArtifactDownloadMojo();
        final ArtifactDownloadMojo spied;
        final List< Artifact > artifacts = Collections.singletonList( new DefaultArtifact( "groupId:artifactId:2.0.0" ) );

        mojo.sourcesDirectory = testContext.getNonExistingDir().toFile();
        mojo.diffOnError = DiffActionOnError.BUILD_BREAK;
        spied = Mockito.spy( mojo );
        Mockito
            .doReturn( locator )
            .when( spied ).createPreviousArtifactLocator();
        spied.downloadAndUnpackPreviousVersionOfArtifacts( artifacts );
    }
}
