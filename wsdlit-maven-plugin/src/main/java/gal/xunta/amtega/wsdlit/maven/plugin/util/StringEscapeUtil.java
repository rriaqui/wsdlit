package gal.xunta.amtega.wsdlit.maven.plugin.util;

/*-
 * #%L
 * AMTEGA WsdlIT Maven Plugin
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
 * Class with many utils for String manipulation.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class StringEscapeUtil {
    // FROM https://www.w3.org/TR/REC-xml/#syntax
    private static final String[][] METACHARACTERS = {
        { "&", "&amp;" },
        { "<", "&lt;"},
        { ">", "&gt;" },
        { "\"", "&quot;" },
        { "'", "&apos;" }
    };

    private StringEscapeUtil() {}

    /**
     * Escapes a text following the XML rules.
     *
     * @param text the text to escape
     * @return the escaped text or the empty string if its value is {@code null} or {@code StringUtil.EMPTY}.
     */
    public static String escapeXml( final String text ) {
        if ( StringUtil.isBlank( text ) ) {
            return "";
        }
        String replaced = text;
        for( final String[] metachar : METACHARACTERS ) {
            if ( replaced.contains( metachar[ 0 ] ) ) {
                replaced = replaced.replace( metachar[ 0 ], metachar[ 1 ] );
            }
        }
        return replaced;
    }
}
