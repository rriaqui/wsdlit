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

import com.predic8.wsdl.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OperationTestCaseAbstract {

    public Operation createOperationNotification() {
        final List<Message> messages = Collections.singletonList( createOutputMessage() );
        return createOperation( messages, null, createOutput() );
    }

    public Operation createOperationUnknown() {
        return createOperation( Collections.emptyList(), null, null );
    }

    public Operation createOperationOneWay() {
        final List<Message> messages = Collections.singletonList( createInputMessage() );
        return createOperation( messages, createInput(), null );
    }

    public Operation createOperationRequestResponse() {
        final List< Message > messages = Arrays.asList(
            createInputMessage(),
            createOutputMessage()
        );
        return createOperation( messages, createInput(), createOutput() );
    }

    public Operation createOperationSolicitResponse() {
        final List< Message> messages = Arrays.asList( createOutputMessage(), createInputMessage() );
        return createOperation( messages, createInput(), createOutput() );
    }

    public Operation createOperation( final List<Message> messages, final Input input, final Output output ) {
        final Definitions definitions = Mockito.mock( Definitions.class );
        final Operation operation = Mockito.mock( Operation.class );
        Mockito.when( operation.getInput() ).thenReturn( input );
        Mockito.when( operation.getOutput() ).thenReturn( output );
        Mockito.when( operation.getDefinitions() ).thenReturn( definitions );
        Mockito.when( definitions.getMessages() ).thenReturn( messages );
        Mockito.when( operation.getName() ).thenReturn( "multiply" );
        return operation;
    }

    public Message createMessage( final String name ) {
        final Message message = new Message();
        message.setName( name );
        return message;
    }

    public Input createInput() {
        final Input input = new Input();
        input.setName( "input" );
        return input;
    }

    public Message createInputMessage() {
        final Message message = new Message();
        message.setName( "input" );
        return message;
    }

    public Output createOutput() {
        final Output output = new Output();
        output.setName( "output" );
        return output;
    }

    public Message createOutputMessage() {
        final Message message = new Message();
        message.setName( "output" );
        return message;
    }
}
