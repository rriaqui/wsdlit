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

import com.predic8.schema.*;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.model.GroupNode;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.AbstractTestCase;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.xml.namespace.QName;

public class SchemaComponentConverterTestCase
extends AbstractTestCase {
    final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
    private final ConverterManager converters = ConverterManager.getInstance().initNamespacePrefixCache( cache );
    final SchemaComponentConverter converter = converters.getSchemaComponentConverter();

    @Test( expected = IllegalArgumentException.class )
    public void convertAttributeThrowsIllegalArgumentExceptionTest() {
        final Attribute attribute = new Attribute( NAME, new QName( NAME  ) );

        converter.convert( attribute );
    }

    @Test
    public void convertComplexTypeTypeTest() {
        final ComplexType type = createComplexType();
        final ModelGroup sequence = createSequence();

        Mockito.when( type.getModel() ).thenReturn( sequence );

        final ElementNode node = converter.convert( type );
        final ElementNode expected =
            new ElementNodeBuilder()
                .setName( NAME )
                .setNamespaceUri( TNS )
                .setType( "-" )
                .setDocumentation( DOCUMENTATION_CONTENT )
                .setPrefix( PREFIX )
                .add( createSequenceNode() )
                .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void convertElementTest() {
        final Element element = createElement();
        final ElementNode node = converter.convert( element );
        final ElementNode expected =
            new ElementNodeBuilder()
                .setName( NAME )
                .setNamespaceUri( XSD_TNS )
                .setType( XSD_STRING_TYPE )
                .setDocumentation( DOCUMENTATION_CONTENT )
                .setPrefix( XSD_PREFIX )
                .build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void convertGroupTest() {
        final Schema schema = createSchema();
        final ModelGroup sequence = createSequence();
        final Group group = Mockito.mock( Group.class );
        Mockito.when( group.getName() ).thenReturn( NAME );
        Mockito.when( group.getQname() ).thenReturn( AbstractTestCase.QNAME );
        Mockito.when( group.getSchema() ).thenReturn( schema );
        Mockito.when( group.getModel() ).thenReturn( sequence );
        Mockito.when( group.getNamespaceUri() ).thenReturn( TNS );
        Mockito.when( group.getPrefix() ).thenReturn( PREFIX );

        final ElementNode node = converter.convert( group );
        final GroupNode expected =
            new GroupNode(
                new ElementNodeBuilder()
                    .setName( GroupNode.NAME )
                    .setNamespaceUri( TNS )
                    .setPrefix( PREFIX )
                    .setType( "-" )
                    .add( createSequenceNode() )
            );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void convertGroupRefTest() {
        final GroupRef groupRef = GroupRefConverterTestCase.createGroupRef();
        final ElementNode node = converter.convert( groupRef );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, GroupRefConverterTestCase.MAX_OCCURRENCES_EXPECTED, node.getMaxOccurs() );
    }


    @Test
    public void convertSimpleTypeTest() {
        final SimpleType type = createSimpleTypeRestriction();
        final ElementNode node = converter.convert( type );
        final ElementNode expected =
            new ElementNodeBuilder()
                .setName( NAME )
                .setNamespaceUri( TNS )
                .setType( XSD_STRING_TYPE )
                .setDocumentation( DOCUMENTATION_CONTENT )
                .setPrefix( PREFIX )
                .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }
}
