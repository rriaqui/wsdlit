package gal.xunta.amtega.wsdlit.membranesoa.util;

/*-
 * #%L
 * wsdlit-membranesoa
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

import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Output;
import gal.xunta.amtega.wsdlit.core.model.OperationType;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

public class InputOutputParameterUtilUpdateOutputNameTestCase
extends OperationTestCaseAbstract {

    @Test
    public void updateOutputParameterNameWhenOutputIsNullTest() {
        final Operation operation = createOperationUnknown();
        InputOutputParameterUtil.updateOutputParameterName( null, OperationType.UNKNOWN, operation );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, operation.getOutput() );
    }

    @Test
    public void updateOutputParameterNameWhenOutputIsNotNullButNameIsNullTest() {
        final Operation operation = createOperationUnknown();
        final Output output = createOutput();
        output.setName( null );
        InputOutputParameterUtil.updateOutputParameterName( output, OperationType.UNKNOWN, operation );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, operation.getOutput() );
    }

    @Test
    public void updateOutputParameterNameWhenOperationIsRequestResponseAndNameIsNullTest() {
        final Operation operation = createOperationRequestResponse();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.REQUEST_RESPONSE;
        final String expected = "multiplyResponse";

        output.setName( null );
        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, output.getName() );
    }

    @Test
    public void updateOutputParameterNameWhenOperationIsRequestResponseAndNameIsNotNullTest() {
        final Operation operation = createOperationRequestResponse();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.REQUEST_RESPONSE;
        final String expected = "output";

        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, output.getName() );
    }


    @Test
    public void updateOutputParameterNameWhenOperationIsSolicitResponseAndNameIsNullTest() {
        final Operation operation = createOperationSolicitResponse();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.SOLICIT_RESPONSE;
        final String expected = "multiplyResponse";

        output.setName( null );
        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, output.getName() );
    }

    @Test
    public void updateOutputParameterNameWhenOperationIsSolicitResponseAndNameIsNotNullTest() {
        final Operation operation = createOperationSolicitResponse();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.SOLICIT_RESPONSE;
        final String expected = "output";

        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, output.getName() );
    }

    @Test
    public void updateOutputParameterNameWhenOperationIsNotificationAndNameIsNullTest() {
        final Operation operation = createOperationNotification();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.NOTIFICATION;
        final String expected = "multiply";

        output.setName( null );
        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, output.getName() );
    }

    @Test
    public void updateOutputParameterNameWhenOperationIsNotificationAndNameIsNotNullTest() {
        final Operation operation = createOperationNotification();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.NOTIFICATION;
        final String expected = "output";

        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, output.getName() );
    }

    @Test
    public void updateOutputParameterNameWhenOperationIsOneWayAndNameIsNullTest() {
        final Operation operation = createOperationOneWay();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.ONE_WAY;

        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, operation.getOutput() );
    }

    @Test
    public void updateOutputParameterNameWhenOperationIsOneWayAndNameIsNotNullTest() {
        final Operation operation = createOperationOneWay();
        final Output output = operation.getOutput();
        final OperationType ote = OperationType.ONE_WAY;

        InputOutputParameterUtil.updateOutputParameterName( output, ote, operation );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, operation.getOutput() );
    }
}
