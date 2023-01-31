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
public class OperationNodeEqualsHashCompareTestCase
extends ModelTestCaseAbstract {
    @Test
    public void equalsToIdenticalTest() {
        final OperationNode node = createOperationNode();
        final OperationNode expected = createOperationNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void notEqualsToDifferentClassTest() {
        final Object notExpected = Integer.MAX_VALUE;
        final OperationNode node = createOperationNode();
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, notExpected, node );
    }

    /*
     * Simetrical comparison
     */
    @Test
    public void equalsSimetricalTest() {
        final OperationNode node = createOperationNode();
        final OperationNode secondary = createOperationNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, secondary, node );
    }

    @Test
    public void equalsToItselfTest() {
        final OperationNode node = createOperationNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, node, node );
    }

    @Test
    public void notEqualsToNullTest() {
        final OperationNode node = createOperationNode();
        final OperationNode unexpected = null;
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void equalsPrefixTest() {
        final OperationNode node = createOperationNode();
        final OperationNode unexpected = createOperationNode();
        unexpected.setPrefix( "another" );
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void notEqualsTest() {
        final OperationNode node = createOperationNode();
        final OperationNode unexpected = createOperationNode();
        unexpected.add( createFaultMessage() );
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void notEqualsWhenInputMessageIsDifferentTest() {
        final OperationNode node = createOperationNode();
        final OperationNode unexpected = createOperationNode();
        final MessageNode message = createInputMessage();
        unexpected.setInputMessage( message );
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void notEqualsWhenOutputMessageIsDifferentTest() {
        final OperationNode node = createOperationNode();
        final OperationNode unexpected = createOperationNode();
        final MessageNode message = createOutputMessage();
        unexpected.setOutputMessage( message );
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void notEqualsWhenTypeIsDifferentTest() {
        final OperationNode node = createOperationNode();
        final OperationNode unexpected = createOperationNode();
        unexpected.setType( OperationType.UNKNOWN );
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void equalsReturnsFalseWhenNullTest() {
        final OperationNode node = createOperationNode();
        final OperationNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenDifferentClassTest() {
        OperationNode node = createOperationNode();
        final boolean value = node.equals( 1 );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }
}
