package gal.xunta.amtega.wsdlit.core.converter;

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

import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;

/**
 * An object that converts one data model (source) to another data model (destination)
 * <p>
 * The converter adapts the structure of the source data model
 * to a structure more appropriate to the interests of further processing.
 *
 * @param <S> the class of the source data model.
 * @param <T> the target class to which you want to convert the source data model.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public interface Converter<S, T> {
    /**
     * Converts the source data model to the target data model.
     *
     * @param item the source data model to be converted.
     * @return the target class.
     */
    T convert( S item );

    /**
     * Converts the source data model to the target data model,
     * ignoring the history of the conversion.
     * <p>
     *     The history of the conversion is used to check recursion.
     * </p>
     *
     * @param item the source data model to be converted.
     * @param ignoreHistory {@code true} para ignorar o historial.
     * @return the target class.
     */
    T convert( S item, boolean ignoreHistory );

    /**
     * Returns cache of the prefixes / namespaces relationships for this converter.
     *
     * @return the prefixes / namespaces relationships cache.
     */
    NamespacePrefixCache getNamespacePrefixCache();

    /**
     * Sets the prefixes / namespaces relationships for this converter.
     *
     * @param cache the prefixes / namespaces relationships cache.
     */
    void setNamespacePrefixCache( NamespacePrefixCache cache );

    /**
     * Finds the prefix associated to a namespace,
     * or create a prefix from the supplied one if it exists to avoid collision of prefixes / namespaces.
     *
     * @param prefix the prefix value.
     * @param namespaceUri the namespace associated to the prefix.
     * @return the value of the prefix parameter if the relationship does not exist in the cache, or a new prefix.
     */
    String findPrefix( String prefix, String namespaceUri );
}
