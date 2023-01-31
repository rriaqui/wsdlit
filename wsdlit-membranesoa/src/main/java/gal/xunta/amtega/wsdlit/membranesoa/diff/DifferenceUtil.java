package gal.xunta.amtega.wsdlit.membranesoa.diff;

/*-
 * #%L
 * wsdlit-membranesoa
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

import com.predic8.soamodel.Difference;

import java.util.List;

/**
 * Utilities related to MembraneSOA {@code Difference},
 * such as counting all found differences or dumping them.
 *
 * @author rriaqui
 * @since 1.1.0
 */
public final class DifferenceUtil {
    private static final String INDENTATION_TEXT = "  ";
    private DifferenceUtil() {}

    /**
     * Returns the total count of {@code Differences} exist in the list of differences,
     * resolving the count of children {@code Differences}.
     *
     * @param differences the list of differences to be counted.
     * @return a number expressing the count of all differences in the list.     */
    public static int sizeTotal( final List<Difference> differences ) {
        int count = 0;
        for( final Difference difference : differences ) {
            count++;
            count += sizeTotal( difference.getDiffs() );
        }
        return count;
    }
}
