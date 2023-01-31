package gal.xunta.amtega.wsdlit.core.util.xml;

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

/**
 * Utilities related to QName.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class QNameUtil {
    private QNameUtil() {}

    /**
     * Creates a textual representation of a namespace uri and a name.
     *
     * @param namespaceUri the uri representing the namespace.
     * @param name the name associated with the namespace uri.
     * @return a composition of the uri between {@code {}} immediately followed by the name.
     */
    public static String compose( final String namespaceUri, final String name ) {
        final StringBuilder builder = new StringBuilder();

        if ( StringUtil.isNotBlank( namespaceUri ) ) {
            builder.append('{').append(namespaceUri.trim()).append('}');
        }
        if ( StringUtil.isNotBlank( name ) ) {
            builder.append( name );
        }
        return builder.toString();
    }
}
