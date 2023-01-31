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
import java.util.Map;

/**
 * An object that acts as a cache of namespaces and prefixes.
 *
 * <p>
 *      Allows you to search by prefix or by namespace
 *      or create a new prefix from another one
 *      among other possibilities.
 * </p>
 *
 * <p>
 *      This class acts as a mediator when assigning prefixes to elements so that
 *      when a prefix already exists and is associated with a different namespace,
 *      a new prefix is created from the original (for example, adding a number to the original prefix),
 *      which makes it possible to greatly simplify the information in the generated documentation
 *      and maintain, at the same time, a relationships between prefixes and namespaces.
 * </p>
 *
 * @author rriaqui
 * @since 1.0
 */
public interface NamespacePrefixCache {
    /**
     * Returns {@code true} if the prefix has already been cached.
     *
     * <p>
     *      When a prefix is cached the relationship {@code prefix} + {@code namespace} is stored.
     *      There is a possibility that you will be asked to add the same prefix associated with a different namespace.
     *      In this case,
     *      it will be necessary to create a new value for the prefix and store the relation {@code newPrefix} + {@code namespace}.
     *  </p>
     *
     * @param prefix the prefix whose presence in the cache is to be tested.
     * @return {@code true} if this cache contains a relationship for the specified prefix.     */
    boolean containsPrefix( String prefix );

    /**
     * Returns {@code true} if the namespace was previously cached.
     *
     * @param namespaceUri the spacename whose presence in this cache is to be tested.
     * @return {@code true} if the namespace was previously cached.
     */
    boolean containsNamespaceUri( String namespaceUri );

    /**
     * Returns a new prefix for the specified prefix if it was already cached or the specified prefix otherwise..
     *
     * @param prefix the specified prefix whose presence in the cache is to be tested.
     * @return a new prefix for the specified prefix if it was already cached or the specified prefix otherwise.
     */
    String createPrefix( final String prefix );

    /**
     * Returns the namespace that is in a relationship with the specified prefix,
     * otherwise it returns {@code null}.
     *
     * @param prefix the specific prefix whose presence in the cache is to be tested.
     * @return the namespace that is in a relationship with the specified prefix,
     *         otherwise it returns {@code null}.
     */
    String findNamespace( final String prefix );

    /**
     * Returns the prefix that is in a relationship with the specified namespace,
     * otherwise it returns {@code null}.
     *
     * @param namespaceUri the specific namespace whose presence in the cache is to be tested.
     * @return the prefix that is in a relationship with the specified namespace,
     *         otherwise it returns {@code null}.
     */
    NamespaceNode findPrefix( final String namespaceUri );

    /**
     * Returns a {@code Collection} with the {@code NamespaceNode} objects that are cached.
     *
     * @return a {@code Collection} with the {@code NamespaceNode} objects that are cached.
     */
    Collection<NamespaceNode> getNamespaces();

    /**
     * Returns the relationships {@code Map} of the prefixes and namespaces,
     * where each entry key is the prefix and the value is the namespace value.
     *
     * @return the relationships {@code Map} of the prefixes and namespaces,
     *         where each entry key is the prefix and the value is the namespace value.
     */
    Map<String, String> getPrefixMap();

    /**
     * Returns a prefix unique for this namespace.
     * If the namespace was already cached, then return its prefix in the cache,
     * otherwise it ensures that the prefix is unique calling {@link #createPrefix(String)} method.
     *
     * @param prefix the prefix used as base when the namespace was not cached.
     * @param namespace the namespace.
     * @return a prefix unique for this namespace.
     *         If the namespace was already cached, then return its prefix in the cache,
     *         otherwise it ensures that the prefix is unique calling {@link #createPrefix(String)} method.
     */
    String findPrefix( final String prefix, final String namespace );

    default String resolvePrefix( final String namespaceUri ) {
        return StringUtil.EMPTY;
    }

    default void updateFormDefaultValues( final String namespaceUri, final NamespaceNode namespaceNode ) {
    }
}
