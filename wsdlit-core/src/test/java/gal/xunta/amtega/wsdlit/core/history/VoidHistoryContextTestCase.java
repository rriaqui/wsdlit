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
public class VoidHistoryContextTestCase {
    private static final String DEFAULT_NAMESPACE_URI = "http://wsdlit";
    private static final String DEFAULT_NAME = "wsdlit";

    @Test
    public void containsTest() {
        final VoidHistoryContext context = new VoidHistoryContext();
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, context.contains( DEFAULT_NAMESPACE_URI, DEFAULT_NAME ) );
    }

    @Test
    public void isEmptyTest() {
        final VoidHistoryContext context = new VoidHistoryContext();
        context.pushHistory( DEFAULT_NAMESPACE_URI, DEFAULT_NAME );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, context.isEmpty() );
    }

    @Test
    public void popHistoryTest() {
        final VoidHistoryContext context = new VoidHistoryContext();
        context.popHistory( null );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, context.isEmpty() );
    }
    @Test
    public void pushHistoryReturnsTrueTest() {
        final VoidHistoryContext context = new VoidHistoryContext();
        final boolean pushed = context.pushHistory( null );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, pushed );
    }

    @Test
    public void clearHistoryTest() {
        final VoidHistoryContext context = new VoidHistoryContext();
        context.clearHistory();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, context.isEmpty() );
    }
}
