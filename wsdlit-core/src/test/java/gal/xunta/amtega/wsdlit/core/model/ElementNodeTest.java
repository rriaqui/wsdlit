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

import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ElementNodeTest
extends ModelTestCaseAbstract {
    @Test
    public void constructorTest() {
        final ElementNode node = createElementNode();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, node );
    }

    @Test
    public void containsNameReturnsFalseTest() {
        final ElementNode node = createElementNodeBuilder()
            .build();
        final boolean value = node.containsName( ANOTHER_NAME );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void containsNameReturnsTrueTest() {
        final ElementNode expected = createElementNodeBuilder()
            .setName( ANOTHER_NAME )
            .build();
        final ElementNode node = createElementNodeBuilder()
            .add( expected )
            .build();
        final boolean value = node.containsName( ANOTHER_NAME );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, value );
    }

    @Test
    public void computeOcurrencesWhenMinOccursIsEqualsToMaxOccursTest() {
        final ElementNode node = createElementNodeBuilder()
            .setMinOccurs( "2" )
            .setMaxOccurs( "2" )
            .build();
        final String expected = "2";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOccurrences() );
    }

    @Test
    public void computeOcurrencesWhenMaxOccursIsEqualsToUnboundedTest() {
        final ElementNode node = createElementNodeBuilder()
            .setMinOccurs( "2" )
            .setMaxOccurs( "unbounded" )
            .build();
        final String expected = "2..\u221e";
        final String value = node.getOccurrences();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );

    }

    @Test
    public void equalsTest() {
        final ElementNode node = createElementNode();
        final ElementNode expected = createElementNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void equalsOtherTest() {
        final ElementNode node = createElementNode();
        final ElementNode expected = createElementNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void notEqualsToDifferentChildrenTest() {
        final ElementNode node = createElementNode();
        final ElementNode expected = createElementNodeBuilder()
                .add( createElementNode() )
                .build();
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void notEqualsToDifferentClassTest() {
        final Object object = Integer.MAX_VALUE;
        final ElementNode node = createElementNode();
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, object, node );
    }

    @Test
    public void notEqualsToDifferentMaxOccursTest() {
        final ElementNode node = createElementNode();
        final ElementNode expected = createElementNodeBuilder()
                .setMaxOccurs( "0" )
                .build();
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void notEqualsToDifferentMinOccursTest() {
        final ElementNode node = createElementNode();
        final ElementNode expected = createElementNodeBuilder()
                .setMinOccurs( "0" )
                .build();
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void notEqualsToDifferentTypeTest() {
        final ElementNode node = createElementNode();
        final ElementNode expected = createElementNodeBuilder()
                .setType( ANOTHER_TYPE )
                .build();
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void equalsToItselfTest() {
        final ElementNode node = createElementNode();
        Assert.assertSame( AssertMessages.SAME_EXPECTED, node, node );
    }

    @Test
    public void notEqualsToNullTest() {
        final ElementNode node = createElementNode();
        final ElementNode unexpected = null;
        Assert.assertNotEquals( AssertMessages.NOT_EQUALS_EXPECTED, unexpected, node );
    }

    @Test
    public void hashCodeDifferentInstancesWithSameHasSameValueTest() {
        final ElementNode node = createElementNodeWithChild();
        final AbstractNode abstractNode = createAbstractNode( node );
        final int expected = Objects.hash(
                abstractNode.hashCode(),
                 node.getChildren(),
                ELEMENT_NODE_MIN_OCCURS,
                ELEMENT_NODE_MAX_OCCURS,
                ELEMENT_NODE_TYPE
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.hashCode() );
    }

    @Test
    public void getChildrenTest() {
        final ElementNode node = createElementNodeWithChild();
        final ElementNode[] expected = new ElementNode[] {
                createElementNodeBuilder().setName( ANOTHER_NAME ).build()
        };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, node.getChildren().toArray() );
    }

    @Test
    public void getMinOccursTest() {
        final ElementNode node = createElementNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, ELEMENT_NODE_MIN_OCCURS, node.getMinOccurs() );
    }

    @Test
    public void getMaxOccursTest() {
        final ElementNode node = createElementNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, ELEMENT_NODE_MAX_OCCURS, node.getMaxOccurs() );
    }

    @Test
    public void getOccurrencesTest() {
        final ElementNode node = createElementNode();
        final String expected = ELEMENT_NODE_MIN_OCCURS + ".." + ELEMENT_NODE_MAX_OCCURS;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOccurrences() );
    }

    @Test
    public void getOccurrencesWhenMinOccursAndMaxOccursAreEqualsTest() {
        final String expected = "1";
        final ElementNode node = createElementNodeBuilder()
                .setMaxOccurs( expected )
                .setMinOccurs( expected )
                .build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOccurrences() );
    }

    @Test
    public void getOccurrencesWhenUnboundTest() {
        final String expected = "0..unbound";
        final ElementNode node = createElementNodeBuilder()
                .setMaxOccurs( "unbound" )
                .setMinOccurs( "0" )
                .build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOccurrences() );
    }

    @Test
    public void getTypeTest() {
        final ElementNode node = createElementNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, ELEMENT_NODE_TYPE, node.getType() );
    }

    @Test
    public void getTest() {
        final ElementNode expected = createElementNodeBuilder()
                .setName( ANOTHER_NAME )
                .build();
        final ElementNode node = createElementNodeBuilder()
                .add( createElementNode() )
                .add( expected )
                .build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.get( 1 ) );
    }

    @Test
    public void sizeIsZeroTest() {
        final ElementNode node = createElementNode();
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.size() );
    }

    @Test
    public void sizeIsOneTest() {
        final ElementNode node = createElementNodeBuilder()
                .add( createElementNode() )
                .build();
        final int expected = 1;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.size() );
    }

    @Test
    public void totalIsZeroTest() {
        final ElementNode node = createElementNode();
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.totalSize() );
    }

    @Test
    public void totalIsOneTest() {
        final ElementNode node = createElementNodeBuilder()
                .add( createElementNode() )
                .build();
        final int expected = 1;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.totalSize() );
    }

    @Test
    public void totalDeepTest() {
        final ElementNode child = createElementNodeBuilder()
                .add( createElementNode() )
                .build();
        final ElementNode node = createElementNodeBuilder()
                .add( child )
                .build();
        final int expected = 2;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.totalSize() );
    }

    @Test
    public void isEmptyTest() {
        final ElementNode node = createElementNode();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, node.isEmpty() );
    }
    @Test
    public void isNotEmptyReturnsFalseTest() {
        final ElementNode node = createElementNodeBuilder()
            .build();
        Assert.assertFalse( AssertMessages.TRUE_EXPECTED, node.isNotEmpty() );
    }
    @Test
    public void isNotEmptyReturnsTrueTest() {
        final ElementNode node = createElementNodeBuilder()
                .add( createElementNode() )
                .build();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, node.isNotEmpty() );
    }

    @Test
    public void findByNameReturnsNodeTest() {
        final ElementNode expected = createElementNodeBuilder()
                .setName( ANOTHER_NAME )
                .build();
        final ElementNode node = createElementNodeBuilder()
                .add( expected )
                .build();
        final ElementNode founded = node.findByName( ANOTHER_NAME );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void findByNameReturnsNullTest() {
        final String thisUriDoesntShouldBeFounded = "uri/uri";
        final ElementNode child = createElementNodeBuilder()
            .setName( ANOTHER_NAME )
            .build();
        final ElementNode node = createElementNodeBuilder()
            .add( child )
            .build();
        final ElementNode founded = node.findByName( thisUriDoesntShouldBeFounded );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, founded );
    }

    @Test
    public void findByNameDeepTest() {
        final ElementNode expected = createElementNodeBuilder()
                .setName( ANOTHER_NAME )
                .build();
        final ElementNode node = createElementNodeBuilder()
                .add(
                        createElementNodeBuilder().add( expected ).build()
                )
                .build();
        final ElementNode founded = node.findByName( ANOTHER_NAME );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void findByNameNotFoundTest() {
        final ElementNode child = createElementNodeBuilder()
                .setName( ANOTHER_NAME )
                .build();
        final ElementNode node = createElementNodeBuilder()
                .add( child )
                .build();
        final ElementNode founded = node.findByName( "unavailable" );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, founded );
    }

    @Test
    public void isModelGroupTest() {
        final ElementNode node = createElementNode();
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, node.isModelGroup() );
    }
}
