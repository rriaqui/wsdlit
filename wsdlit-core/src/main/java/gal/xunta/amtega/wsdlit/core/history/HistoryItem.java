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
 * Class that represents an entry from the history context.
 *
 * <p>
 *      The history item contains relevant information,
 *      such as the key with which it will be stored,
 *      the namespaceUri,
 *      whether it is a circular reference or has been saved to history.
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public interface HistoryItem {
    /**
     * Returns the key for this item to be stored / retrieved with in the history.
     *
     * @return the key for this item to be stored / retrieved with in the history.
     */
    String getKey();

    /**
     * Returns the namespace uri.
     *
     * @return the namespace uri.
     */
    String getNamespaceUri();

    /**
     * Returns {@code true} if this is a circular reference.
     *
     * @return {@code true} if this is a circular reference.
     */
    boolean isCyclicReference();

    /**
     * Returns {@code true} if it was stored in the history.
     *
     * @return {@code true} if it was stored in the history.
    */
    boolean isPushedInHistory();

    /**
     * Actions to execute when a cyclic reference is detected.
     */
    void onCyclicReference();

    /**
     * Actions to execute when an item is stored in the history.
     */
    void onPushedInHistory();
}
