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
import gal.xunta.amtega.wsdlit.core.diff.DefaultDiffItem;
import gal.xunta.amtega.wsdlit.core.diff.DiffItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to convert schema differences from MembraneSOA classes to wsdlit's classes.
 *
 * @author rriaqui
 * @since 1.1.0
 */
public class DifferenceToDiffItemConverter {
    /**
     * Returns the list of wsdlit {@code DiffItem} classes for the specified list of MembraneSOA {@code Difference}.
     *
     * @param differences the specified list of MembraneSOA {@code Difference} to be converted.
     * @return the list of wsdlit {@code DiffItem} classes.
     */
    public List<DiffItem> convert( final List<Difference> differences ) {
        final List<DiffItem> diffItems = new ArrayList<>();
        for( final Difference difference : differences ) {
            diffItems.add( convert( difference ) );
        }
        return Collections.unmodifiableList( diffItems );
    }

    /**
     * Returns the wsdlit {@code DiffItem} class for the specified MembraneSOA {@code Difference}.
     *
     * @param difference the specified MembraneSOA {@code Difference} to be converted.
     * @return the wsdlit {@code DiffItem} class.
     */
    public DiffItem convert( final Difference difference ) {
        final List<DiffItem> children = convert( difference.getDiffs() );
        return new DefaultDiffItem( difference.getDescription(), false, children );
    }
}
