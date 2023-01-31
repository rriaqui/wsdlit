package gal.xunta.amtega.wsdlit.membranesoa.diff;

/*-
 * #%L
 * wsdlit-membranesoa
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

import com.predic8.soamodel.Difference;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DifferenceUtilTestCase {
    @Test
    public void sizeTotalIsEqualsToZeroTest() {
        final List<Difference> differences = Collections.emptyList();
        final int expected = 0;
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, expected, DifferenceUtil.sizeTotal( differences ) );
    }

    @Test
    public void sizeTotalIsEqualsToOneTest() {
        final List<Difference> differences = Collections.singletonList(
                new Difference()
        );
        final int expected = 1;
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, expected, DifferenceUtil.sizeTotal( differences ) );
    }

    @Test
    public void sizeTotalIsEqualsToTwoTest() {
        final List<Difference> differences = Arrays.asList(
                new Difference(),
                new Difference()
        );
        final int expected = 2;
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, expected, DifferenceUtil.sizeTotal( differences ) );
    }

    @Test
    public void sizeTotalDeepSizeIsEqualsToThreeTest() {
        final List<Difference> differences = createDeep();
        final int expected = 3;

        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, expected, DifferenceUtil.sizeTotal( differences ) );
    }

    private List<Difference> createDeep() {
        final Difference firstChild = createDifference( "first" );
        final List<Difference> firstChildDifferences = Collections.singletonList(
                createDifference( "first: firstChild" )
        );
        final List<Difference> differences = Arrays.asList(
                firstChild,
                createDifference( "second" )
        );

        firstChild.setDiffs( firstChildDifferences );
        return differences;
    }

    public final Difference createDifference( final String prefix ) {
        final Difference difference = new Difference();
        difference.setDescription( prefix + "Description" );
        difference.setType( prefix + "Type" );
        return difference;
    }
}
