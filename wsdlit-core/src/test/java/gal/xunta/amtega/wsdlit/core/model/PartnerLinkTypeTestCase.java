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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class PartnerLinkTypeTestCase
extends NodeTestCaseAbstract {
    @Test
    public void getDocumentationTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final String expected = "Nome de clase: PartnerLinkTypeNode";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getDocumentation() );
    }

    @Test
    public void getRolesTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final List< RoleNode > expected = Collections.singletonList( this.createRoleNode() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getRoles() );
    }

    @Test
    public void hashCodeTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final int hashCodeParent = Objects.hash( node.getName(), node.getNamespaceUri(), node.getDocumentation(), node.getPrefix() );
        final int expected = Objects.hash( node.getRoles(), hashCodeParent );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.hashCode() );
    }

    @Test
    public void notEqualsToDifferentTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final PartnerLinkTypeNode secondNode = new PartnerLinkTypeNode();
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsToItselfTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final PartnerLinkTypeNode secondary = node;
        final boolean value = node.equals( secondary );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsToAnotherTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final PartnerLinkTypeNode secondNode = this.createPartnerLinkTypeNode();
        final boolean value = node.equals( secondNode );
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, value );
    }

    @Test
    public void notEqualsToNullTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final PartnerLinkTypeNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void notEqualsToOtherObjectTest() {
        final PartnerLinkTypeNode node = this.createPartnerLinkTypeNode();
        final String object = "Hello, world";
        final boolean value = node.equals( object );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }
}
