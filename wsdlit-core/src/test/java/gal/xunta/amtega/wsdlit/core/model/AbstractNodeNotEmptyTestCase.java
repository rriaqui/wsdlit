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

import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractNodeNotEmptyTestCase {
    private static final String DOCUMENTATION = "documentation";
    private static final String NAME = "name";
    private static final String PREFIX = "prefix";
    private static final String TARGETNAMESPACE = "http://localhost/targetnamespace";

    @Test
    public void constructorWithParamsGetNameTest() {
        final AbstractNode node = new AbstractNode( NAME, TARGETNAMESPACE ) {
        };
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, NAME, node.getName() );
    }

    @Test
    public void constructorWithParamsGetNamespaceUriTest() {
        final AbstractNode node = new AbstractNode( NAME, TARGETNAMESPACE ) {
        };
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TARGETNAMESPACE, node.getNamespaceUri() );
    }

    @Test
    public void isNotEqualsToDifferentClassTest() {
        final AbstractNode node = createAbstractNode();
        final boolean value = node.equals( 1 );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsItselfTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondary = node;

        Assert.assertSame( AssertMessages.SAME_EXPECTED, secondary, node );
    }

    @Test
    public void isNotEqualsToNullTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsToOtherInstanceSameDataTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondNode = createAbstractNode();
        final boolean value = node.equals( secondNode );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void isNotEqualsToOtherInstanceDifferentDocumentationTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondNode = createAbstractNode();
        secondNode.setDocumentation( StringUtil.EMPTY );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void isNotEqualsToOtherInstanceDifferentNameTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondNode = createAbstractNode();
        secondNode.setName( StringUtil.EMPTY );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void isNotEqualsToOtherInstanceWithDifferentPrefixTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondNode = createAbstractNode();
        secondNode.setPrefix( StringUtil.EMPTY );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void isNotEqualsToOtherInstanceWithDifferentTargetNamespaceTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondNode = createAbstractNode();
        secondNode.setNamespaceUri( StringUtil.EMPTY );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void getDocumentationTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, DOCUMENTATION, node.getDocumentation() );
    }

    @Test
    public void getFullNameTest() {
        final AbstractNode node = createAbstractNode();
        final String expected = PREFIX + ":" + NAME;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getFullName() );
    }

    @Test
    public void getNameTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, NAME, node.getName() );
    }

    @Test
    public void getNamespaceUriTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TARGETNAMESPACE, node.getNamespaceUri() );
    }

    @Test
    public void getPrefixTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREFIX, node.getPrefix() );
    }

    @Test
    public void getQnameTest() {
        final AbstractNode node = createAbstractNode();
        final String expected = "{" + TARGETNAMESPACE + "}" + NAME;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getQname() );
    }

    @Test
    public void compareToNullIsEqualsToOneTest() {
        final AbstractNode node = createAbstractNode();
        final int expected = 1;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( null ) );
    }

    @Test
    public void compareToItselfIsEqualsToZeroTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondary = node;
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( secondary ) );
    }

    @Test
    public void compareToOtherInstanceWithSameDataIsEqualsToZeroTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode another = createAbstractNode();
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( another ) );
    }

    @Test
    public void compareToOtherInstanceWithDifferentNameIsNotEqualsTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode another = createAbstractNode();
        another.setName( StringUtil.EMPTY );
        final int expected = 4;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( another ) );
    }

    @Test
    public void compareToOtherInstanceWithDifferentNamespaceIsNotEqualsTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode another = createAbstractNode();
        another.setNamespaceUri( StringUtil.EMPTY );
        final int expected = 32;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( another ) );
    }

    @Test
    public void hashCodeTest() {
        final AbstractNode node = createAbstractNode();
        final int expected = Objects.hash( NAME, TARGETNAMESPACE, DOCUMENTATION, PREFIX );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.hashCode() );
    }

    @Test
    public void hashCodeTwoInstancesWithSameDataHasSameValueTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode other = createAbstractNode();
        final int expected = other.hashCode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.hashCode() );
    }

    public AbstractNode createAbstractNode() {
        final AbstractNode node = new AbstractNode() {
        };
        node.setName( NAME );
        node.setNamespaceUri( TARGETNAMESPACE );
        node.setDocumentation( DOCUMENTATION );
        node.setPrefix( PREFIX );
        return node;
    }
}
