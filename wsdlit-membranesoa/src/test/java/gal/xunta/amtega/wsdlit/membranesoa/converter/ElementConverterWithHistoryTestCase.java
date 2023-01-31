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

import com.predic8.schema.Element;
import com.predic8.schema.Schema;
import com.predic8.schema.TypeDefinition;
import gal.xunta.amtega.wsdlit.core.history.DefaultHistoryContext;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import groovy.namespace.QName;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ElementConverterWithHistoryTestCase {
    private static final String NAME = "name";
    private static final String NAMESPACE_URI = "namesapce/uri";
    private static final String PREFIX = "prefix";

    private static final String TYPE = PREFIX + ":" + NAME;

    @Test
    public void convertTest() {
        final ElementConverter converter = create();
        final Element element = createElement();

        final ElementNode node = converter.convert( element, true );
        final ElementNode expected = new ElementNodeBuilder()
                .setName( NAME )
                .setNamespaceUri( NAMESPACE_URI )
                .setPrefix( PREFIX )
                .setType( TYPE )
                .build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );

    }

    public ElementConverter create() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final HistoryContext historyContext = new DefaultHistoryContext();
        final ElementConverter converter = new ElementConverter( historyContext );
        converter.setNamespacePrefixCache( cache );
        return converter;
    }

    public Element createElement() {
        final Schema schema = createSchema();
        final Element element = Mockito.mock( Element.class );
        Mockito.when( element.getName() ).thenReturn( NAME );
        Mockito.when( element.getType() ).thenReturn( createQName() );
        Mockito.when( element.getPrefix() ).thenReturn( PREFIX );
        Mockito.when( element.getNamespaceUri() ).thenReturn( NAMESPACE_URI );
        Mockito.when( element.getSchema() ).thenReturn( schema );
        return element;
    }

    public QName createQName() {
        return new QName( NAMESPACE_URI, NAME, PREFIX );
    }

    public Schema createSchema() {
        final TypeDefinition typeDefinition = createTypeDefinition();
        final Schema schema = Mockito.mock( Schema.class );
        Mockito.when( schema.getType( Mockito.any( QName.class ) ) ).thenReturn( typeDefinition );
        return schema;
    }

    public TypeDefinition createTypeDefinition() {
        final QName qname = createQName();
        final TypeDefinition typeDefinition = Mockito.mock( TypeDefinition.class );
        Mockito.when( typeDefinition.getQname() ).thenReturn( qname );
        return typeDefinition;
    }
}
