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

import java.util.List;
import java.util.Objects;

/**
 * Class that models the {@code ComplexType}, {@code SimpleType} and {@code element} in XSD,
 * and it is aimed at simplifying the modeling of XML data structures-
 *
 * @author rriaqui
 * @since 1.0
 */
public class ElementNode
extends AbstractNode {
    public static final ElementNode NULL_ELEMENT = new ElementNodeBuilder().build();
    private final List<ElementNode> children;
    private final String minOccurs;
    private final String maxOccurs;
    private final String occurrences;
    private final String type;

    /**
     * Constructs a {@code ElementNode} from the specified {@code ElementNodeBuilder}.
     *
     * @param builder the {@code ElementNodeBuilder}.
     */
    protected ElementNode( final ElementNodeBuilder builder ) {
        super( builder.name, builder.namespaceUri);
        this.children = builder.getChildren();
        this.minOccurs = builder.minOccurs;
        this.maxOccurs = builder.maxOccurs;
        this.setPrefix( builder.prefix );
        this.type = builder.type;
        this.occurrences = this.computeOccurrences();
        this.setDocumentation( builder.documentation );
        this.setCyclicReference( builder.cyclicReference );
    }

    /*
     * Computes the textual representation of occurrences.
     *
     * <p>
     *      The output will be similar to:
     * </p>
     *
     * <ul>
     *      <li>{@code 4}: when {@code minOccurs = maxOccurs = 4}</li>
     *      <li>{@code 0..4}: when {@code minOccurs = 0} and {@code maxOccurs = 4}.</li>
     *      <li>{@code 0..unbound}: when {@code minOccurs=0} and {@code maxOccurs = unbound}.</li>
     * </ul>
     *
     * @return the textual representation of occurrences.
     */
    private String computeOccurrences() {
        if ( this.minOccurs.equals( this.maxOccurs ) ) {
            return this.minOccurs;
        }
        return
            this.minOccurs
            + ".."
            + ( ( "unbounded".equals( this.maxOccurs ) ) ? "\u221e" : this.maxOccurs );
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if (
            o == null
            || this.getClass() != o.getClass()
            || !super.equals( o )
        ) {
            return false;
        }
        final ElementNode that = ( ElementNode ) o;
        return
                minOccurs.equals( that.minOccurs )
                && maxOccurs.equals( that.maxOccurs )
                && type.equals( that.type )
                && children.equals( that.children )
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), children, minOccurs, maxOccurs, type );
    }

    /**
     * Returns the node list.
     *
     * @return the node list.
     */
    public final List<ElementNode> getChildren() {
        return this.children;
    }

    /**
     * Returns the maximum number of ocurrences.
     *
     * @return the maximum number of ocurrences.
     */
    public String getMaxOccurs() {
        return this.maxOccurs;
    }

    /**
     * Returns the minimum number of ocurrences.
     *
     * @return the minimum number of ocurrences.
     */
    public String getMinOccurs() {
        return this.minOccurs;
    }

    /**
     * Returns the textual representation of occurrences.
     *
     * @return the textual representation of occurrences.
     * @see #computeOccurrences()
     */
    public String getOccurrences() {
        return this.occurrences;
    }

    /**
     * Returns the data type.
     *
     * @return the data type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the child of the specified position in the children list.
     *
     * @param index position of the child in the children list.
     * @return the child of the specified position in the children list.
     */
    public ElementNode get( final int index ) {
        return this.children.get( index );
    }

    /**
     * Returns the direct number of children in the children list.
     *
     * @return the direct number of children in the children list.
     */
    public int size() {
        return this.children.size();
    }

    /**
     * Returns the total number of children in the children list.
     *
     * @return the total number of children in the children list.
     */
    public int totalSize() {
        int totalSize = this.size();
        for( final ElementNode child : this.children ) {
            totalSize += child.totalSize();
        }
        return totalSize;
    }

    /**
     * Returns {@code true} when the children list has no entries.
     *
     * @return {@code true} when the children list has no entries.
     */
    public boolean isEmpty() {
        return this.children.isEmpty();
    }

    /**
     * Returns {@code false} when the children list has no entries.
     *
     * @return {@code false} when the children list has no entries.
     */
    public boolean isNotEmpty() {
        return ! this.children.isEmpty();
    }

    /**
     * Returns the first child in the children list whose name matches the specified one.
     *
     * @param name the name of the node to find.
     * @return the first child in the children list whose name matches the specified one.
     */
    public ElementNode findByName( final String name ) {
        final ElementNode node = this.children
                .stream()
                .filter( item -> item.getName().equals( name ) )
                .findAny()
                .orElse(null );
        if ( node == null ) {
            for( final ElementNode child: this.children ) {
                final ElementNode element = child.findByName( name );
                if ( element != null ) {
                    return element;
                }
            }
        }
        return node;
    }

    /**
     * Returns {@code true} if the children list contains an element with the specified name.
     *
     * @param name the name of the element whose presence in the children list is to be tested.
     * @return {@code true} if the children list contains an element with the specified name.
     */
    public boolean containsName( final String name ) {
        return findByName( name ) != null;
    }

    /**
     * Returns {@code true} when it represents an {@code AbstractModelGroup}.
     *
     * @return {@code true} when it represents an {@code AbstractModelGroup}.
     */
    public boolean isModelGroup() {
        return false;
    }
}
