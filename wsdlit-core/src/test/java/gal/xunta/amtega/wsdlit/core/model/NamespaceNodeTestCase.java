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
import gal.xunta.amtega.wsdlit.core.util.FormDefault;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class NamespaceNodeTestCase
extends ModelTestCaseAbstract {
    @Test
    public void hashCodeIsEqualsTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondNode = createNamespaceNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, secondNode.hashCode(), node.hashCode() );
    }

    @Test
    public void hashCodeIsNotEqualsTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondNode = createNamespaceNode().setVersion( "45");
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsToItselfTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondary = node;
        final boolean value = node.equals( secondary );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void notEqualsToNullTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondNode = createNamespaceNode();
        final boolean value = node.equals( secondNode );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenAttributeFormDefaultIsDifferentTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondNode = createNamespaceNode();
        secondNode.setAttributeFormDefault( "unqualified" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenElementFormDefaultIsDifferentTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondNode = createNamespaceNode();
        secondNode.setElementFormDefault( "unqualified" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenVersionIsDifferentTest() {
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode secondNode = createNamespaceNode();
        secondNode.setVersion( "4.0.0" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void getAttributeFormDefaultIsTriStateFalseTest() {
        final NamespaceNode node = this.createNamespaceNode();
        node.setAttributeFormDefault( "unqualified" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.UNQUALIFIED, node.getAttributeFormDefault() );
    }

    @Test
    public void getElementFormDefaultIsTriStateFalseTest() {
        final NamespaceNode node = this.createNamespaceNode();
        node.setElementFormDefault( "unqualified" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.UNQUALIFIED, node.getElementFormDefault() );
    }

    @Test
    public void getAttributeFormDefaultIsTriStateTrueTest() {
        final NamespaceNode node = this.createNamespaceNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.QUALIFIED, node.getAttributeFormDefault() );
    }

    @Test
    public void getElementFormDefaultIsTriStateTrueTest() {
        final NamespaceNode node = this.createNamespaceNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.QUALIFIED, node.getElementFormDefault() );
    }

    @Test
    public void getAttributeFormDefaultIsTriStateUnsetTest() {
        final NamespaceNode node = this.createNamespaceNode();
        node.setAttributeFormDefault( "unset" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.NONE, node.getAttributeFormDefault() );
    }

    @Test
    public void getElementFormDefaultIsTriStateUnsetTest() {
        final NamespaceNode node = this.createNamespaceNode();
        node.setElementFormDefault( "unset" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.NONE, node.getElementFormDefault() );
    }

    @Test
    public void getLongVersionTest() {
        final String expected = "v4";
        final NamespaceNode node = this.createNamespaceNode();
        node.setVersion( "4" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getLongVersion() );
    }

    @Test
    public void getLongVersionIsEmptyTest() {
        final NamespaceNode node = this
            .createNamespaceNode()
            .setVersion( "" );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, node.getLongVersion().isEmpty() );
    }

    @Test
    public void getVersionTest() {
        final String expected = "3";
        final NamespaceNode node = this.createNamespaceNode();
        node.setVersion( "3" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getVersion() );
    }

    @Test
    public void setVersionWhenVersionIsNullTest() {
        final String expected = StringUtil.EMPTY;
        final NamespaceNode node = this.createNamespaceNode();
        node.setVersion( null );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getVersion() );

    }

    @Test
    public void equalsToAnotherTest() {
        final NamespaceNode node = this.createNamespaceNode();
        final NamespaceNode secondNode = this.createNamespaceNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, secondNode, node );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToDifferentClassTest() {
        final NamespaceNode node = this.createNamespaceNode();
        final String object = "Hello world";
        final boolean value = node.equals( object );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenSuperReturnsFalseTest() {
        final NamespaceNode node = this.createNamespaceNode();
        final NamespaceNode secondNode = this.createNamespaceNode();
        secondNode.setName( "another" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }
}
