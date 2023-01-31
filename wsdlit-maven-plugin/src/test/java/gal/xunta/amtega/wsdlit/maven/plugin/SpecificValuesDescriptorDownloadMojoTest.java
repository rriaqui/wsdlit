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

import java.util.Arrays;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class SpecificValuesDescriptorDownloadMojoTest
extends AbstractParameterSpecificValuesTest {

    private static final String GOAL = "descriptor-download";

    protected DescriptorDownloadMojo mojo;

    @Before
    public void before()
    throws Exception {
        mojo = ( DescriptorDownloadMojo ) this.mojoRule.lookupMojo( GOAL, this.getPomFile( GOAL ) );
    }

    @Test
    public void descriptorsTest() {
        final List<String> expected = Arrays.asList(
            "specific/descriptor/1",
            "specific/descriptor/2"
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, mojo.descriptors );
    }
}
