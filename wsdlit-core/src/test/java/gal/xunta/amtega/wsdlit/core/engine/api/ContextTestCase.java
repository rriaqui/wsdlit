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
import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ContextTestCase {
    @Test
    public void getTemplatesPathWhenDefaultConstructorTest() {
        final String expected = "/templates/default";
        final Context context = new Context( expected );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getTemplatesPath() );
    }

    @Test
    public void getTemplatesPathTest() {
        final String expected = "/tmp/templates/path";
        final Context context = new Context( expected );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getTemplatesPath() );
    }


    @Test
    public void getOutputCharsetTest() {
        final Charset expected = StandardCharsets.UTF_8;
        final Context context = new Context();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getOutputCharset() );
    }

    @Test
    public void getOutputPathTest() {
        final File expected = new File( "/tmp/output/path" );
        final Context context = new Context();
        context.setOutputPath( expected );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getOutputPath() );
    }

    @Test
    public void getTemplateCharsetReturnsUTF8Test() {
        final Charset expected = StandardCharsets.UTF_8;
        final Context context = new Context();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getTemplateCharset() );
    }

    @Test
    public void getTemplateCharsetReturnsISO88591Test() {
        final Charset expected = StandardCharsets.ISO_8859_1;
        final Context context = new Context();
        context.setTemplateCharSet( expected );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, context.getTemplateCharset() );
    }

    @Test
    public void equalsReturnsFalseWhenClassesAreDifferentTest() {
        final Context context = new Context();
        final Object object = "Hello world";
        final boolean value = context.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToNullTest() {
        final Context context = new Context();
        final Object object = null;
        final boolean value = context.equals( null );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenOutputCharsetIsDifferentTest() {
        final Context context = new Context();
        final Context object = new Context().setOutputCharset( StandardCharsets.ISO_8859_1 );
        final boolean value = context.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenOutputPathIsDifferentTest() {
        final Context context = new Context();
        final Context object = new Context();
        object.setOutputPath( new File( "target/output/path") );
        final boolean value = context.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }
    @Test
    public void equalsReturnsFalseWhenTemplatesCharsetIsDifferentTest() {
        final Context context = new Context();
        final Context object = new Context();
        object.setTemplateCharSet( StandardCharsets.ISO_8859_1 );
        final boolean value = context.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenTemplatesPathIsDifferentTest() {
        final Context context = new Context();
        final Context object = new Context( "another/templates/path" );
        final boolean value = context.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsTrueWhenCompareToItselfTest() {
        final Context context = new Context();

        Assert.assertSame( AssertMessages.SAME_EXPECTED, context, context );
    }

    @Test
    public void hashCodeTest() {
        final Charset outputCharset = StandardCharsets.UTF_8;
        final File outputPath = new File( "/tmp/output/path" );
        final Charset templatesCharset = StandardCharsets.UTF_8;
        final String templatesPath = "/tmp/templates/path";

        final Context context = new Context( templatesPath );
        context.setOutputPath( outputPath );
        context.setTemplateCharSet( templatesCharset );
        final int expected = Objects.hash( templatesPath, outputCharset, templatesCharset, outputPath );
        final int value = context.hashCode();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }
}
