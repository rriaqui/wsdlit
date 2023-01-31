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
import gal.xunta.amtega.wsdlit.core.sort.SortAlgorithms;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class PortTypeNodeTestCase
extends ModelTestCaseAbstract {
    @Test
    public void notEqualsToNullTest() {
        final PortTypeNode node = createPortTypeNode();
        final PortTypeNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenNullTest() {
        PortTypeNode node = createPortTypeNode();
        PortTypeNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenDifferentClassTest() {
        PortTypeNode node = createPortTypeNode();
        final boolean value = node.equals( 1 );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenSuperReturnsFalseTest() {
        PortTypeNode node = createPortTypeNode();
        PortTypeNode secondNode = createPortTypeNode();

        secondNode.setName( "anotherName" );

        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsToItselfTest() {
        final PortTypeNode node = createPortTypeNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, node, node );
    }

    @Test
    public void equalsToSameTest() {
        final PortTypeNode node = createPortTypeNode();
        final PortTypeNode secondary = node;
        Assert.assertSame( AssertMessages.SAME_EXPECTED, node, secondary );
    }

    @Test
    public void equalsReturnFalseWhenSuperIsDifferentTest() {
        final PortTypeNode node = createPortTypeNode();
        final PortTypeNode secondNode = createPortTypeNode();
        secondNode.setName( "portType 2" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsWhenNoOperationsTest() {
        final PortTypeNode node = createPortTypeNode();
        final PortTypeNode secondNode = createPortTypeNode();

        final boolean value = node.equals( secondNode );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsWhenOperationsAreIdenticalTest() {
        final List< OperationNode > operations = Arrays.asList(
            createOperationNode(),
            createOperationNode()
        );
        final PortTypeNode node = createPortTypeNode( operations );
        final PortTypeNode secondNode = createPortTypeNode( operations );

        final boolean value = node.equals( secondNode );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void notEqualsWhenOperationsAreDifferentTest() {
        final List<OperationNode> operations = Arrays.asList(
                createOperationNode(),
                createOperationNode()
        );
        final PortTypeNode node = createPortTypeNode( operations );
        final PortTypeNode secondNode = createPortTypeNode();

        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void getInterfaceTest() {
        final PortTypeNode node = createPortTypeNode();
        final String expected = TNS + "{" + PORT_TYPE_NAME + "}";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getInterface() );
    }

    @Test
    public void getOperationsIsEmptyTest() {
        final PortTypeNode node = createPortTypeNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, Collections.emptyList(), node.getOperations() );
    }

    @Test
    public void getOperationsIsNotEmptyTest() {
        final List<OperationNode> operations = Arrays.asList(
                createOperationNode(),
                createOperationNode()
        );
        final PortTypeNode node = createPortTypeNode( operations );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, operations, node.getOperations() );
    }

    @Test
    public void hashCodeTest() {
        final OperationNode operation = createOperationNode();
        final PortTypeNode node = createPortTypeNode( Collections.singletonList( operation ) );

        final AbstractNode abstractNode = createAbstractNode( node );
        final int expected = Objects.hash(
                abstractNode.hashCode(),
                Collections.singletonList( operation )
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.hashCode() );
    }

    @Test
    public void sortWithNoneAlgorithmTest() {
        final PortTypeNode node = createPortTypeNodeForSortTests();
        final String expected = "operation2";

        node.sort( SortAlgorithms.SORT_BY_NAME );
        node.sort( SortAlgorithms.NONE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOperations().get( 0 ).getName() );
    }

    @Test
    public void sortWithSortByNameAlgorithmTest() {
        final PortTypeNode node = createPortTypeNodeForSortTests();
        final String expected = "operation1";

        node.sort( SortAlgorithms.NONE );
        node.sort( SortAlgorithms.SORT_BY_NAME );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOperations().get( 0 ).getName() );
    }

    @Test
    public void unsortTest() {
        final PortTypeNode node = createPortTypeNodeForSortTests();
        final String expected = "operation2";

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOperations().get( 0 ).getName() );
    }

    public PortTypeNode createPortTypeNodeForSortTests() {
        final List< OperationNode > operationNodes = Arrays.asList(
            createOperationNode( "operation2" ),
            createOperationNode( "operation1" )
        );
        return createPortTypeNode( operationNodes );
    }
}
