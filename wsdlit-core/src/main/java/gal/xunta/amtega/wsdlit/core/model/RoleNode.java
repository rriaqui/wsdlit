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

import java.util.Objects;

/**
 * An object representing the {@code role} element information of a wsdl document.
 *
 * @author rriaqui
 * @since 1.0
 */
public class RoleNode
extends AbstractNode {
    private OperationNode operation = null;

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

        final RoleNode roleNode = ( RoleNode ) o;
        return operation.equals( roleNode.operation );
    }

    /**
     * Returns the {@code OperationNode}.
     *
     * @return the {@code OperationNode}.
     */
    public OperationNode getOperation() {
        return this.operation;
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), operation );
    }

    /**
     * Sets the {@code OperationNode}.
     *
     * @param operation the {@code OperationNode}.
     * @return a reference to this {@code RoleNode}.
     */
    public RoleNode setOperation( final OperationNode operation ) {
        this.operation = operation;
        return this;
    }
}
