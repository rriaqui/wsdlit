package gal.xunta.amtega.wsdlit.membranesoa.converter;

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

import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.Message;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.core.model.PartNode;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;
import org.junit.Assert;
import org.junit.Test;

public class PartConverterTestCase {
    private final String path = "src/test/resources/converter/converter.wsdl";

    private final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
    private final ConverterManager converterManager = ConverterManager.getInstance().initNamespacePrefixCache( cache );
    private final PartConverter converter = converterManager.getPartConverter();
    private final Definitions definitions = MembraneSOAUtil.readWSDLFile( path );

    public static final String NAME_EXPECTED = "part";
    public static final String TNS_EXPECTED = "http://localhost.localdomain/wsdlit/test/converter";

    @Test
    public void convertWhenPartIsTypeCheckNameTest() {
        final Message message = definitions.getMessage( "messagePartConverterTestCaseTypeRequest" );
        final PartNode partNode = converter.convert( message.getParts().get( 0 ) );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, NAME_EXPECTED, partNode.getName() );
    }

    @Test
    public void convertWhenPartIsTypeCheckNamespaceUriTest() {
        final Message message = definitions.getMessage( "messagePartConverterTestCaseTypeRequest" );
        final PartNode partNode = converter.convert( message.getParts().get( 0 ) );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TNS_EXPECTED, partNode.getNamespaceUri() );
    }

    @Test
    public void convertWhenPartIsElementTest() {
        final Message message = definitions.getMessage( "messagePartConverterTestCaseComplexTypeRequest" );
        final PartNode partNode = converter.convert( message.getParts().get( 0 ) );
        final String expected = "tns:elementPartConverterTestCaseComplexType";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, partNode.getType() );
    }
}
