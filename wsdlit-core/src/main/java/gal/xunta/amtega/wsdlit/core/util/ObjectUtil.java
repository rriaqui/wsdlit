package gal.xunta.amtega.wsdlit.core.util;

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
 * Utilities related to Object.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ObjectUtil {
    private ObjectUtil() {}

    /**
     * Returns the result of executing {@code toString()} when the object is not {@code null},
     * and a default text when it is.
     *
     * @param object the object to check.
     * @param defaultValue the default value to return when {@code object} is {@code null}.
     * @return the default value if the object is {@code null},
     *         or the result of executing {@code object.toString()} when it is not.
     */
    public static String defaultStringIfNull( final Object object, final String defaultValue ) {
        if ( object == null ) {
            return defaultValue;
        }
        return object.toString();
    }

    /**
     * Returns the result of executing {@code toString()} when the object is not null,
     * and the empty string when it is.
     *
     * @param object the object to check.
     * @return the empty string if the object is empty,
     *         or the result of executing {@code object.toString()} when it is not.
     */
    public static String emptyStringIfNull( final Object object ) {
        return defaultStringIfNull( object, StringUtil.EMPTY );
    }
}
