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

import com.predic8.schema.ComplexType;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.AbstractTestCase;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import org.junit.Assert;
import org.junit.Test;

public class ComplexTypeConverterTestCase
extends AbstractTestCase {
    protected final String EXTENSION_ELEMENT_STRING_COMPLEX_TYPE_NAME = "ExtensionElementStringComplexType";
    protected final String EXTENSION_STRING_COMPLEX_TYPE_NAME = "ExtensionStringComplexType";

    protected final String RESTRICTION_STRING_COMPLEX_TYPE_NAME = "RestrictionStringComplexType";
    protected static final String STRING_ELEMENT_COMPLEX_TYPE_NAME = "StringElementComplexType";
    protected static final String EMPTY_COMPLEX_TYPE_NAME = "EmptyComplexType";

    protected static final String CYCLIC_COMPLEX_TYPE_NAME = "CyclicComplexType";
    protected static final String NOT_CYCLIC_COMPLEX_TYPE_NAME = "NotCyclicComplexType";

    protected static final String MAIN_SIMPLE_TYPE_NAME = "MainSimpleType";
    protected static final String MAIN_SIMPLE_TYPE_TYPE = "tns:" + MAIN_SIMPLE_TYPE_NAME;

    private final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
    private final ConverterManager converterManager = ConverterManager.getInstance().initNamespacePrefixCache( cache );
    private final ComplexTypeConverter converter = converterManager.getComplexTypeConverter();

    @Test
    public void emptyComplexTypeTest() {
        final ComplexType type = schemaConverter.getComplexType( EMPTY_COMPLEX_TYPE_NAME );
        final ElementNode node = converter.convert( type );
        final ElementNode expected =
            new ElementNodeBuilder( EMPTY_COMPLEX_TYPE_NAME, TNS_CONVERTER )
                .setPrefix( TNS_PREFIX )
                .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void restrictionStringComplexTypeTest() {
        final ComplexType type = schemaConverter.getComplexType( RESTRICTION_STRING_COMPLEX_TYPE_NAME );
        final ElementNode node = converter.convert( type );
        final ElementNode expected =
            new ElementNodeBuilder( RESTRICTION_STRING_COMPLEX_TYPE_NAME, TNS_CONVERTER )
                .setPrefix( TNS_PREFIX )
                .setType( MAIN_SIMPLE_TYPE_TYPE )
                .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void extensionElementStringComplexTypeTest() {
        final ComplexType type = schemaConverter.getComplexType( EXTENSION_ELEMENT_STRING_COMPLEX_TYPE_NAME );
        final ElementNode node = converter.convert( type );
        final ElementNode expected =
            new ElementNodeBuilder( EXTENSION_ELEMENT_STRING_COMPLEX_TYPE_NAME, TNS_CONVERTER )
                .setPrefix( TNS_PREFIX )
                .add( createSequenceNode( ELEMENT_NAME, TNS_CONVERTER, TNS_PREFIX ) )
                .add( createSequenceNode( ELEMENT_TWO_NAME, TNS_CONVERTER, TNS_PREFIX ) )
                .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void extensionStringComplexTypeTest() {
        final ComplexType type = schemaConverter.getComplexType( EXTENSION_STRING_COMPLEX_TYPE_NAME );
        final ElementNode node = converter.convert( type );
        final ElementNode expected =
            new ElementNodeBuilder( EXTENSION_STRING_COMPLEX_TYPE_NAME, TNS_CONVERTER )
                .setPrefix( TNS_PREFIX )
                .setType( XSD_STRING_TYPE )
                .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void stringElementComplexTypeTest() {
        final ComplexType type = schemaConverter.getComplexType( STRING_ELEMENT_COMPLEX_TYPE_NAME);
        final ElementNode node = converter.convert( type );
        final ElementNode expected =
            new ElementNodeBuilder( STRING_ELEMENT_COMPLEX_TYPE_NAME, TNS_CONVERTER )
                .setPrefix( TNS_PREFIX )
                .add( createSequenceNode( ELEMENT_NAME, TNS_CONVERTER, TNS_PREFIX ) )
                .build();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void isNotCyclicReferenceComplexTypeTest() {
        final ComplexType type = schemaConverter.getComplexType( NOT_CYCLIC_COMPLEX_TYPE_NAME );
        final ElementNode node = converter.convert( type );
        final ElementNode cyclic = node.findByName( "cyclic" );

        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, cyclic.isCyclicReference() );
    }

    @Test
    public void isCyclicReferenceComplexTypeTest() {
        final ComplexType type = schemaConverter.getComplexType( CYCLIC_COMPLEX_TYPE_NAME );
        final ElementNode node = converter.convert( type );
        final ElementNode cyclic = node.findByName( "cyclic" );

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, cyclic.isCyclicReference() );
    }
}
