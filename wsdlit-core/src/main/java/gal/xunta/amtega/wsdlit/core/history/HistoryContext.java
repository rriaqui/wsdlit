package gal.xunta.amtega.wsdlit.core.history;

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

/**
 * History context.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public interface HistoryContext {

    /**
     * Clear history.
     */
    void clearHistory();

    /**
     * Deletes the history item, if it was previously stored.
     *
     * @param item the item to delete.
     */
    void popHistory( final HistoryItem item );

    /**
     * Stores an item to history,
     * and returns whether it is a cyclic reference.
     *
     * @param item the item to store in the history.
     * @return {@code true} if this is a circular reference.
     */
    boolean pushHistory( final HistoryItem item );

    /**
     * Check if a combination of a localPart and a namespaceUri was already stored in history.
     *
     * @param namespaceUri the namespaceUri of an element.
     * @param name the name of an element.
     *
     * @return {@code true} if it has already been previously stored in the history.
     */
    boolean contains( final String namespaceUri, final String name );

    /**
     * Returns {code true} if the history does not contain any stored items.
     *
     * @return {@code true} if the history does not contain any stored items.
     */
    boolean isEmpty();

    /**
     * Stores an item in the history,
     * by its namespaceUri and its name,
     * and returns a reference to the saved {@code HistoryItem}.
     *
     * @param namespaceUri the namespaceUri of an element.
     * @param name the name of an element.
     * @return a reference to the saved {@code HistoryItem}.
     */
    default HistoryItem pushHistory( final String namespaceUri, final String name ) {
        final HistoryItem item = new HistoryItemDefault( namespaceUri, name );
        this.pushHistory( item );
        return item;
    }
}
