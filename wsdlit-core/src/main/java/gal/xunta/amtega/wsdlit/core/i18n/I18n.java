package gal.xunta.amtega.wsdlit.core.i18n;

/*-
 * #%L
 * wsdlit-core
 * %%
 * Copyright (C) 2021 - 2023 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
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

import org.slf4j.helpers.MessageFormatter;

import java.util.ResourceBundle;

/**
 * A very simple class for managing wsdlit logging messages.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public final class I18n {
    private final ResourceBundle resourceBundle;

    public I18n() {
        this( I18n.class );
    }

    public I18n( final Class<?> clazz ) {
        this( clazz.getName() );
    }

    public I18n(final String bundle ) {
        this.resourceBundle = ResourceBundle.getBundle( bundle );
    }

    public String getString( final String key ) {
        return this.resourceBundle.getString( key );
    }

    public String format(final String key, final Object arg ) {
        return arrayFormat( key, new Object[] { arg } );
    }

    public String format(final String key, final Object arg1, Object arg2 ) {
        return arrayFormat( key, new Object[] { arg1, arg2 } );
    }

    public String arrayFormat(final String key, final Object[] args ) {
        final String parameterizedMessage = this.getString( key );
        return MessageFormatter.arrayFormat( parameterizedMessage, args ).getMessage();
    }

    public String getBaseBundleName() {
        return this.resourceBundle.getBaseBundleName();
    }
}
