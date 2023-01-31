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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A base class for {@code NamespacePrefixCache} implementations.
 *
 * <p>
 *      The main functionality of this class is found in the {@link #findPrefix( String, String )} method,
 *      which is responsible for returning a prefix for a namespace,
 *      so that when the prefix is already assigned to a different namespace,
 *      a new prefix is generated, associated with the namespace, and returned.
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractNamespacePrefixCache
implements NamespacePrefixCache {
    private final Map<String, String> prefixNamespaceCached = new LinkedHashMap<>();
    private final Map<String, NamespaceNode> namespaces = new LinkedHashMap<>();

    @Override
    public boolean containsNamespaceUri( final String namespaceUri ) {
        return namespaces.containsKey( namespaceUri );
    }

    @Override
    public boolean containsPrefix( final String prefix ) {
        return this.prefixNamespaceCached.containsKey( prefix );
    }

    @Override
    public String createPrefix( final String prefix ) {
        int index = 0;
        String newPrefix = prefix;
        while( this.containsPrefix( newPrefix ) ) {
            newPrefix = prefix + index++;
        }
        return newPrefix;
    }

    @Override
    public String findNamespace( final String prefix ) {
        return this.prefixNamespaceCached.get( prefix );
    }


    @Override
    public NamespaceNode findPrefix( final String namespaceUri ) {
        return this.namespaces.get( namespaceUri );
    }

    @Override
    public String findPrefix( final String prefix, final String namespace ) {
        // En caso de recibir un prefixo o buscamos ou calculamos.
        if ( StringUtil.isNotBlank( prefix ) ) {
            // Se o namespace xa está cacheado, devolvemos o prefixo
            if ( this.containsNamespaceUri( namespace ) ) {
                return findPrefix( namespace ).getPrefix();
            }

            String newPrefix = resolvePrefix( namespace );
            if ( newPrefix.isEmpty() ) {
                newPrefix = createPrefix( prefix );
            }

            final NamespaceNode namespaceNode = new NamespaceNode();
            namespaceNode.setPrefix( newPrefix );
            namespaceNode.setNamespaceUri( namespace );
            this.put( newPrefix, namespaceNode );
            this.updateFormDefaultValues( namespace, namespaceNode );
            return newPrefix;
        }
        return StringUtil.EMPTY;
    }

    @Override
    public Collection<NamespaceNode> getNamespaces() {
        return this.namespaces.values();
    }

    @Override
    public Map<String, String> getPrefixMap() {
        return Collections.unmodifiableMap( this.prefixNamespaceCached );
    }

    /**
     * Stores the relationships between a {@code NamespaceNode} and a prefix.
     *
     * <p>
     *     Two relationships are stored:
     * </p>
     *
     * <ul>
     *     <li>The relationship between the namespace of the specified {@code NamespaceNode} and the {@code NamespaceNode}.</li>
     *     <li>The relationship between the prefix and the namespace of the specified {@code NamespaceNode}.</li>
     * </ul>
     *
     * @param prefix the prefix for the namespace
     * @param namespaceNode the node.
     */
    protected void put( final String prefix, final NamespaceNode namespaceNode ) {
        this.namespaces.put( namespaceNode.getNamespaceUri(), namespaceNode );
        this.prefixNamespaceCached.put( prefix, namespaceNode.getNamespaceUri() );
    }
}
