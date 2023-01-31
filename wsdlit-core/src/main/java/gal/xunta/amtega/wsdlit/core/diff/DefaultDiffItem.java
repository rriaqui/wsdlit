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

import java.util.Collections;
import java.util.List;

/**
 * Default class that represents a concrete difference ({@code DiffItem}) between wsdls.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public class DefaultDiffItem
implements DiffItem {
    /**
     * The description of the difference.
     */
    private final String message;

    /**
     * Marks the difference as unsafe ({@code true}) or safe ({@code false}).
     */
    private final boolean breakContract;

    /**
     * Details of the differences in which this materializes.
     * A difference can materialize in one or more specific differences;
     * for example, a difference in a port can materialize in the change of messages, of their names
     * or even of its structure.
     */
    private final List<DiffItem> differences;

    /**
     * The number of safe differences.
     */
    private final int safeDifferences;

    /**
     * The number of unsafe differences.
     */
    private final int unsafeDifferences;

    /**
     * Constructs an element of differences from the basic information.
     *
     * @param message the difference message.
     * @param breakContract {@code true} if this difference breaks the contract.
     * @param differences detail of differences.
     */
    public DefaultDiffItem( final String message, final boolean breakContract, final List<DiffItem> differences ) {
        this.breakContract = breakContract;
        this.differences = differences;
        this.message = message;
        final int[] counters = DiffItemUtil.countSafeAndUnsafeModifications( differences );
        this.safeDifferences = counters[0];
        this.unsafeDifferences = counters[1];
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int countSafeDifferences() {
        return this.safeDifferences;
    }

    @Override
    public int countUnsafeDifferences() {
        return this.unsafeDifferences;
    }

    @Override
    public boolean isBreak() {
        return this.breakContract;
    }

    @Override
    public boolean isEmpty() {
        return this.differences.isEmpty();
    }

    @Override
    public List<DiffItem> getDifferences() {
        return Collections.unmodifiableList( this.differences );
    }
}
