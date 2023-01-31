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

import gal.xunta.amtega.wsdlit.core.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.MissingResourceException;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class I18nTestCase {
    private static final String PARAMETER_1 = "wsdl";
    private static final String PARAMETER_2 = "it";
    private static final Map.Entry< String, String > UNKNOWN = createEntry( "unknown-key", "" );
    private static final Map.Entry< String, String > GENERIC_GREETING = createEntry( "generic-greeting", "Hello world." );
    private static final Map.Entry< String, String > CUSTOMIZED_GREETING_1 = createEntry( "customized-greeting", "Hello world, wsdl{}." );
    private static final Map.Entry< String, String > CUSTOMIZED_GREETING_2 = createEntry( "customized-greeting", "Hello world, wsdlit." );

    private final I18n i18n = new I18n();

    @Test( expected = MissingResourceException.class )
    public void getStringWhenKeyDoesNotExistsTest() {
        i18n.getString( UNKNOWN.getKey() );
    }

    @Test
    public void formatWithOneParameterTest() {
        final String value = i18n.format( CUSTOMIZED_GREETING_1.getKey(), PARAMETER_1 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, CUSTOMIZED_GREETING_1.getValue(), value );
    }

    @Test
    public void formatWithOneParameterWhenMessageIsNotParameterizedTest() {
        final String value = i18n.format( GENERIC_GREETING.getKey(), PARAMETER_1 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, GENERIC_GREETING.getValue(), value );
    }

    @Test
    public void formatWithTwoParametersTest() {
        final String value = i18n.format( CUSTOMIZED_GREETING_2.getKey(), PARAMETER_1, PARAMETER_2 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, CUSTOMIZED_GREETING_2.getValue(), value );
    }

    @Test
    public void formatWithTwoParametersWhenMessageIsNotParameterizedTest() {
        final String value = i18n.format( GENERIC_GREETING.getKey(), PARAMETER_1, PARAMETER_2 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, GENERIC_GREETING.getValue(), value );
    }

    @Test
    public void getStringWhenKeyIsGenericGreetingTest() {
        final String value = i18n.getString( GENERIC_GREETING.getKey() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, GENERIC_GREETING.getValue(), value );
    }

    private static Map.Entry< String, String > createEntry( final String key, final String value ) {
        return new AbstractMap.SimpleImmutableEntry<>( key, value );
    }
}
