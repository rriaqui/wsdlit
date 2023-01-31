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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ParameterDefaultValuesGenerateMavenProjectMojoTest
extends AbstractParameterDefaultValuesTest {
    private static final String GOAL = "generate-maven-project";

    protected GenerateMavenProjectMojo mojo;

    @Before
    public void before()
    throws Exception {
        mojo = ( GenerateMavenProjectMojo ) this.mojoRule.lookupConfiguredMojo( this.getBaseDir(), GOAL );
    }

    @Test
    public void mavenProjectOutputDirectoryTest() {
        final File expected = new File( this.projectBuildDirectory, "generated-sources/maven" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.mavenProjectOutputDirectory );
    }

    @Test
    public void groupIdTest() {
        final String expected = "gal.xunta.amtega.wsdlit.maven.plugin.test";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.groupId );
    }

    @Test
    public void artifactIdTest() {
        final String expected = "defaultparameters-test-it";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.artifactId );
    }

    @Test
    public void versionTest() {
        final String expected = "1.0-SNAPSHOT";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.version );
    }

    @Test
    public void themeTest() {
        final String expected = "gal.xunta.amtega.wsdlit:wsdlit-default-theme:zip:" + mojo.plugin.getVersion();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.theme );
    }

    @Test
    public void copyrightTest() {
        Assert.assertNull( AssertMessages.EQUALS_EXPECTED, mojo.copyright );
    }
}
