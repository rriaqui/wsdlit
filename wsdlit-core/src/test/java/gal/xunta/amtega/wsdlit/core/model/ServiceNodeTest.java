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
import gal.xunta.amtega.wsdlit.core.diff.DefaultDiffItem;
import gal.xunta.amtega.wsdlit.core.diff.DiffItem;
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
public class ServiceNodeTest
extends ModelTestCaseAbstract {
    @Test
    public void constructorTest() {
        final ServiceNode node = createApiNode();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, node );
    }

    @Test
    public void addTest() {
        final ServiceNode node = createApiNode();
        final NamespaceNode namespaceNode = createNamespaceNode();
        node.add( namespaceNode );
        final NamespaceNode[] expected = new NamespaceNode[] {
                namespaceNode
        };

        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, node.getNamespaces().toArray() );
    }

    @Test
    public void addAllTest() {
        final ServiceNode node = createApiNode();
        final List<NamespaceNode> namespaceNodes = Arrays.asList(
                createNamespaceNode(),
                createNamespaceNode()
        );
        node.addAll( namespaceNodes );
        final NamespaceNode[] expected = new NamespaceNode[] {
                createNamespaceNode(),
                createNamespaceNode()
        };

        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, node.getNamespaces().toArray() );

    }

    @Test
    public void equalsTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode expected = createApiNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void equalsToItselfTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondary = node;
        final boolean value = node.equals( secondary );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, value );
    }

    @Test
    public void equalsToOtherInstanceWithSameDataTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode expected = createApiNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void notEqualsToDifferentClassTest() {
        final ServiceNode node = createApiNode();
        final Object object = 1;
        final boolean value = node.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void notEqualsToDifferentAttributeFormDefaultTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondNode = createApiNode();
        node.setAttributeFormDefault( "unqualified" );
        secondNode.setAttributeFormDefault( "qualified" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void notEqualsToDifferentElementFormDefaultTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondNode = createApiNode();
        node.setElementFormDefault( "unqualified" );
        secondNode.setElementFormDefault( "qualified" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void notEqualsToNullTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void notEqualsWhenNameIsNotEqualsTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondNode = createApiNode();
        secondNode.setName( ANOTHER_NAME );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnFalseWhenPortTypesAreNotEqualsTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondNode = createApiNode( Collections.emptyList() );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnFalseWhenNamespacesAreNotEqualsTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondNode = createApiNode();
        secondNode.add( createNamespaceNode() );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnFalseWhenDifferencesAreNotEqualsTest() {
        final ServiceNode node = createApiNode();
        final ServiceNode secondNode = createApiNode();
        secondNode.setDifferences( Collections.singletonList(
            new DefaultDiffItem( "Difference", false, Collections.emptyList() )
        ) );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }
    @Test
    public void hashCodeTest() {
        final ServiceNode node = createApiNode();
        final AbstractNode abstractNode = createAbstractNode( node );
        final int expected = Objects.hash(
                abstractNode.hashCode(),
                node.getAttributeFormDefault(),
                node.getElementFormDefault(),
                node.getPortTypes(),
                node.getNamespaces(),
                node.getDifferences()
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.hashCode() );
    }

    @Test
    public void getAttributeFormDefaultTest() {
        final ServiceNode node = createApiNode();
        final String expected = "Qualified";
        node.setAttributeFormDefault( expected );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getAttributeFormDefault() );
    }

    @Test
    public void getElementFormDefaultTest() {
        final ServiceNode node = createApiNode();
        final String expected = "Qualified";
        node.setElementFormDefault( expected );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getElementFormDefault() );
    }

    @Test
    public void getNamespacesTest() {
        final ServiceNode node = createApiNode();
        final NamespaceNode namespaceNode = createNamespaceNode();
        final NamespaceNode[] expected = new NamespaceNode[] {
                namespaceNode
        };
        node.add( namespaceNode );
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, node.getNamespaces().toArray() );
    }

    @Test
    public void getPortTypesTest() {
        final ServiceNode node = createApiNode();
        final PortTypeNode portTypeNode = createPortTypeNode();
        final PortTypeNode[] expected = new PortTypeNode[] {
                portTypeNode
        };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, node.getPortTypes().toArray() );

    }

    @Test
    public void getDifferencesTest() {
        final ServiceNode node = createApiNode();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, node.getDifferences().isEmpty() );
    }

    @Test
    public void setDifferencesTest() {
        final ServiceNode node = createApiNode();
        final DiffItem difference = createDiffItem();
        node.setDifferences( Collections.singletonList( difference ) );
        final DiffItem[] expected = new DiffItem[] {
                difference
        };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, node.getDifferences().toArray() );
    }

    @Test
    public void sortWithNoneAlgorithmTest() {
        final ServiceNode serviceNode = createServiceNodeForSortTests();
        serviceNode.sort( SortAlgorithms.SORT_BY_NAME );
        serviceNode.sort( SortAlgorithms.NONE );

        final String expected = "node2";
        final String value = serviceNode.getPortTypes().get( 0 ).getName();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void sortWithSortByNameAlgorithmTest() {
        final ServiceNode serviceNode = createServiceNodeForSortTests();
        serviceNode.sort( SortAlgorithms.NONE );
        serviceNode.sort( SortAlgorithms.SORT_BY_NAME );

        final String expected = "node1";
        final String value = serviceNode.getPortTypes().get( 0 ).getName();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void unsortTest() {
        final ServiceNode serviceNode = createServiceNodeForSortTests();

        final String expected = "node2";
        final String value = serviceNode.getPortTypes().get( 0 ).getName();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    public ServiceNode createServiceNodeForSortTests() {
        final List< OperationNode > operationNodes = Collections.singletonList( createOperationNode() );
        final List< PortTypeNode > portNodes = Arrays.asList(
            createPortTypeNode( "node2", operationNodes ),
            createPortTypeNode( "node1", operationNodes )
        );
        return createApiNode( portNodes );
    }
}
