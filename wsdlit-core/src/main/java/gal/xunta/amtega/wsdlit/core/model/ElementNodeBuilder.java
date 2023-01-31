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

import gal.xunta.amtega.wsdlit.core.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@code ElementNodeBuilder} is used to build instances of {@code ElementNode} from values configured by the {@code setters}.
 *
 * @author rriaqui
 * @since 1.0
 */
public class ElementNodeBuilder {
    /*
     * List of child nodes.
     */
    private final List<ElementNode> children = new ArrayList<>();

    /*
     * Minimum number of occurrences.
     * Supports a numeric value &gt;= 0 and the word <strong>unbound</strong>.
     * The default value is {@code 1}.
     */
    protected String minOccurs = String.valueOf( 1 );

    /*
     * Maximum number of occurrences.
     * Supports a numeric value &gt;= 0 and the word <strong>unbound</strong>.
     * The default value is {@code 1}.
     */
    protected String maxOccurs = String.valueOf( 1 );

    /*
     * The name.
     */
    protected String name;

    /*
     * The namespace.
     */
    protected String namespaceUri;

    /*
     * The prefix.
     * The default value is {@code -}.
     */
    protected String prefix = "-";

    /*
     * The data type.
     * The default value is {@code -}.
     */
    protected String type = "-";

    /*
     * The documentation.
     * The default value is {@code ""}.
     */
    protected String documentation = "";

    /*
     * {@code true} when the node is part of a cyclic structure.
     * O valor por defecto é {@code false}.
     */
    protected boolean cyclicReference = false;

    /**
     * Build a {@code ElementNodeBuilder} with the default values.
     */
    public ElementNodeBuilder() {}

    /**
     * Constructs a builder instance from the name and namespace of the element.
     *
     * @param name the name of the element.
     * @param namespaceUri the namespace of the element.
     */
    public ElementNodeBuilder( final String name, final String namespaceUri ) {
        this.name = name;
        this.namespaceUri = namespaceUri;
    }

    /**
     * Appends a child at the end of the child nodes list.
     *
     * @param child the child to append.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder add( final ElementNode child ) {
        this.children.add( child );
        return this;
    }

    /**
     * Inserts the specified child at specified position in the list of child nodes.
     *
     * @param index index at which the specified child is to be inserted.
     * @param child the child to insert.
     * @return a reference to this builder.
     *
     */
    public ElementNodeBuilder add( final int index, final ElementNode child ) {
        this.children.add( index, child );
        return this;
    }

    /**
     * Appends all the child nodes from the specified {@code ElementNode} at the end of the list of child nodes.
     *
     * @param parent The {@code ElementNode} whose children we want to append to the end of the list of child nodes.     * @return unha referencia a este builder.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder addAll( final ElementNode parent ) {
        if ( parent != null ) {
            this.addAll( parent.getChildren() );
        }
        return this;
    }

    /**
     * Inserts the child nodes of the specified {@code ElementNode} at specified position in the list of child nodes.
     *
     * @param index index at which the child nodes of the specified {@code ElementNode} is to be inserted.
     * @param parent The {@code ElementNode} whose children we want to insert to the specified position of the list of child nodes.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder addAll( final int index, final ElementNode parent ) {
        if ( parent != null ) {
            this.addAll( index, parent.getChildren() );
        }
        return this;
    }

    /**
     * Appends a list of child nodes at the end of the child nodes list.
     *
     * @param children the list of child nodes to append.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder addAll( final List<ElementNode> children ) {
        this.children.addAll( children );
        return this;
    }

    /**
     * Appends a list of child nodes at the specified position of the child nodes list.
     *
     * @param index index at which the child nodes of the specified {@code ElementNode} is to be inserted.
     * @param children the list of child nodes to append.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder addAll( final int index, final List< ElementNode > children ) {
        this.children.addAll( index, children );
        return this;
    }

    /**
     * Returns the list of child nodes.
     *
     * @return the list of child nodes.
     */
    public List<ElementNode> getChildren() {
        return Collections.unmodifiableList( this.children );
    }

    /**
     * Returns the documentation.
     *
     * @return the documentation.
     */
    public String getDocumentation() {
        return this.documentation;
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
     * Returns the name.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the namespace.
     *
     * @return the namespace.
     */
    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    /**
     * Returns the prefix.
     *
     * @return the prefix.
     */
    public String getPrefix() {
        return this.prefix;
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
     * Returns {@code true} if this node is part of cyclic structure.
     *
     * @return {@code true} if this node is part of cyclic structure.
     */
    public boolean isCyclicReference() {
        return this.cyclicReference;
    }

    /**
     * Returns {@code true} if this node is not part of cyclic structure.
     *
     * @return {@code true} if this node is not part of cyclic structure.
     */
    public boolean isNotCyclicReference() {
        return ! this.isCyclicReference();
    }

    /**
     * Sets {@code cyclicReference} active when {@code true} .
     *
     * @param cyclicReference {@code cyclicReference} active when {@code true} .
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setCyclicReference( final boolean cyclicReference ) {
        this.cyclicReference = cyclicReference;
        return this;
    }

    /**
     * Sets the documentation.
     *
     * @param documentation the documentation.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setDocumentation( final String documentation ) {
        this.documentation = StringUtil.emptyIfBlank( documentation );
        return this;
    }

    /**
     * Sets the documentation value when its current value is empty.
     *
     * @param documentation the documentation to set if the current value is empty.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setDocumentationIfEmpty( final String documentation ) {
        if ( this.documentation.isEmpty() ) {
            this.documentation = StringUtil.emptyIfBlank( documentation );
        }
        return this;
    }

    /**
     * Sets the maximum number of occurrences.
     *
     * <p>
     *     If the specified value is empty, sets as {@code "1"}.
     * </p>
     *
     * @param maxOccurs the maximum number of occurrences.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setMaxOccurs( final String maxOccurs ) {
        this.maxOccurs = StringUtil.defaultIfBlank( maxOccurs, "1" );
        return this;
    }

    /**
     * Sets the minimum number of occurrences.
     *
     * <p>
     *     If the specified value is empty, sets as {@code "1"}.
     * </p>
     *
     * @param minOccurs the minimum number of occurrences.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setMinOccurs( final String minOccurs ) {
        this.minOccurs = StringUtil.defaultIfBlank( minOccurs, "1" );
        return this;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setName( final String name ) {
        this.name = name;
        return this;
    }

    /**
     * Sets the namespace.
     *
     * @param namespaceUri the namespace.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setNamespaceUri(final String namespaceUri ) {
        this.namespaceUri = namespaceUri;
        return this;
    }

    /**
     * Sets the prefix.
     *
     * @param prefix the prefix.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setPrefix( final String prefix ) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Sets the datatype.
     *
     * @param type the datatype.
     * @return a reference to this builder.
     */
    public ElementNodeBuilder setType( final String type ) {
        this.type = type;
        return this;
    }

    /**
     * Constructs a {@code ElementNode} configured from this builder.
     *
     * @return a {@code ElementNode} configured from this builder.
     */
    public ElementNode build() {
        return new ElementNode( this );
    }
}
