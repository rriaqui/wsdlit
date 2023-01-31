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
import gal.xunta.amtega.wsdlit.core.model.OperationType;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

public class OperationUtilTestCase
extends OperationTestCaseAbstract {

    @Test
    public void computeOperationTypeIsNotificationTest() {
        final OperationType expected = OperationType.NOTIFICATION;
        final Operation operation = createOperationNotification();
        final OperationType computed = OperationUtil.computeOperationType( operation );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, computed );
    }

    @Test
    public void computeOperationTypeIsOneWayTest() {
        final OperationType expected = OperationType.ONE_WAY;
        final Operation operation = createOperationOneWay();
        final OperationType computed = OperationUtil.computeOperationType( operation );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, computed );
    }

    @Test
    public void computeOperationTypeIsRequestResponseTest() {
        final OperationType expected = OperationType.REQUEST_RESPONSE;
        final Operation operation = createOperationRequestResponse();
        final OperationType computed = OperationUtil.computeOperationType( operation );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, computed );
    }

    @Test
    public void computeOperationTypeIsSolicitResponseTest() {
        final OperationType expected = OperationType.SOLICIT_RESPONSE;
        final Operation operation = createOperationSolicitResponse();
        operation.newInput( "input" );
        final OperationType computed = OperationUtil.computeOperationType( operation );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, computed );
    }

    @Test
    public void computeOperationTypeIsUnknownTest() {
        final OperationType expected = OperationType.UNKNOWN;
        final Operation operation = createOperationUnknown();
        final OperationType computed = OperationUtil.computeOperationType( operation );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, computed );
    }
}
