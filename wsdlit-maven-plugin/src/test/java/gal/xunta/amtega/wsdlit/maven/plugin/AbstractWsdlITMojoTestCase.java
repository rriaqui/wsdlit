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

import gal.xunta.amtega.wsdlit.maven.plugin.test.TestContext;
import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractWsdlITMojoTestCase {
    private final AbstractWsdlITMojo mojo = new AbstractWsdlITMojo() {
        @Override
        public void execute() {

        }
    };

    @Test( expected = MojoExecutionException.class )
    public void copyAdditionalFilesThrowsIOExceptionWhenSourceDirectoryCantBeCreatedTest()
    throws IOException, MojoExecutionException {
        final TestContext context = new TestContext( this.getClass(), "copyAdditionalFilesWhenSourcesDirectoryContainsAService" ).init();
        this.mojo.sourcesDirectory = context.getSourcesDirectory().toFile();
        this.mojo.sourceDirectory = context.getNonExistingDir().toFile();
        mojo.copyAdditionalFiles();
    }

    @Test
    public void copyAdditionalFilesWhenSourcesDirectoryContainsAServiceTest()
        throws IOException, MojoExecutionException {
        final TestContext context = new TestContext( this.getClass(), "copyAdditionalFilesWhenSourcesDirectoryContainsAService" ).init();
        final File examples = new File( context.getAdditionalFiles().toFile(), "examples.adoc" );
        this.mojo.sourcesDirectory = context.getSourcesDirectory().toFile();
        this.mojo.sourceDirectory = context.getOutputPath().toFile();
        mojo.copyAdditionalFiles();

        Assert.assertFalse( "Se esperaba que o arquivo 'index' existise", examples.exists() );
    }

    @Test
    public void copyAdditionalFilesWhenSourcesDirectoryIsEmptyTest()
        throws IOException, MojoExecutionException {
        final TestContext context = new TestContext( this.getClass(), "copyAdditionalFilesWhenSourcesDirectoryIsNotADirectory" ).init();
        final File expected = context.getApiVersionPath().toFile();
        this.mojo.sourcesDirectory = context.getSourcesDirectory().toFile();
        this.mojo.sourceDirectory = context.getOutputPath().toFile();
        mojo.copyAdditionalFiles();

        Assert.assertFalse( "Non se esperaba que o directorio 'api' existise", expected.isDirectory() );
    }

    @Test
    public void copyAdditionalFilesWhenSourcesDirectoryIsNotADirectoryTest()
    throws IOException, MojoExecutionException {
        final TestContext context = new TestContext( this.getClass(), "copyAdditionalFilesWhenSourcesDirectoryIsNotADirectory" ).init();
        final File expected = context.getApiVersionPath().toFile();
        this.mojo.sourcesDirectory = context.getSourcesDirectory().toFile();
        this.mojo.sourceDirectory = context.getOutputPath().toFile();
        mojo.copyAdditionalFiles();

        Assert.assertFalse( "Non se esperaba que o directorio 'api' existise", expected.isDirectory() );
    }

    @Test
    public void copyDirToWhenSourceDirectoryDoesntExistsTest()
    throws IOException {
        final TestContext context = new TestContext( this.getClass(), "copyDirToWhenSourceDirectoryDoesntExists" ).init();
        this.mojo.sourceDirectory =  context.getOutputPath().toFile();
        mojo.copyDirToApi( context.getNonExistingDir() );

        Assert.assertFalse( "O directorio non debería existir", context.getApiPath().toFile().exists() );
    }


    @Test
    public void copyDirToTest()
    throws IOException {
        final TestContext context = new TestContext( this.getClass(), "copyDirTo" ).init();
        final File index = new File( context.getApiVersionPath().toFile(), "index.adoc" );
        final File subIndex = new File( context.getApiVersionPath().toFile(), "subfolder/subIndex.adoc" );
        this.mojo.sourceDirectory = context.getOutputPath().toFile();
        mojo.copyDirToApi( context.getResourcesPath() );

        Assert.assertTrue(
            "Esperábase a existencia de '1.0.0/index.adoc' e '1.0.0/subfolder/subIndex.adoc'",
            index.isFile() && subIndex.isFile()
        );
    }
}
