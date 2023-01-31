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
import com.predic8.wsdl.Message;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Output;
import gal.xunta.amtega.wsdlit.core.model.OperationType;

import java.util.List;

/**
 * Utilities related to the membranesoa {@code Operation} class.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class OperationUtil {
    private OperationUtil() {}

    /**
     * Returns the type of the specified operation, according to the standards:
     * {@code NOTIFICATION}, {@code ONE_WAY}, {@code SOLICIT_RESPONSE}, {@code REQUEST_RESPONSE},
     * or {@code UNKNOWN} if it cannot be determined.
     *
     * @param operation the specified operation whose operation type is to be guessed.
     * @return the type of the specified operation, according to the standards:
     */
    public static OperationType computeOperationType( final Operation operation ) {
        final Input input = operation.getInput();
        final Output output = operation.getOutput();
        if ( input == null && output == null ) {
            return OperationType.UNKNOWN;
        }
        if ( input == null ) {
            return OperationType.NOTIFICATION;
        }
        if ( output == null ) {
            return OperationType.ONE_WAY;
        }
        final List<Message> messages = operation.getDefinitions().getMessages();
        final Message firstMessage = messages.get( 0 );

        if ( firstMessage.getName().equals( output.getName() ) ) {
            return OperationType.SOLICIT_RESPONSE;
        }
        return OperationType.REQUEST_RESPONSE;
    }
}
