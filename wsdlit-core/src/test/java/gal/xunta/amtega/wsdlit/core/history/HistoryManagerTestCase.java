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
public class HistoryManagerTestCase {
    @Test
    public void containsReturnTrueTest() {
        final HistoryItem one = createElementOne();
        final HistoryManager history = new HistoryManager();
        history.push( one );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, history.contains( one ) );
    }

    @Test
    public void containsReturnsFalseTest() {
        final HistoryItem one = createElementOne();
        final HistoryManager history = new HistoryManager();
        history.push( createElementTwo() );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, history.contains( one ) );
    }

    @Test
    public void popTest() {
        final HistoryManager history = new HistoryManager();
        final HistoryItem item = createElementTwo();
        history.push( createElementOne() );
        history.push( item );
        history.pop( item );
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, 1, history.size() );
    }

    @Test
    public void popWhenItemWasNotPreviouslyPushedTest() {
        final HistoryManager history = new HistoryManager();
        final HistoryItem item = createElementTwo();
        history.push( createElementOne() );
        history.pop( item );
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, 1, history.size() );
    }

    @Test
    public void pushTest() {
        final HistoryManager history = new HistoryManager();
        history.push( createElementOne() );
        history.push( createElementTwo() );
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED,2, history.size() );
    }

    @Test
    public void pushWhenEmptyNameSpaceAndNameTest() {
        final HistoryManager history = new HistoryManager();
        history.push( new HistoryItemDefault( "", "" ) );
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, 0, history.size() );
    }

    @Test
    public void isCyclicReferenceEqualsToTrueTest() {
        final HistoryManager history = new HistoryManager();
        final HistoryItem two = createElementOne();
        history.push( createElementOne() );
        history.push( two );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, two.isCyclicReference() );
    }

    private HistoryItem createElementOne() {
        return new HistoryItemDefault(
            "src/test/resources/element", "http://emprego.xunta.es/namespace1"
        );
    }

    private HistoryItem createElementTwo() {
        return new HistoryItemDefault(
            "src/test/resources/element", "http://emprego.xunta.gal/namespace1"
        );
    }
}
