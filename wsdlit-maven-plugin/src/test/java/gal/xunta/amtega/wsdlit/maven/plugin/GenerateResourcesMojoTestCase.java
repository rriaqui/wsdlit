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
public class GenerateResourcesMojoTestCase {
    @Test
    public void executeTest()
    throws MojoExecutionException, IOException {
        final TestContext testContext = new TestContext( GenerateResourcesMojoTestCase.class, "execute" ).init();
        final GenerateResourcesMojo mojo = createMojo( testContext, false );
        final File wsdlFile = new File( testContext.getSourcesDirectory().toFile(), "helloworld/empty.wsdl" );
        final File adocFile = new File( testContext.getSourcesDirectory().toFile(), "helloworld/additionalDocumentation/empty.adoc" );

        mojo.execute();
        Assert.assertTrue(
            String.format( AssertMessages.TRUE_EXPECTED ),
            wsdlFile.isFile() && adocFile.isFile()
        );
    }

    @Test
    public void executeWhenPatchSkipIsTrueTest()
        throws MojoExecutionException, IOException {
        final TestContext testContext = new TestContext( GenerateResourcesMojoTestCase.class, "executeWhenPatchSkipIsTrue" ).init();
        final GenerateResourcesMojo mojo = createMojo( testContext, true );
        final File wsdlFile = new File( testContext.getSourcesDirectory().toFile(), "helloworld/empty.wsdl" );
        final File adocFile = new File( testContext.getSourcesDirectory().toFile(), "helloworld/additionalDocumentation/empty.adoc" );

        mojo.execute();
        Assert.assertFalse(
            String.format( AssertMessages.FALSE_EXPECTED ),
            ( wsdlFile.isFile() || adocFile.isFile() )
        );
    }

    @Test
    public void patchContractsTest()
    throws MojoExecutionException, IOException {
        final TestContext testContext = new TestContext( GenerateResourcesMojoTestCase.class, "patchContracts" ).init();
        final GenerateResourcesMojo mojo = createMojo( testContext, false );
        final File emptyFile = new File( testContext.getSourcesDirectory().toFile(), "helloworld/empty.wsdl" );

        mojo.patchContracts();
        Assert.assertTrue( String.format( AssertMessages.FILE_EXPECTED, emptyFile ), emptyFile.isFile() );
    }

    @Test( expected = MojoExecutionException.class )
    public void patchContractsThrowsMojoExecutionExceptionTest()
        throws MojoExecutionException, IOException {
        final TestContext testContext = new TestContext( GenerateResourcesMojoTestCase.class, "patchContracts" ).init();
        final GenerateResourcesMojo mojo = createMojo( testContext, false );

        mojo.sourcesDirectory = testContext.getNonExistingDir().toFile();
        mojo.patchContracts();
    }

    @Test
    public void patchContractsWhenPatchSkipIsTrueTest()
    throws MojoExecutionException, IOException {
        final TestContext testContext = new TestContext( GenerateResourcesMojoTestCase.class, "patchContractsWhenPatchSkipIsTrue" ).init();
        final GenerateResourcesMojo mojo = createMojo( testContext, true );
        final File emptyFile = new File( testContext.getSourcesDirectory().toFile(), "helloworld/empty.wsdl" );

        mojo.patchContracts();
        Assert.assertFalse( String.format( AssertMessages.FILE_EXPECTED, emptyFile ), emptyFile.isFile() );
    }

    public static GenerateResourcesMojo createMojo( final TestContext testContext, final boolean patchSkip ) {
        final GenerateResourcesMojo mojo = new GenerateResourcesMojo();
        mojo.patchSkip = patchSkip;
        mojo.wsdlDirectory = testContext.getWsdlDirectory().toFile();
        mojo.sourcesDirectory = testContext.getSourcesDirectory().toFile();
        mojo.sourceDirectory = testContext.getSourceDirectory().toFile();
        return mojo;
    }
}
