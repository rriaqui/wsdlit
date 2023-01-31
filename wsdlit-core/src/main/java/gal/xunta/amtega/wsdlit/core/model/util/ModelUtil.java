package gal.xunta.amtega.wsdlit.core.model.util;

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

import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;

/**
 * Utilities common to data model classes.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ModelUtil {
    private ModelUtil() {}

    /**
     * Updates a builder with the indicated occurrences.
     *
     * @param builder the builder to update.
     * @param minOccurs the value of {@code minOccurs}.
     * @param maxOccurs the value of {@code maxOccurs}.
    */
    public static void updateOccurrences(final ElementNodeBuilder builder, final Object minOccurs, final Object maxOccurs ) {
        // MembraneSOA API delegates in groovy to set the type.
        // Parameters are sometimes received as Integer and sometimes as String.
        // The following code supports the different types of parameters.
        if ( minOccurs != null ) {
            builder.setMinOccurs( String.valueOf( minOccurs ) );
        }
        if ( maxOccurs != null ) {
            builder.setMaxOccurs( String.valueOf( maxOccurs ) );
        }
    }
}
