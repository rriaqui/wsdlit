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
public class RoleNodeTestCase
extends ModelTestCaseAbstract {
    @Test
    public void equalsToItselfTest() {
        final RoleNode node = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, node, node );
    }

    @Test
    public void equalsToSameTest() {
        final RoleNode node = create();
        final RoleNode secondary = node;
        Assert.assertSame( AssertMessages.SAME_EXPECTED, node, secondary );
    }

    @Test
    public void equalsReturnsFalseWhenNullTest() {
        final RoleNode node = create();
        final RoleNode secondNode = null;
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenDifferentClassTest() {
        RoleNode node = create();
        final boolean value = node.equals( 1 );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenSuperReturnsFalseTest() {
        RoleNode node = create();
        RoleNode secondNode = create();

        secondNode.setName( "anotherName" );
        final boolean value = node.equals( secondNode );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, value );
    }

    @Test
    public void equalsToSymetricTest() {
        final RoleNode one = create();
        final RoleNode two = create();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED,
            one.equals( two )
            && two.equals( one )
        );
    }

    @Test
    public void getOperationTest() {
        final RoleNode one = create();
        final OperationNode expected = createOperationNode();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, one.getOperation() );
    }

    @Test
    public void hashCodeWhenTwoInstancesContainsSameDataTest() {
        final RoleNode one = create();
        final RoleNode two = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, one.hashCode(), two.hashCode() );
    }

    private RoleNode create() {
        return new RoleNode().setOperation( createOperationNode() );
    }
}
