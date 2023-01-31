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

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class SpecificValuesGenerateSourcesMojoTest
extends AbstractParameterSpecificValuesTest {
    private static final String GOAL = "generate-sources";

    @Rule
    public final MojoRule mojoRule = new MojoRule();

    protected GenerateSourcesMojo mojo;

    @Before
    public void before()
    throws Exception {
        mojo = ( GenerateSourcesMojo )  this.mojoRule.lookupMojo( GOAL, this.getPomFile( GOAL ) );
    }

    @Test
    public void sourcesTest() {
        final List<String> expected = Arrays.asList(
            "specific/source/1.wsdl",
            "specific/source/2.wsdl"
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.sources );
    }

    @Test
    public void titleTest() {
        final String expected = "Specific title";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.title );
    }

    @Test
    public void versionTest() {
        final String expected = "0.0.1";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.version );
    }
}
