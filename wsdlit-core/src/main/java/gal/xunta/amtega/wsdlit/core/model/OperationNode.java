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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An object that models an operation associated with a port of a service defined in a wsdl document.
 *
 * @author rriaqui
 * @since 1.0
 */
public class OperationNode
extends AbstractNode {
    private MessageNode input = null;
    private MessageNode output = null;
    private final List<MessageNode> faults = new ArrayList<>();
    private OperationType type = OperationType.UNKNOWN;

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if (
            o == null
                || this.getClass() != o.getClass()
                || ! super.equals( o )
        ) {
            return false;
        }

        final OperationNode that = ( OperationNode ) o;
        return
            Objects.equals( input, that.input )
                && Objects.equals( output, that.output )
                && faults.equals( that.faults )
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), input, output, faults, type );
    }

    /**
     * Appends the specified {@code Fault} message to the end of the {@code Fault} message list.
     *
     * @param fault the message to add.
     */
    public void add( final MessageNode fault ) {
        this.faults.add( fault );
    }

    /**
     * Returns the {@code Fault} message list {@code Fault} supported by this operation.
     *
     * @return unha lista con mensaxes de {@code Fault}.
     */
    public List<MessageNode> getFaults() {
        return this.faults;
    }

    /**
     * Returns the input {@code MessageNode} if setted, or {@code null} otherwise.
     *
     * @return the input {@code MessageNode} if setted, or {@code null} otherwise.
     */
    public MessageNode getInputMessage() {
        return this.input;
    }

    /**
     * Returns the output {@code MessageNode} if setted, or {@code null} otherwise.
     *
     * @return the output {@code MessageNode} if setted, or {@code null} otherwise.
     */
    public MessageNode getOutputMessage() {
        return this.output;
    }

    /**
     * Returns the operation type following WSDL specification.
     *
     * @return the operation type following WSDL specification.
     */
    public OperationType getType() {
        return this.type;
    }

    /**
     * Set the input {@code MessageNode}.
     *
     * @param input the input {@code MessageNode}.
     */
    public void setInputMessage( final MessageNode input ) {
        this.input = input;
    }

    /**
     * Set the output {@code MessageNode}.
     *
     * @param output the input {@code MessageNode}.
     */
    public void setOutputMessage( final MessageNode output ) {
        this.output = output;
    }

    /**
     * Sets the operation type following WSDL specification.
     *
     * @param type the operation type following WSDL specification.
     */
    public void setType( final OperationType type ) {
        this.type = type;
    }
}
