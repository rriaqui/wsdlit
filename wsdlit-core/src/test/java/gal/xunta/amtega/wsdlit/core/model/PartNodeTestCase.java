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
public class PartNodeTestCase
extends NodeTestCaseAbstract {
    @Test
    public void notEqualsToNullTest() {
        final PartNode node = createPartNode();
        final PartNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenDefinedByElementIsNotEqualsTest() {
        final PartNode node = createPartNode();
        final PartNode secondNode = createPartNode();
        secondNode.setDefinedByElement( !node.isDefinedByElement() );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenElementIsNotEqualsTest() {
        final PartNode node = createPartNode();
        final PartNode secondNode = createPartNode();
        final ElementNode element = createElementNode();
        element.setName( "anotherName" );
        secondNode.setElement( element );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenTypeIsNotEqualsTest() {
        final PartNode node = createPartNode();
        final PartNode secondNode = createPartNode();
        secondNode.setType( "xsd:boolean" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsSymetricTest() {
        final PartNode one = createPartNode();
        final PartNode two = createPartNode();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED,
            one.equals( two )
            && two.equals( one )
        );
    }

    @Test
    public void notNullTest() {
        final PartNode partNode = createPartNode();

        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, partNode );
    }

    @Test
    public void getNameTest() {
        final PartNode partNode = createPartNode();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PART_NAME, partNode.getName() );
    }

    @Test
    public void getNamespaceUriTest() {
        final PartNode partNode = createPartNode();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TNS, partNode.getNamespaceUri() );
    }

    @Test
    public void equalsToItselfTest() {
        final PartNode node = this.createPartNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, node, node );
    }

    @Test
    public void equalsToAnotherTest() {
        final PartNode node = this.createPartNode();
        final PartNode secondNode = this.createPartNode();
        final boolean value = node.equals( secondNode );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToDifferentClassTest() {
        final PartNode node = this.createPartNode();
        final String object = "Hello world";
        final boolean value = node.equals( object );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenSuperReturnsFalseTest() {
        final PartNode node = this.createPartNode();
        final PartNode secondNode = this.createPartNode();
        secondNode.setName( "another" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void getTypeTest() {
        final PartNode node = this.createPartNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TYPE_EXPECTED, node.getType() );
    }
}
