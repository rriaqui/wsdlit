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

import gal.xunta.amtega.wsdlit.maven.plugin.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class StringEscapeUtilTestCase {
    @Test
    public void escapeXmlWhenTextIsNullTest() {
        final String expected = "";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringEscapeUtil.escapeXml( null ) );
    }

    @Test
    public void escapeXmlWhenTextIsBlankTest() {
        final String expected = "";
        final String text = "  ";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringEscapeUtil.escapeXml( text ) );
    }

    @Test
    public void escapeXmlTest() {
        final String expected = "Cando &quot;4 &gt; 1&quot; e &quot;1 &lt; 4&quot; entón: &apos;erro&apos;.";
        final String text = "Cando \"4 > 1\" e \"1 < 4\" entón: 'erro'.";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, StringEscapeUtil.escapeXml( text ) );
    }
}
