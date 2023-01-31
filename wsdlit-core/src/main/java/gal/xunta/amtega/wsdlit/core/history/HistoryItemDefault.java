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

import gal.xunta.amtega.wsdlit.core.util.xml.QNameUtil;

import java.util.Objects;

/**
 * Default implementation of a history item.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class HistoryItemDefault
implements HistoryItem {
    /**
     * Stores whether it is a cyclic reference.
     */
    private boolean cyclicReference = false;

    /**
     * Stores if it was previously stored in the history.
     */
    private boolean pushedInHistory = false;

    /**
     * The key to store it in the history (a composition of localPart and namespaceUri).
     */
    private final String key;

    /**
     * The namespace uri.
     */
    private final String namespaceUri;

    /**
     * Constructs a {@code HistoryItemDefault} with the specified values of the namespace uri
     * and the name of the local port.
     *
     * @param namespaceUri the namespace uri.
     * @param name the name of the local part.
     */
    public HistoryItemDefault( final String namespaceUri, final String name ) {
        this.key = QNameUtil.compose( namespaceUri, name );
        this.namespaceUri = namespaceUri;
    }

    @Override
    public void onCyclicReference() {
        this.cyclicReference = true;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    @Override
    public boolean isCyclicReference() {
        return this.cyclicReference;
    }

    @Override
    public boolean isPushedInHistory() {
        return this.pushedInHistory;
    }

    @Override
    public void onPushedInHistory() {
        this.pushedInHistory = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoryItemDefault)) return false;
        HistoryItemDefault that = ( HistoryItemDefault ) o;
        return key.equals( that.key );
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
