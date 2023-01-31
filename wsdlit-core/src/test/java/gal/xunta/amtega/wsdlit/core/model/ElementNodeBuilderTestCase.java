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
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ElementNodeBuilderTestCase {
    private static final String CHILD_NODE_NAME = "childNodeName";
    private static final String FIRST_NODE_NAME = "firstNodeName";
    private static final String ANOTHER_NAME = "another name";
    private static final String ANOTHER_DOCUMENTATION = "another documentation";
    private static final String DOCUMENTATION = "documentation";
    private static final String MAX_OCCURS = "unbound";
    private static final String MIN_OCCURS = "1";
    private static final String NAME = "name";
    private static final String PREFIX = "tns";
    private static final String TYPE = "type";
    private static final String TNS = "http://localhost/elementItemBuilderTestCase";

    @Test
    public void addTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = createElementNode();
        builder.add( node );
        final List<ElementNode> children = builder.getChildren();
        final ElementNode[] expected = new ElementNode[] { node };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, children.toArray() );
    }

    @Test
    public void addAtPositionTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = createElementNode();
        final ElementNode first = createElementNode( FIRST_NODE_NAME );
        builder.add( node );
        builder.add( 0, first );

        final List<ElementNode> children = builder.getChildren();
        final ElementNode expected = first;
        Assert.assertEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, children.get( 0 ) );
    }

    @Test
    public void addAllAtPositionWhenElementNodeTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = createElementNode();
        final ElementNode child = createElementNode( CHILD_NODE_NAME );
        final ElementNode first =
            new ElementNodeBuilder( FIRST_NODE_NAME, TNS )
                .add( child )
                .build();
        builder.add( node );
        builder.addAll( 0, first );

        final List<ElementNode> children = builder.getChildren();
        final ElementNode expected = child;
        Assert.assertEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, children.get( 0 ) );
    }

    @Test
    public void addAllSingleNodeTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = createElementNode();
        final List<ElementNode> nodes = Collections.singletonList( node );
        builder.addAll( nodes );
        final List<ElementNode> children = builder.getChildren();
        final ElementNode[] expected = new ElementNode[] { node };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, children.toArray() );
    }

    @Test
    public void addAllNodeWithChildrenTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode theChild = createElementNode();
        final ElementNode elementNode = create()
            .addAll( Collections.singletonList( theChild ) )
            .build();
        builder.addAll( elementNode );
        final List<ElementNode> children = builder.getChildren();
        final ElementNode[] expected = new ElementNode[] { theChild };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expected, children.toArray() );
    }

    @Test
    public void addAllNullTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = null;
        builder.addAll( node );
        final List<ElementNode> children = builder.getChildren();
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, children.size() );
    }

    @Test
    public void addAllAtPositionWhenElementIsNullTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = null;
        builder.addAll( 0, node );
        final List<ElementNode> children = builder.getChildren();
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, children.size() );
    }

    @Test
    public void buildTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, node );
    }

    @Test
    public void buildGetDocumentationTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, DOCUMENTATION, node.getDocumentation() );
    }

    @Test
    public void buildGetMaxOccursTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MAX_OCCURS, node.getMaxOccurs() );
    }

    @Test
    public void buildGetMinOccursTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MIN_OCCURS, node.getMinOccurs() );
    }

    @Test
    public void buildGetNameTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, NAME, node.getName() );
    }

    @Test
    public void buildGetNamespaceUriTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TNS, node.getNamespaceUri() );
    }

    @Test
    public void buildGetPrefixTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREFIX, node.getPrefix() );
    }

    @Test
    public void buildGetTypeTest() {
        final ElementNodeBuilder builder = create();
        final ElementNode node = builder.build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TYPE, node.getType() );
    }

    @Test
    public void constructorTest() {
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, builder );
    }

    @Test
    public void constructorWithArgumentsNameAndNamespaceTest() {
        final ElementNodeBuilder builder = new ElementNodeBuilder( NAME, TNS );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED,
            NAME.equals( builder.getName() )
                && TNS.equals( builder.getNamespaceUri() )
        );
    }

    @Test
    public void getDocumentationTest() {
        final ElementNodeBuilder builder = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, DOCUMENTATION, builder.getDocumentation() );
    }

    @Test
    public void getMaxOccursTest() {
        final ElementNodeBuilder builder = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MAX_OCCURS, builder.getMaxOccurs() );
    }

    @Test
    public void getMinOccursTest() {
        final ElementNodeBuilder builder = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MIN_OCCURS, builder.getMinOccurs() );
    }

    @Test
    public void getNameTest() {
        final ElementNodeBuilder builder = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, NAME, builder.getName() );
    }

    @Test
    public void getNamespaceUriTest() {
        final ElementNodeBuilder builder = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TNS, builder.getNamespaceUri() );
    }

    @Test
    public void getPrefixTest() {
        final ElementNodeBuilder builder = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREFIX, builder.getPrefix() );
    }

    @Test
    public void getTypeTest() {
        final ElementNodeBuilder builder = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TYPE, builder.getType() );
    }


    @Test
    public void isNotCyclicReferenceReturnsTrueTest() {
        final ElementNodeBuilder node = create();
        node.setCyclicReference( false );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, node.isNotCyclicReference() );
    }

    @Test
    public void isNotCyclicReferenceReturnsFalseTest() {
        final ElementNodeBuilder node = create();
        node.setCyclicReference( true );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, node.isNotCyclicReference() );
    }

    @Test
    public void setDocumentationIfEmptyWhenDocumentationIsEmptyTest() {
        final ElementNodeBuilder builder = create()
            .setDocumentation( StringUtil.EMPTY );
        builder.setDocumentationIfEmpty( ANOTHER_DOCUMENTATION );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, ANOTHER_DOCUMENTATION, builder.getDocumentation() );
    }

    @Test
    public void setDocumentationIfEmptyWhenDocumentationIsNotEmptyTest() {
        final String expected = "Hello world";
        final ElementNodeBuilder builder = create()
            .setDocumentation( expected );
        builder.setDocumentationIfEmpty( ANOTHER_DOCUMENTATION );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getDocumentation() );
    }

    @Test
    public void setNameTest() {
        final ElementNodeBuilder builder = create();
        builder.setName( ANOTHER_NAME );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, ANOTHER_NAME, builder.getName() );
    }

    @Test
    public void setNamespaceUriTest() {
        final String expected = "another/namespace/uri";
        final ElementNodeBuilder builder = create();
        builder.setNamespaceUri( expected );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getNamespaceUri() );
    }

    private ElementNodeBuilder create() {
        final ElementNodeBuilder builder = new ElementNodeBuilder( NAME, TNS );
        builder.setDocumentation( DOCUMENTATION );
        builder.setMinOccurs( MIN_OCCURS );
        builder.setMaxOccurs( MAX_OCCURS );
        builder.setPrefix( PREFIX );
        builder.setType( TYPE );
        return builder;
    }

    private ElementNode createElementNode() {
        return createElementNode( ANOTHER_NAME );
    }

    private ElementNode createElementNode( final String name ) {
        final ElementNodeBuilder builder = create();
        builder.setName( name );
        builder.setDocumentation( ANOTHER_DOCUMENTATION );
        return builder.build();
    }
}
