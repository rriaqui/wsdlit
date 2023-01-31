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

import com.predic8.schema.ComplexContent;
import com.predic8.schema.ComplexType;
import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.core.model.SequenceNode;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;
import org.junit.Assert;
import org.junit.Test;

public class ComplexContentExtensionTestCase {
    public static final String TNS = "http://localhost.localdomain/complexContentExtensionTestCase";
    public static final String PREFIX = "tns";

    public static final String ID_NAME = "id";

    public static final String NAME = "ExtendedComplexType";

    public static final String XSD_STRING_TYPE = "xsd:string";

    private final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
    private final ConverterManager converterManager = ConverterManager.getInstance().initNamespacePrefixCache( cache );
    private final ComplexContentConverter converter = converterManager.getComplexContentConverter();

    @Test
    public void extensionTest() {
        final String filename = "src/test/resources/ComplexContentExtensionTestCase/contract.wsdl";
        final Definitions definitions = MembraneSOAUtil.readWSDLFile( filename );

        final ComplexType complexType = definitions
            .getSchema( TNS )
            .getComplexType( NAME );

        final ComplexContent complexContent = ( ComplexContent ) complexType.getModel();
        final ElementNode node = converter.convert( complexContent );
        final ElementNode expected =
            new ElementNodeBuilder()
                .setName( ID_NAME )
                .setNamespaceUri( TNS )
                .setType( XSD_STRING_TYPE )
                .setPrefix( PREFIX )
                .build();

        final ElementNode nodeId = node.findByName( ID_NAME );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, nodeId );
    }

    final ElementNode createSequence( final ElementNodeBuilder builder ) {
        return new SequenceNode( builder );
    }
}
