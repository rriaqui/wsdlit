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

import gal.xunta.amtega.wsdlit.core.diff.DiffItem;
import gal.xunta.amtega.wsdlit.core.sort.SortAlgorithms;

import java.util.*;

/**
 * An object representing the {@code service} element information of a wsdl document.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ServiceNode
extends AbstractNode {
    private String attributeFormDefault = "Unqualified";
    private String elementFormDefault = "Unqualified";
    private List<PortTypeNode> portTypes;

    private final List< PortTypeNode > unsortedPortTypes;
    private final List<NamespaceNode> namespaces = new ArrayList<>();
    private final List<DiffItem> differences = new ArrayList<>();

    /**
     * Constructs a {@code ServiceNode} with specified values for the name,
     * the namespace and the unsorted list of portTypes that it supports.
     *
     * @param name the name of the service (the value of the WSDL {@code name} attribute).
     * @param namespaceUri the namespace of the service.
     * @param unsortedPortTypes the unsorted  list of {@code PortTypeNode} supported by this service.
     */
    public ServiceNode( final String name, final String namespaceUri, final List<PortTypeNode> unsortedPortTypes ) {
        super( name, namespaceUri );
        this.unsortedPortTypes = Collections.unmodifiableList( Objects.requireNonNull( unsortedPortTypes ) );
        this.portTypes = new ArrayList<>( unsortedPortTypes );
    }

    /**
     * Appends the specified namespace to end of the list of namespaces.
     *
     * @param namespace the namespace to be appended to the list of namespaces.
     */
    public void add( final NamespaceNode namespace ) {
        this.namespaces.add( namespace );
    }

    /**
     * Appends the specified list of namespaces to end of the list of namespaces.
     *
     * @param namespaces the list of namespaces to be appended to the list of namespaces.
     */
    public void addAll( final Collection<NamespaceNode> namespaces ) {
        this.namespaces.addAll( namespaces );
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof ServiceNode ) ) return false;
        if ( !super.equals( o ) ) return false;
        final ServiceNode serviceNode = ( ServiceNode ) o;
        return
                attributeFormDefault.equals( serviceNode.attributeFormDefault )
                && elementFormDefault.equals( serviceNode.elementFormDefault )
                && portTypes.equals( serviceNode.portTypes )
                && namespaces.equals( serviceNode.namespaces )
                && differences.equals( serviceNode.differences );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(),
                this.attributeFormDefault,
                this.elementFormDefault,
                this.portTypes,
                this.namespaces,
                this.differences
        );
    }

    /**
     * Returns the value of the {@code attributeFormDefault} service schema.
     *
     * @return the value of the {@code attributeFormDefault} service schema.
     */
    public String getAttributeFormDefault() {
        return this.attributeFormDefault;
    }

    /**
     * Returns the value of the {@code elementFormDefault} service schema.
     *
     * @return the value of the {@code elementFormDefault} service schema.
     */
    public String getElementFormDefault() {
        return this.elementFormDefault;
    }

    /**
     * Returns the list of namespaces.
     *
     * @return the list of namespaces.
     */
    public List<NamespaceNode> getNamespaces() {
        return Collections.unmodifiableList( this.namespaces );
    }

    /**
     * Returns the list of {@code PortTypeNode} of the service.
     *
     * @return the list of {@code PortTypeNode} of the service.
     */
    public List<PortTypeNode> getPortTypes() {
        return this.portTypes;
    }

    /**
     * Sets the {@code attributeFormDefault} value.
     *
     * @param attributeFormDefault the {@code attributeFormDefault} value.
     */
    public void setAttributeFormDefault( final String attributeFormDefault ) {
        this.attributeFormDefault = attributeFormDefault;
    }

    /**
     * Sets the {@code elementFormDefault} value.
     *
     * @param elementFormDefault the {@code elementFormDefault} value.
     */
    public void setElementFormDefault( final String elementFormDefault ) {
        this.elementFormDefault = elementFormDefault;
    }

    /**
     * Returns the list of differencs detected between two versions of this service.
     * <p>
     *     This is a uncompleted feature, so it should not be used.
     * </p>
     *
     * @return the list of differencs detected between two versions of this service.
     */
    public List<DiffItem> getDifferences() {
        return Collections.unmodifiableList( this.differences );
    }

    /**
     * Sets the list of differencs detected between two versions of this service.
     * <p>
     *     This is a uncompleted feature, so it should not be used.
     * </p>
     *
     * @param differences the list of differencs detected between two versions of this service.
     */
    public void setDifferences( final List<DiffItem> differences ) {
        this.differences.clear();
        this.differences.addAll( differences );
    }

    /**
     * Sorts the list of ports for this service using the specified algorithm.
     *
     * @param algorithm the specified algorithm used to sort the list of ports for this service.
     *
     * @see SortAlgorithms
     */
    public synchronized void sort( final SortAlgorithms algorithm ) {
        this.portTypes = new ArrayList<>( this.unsortedPortTypes );
        if( algorithm == SortAlgorithms.SORT_BY_NAME ) {
            Collections.sort( this.portTypes );
        }

        for( final PortTypeNode port : this.unsortedPortTypes ) {
            port.sort( algorithm );
        }
    }
}
