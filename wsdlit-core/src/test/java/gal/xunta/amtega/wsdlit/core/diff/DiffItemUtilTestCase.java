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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author rriaqui
 * @since 2.0.0
 */
public class DiffItemUtilTestCase {
    @Test
    public void countSafeAndUnsafeModificationsWhenDifferencesIsEmptyTest() {
        final List< DiffItem > differences = Collections.emptyList();
        final int[] expected = { 0, 0 };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, DiffItemUtil.countSafeAndUnsafeModifications( differences ) );
    }

    @Test
    public void countSafeAndUnsafeModificationsIsEqualsToOneSafeTest() {
        final List<DiffItem> differences = Collections.singletonList(
                new DefaultDiffItem( "New operation: newOperation", false, Collections.emptyList() )
        );
        final int[] expected = { 1, 0 };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, DiffItemUtil.countSafeAndUnsafeModifications( differences ) );
    }

    @Test
    public void countSafeAndUnsafeModificationsIsEqualsToOneSafeAndOneUnsafeTest() {
        final List<DiffItem> differences = Arrays.asList(
                new DefaultDiffItem( "New operation: newOperation", true, Collections.emptyList() ),
                new DefaultDiffItem( "New operation: newOperation", false, Collections.emptyList() )
        );
        final int[] expected = { 1, 1 };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, DiffItemUtil.countSafeAndUnsafeModifications( differences ) );
    }

    @Test
    public void countSafeAndUnsafeModificationsIsEqualsToOneUnsafeTest() {
        final List<DiffItem> differences = Collections.singletonList(
                new DefaultDiffItem( "New operation: newOperation", true, Collections.emptyList() )
        );
        final int[] expected = { 0, 1 };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, DiffItemUtil.countSafeAndUnsafeModifications( differences ) );
    }

    @Test
    public void countSafeAndUnsafeModificationsCheckDeepCountTest() {
        final int[] expected = { 1, 1 };
        final List<DiffItem> unsafeModifications = generateModifications( 4, true );
        final List<DiffItem> safeModifications = generateModifications( 5, false );
        final List<DiffItem> totalModifications = new ArrayList<>();
        totalModifications.addAll( safeModifications );
        totalModifications.addAll( unsafeModifications );
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, DiffItemUtil.countSafeAndUnsafeModifications( totalModifications ) );
    }


    public final List<DiffItem> generateModifications( final int deep, final boolean breakContract ) {
        final List<DiffItem> children;
        if ( deep > 1 ) {
            children = generateModifications( deep - 1, breakContract );
        } else {
            children = Collections.emptyList();
        }
        return Collections.singletonList(
                new DefaultDiffItem( "Message", breakContract, children )
        );
    }
}
