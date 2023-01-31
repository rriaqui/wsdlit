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
 * Utilities related to difference extraction.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public final class DiffItemUtil {
    private DiffItemUtil() {}

    /**
     * Counts safe and unsafe changes from a list of differences.
     *
     * <p>
     *     A <b>safe</b> difference is a difference that keeps the wsdl compatibility.
     * </p>
     *
     * @param differences the list of previously detected differences.
     * @return an array in which the {@code 0} index contains the secure modifications,
     *         and the {@code 1} index contains the unsafe modifications
     *
     * @since 2.0.0
     */
    public static int[] countSafeAndUnsafeModifications( final List<DiffItem> differences ) {
        int safeModifications = 0;
        int unsafeModifications = 0;

        for( final DiffItem difference : differences ) {
            if ( difference.isEmpty() ) {
                if ( difference.isBreak() ) {
                    unsafeModifications ++;
                } else {
                    safeModifications ++;
                }
            } else {
                final int[] modifications = countSafeAndUnsafeModifications( difference.getDifferences() );
                safeModifications += modifications[0];
                unsafeModifications += modifications[1];
            }
        }
        return new int[] { safeModifications, unsafeModifications };
    }
}
