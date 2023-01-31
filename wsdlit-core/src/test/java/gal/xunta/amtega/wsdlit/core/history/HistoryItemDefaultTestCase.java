package gal.xunta.amtega.wsdlit.core.history;

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
public class HistoryItemDefaultTestCase {
    private static final String DEFAULT_NAMESPACE_URI = "http://wsdlit";
    private static final String DEFAULT_NAME = "wsdlit";
    private static final String DEFAULT_KEY = "{" + DEFAULT_NAMESPACE_URI + "}" + DEFAULT_NAME;

    @Test
    public void cyclicReferenceReturnsTrueTest() {
        final HistoryItemDefault item = create();
        item.onCyclicReference();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, item.isCyclicReference() );
    }

    @Test
    public void equalsToItselfTest() {
        final HistoryItemDefault item = create();
        Assert.assertSame( AssertMessages.SAME_EXPECTED, item, item );
    }

    @Test
    public void equalsReturnsFalseWhenDifferentClassTest() {
        final HistoryItemDefault item = create();
        final Object number = 1;
        final boolean value = item.equals( number );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsSymetricTest() {
        final HistoryItemDefault one = create();
        final HistoryItemDefault two = create();
        Assert.assertTrue( AssertMessages.EQUALITY_SYMETRIC_EXPECTED,
            one.equals( two )
            && two.equals( one )
        );
    }

    @Test
    public void hashCodeEqualsWhenTwoInstancesSameDataTest() {
        final HistoryItemDefault one = create();
        final HistoryItemDefault two = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, one.hashCode(), two.hashCode() );
    }

    @Test
    public void getKeyTest() {
        final HistoryItemDefault item = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, DEFAULT_KEY, item.getKey() );
    }

    @Test
    public void getNamespaceUriTest() {
        final HistoryItemDefault item = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, DEFAULT_NAMESPACE_URI, item.getNamespaceUri() );
    }

    @Test
    public void isCyclicReferenceIsFalseTest() {
        final HistoryItemDefault item = create();
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, item.isCyclicReference() );
    }

    @Test
    public void isPushedInHistoryReturnsTrueTest() {
        final HistoryItemDefault item = create();
        item.onPushedInHistory();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, item.isPushedInHistory() );
    }

    private HistoryItemDefault create() {
        return new HistoryItemDefault(
            DEFAULT_NAMESPACE_URI,
            DEFAULT_NAME
        );
    }
}
