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

import gal.xunta.amtega.wsdlit.core.sort.SortAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An object representing the {@code portType} element information of a wsdl document.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class PortTypeNode
extends AbstractNode {
    private final List< OperationNode > unsortedOperations;
    private List< OperationNode > operations;

    /**
     * Constructs a {@code PortTypeNode} with the list of operations it supports.
     *
     * @param unsortedOperations the list of operations it supports.
     */
    public PortTypeNode( final List<OperationNode> unsortedOperations ) {
        this.unsortedOperations = Objects.requireNonNull( unsortedOperations );
        this.operations = new ArrayList<>( unsortedOperations );
    }

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

        final PortTypeNode that = ( PortTypeNode ) o;
        return operations.equals( that.operations );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), operations );
    }

    /**
     * Returns the port interface.
     *
     * @return a interface do porto como {@code namespace{name}}.
     */
    public String getInterface() {
        return this.getNamespaceUri() + "{" + this.getName() + "}";
    }

    /**
     * Returns the list of {@code OperationNode} this port supports.
     *
     * @return the list of {@code OperationNode} this port supports.
     */
    public List<OperationNode> getOperations() {
        return Collections.unmodifiableList( this.operations );
    }

    /**
     * Sorts the list of operations supported by this port
     * with the specified sort algorithm.
     *
     * @param algorithm the sort algorithm to apply.
     *
     * @see SortAlgorithms
     */
    public synchronized void sort( final SortAlgorithms algorithm ) {
        this.operations = new ArrayList<>( this.unsortedOperations );
        if ( algorithm == SortAlgorithms.SORT_BY_NAME ) {
            Collections.sort( this.operations );
        }
    }
}
