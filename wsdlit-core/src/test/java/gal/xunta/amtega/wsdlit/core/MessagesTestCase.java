package gal.xunta.amtega.wsdlit.core;

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

import org.junit.Assert;
import org.junit.Test;

import java.util.MissingResourceException;

/**
 * Test cases for Message.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class MessagesTestCase {
    @Test
    public void formatOneArgumentTest() {
        final String message = Messages.I18N.format( Messages.WSDL_PARSER_SPI_PROVIDER_NOT_FOUND_ERROR_KEY, "/tmp" );
        final String expected = "WSDL parser SPI '/tmp' not found. It returns the default WSDL parser.";

        Assert.assertEquals(AssertMessages.EQUALS_EXPECTED, expected, message );
    }

    @Test
    public void getStringTest() {
        final String message = Messages.I18N.getString( Messages.MISSING_DIRECTORY_AND_OR_FILE_PARAMETER_ERROR_KEY );
        final String expected = "Missing directory and / or file parameter.";

        Assert.assertEquals(AssertMessages.EQUALS_EXPECTED, expected, message );
    }

    @Test( expected = MissingResourceException.class )
    public void getStringThrowsExceptionTest() {
        final String key = "unnknown-key";
        Messages.I18N.getString( key );
    }

}
