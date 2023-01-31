package gal.xunta.amtega.wsdlit.core.diff;

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

import java.util.List;

/**
 * A class that represents a difference between version Y of a service contract and version Z.
 *
 * <p>
 *      A difference is <b>unsafe</b> when it breaks the contract,
 *      so the compatibility between two versions of the same service contract is not guaranteed.
 * </p>
 *
 *  @author rriaqui
 *  @since 2.0.0
 */
public interface DiffItem {
    /**
     * Returns the list of differences associated with this difference.
     *
     * <p>
     * When a difference is detected in an item (eg a new operation)
     * the difference is grouped under the parent element
     * (in the case of {@code operation}, the parent element is the {@code portType}).
     * <p>
     *
     * @return a list of differences associated with this one.
     */
    List<DiffItem> getDifferences();

    /**
     * Returns the message summarizing the difference.
     *
     * @return the message summarizing the difference.
     */
    String getMessage();

    /**
     * Report whether the difference breaks the contract.
     *
     * @return {@code true} if the difference breaks the contract, or {@code false} otherwise.
     */
    boolean isBreak();

    /**
     * Reports whether there are differences associated with this (an equivalent to {@code getDifferences.isEmpty()}.
     *
     * @return {@code true} if there are no differences associated with this one, {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * The number of safe differences associated with this one.
     * <p>
     *      A difference is considered safe if it does not break the contract.
     * </p>
     *
     * @return the number of differences that do not break the contract.
     */
    int countSafeDifferences();

    /**
     * The number of unsafe differences associated with this one.
     *
     *  <p>
     *      A difference is considered unsecured if it breaks the contract.
     *  </p>
     *
     * @return the number of differences that break the contract.
     */
    int countUnsafeDifferences();
}
