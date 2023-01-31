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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class StringUtilBlankEmptyTestCase {
    private final String text;
    private final boolean blankExpected;
    private final boolean emptyExpected;
    private final String emptyIfBlankExpected;
    private final String removeWhitespaceAtEndExpected;
    private final String removeWhitespaceAtStartExpected;

    public StringUtilBlankEmptyTestCase(
            final String text,
            final boolean blankExpected,
            final boolean emptyExpected,
            final String emptyIfBlankExpected,
            final String removeWhitespaceAtEndExpected,
            final String removeWhitespaceAtStartExpected
    ) {
        this.text = text;
        this.blankExpected = blankExpected;
        this.emptyExpected = emptyExpected;
        this.emptyIfBlankExpected = emptyIfBlankExpected;
        this.removeWhitespaceAtEndExpected = removeWhitespaceAtEndExpected;
        this.removeWhitespaceAtStartExpected = removeWhitespaceAtStartExpected;
    }

    @Test
    public void emptyIfBlankTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.emptyIfBlankExpected, StringUtil.emptyIfBlank( this.text ) );
    }
    @Test
    public void isBlankTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.blankExpected, StringUtil.isBlank( this.text ) );
    }

    @Test
    public void isEmptyTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.emptyExpected, StringUtil.isEmpty( this.text ) );
    }

    @Test
    public void isNotBlankTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, !this.blankExpected, StringUtil.isNotBlank( this.text ) );
    }

    @Test
    public void isNotEmptyTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, !this.emptyExpected, StringUtil.isNotEmpty( this.text ) );
    }

    @Test
    public void removeWhitespaceAtEndTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.removeWhitespaceAtEndExpected, StringUtil.removeWhitespaceAtEnd( this.text ) );
    }

    @Test
    public void removeWhitespaceAtStartTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.removeWhitespaceAtStartExpected, StringUtil.removeWhitespaceAtStart( this.text ) );
    }

    @Parameterized.Parameters
    public static Iterable< Object[] > data() {
        return Arrays.asList(
                new Object[][] {
                        { null, true, true, StringUtil.EMPTY, null, null },
                        { StringUtil.EMPTY, true, true, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY },
                        { "  ", true, false, StringUtil.EMPTY, StringUtil.EMPTY, StringUtil.EMPTY },
                        { " Hola, mundo", false, false, " Hola, mundo", " Hola, mundo", "Hola, mundo" },
                        { "Hola, mundo ", false, false, "Hola, mundo ", "Hola, mundo", "Hola, mundo " },
                        { " Hola, mundo ", false, false, " Hola, mundo ", " Hola, mundo", "Hola, mundo " }
                }
        );
    }
}
