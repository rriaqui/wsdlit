package gal.xunta.amtega.wsdlit.core.util;

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

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class StringUtilTestCase {
    @Test
    public void defaultIfBlankWhemEmptyValueThenReturnDefaultValueTest() {
        final String value = "";
        final String expected = "defaultValue";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.defaultIfBlank( value, expected ) );
    }

    @Test
    public void defaultIfBlankWhenNullValueThenReturnDefaultValueTest() {
        final String value = null;
        final String expected = "defaultValue";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.defaultIfBlank( value, expected ) );
    }

    @Test
    public void defaultIfBlankWhenNotBlankThenReturnSameValueTest() {
        final String value = "Value";
        final String expected = "defaultValue";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, value, StringUtil.defaultIfBlank( value, expected ) );
    }

    @Test
    public void lengthTest() {
        final String text = "Hola, mundo ";
        final int expected = 12;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.length( text ) );
    }

    @Test
    public void lengthWhenEmptyTest() {
        final String text = StringUtil.EMPTY;
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.length( text ) );
    }

    @Test
    public void lengthWhenNullTest() {
        final String text = null;
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.length( text ) );
    }

    @Test
    public void multiplyTest() {
        final String text = "-";
        final int repeat = 5;
        final String expected = "-----";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.multiply( text, repeat ) );
    }

    @Test
    public void multiplyWhenEmptyTest() {
        final String text = "";
        final int repeat = 5;
        final String expected = StringUtil.EMPTY;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.multiply( text, repeat ) );
    }

    @Test
    public void multiplyWhenNullTest() {
        final String text = null;
        final int repeat = 5;
        Assert.assertNull( AssertMessages.NULL_EXPECTED, StringUtil.multiply( text, repeat ) );
    }

    @Test
    public void multiplyWhenRepeatIsLessThanOneTest() {
        final String text = "-";
        final int repeat = -1;
        final String expected = StringUtil.EMPTY;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.multiply( text, repeat ) );
    }

    @Test
    public void multiplyWhenRepeatIsOneTest() {
        final String text = "-";
        final int repeat = 1;
        final String expected = "-";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.multiply( text, repeat ) );
    }

    @Test
    public void normalizeTest() {
        final String text = "   Hola, mundo   ";
        final String expected = "Hola, mundo";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.normalize( text ) );
    }

    @Test
    public void normalizeWhenNullTest() {
        final String text = null;
        final String expected = StringUtil.EMPTY;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.normalize( text ) );
    }

    @Test
    public void normalizeWhenIsBlankTest() {
        final String text = "     ";
        final String expected = StringUtil.EMPTY;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringUtil.normalize( text ) );
    }

    @Test
    public void splitTest() {
        final String text = "1|2|3";
        final String[] expected = {
            "1", "2", "3"
        };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, StringUtil.split( "\\|", text ) );
    }

    @Test
    public void splitReturnsEmptyArrayWhenTextIsBlankTest() {
        final String text = "";
        final String[] array = StringUtil.split( "\\|", text );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, 0, array.length );
    }
}
