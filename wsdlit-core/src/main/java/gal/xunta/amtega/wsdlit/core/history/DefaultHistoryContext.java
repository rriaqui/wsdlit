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
 * Default implementation of history context.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class DefaultHistoryContext
implements HistoryContext {
    /**
     * The history manager.
     */
    private final HistoryManager history = new HistoryManager();

    @Override
    public void clearHistory() {
        this.history.clear();
    }

    @Override
    public void popHistory( final HistoryItem item ) {
        this.history.pop( item );
    }

    @Override
    public boolean pushHistory( final HistoryItem item ) {
        this.history.push( item );
        return item.isCyclicReference();
    }

    @Override
    public boolean contains( final String namespaceUri, final String name ) {
        final HistoryItem item = new HistoryItemDefault( namespaceUri, name );
        return this.history.contains( item );
    }

    @Override
    public boolean isEmpty() {
        return this.history.size() == 0;
    }
}
