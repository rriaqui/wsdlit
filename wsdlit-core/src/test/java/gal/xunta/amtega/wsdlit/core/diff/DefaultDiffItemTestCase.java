package gal.xunta.amtega.wsdlit.core.diff;

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

import java.util.Collections;

/**
 * @author rriaqui
 * @since 2.0.0
 */
public class DefaultDiffItemTestCase {
    private static final String MESSAGE = "Message";

    @Test
    public void countUnsafeDifferencesIsEqualsToOneTest() {
        final int expected = 1;
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, false,
                Collections.singletonList(
                    new DefaultDiffItem( MESSAGE, true, Collections.emptyList() )
                )
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, item.countUnsafeDifferences() );
    }

    @Test
    public void countUnsafeDifferencesIsEqualsToZeroTest() {
        final int expected = 0;
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, false, Collections.singletonList(
                new DefaultDiffItem( MESSAGE, false, Collections.emptyList() )
        ) );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, item.countUnsafeDifferences() );
    }


    @Test
    public void countSafeDifferencesIsEqualsToZeroTest() {
        final int expected = 0;
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, false,
                Collections.singletonList(
                        new DefaultDiffItem( MESSAGE, true, Collections.emptyList() )
                )
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, item.countSafeDifferences() );
    }

    @Test
    public void countSafeDifferencesIsEqualsToOneTest() {
        final int expected = 1;
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, false, Collections.singletonList(
                new DefaultDiffItem( MESSAGE, false, Collections.emptyList() )
        ) );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, item.countSafeDifferences() );
    }

    @Test
    public void isBreakContractIsEqualsToFalseTest() {
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, false, Collections.emptyList() );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, item.isBreak() );
    }

    @Test
    public void isBreakContractIsEqualsToTrueTest() {
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, true, Collections.emptyList() );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, item.isBreak() );
    }

    @Test
    public void getDifferencesTest() {
        final int size = 1;
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, true, Collections.singletonList(
                new DefaultDiffItem( MESSAGE, false, Collections.emptyList() )
        ) );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, size, item.getDifferences().size() );
    }


    @Test
    public void getMessageTest() {
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, true, Collections.emptyList() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MESSAGE, item.getMessage() );
    }

    @Test
    public void isEmptyIsEqualsToFalseTest() {
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, true, Collections.singletonList(
                new DefaultDiffItem( MESSAGE, false, Collections.emptyList() )
        ) );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, item.isEmpty() );
    }

    @Test
    public void isEmptyIsEqualsToTrueTest() {
        final DefaultDiffItem item = new DefaultDiffItem( MESSAGE, true, Collections.emptyList() );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, item.isEmpty() );
    }
}
