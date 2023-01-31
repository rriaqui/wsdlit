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
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class GenerateMavenProjectMojoTestCase {
    @Test
    public void copySourceDirectoryTest()
    throws IOException {
        final TestContext testContext  = new TestContext( GenerateMavenProjectMojoTestCase.class, "copySourceDirectory" ).init();
        final GenerateMavenProjectMojo mojo = createMojo( testContext, MojoDefaultValues.TEMPLATES_DIR_SOURCE, testContext.getOutputPath().toFile() );
        final File indexFile = new File( mojo.mavenProjectOutputDirectory, MojoDefaultValues.SOURCE_DIRECTORY_TARGET + "/index.adoc" );
        final String message = String.format( AssertMessages.FILE_EXPECTED, indexFile.getAbsoluteFile() );

        mojo.copySourceDirectory();
        Assert.assertTrue( message, indexFile.isFile() );
    }

    @Test( expected = IOException.class )
    public void copySourceDirectoryThrowsIOExceptionTest()
    throws IOException {
        final TestContext testContext  = new TestContext( GenerateMavenProjectMojoTestCase.class, "copySourceDirectoryThrowsIOException" ).init();
        final GenerateMavenProjectMojo mojo = createMojo( testContext, MojoDefaultValues.TEMPLATES_DIR_SOURCE, testContext.getOutputPath().toFile() );

        mojo.mavenProjectOutputDirectory = testContext.getNonExistingDir().toFile();
        mojo.copySourceDirectory();
    }

    @Test
    public void copyTemplateProjectTest()
    throws IOException {
        final TestContext testContext  = new TestContext( GenerateMavenProjectMojoTestCase.class, "copyTemplateProject" ).init();
        final GenerateMavenProjectMojo mojo = createMojo( testContext, MojoDefaultValues.TEMPLATES_DIR_SOURCE, testContext.getOutputPath().toFile() );
        final File pomFile = new File( mojo.mavenProjectOutputDirectory, MojoDefaultValues.MAVEN_PROJECT_POM_TARGET );
        final String message = String.format( AssertMessages.FILE_EXPECTED, pomFile.getAbsoluteFile() );

        mojo.copyTemplateProject();
        Assert.assertTrue( message, pomFile.isFile() );
    }

    @Test( expected = IOException.class )
    public void copyTemplateProjectWhenTemplateDirDoesNotExistsTest()
    throws IOException {
        final TestContext testContext  = new TestContext( GenerateMavenProjectMojoTestCase.class, "copyTemplateProject" ).init();
        final GenerateMavenProjectMojo mojo = createMojo( testContext, MojoDefaultValues.TEMPLATES_DIR_SOURCE, testContext.getOutputPath().toFile() );

        mojo.templatesDirectory = "/this/templates/directory/does/not/exists";
        mojo.copyTemplateProject();
    }

    @Test( expected = IOException.class )
    public void copyTemplateProjectThrowsIOExceptionWhenUnableToCreateParentDirsTest()
    throws IOException {
        final TestContext testContext  = new TestContext( GenerateMavenProjectMojoTestCase.class, "copyTemplateProject" ).init();
        final GenerateMavenProjectMojo mojo = createMojo( testContext, MojoDefaultValues.TEMPLATES_DIR_SOURCE, testContext.getOutputPath().toFile() );

        mojo.mavenProjectOutputDirectory = testContext.getNonExistingDir().toFile();
        mojo.copyTemplateProject();
    }

    @Test
    public void updatePomContentTest()
    throws IOException {
        final TestContext testContext  = new TestContext( GenerateMavenProjectMojoTestCase.class, "updatePomContent" ).init();
        final GenerateMavenProjectMojo mojo = GenerateMavenProjectMojoReplacePomContentTestCase.createMojo( testContext, MojoDefaultValues.TEMPLATES_DIR_SOURCE, testContext.getOutputPath().toFile() );
        final File pomFile = new File( mojo.mavenProjectOutputDirectory, MojoDefaultValues.MAVEN_PROJECT_POM_TARGET );
        final File expectedPomFile = new File( testContext.getResourcesPath().toFile(), "expected.xml" );

        mojo.copyTemplateProject();
        mojo.updatePom();

        final String value = IOUtils.toString( pomFile.toURI(), StandardCharsets.UTF_8 );
        final String expected = IOUtils.toString( expectedPomFile.toURI(), StandardCharsets.UTF_8 );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    public static GenerateMavenProjectMojo createMojo( final TestContext testContext, final String templatesDirectory, final File mavenProjectOutputDirectory ) {
        final GenerateMavenProjectMojo mojo = new GenerateMavenProjectMojo();
        mojo.templatesDirectory = templatesDirectory;
        mojo.mavenProjectOutputDirectory = mavenProjectOutputDirectory;
        mojo.sourceDirectory = testContext.getSourceDirectory().toFile();
        mojo.sourcesDirectory = testContext.getSourcesDirectory().toFile();
        return mojo;
    }
}
