package gal.xunta.amtega.wsdlit.membranesoa.model;

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

import com.predic8.schema.Include;
import com.predic8.schema.Schema;
import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.core.model.NamespaceNode;
import gal.xunta.amtega.wsdlit.core.util.FormDefault;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import groovy.namespace.QName;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class MembraneSOANamespacePrefixCacheTestCase {
    private static final QName SCHEMA_QNAME = new QName(
        "tns/uri",
        "tns",
        "tns"
    );
    private static final QName UNKNOWN = new QName(
        "this/namespace/doesnt/exists",
        "localPartName",
        "unknown"
    );

    private static final QName PREFIX_REPEATED_QNAME = new QName(
        "prefix/repeated",
        "name",
        "tns"
    );

    private static final QName NAMESPACE_NODE_QNAME = new QName(
        "target/namespace/uri",
        "name",
        "prefix"
    );

    private static final String NAMESPACE_URI_1 = SCHEMA_QNAME.getNamespaceURI();
    private static final String NAMESPACE_URI_2 = UNKNOWN.getNamespaceURI();

    @Test
    public void findPrefixTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final String founded = cache.findPrefix( SCHEMA_QNAME.getPrefix(), SCHEMA_QNAME.getNamespaceURI() );
        final String expected = SCHEMA_QNAME.getPrefix();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void findPrefixReturnsUnknownPrefixTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final String founded = cache.findPrefix( UNKNOWN.getPrefix(), UNKNOWN.getNamespaceURI() );
        final String expected = UNKNOWN.getPrefix();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void findPrefixReturnsDifferentPrefixTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );

        final Schema schema = createImportedSchema( definitions, PREFIX_REPEATED_QNAME );
        final String founded = cache.findPrefix( PREFIX_REPEATED_QNAME.getPrefix(), PREFIX_REPEATED_QNAME.getNamespaceURI() );
        final String expected = PREFIX_REPEATED_QNAME.getPrefix() + "0";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void updateFormDefaultValuesWhenDefinitionsIsNullTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode expected = createNamespaceNode();
        cache.updateFormDefaultValues( node.getNamespaceUri(), node );
        final boolean value = expected.equals( node );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, value );
    }

    @Test
    public void updateFormDefaultValuesTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final NamespaceNode node = createNamespaceNode(
            NAMESPACE_NODE_QNAME
        );

        final Schema schema = createImportedSchema( definitions, NAMESPACE_NODE_QNAME );
        cache.updateFormDefaultValues( node.getNamespaceUri(), node );
        Assert.assertTrue(
            AssertMessages.TRUE_EXPECTED,
            node.getElementFormDefault() == FormDefault.QUALIFIED
            && node.getAttributeFormDefault() == FormDefault.UNQUALIFIED
        );
    }

    @Test
    public void resolvePrefixReturnsPrefixTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final String founded = cache.resolvePrefix( SCHEMA_QNAME.getNamespaceURI() );
        final String expected = SCHEMA_QNAME.getPrefix();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void resolvePrefixReturnsEmtpyTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final String founded = cache.resolvePrefix( NAMESPACE_NODE_QNAME.getNamespaceURI() );
        final String expected = StringUtil.EMPTY;

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void resolvePrefixReturnsEmptyWhenDefinitionsIsNullTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String founded = cache.resolvePrefix( SCHEMA_QNAME.getNamespaceURI() );
        final String expected = StringUtil.EMPTY;

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, founded );
    }

    @Test
    public void findSchemaInIncludesReturnsNullTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final Schema schemaFounded = cache.findSchemaInIncludesOfCurrentSchema( NAMESPACE_URI_2 );
        Assert.assertNull(
                AssertMessages.NULL_EXPECTED,
                schemaFounded
        );
    }

    @Test
    public void findSchemaInIncludesReturnsSchemaTest() {
        final Definitions definitions = createDefinitions();
        final Schema schema = Mockito.mock( Schema.class );
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final List<Include> includes = Arrays.asList(
                NamespacePrefixCacheForMembraneSOATestCase.createInclude( NAMESPACE_URI_1 ),
                NamespacePrefixCacheForMembraneSOATestCase.createInclude( NAMESPACE_URI_2 )
        );

        Mockito.doReturn( schema ).when( definitions ).getSchema( NAMESPACE_URI_1 );
        Mockito.doReturn( includes ).when( schema ).getIncludes();
        final Schema schemaFounded = cache.findSchemaInIncludesOfCurrentSchema( NAMESPACE_URI_2 );
        assert schemaFounded != null;
        Assert.assertEquals(
                AssertMessages.EQUALS_EXPECTED,
                NAMESPACE_URI_2,
                schemaFounded.getTargetNamespace()
        );
    }

    public Definitions createDefinitions() {
        final QName qname = SCHEMA_QNAME;
        final Definitions definitions = Mockito.mock( Definitions.class );
        Mockito.when( definitions.getTargetNamespace() ).thenReturn( qname.getNamespaceURI() );
        Mockito.when( definitions.getPrefix() ).thenReturn( qname.getPrefix() );
        final Schema schema = createSchema( qname );

        Mockito
            .when( definitions.getSchema( qname.getNamespaceURI() ) )
            .thenReturn( schema );
        return definitions;
    }

    public Schema createSchema( final QName qname ) {
        final Schema schema = Mockito.mock( Schema.class );
        Mockito.when( schema.getName() ).thenReturn( qname.getLocalPart() );
        Mockito.when( schema.getNamespaceUri() ).thenReturn( qname.getNamespaceURI() );
        Mockito.when( schema.getPrefix() ).thenReturn( qname.getPrefix() );
        Mockito.when( schema.getPrefix( qname.getNamespaceURI() ) ).thenReturn( qname.getPrefix() );
        Mockito.when( schema.getElementFormDefault() ).thenReturn( "qualified" );
        Mockito.when( schema.getAttributeFormDefault() ).thenReturn( "unqualified" );

        return schema;
    }

    public Schema createImportedSchema( final Definitions definitions, final QName qname ) {
        final Schema schema = createSchema( qname );
        final Schema tns = definitions.getSchema( SCHEMA_QNAME.getNamespaceURI() );
        Mockito
            .when( definitions.getSchema( qname.getNamespaceURI() ) )
            .thenReturn( schema );
        Mockito
            .when( tns.getImportedSchema( qname.getNamespaceURI() ) )
            .thenReturn( schema );
        return schema;
    }

    public NamespaceNode createNamespaceNode() {
        return createNamespaceNode( NAMESPACE_NODE_QNAME );
    }

    public NamespaceNode createNamespaceNode( final QName qname ) {
        final NamespaceNode node = new NamespaceNode();
        node.setName( qname.getLocalPart() );
        node.setNamespaceUri( qname.getNamespaceURI() );
        node.setPrefix( qname.getPrefix() );
        return node;
    }
}
