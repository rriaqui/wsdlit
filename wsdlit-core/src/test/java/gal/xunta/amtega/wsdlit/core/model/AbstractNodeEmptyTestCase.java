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

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractNodeEmptyTestCase {
    @Test
    public void equalsToDifferentClassTest() {
        final AbstractNode node = createAbstractNode();
        final boolean value = node.equals( 1 );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsToItselfTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondary = node;

        Assert.assertSame( AssertMessages.SAME_EXPECTED, secondary, node );
    }

    @Test
    public void equalsToNullTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode unexpected = null;
        final boolean value = node.equals( unexpected );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsOtherInstanceSameDataTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode expected = createAbstractNode();
        final boolean value = node.equals( expected );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void getDocumentationTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, StringUtil.EMPTY, node.getDocumentation() );
    }

    @Test
    public void getFullNameTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, StringUtil.EMPTY, node.getFullName() );
    }

    @Test
    public void getNameTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, StringUtil.EMPTY, node.getName() );
    }

    @Test
    public void getNamespaceUriTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, StringUtil.EMPTY, node.getNamespaceUri() );
    }

    @Test
    public void getPrefixTest() {
        final AbstractNode node = createAbstractNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, StringUtil.EMPTY, node.getPrefix() );
    }

    @Test
    public void getQnameTest() {
        final AbstractNode node = createAbstractNode();
        final String expected = "{}";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getQname() );
    }

    @Test
    public void isCyclicReferenceReturnsFalseTest() {
        final AbstractNode node = createAbstractNode();
        node.setCyclicReference( false );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, node.isCyclicReference() );
    }

    @Test
    public void isCyclicReferenceReturnsTrueTest() {
        final AbstractNode node = createAbstractNode();
        node.setCyclicReference( true );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, node.isCyclicReference() );
    }

    @Test
    public void compareToNullTest() {
        final AbstractNode node = createAbstractNode();
        final int expected = 1;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( null ) );
    }

    @Test
    public void compareToItselfTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode secondary = node;
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( secondary ) );
    }

    @Test
    public void compareToOtherInstanceWhenBothAreEmptyTest() {
        final AbstractNode node = createAbstractNode();
        final AbstractNode another = createAbstractNode();
        final int expected = 0;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.compareTo( another ) );
    }

    public AbstractNode createAbstractNode() {
        return new AbstractNode() {
        };
    }
}
