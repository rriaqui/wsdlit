package gal.xunta.amtega.wsdlit.core.engine.api;

/*-
 * #%L
 * wsdlit-core
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

import gal.xunta.amtega.wsdlit.core.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ContextDefaultTestCase {
    @Test
    public void getOutputCharsetTest() {
        final Context context = new Context();
        final Charset expected = StandardCharsets.UTF_8;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getOutputCharset() );
    }

    @Test
    public void getOutputFileTest() {
        final Context context = new Context();
        final File expected = new File( System.getProperty( "java.io.tmpdir" ), "output/output.html" );
        Assert.assertEquals( AssertMessages.FILE_NOT_EXPECTED, expected, context.getOutputFile(  "output.html" ) );
    }

    @Test
    public void getOutputPathTest() {
        final Context context = new Context();
        final File expected = new File( System.getProperty( "java.io.tmpdir" ), "output" );
        Assert.assertEquals( AssertMessages.FILE_NOT_EXPECTED, expected, context.getOutputPath() );
    }

    @Test
    public void getTemplateCharsetTest() {
        final Context context = new Context();
        final Charset expected = StandardCharsets.UTF_8;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getTemplateCharset() );
    }
}
