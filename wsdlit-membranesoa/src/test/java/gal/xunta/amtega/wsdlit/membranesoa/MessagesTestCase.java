package gal.xunta.amtega.wsdlit.membranesoa;

/*-
 * #%L
 * wsdlit-membranesoa
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
        final String message = Messages.I18N.format( Messages.WSDL_DOCUMENT_CONVERTER_START_KEY, "/tmp/file.wsdl" );
        final String expected = "[ START ] Wsdl file '/tmp/file.wsdl' processing started.";

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, message );
    }

    @Test
    public void getStringTest() {
        final String message = Messages.I18N.getString( Messages.WSDL_DOCUMENT_CONVERTER_START_KEY );
        final String expected = "[ START ] Wsdl file '{}' processing started.";

        Assert.assertEquals(AssertMessages.EQUALS_EXPECTED, expected, message );
    }

    @Test( expected = MissingResourceException.class )
    public void getStringThrowsExceptionTest() {
        final String key = "unnknown-key";
        Messages.I18N.getString( key );
    }

}
