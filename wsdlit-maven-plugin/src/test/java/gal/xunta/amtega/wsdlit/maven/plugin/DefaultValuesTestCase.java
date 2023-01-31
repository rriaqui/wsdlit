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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class DefaultValuesTestCase
extends AbstractParameterDefaultValuesTest {
    private static final String GOAL = "artifact-download";

    protected AbstractWsdlITMojo mojo;

    @Before
    public void before()
    throws Exception {
        mojo = ( AbstractWsdlITMojo )  this.mojoRule.lookupConfiguredMojo( this.getBaseDir(), GOAL );
    }

    @Test
    public void wsdlDirectoryTest() {
        final File expected = new File( this.getBaseDir(), "src/main/wsdlit" ).getAbsoluteFile();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.wsdlDirectory );
    }

    @Test
    public void templatesDirectoryTest() {
        final String expected = MojoDefaultValues.TEMPLATES_DIR_SOURCE;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.templatesDirectory );
    }

    @Test
    public void sourcesDirectoryTest() {
        final File expected = new File( this.projectBuildDirectory, "wsdlit" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.sourcesDirectory );
    }

    @Test
    public void diffSkipTest() {
        final boolean expected = true;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.diffSkip );
    }

    @Test
    public void diffOnErrorTest() {
        final DiffActionOnError expected = DiffActionOnError.BUILD_BREAK;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.diffOnError );
    }

    @Test
    public void diffPropertiesTest() {
        final File expected = new File( this.projectBuildDirectory, "wsdlit.diff.properties" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.diffPropertiesFile );
    }
}
