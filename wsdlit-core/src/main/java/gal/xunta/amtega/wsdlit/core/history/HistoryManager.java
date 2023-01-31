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

import gal.xunta.amtega.wsdlit.core.util.StringUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * O xestor do historial.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class HistoryManager {
    /**
     * Queue that stores history items.
     */
    private final Deque<HistoryItem> history = new ArrayDeque<>();

    /**
     * Delete all history items.
     */
    public void clear() {
        this.history.clear();
    }

    /**
     * Checks if the specified {@code HistoryItem} exists in this history.
     *
     * @param item the history item to check.
     * @return {@code true} if the history contains a mapping for the {@code HistoryItem}.
     */
    public boolean contains( final HistoryItem item ) {
        return this.history.contains( item );
    }

    /**
     * Stores a non-cyclic history item in this history,
     * or execute {@code item.onCyclicReference()} in case it was already stored.
     *
     * @param item the history item to store in the history.
     */
    public void push( final HistoryItem item ) {
        if ( this.contains( item ) ) {
            item.onCyclicReference();
        } else {
            if( StringUtil.isNotBlank( item.getKey() ) ) {
                history.push( item );
                item.onPushedInHistory();
            }
        }
    }

    /**
     * Delete an item from the history.
     *
     * @param item the history item to delete.
     */
    public void pop( final HistoryItem item ) {
        if( item.isPushedInHistory() ) {
            this.history.pop();
        }
    }

    /**
     * Returns the number of items in the history, a value &gt;= 0.
     *
     * @return the number of items in the history, a value &gt;= 0.
     */
    public int size() {
        return this.history.size();
    }
}
