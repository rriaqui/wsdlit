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

import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.AbstractTestCase;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import org.junit.Assert;
import org.junit.Test;

public class SimpleTypeConverterTestCase
extends AbstractTestCase {
    @Test
    public void convertListTest() {
        final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final SimpleTypeConverter converter = new SimpleTypeConverter();
        final ElementNode node;
        converter.setNamespacePrefixCache( cache );
        node = converter.convert( createSimpleTypeList() );

        final ElementNode expected = new ElementNodeBuilder( NAME, TNS )
            .setPrefix( PREFIX )
            .setDocumentation( DOCUMENTATION_CONTENT )
            .setType( XSD_STRING_TYPE )
            .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void convertRestrictionTest() {
        final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final SimpleTypeConverter converter = new SimpleTypeConverter();
        final ElementNode node;
        converter.setNamespacePrefixCache( cache );
        node = converter.convert( createSimpleTypeRestriction() );

        final ElementNode expected = new ElementNodeBuilder( NAME, TNS )
            .setPrefix( PREFIX )
            .setDocumentation( DOCUMENTATION_CONTENT )
            .setType( XSD_STRING_TYPE )
            .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void convertSimpleTest() {
        final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final SimpleTypeConverter converter = new SimpleTypeConverter();
        final ElementNode node;
        converter.setNamespacePrefixCache( cache );
        node = converter.convert( createSimpleTypeRestriction() );

        final ElementNode expected = new ElementNodeBuilder( NAME, TNS )
                                         .setPrefix( PREFIX )
                                         .setDocumentation( DOCUMENTATION_CONTENT )
                                         .setType( XSD_STRING_TYPE )
                                         .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }
}
