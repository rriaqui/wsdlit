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
public class OperationNodeTestCase
extends ModelTestCaseAbstract {
    @Test
    public void getFaultsMessageTest() {
        final MessageNode fault = createFaultMessage();
        final OperationNode node = createOperationNode();
        final List<MessageNode> faultsExpected = Collections.singletonList( createFaultMessage() );
        node.add( fault );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, faultsExpected, node.getFaults() );
    }

    @Test
    public void getInputMessageTest() {
        final MessageNode message = createInputMessage();
        final OperationNode node = createOperationNode();
        final MessageNode expected = createInputMessage();
        node.setInputMessage( message );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getInputMessage() );
    }

    @Test
    public void getOutputMessageTest() {
        final MessageNode message = createOutputMessage();
        final MessageNode expected = createOutputMessage();
        final OperationNode node = createOperationNode();
        node.setOutputMessage( message );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getOutputMessage() );
    }

    @Test
    public void hashCodeTest() {
        final MessageNode fault = createFaultMessage();
        final MessageNode input = createInputMessage();
        final MessageNode output = createOutputMessage();
        final OperationNode node = createOperationNode();
        final AbstractNode abstractNode = createAbstractNode( node );
        node.setInputMessage( input );
        node.setOutputMessage( output );
        node.add( fault );
        final int expected = Objects.hash(
                abstractNode.hashCode(),
                input,
                output,
                Collections.singletonList( fault ),
                OperationType.REQUEST_RESPONSE
        );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.hashCode() );
    }

    @Test
    public void setTypeWhenTypeIsOneWayTest() {
        final OperationNode node = createOperationNode();
        final OperationType expected = OperationType.ONE_WAY;
        node.setType( OperationType.ONE_WAY );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getType() );
    }

    @Test
    public void setTypeWhenTypeIsRequestResponseTest() {
        final OperationNode node = createOperationNode();
        final OperationType expected = OperationType.REQUEST_RESPONSE;
        node.setType( OperationType.REQUEST_RESPONSE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getType() );
    }

    @Test
    public void setTypeWhenTypeIsSolicitResponseTest() {
        final OperationNode node = createOperationNode();
        final OperationType expected = OperationType.SOLICIT_RESPONSE;
        node.setType( OperationType.SOLICIT_RESPONSE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getType() );
    }

    @Test
    public void setTypeWhenTypeIsNotificationTest() {
        final OperationNode node = createOperationNode();
        final OperationType expected = OperationType.NOTIFICATION;
        node.setType( OperationType.NOTIFICATION );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getType() );
    }

    @Test
    public void setTypeWhenTypeIsUnknownTest() {
        final OperationNode node = createOperationNode();
        final OperationType expected = OperationType.UNKNOWN;
        node.setType( OperationType.UNKNOWN );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node.getType() );
    }
}
