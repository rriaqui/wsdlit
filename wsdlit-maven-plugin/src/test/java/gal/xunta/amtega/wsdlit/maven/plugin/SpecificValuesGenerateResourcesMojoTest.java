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

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class SpecificValuesGenerateResourcesMojoTest
extends AbstractParameterSpecificValuesTest {
    private static final String GOAL = "generate-resources";

    @Rule
    public final MojoRule mojoRule = new MojoRule();

    protected GenerateResourcesMojo mojo;

    @Before
    public void before()
    throws Exception {
        mojo = ( GenerateResourcesMojo )  this.mojoRule.lookupMojo( GOAL, this.getPomFile( GOAL ) );
    }

    @Test
    public void patchSkipTest() {
        final boolean expected = true;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.patchSkip );
    }
}
