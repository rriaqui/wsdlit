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

import java.util.Objects;

/**
 * A base class for {@code Node},
 * which provides the default implementation of the methods of the {@code Node} interface.
 *
 * @author rriaqui
 * @since 1.0
 */
public abstract class AbstractNode
implements Node, Comparable<AbstractNode> {
    private String name = "";
    private String namespaceUri = "";
    private String documentation = "";
    private String prefix = "";
    private boolean cyclicReference = false;

    protected AbstractNode() {
        super();
    }

    /**
     * Constructs the {@code AbstractNode} from the specified name and namespace.
     *
     * @param name the name.
     * @param namespaceUri the namespace.
     */
    protected AbstractNode( final String name, final String namespaceUri ) {
        this();
        this.name = name;
        this.namespaceUri = namespaceUri;
    }

    @Override
    public int compareTo( final AbstractNode o ) {
        if ( o == null ) {
            return 1;
        }
        if ( this == o ) {
            return 0;
        }
        int compare = this.name.compareTo( o.name );
        if ( compare != 0 ) {
            return compare;
        }
        compare = this.namespaceUri.compareTo( o.namespaceUri );
        if ( compare != 0 ) {
            return compare;
        }
        return this.documentation.compareTo( o.documentation );
    }


    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null ) {
            return false;
        }
        if ( this.getClass() != o.getClass() ) {
            return false;
        }
        final AbstractNode that = ( AbstractNode ) o;
        return  this.name.equals( that.name )
                && this.namespaceUri.equals( that.namespaceUri )
                && this.documentation.equals( that.documentation )
                && this.prefix.equals( that.prefix );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, namespaceUri, documentation, prefix );
    }

    @Override
    public final String getDocumentation() {
        return documentation;
    }

    @Override
    public final String getFullName() {
        return computeFullName();
    }

    @Override
    public boolean isCyclicReference() {
        return this.cyclicReference;
    }

    protected String computeFullName() {
        if ( prefix.isEmpty() ) {
            return name;
        }
        return prefix + ":" + name;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getNamespaceUri() {
        return namespaceUri;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public final String getQname() {
        return "{" + namespaceUri + "}" + name;
    }

    @Override
    public final void setCyclicReference( final boolean cyclicReference ) {
        this.cyclicReference = cyclicReference;
    }
    @Override
    public final void setDocumentation( final String documentation ) {
        this.documentation = StringUtil.emptyIfBlank( documentation );
    }

    @Override
    public final void setName( final String name ) {
        this.name = StringUtil.emptyIfBlank( name );
    }

    @Override
    public final void setPrefix( final String prefix ) {
        this.prefix = StringUtil.emptyIfBlank( prefix );
    }

    @Override
    public void setNamespaceUri(final String targetNamespace ) {
        this.namespaceUri = StringUtil.emptyIfBlank( targetNamespace );
    }
}
