package gal.xunta.amtega.wsdlit.core.model;

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
public class MessageNodeEqualsHashCompareTestCase
extends ModelTestCaseAbstract {
    @Test
    public void equalsToOtherInstanceWithSameDataTest() {
        final MessageNode node = createInputMessage();
        final MessageNode expected = createInputMessage();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void notEqualsToDifferentClassTest() {
        final Object notExpected = Integer.MAX_VALUE;
        final MessageNode node = createInputMessage();
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, notExpected, node );
    }

    @Test
    public void equalsToSimetricalTest() {
        final MessageNode node = createInputMessage();
        final MessageNode secondary = createInputMessage();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, node, secondary );
    }
    @Test
    public void equalsToItselfTest() {
        final MessageNode node = createInputMessage();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, node, node );
    }

    @Test
    public void notEqualsToNullTest() {
        final MessageNode node = createInputMessage();
        Assert.assertNotNull( AssertMessages.NOT_EQUALS_EXPECTED, node );
    }

    @Test
    public void notEqualsWhenPrefixNotEqualsTest() {
        final MessageNode node = createInputMessage();
        final MessageNode unexpected = createInputMessage();
        unexpected.setPrefix( "another" );
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void notEqualsWhenPartsNotEqualsTest() {
        final MessageNode node = createInputMessage();
        final MessageNode unexpected = createInputMessage();
        unexpected.add( createPartNode() );
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToDifferentClassTest() {
        final MessageNode node = createInputMessage();
        final String helloWorld = "Hello world";
        final boolean value = node.equals( helloWorld );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }
}
