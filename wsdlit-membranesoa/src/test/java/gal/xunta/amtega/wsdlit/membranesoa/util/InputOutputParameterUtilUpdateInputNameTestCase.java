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

import com.predic8.wsdl.Input;
import com.predic8.wsdl.Operation;
import gal.xunta.amtega.wsdlit.core.model.OperationType;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

public class InputOutputParameterUtilUpdateInputNameTestCase
extends OperationTestCaseAbstract {

    @Test
    public void updateInputParameterNameWhenInputIsNullTest() {
        final Operation operation = createOperationUnknown();
        final OperationType operationType = OperationType.UNKNOWN;
        InputOutputParameterUtil.updateInputParameterName( null, operationType, operation );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, operation.getInput() );
    }

    @Test
    public void updateInputParameterNameWhenInputIsNotNullButNameIsNullTest() {
        final Input input = createInput();
        final Operation operation = createOperationUnknown();
        final OperationType operationType = OperationType.UNKNOWN;
        input.setName( null );
        InputOutputParameterUtil.updateInputParameterName( input, operationType, operation );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, operation.getInput() );
    }

    @Test
    public void updateInputParameterNameWhenOperationIsRequestResponseAndNameIsNullTest() {
        final Operation operation = createOperationRequestResponse();
        final Input input = operation.getInput();
        final OperationType ote = OperationType.REQUEST_RESPONSE;
        final String expected = "multiplyRequest";

        input.setName( null );
        InputOutputParameterUtil.updateInputParameterName( input, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, input.getName() );
    }

    @Test
    public void updateInputParameterNameWhenOperationIsRequestResponseAndNameIsNotNullTest() {
        final Operation operation = createOperationRequestResponse();
        final Input input = operation.getInput();
        final OperationType ote = OperationType.SOLICIT_RESPONSE;
        final String expected = "input";

        InputOutputParameterUtil.updateInputParameterName( input, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, input.getName() );
    }

    @Test
    public void updateInputParameterNameWhenOperationIsSolicitResponseAndNameIsNullTest() {
        final Operation operation = createOperationSolicitResponse();
        final Input input = operation.getInput();
        final OperationType ote = OperationType.SOLICIT_RESPONSE;
        final String expected = "multiplySolicit";

        input.setName( null );
        InputOutputParameterUtil.updateInputParameterName( input, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, input.getName() );
    }

    @Test
    public void updateInputParameterNameWhenOperationIsSolicitResponseAndNameIsNotNullTest() {
        final Operation operation = createOperationSolicitResponse();
        final Input input = operation.getInput();
        final OperationType ote = OperationType.SOLICIT_RESPONSE;
        final String expected = "input";

        InputOutputParameterUtil.updateInputParameterName( input, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, input.getName() );
    }

    @Test
    public void updateInputParameterNameWhenOperationIsNotificationAndNameIsNullTest() {
        final Operation operation = createOperationNotification();
        final Input input = operation.getInput();
        final OperationType ote = OperationType.NOTIFICATION;

        InputOutputParameterUtil.updateInputParameterName( input, ote, operation );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, operation.getInput() );
    }

    @Test
    public void updateInputParameterNameWhenOperationIsOneWayAndNameIsNullTest() {
        final Operation operation = createOperationOneWay();
        final Input input = operation.getInput();
        final OperationType ote = OperationType.ONE_WAY;
        final String expected = "multiply";

        input.setName( null );
        InputOutputParameterUtil.updateInputParameterName( input, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, input.getName() );
    }

    @Test
    public void updateInputParameterNameWhenOperationIsOneWayAndNameIsNotNullTest() {
        final Operation operation = createOperationOneWay();
        final Input input = operation.getInput();
        final OperationType ote = OperationType.ONE_WAY;
        final String expected = "input";

        InputOutputParameterUtil.updateInputParameterName( input, ote, operation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, input.getName() );
    }
}
