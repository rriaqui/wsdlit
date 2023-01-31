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
public class DefaultHistoryContextTestCase {
    private static final String DEFAULT_NAMESPACE_URI = "http://wsdlit";
    private static final String DEFAULT_NAME = "wsdlit";

    @Test
    public void clearHistoryTest() {
        final DefaultHistoryContext history = create();
        history.pushHistory( createHistoryItem() );
        history.clearHistory();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, history.isEmpty() );
    }

    @Test
    public void containsTest() {
        final DefaultHistoryContext history = create();
        final HistoryItem item = createHistoryItem();
        history.pushHistory( item );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, history.contains( DEFAULT_NAMESPACE_URI, DEFAULT_NAME ) );
    }

    @Test
    public void isEmptyReturnsFalseTest() {
        final DefaultHistoryContext history = create();
        history.pushHistory( DEFAULT_NAMESPACE_URI, DEFAULT_NAME );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, history.isEmpty() );
    }

    @Test
    public void isEmptyReturnsTrueTest() {
        final DefaultHistoryContext history = create();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, history.isEmpty() );
    }

    @Test
    public void popHistoryTest() {
        final DefaultHistoryContext context = create();
        final HistoryItem item = createHistoryItem();
        context.pushHistory( item );
        context.popHistory( item );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, context.isEmpty() );
    }
    @Test
    public void pushHistoryReturnsTrueTest() {
        final DefaultHistoryContext context = create();
        context.pushHistory( createHistoryItem() );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, context.pushHistory( createHistoryItem() ) );
    }

    @Test
    public void pushHistoryReturnsFalseTest() {
        final DefaultHistoryContext context = create();
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, context.pushHistory( createHistoryItem() ) );
    }
    @Test
    public void pushHistoryNamespaceUriAndNameTest() {
        final DefaultHistoryContext context = create();
        final HistoryItem item = context.pushHistory( DEFAULT_NAMESPACE_URI, DEFAULT_NAME );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, createHistoryItem(), item );
    }

    private HistoryItem createHistoryItem() {
        return new HistoryItemDefault( DEFAULT_NAMESPACE_URI, DEFAULT_NAME );
    }

    public DefaultHistoryContext create() {
        return new DefaultHistoryContext();
    }
}
