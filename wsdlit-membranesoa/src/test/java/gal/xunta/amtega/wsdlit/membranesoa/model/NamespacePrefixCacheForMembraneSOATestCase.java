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
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class NamespacePrefixCacheForMembraneSOATestCase {
    private static final String NAME = "service";
    private static final String TNS = "http://localhost/namespacePrefixCache";
    private static final String TNS_PREFIX = "tns";

    private static final String NAMESPACE_URI_1 = "http://namespaceUri/1";
    private static final String NAMESPACE_URI_2 = "http://namespaceUri/2";
    private static final String NAMESPACE_URI_MISSING = "http://namespaceUri/3";

    private static final String TNS_NAMESPACE_URI_1 = "namespaceUri1";
    @Test
    public void addTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String expected = "prefix";
        final String prefix = cache.findPrefix( expected, "http://namespace-1" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, prefix );
    }

    @Test
    public void addSamePrefixDistinctNamespaceTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();

        cache.findPrefix( "prefix", "http://namespace-1" );
        final String prefix = cache.findPrefix( "prefix", "http://namespace-2" );
        final String expected = "prefix0";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, prefix );
    }

    @Test
    public void addSamePrefixAndNamespaceTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String expectedPrefix = "prefix";
        final String namespace = "http://namespace";
        cache.findPrefix( expectedPrefix, namespace );
        final String secondPrefix = cache.findPrefix( "prefix", namespace );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedPrefix, secondPrefix );
    }

    @Test
    public void constructorTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, cache );
    }

    @Test
    public void constructorWhenDefinitionsIsNotNullTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, cache );
    }

    @Test
    public void constructorWhenDefinitionsIsNullTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( null );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, cache );
    }

    @Test
    public void getNamespacesTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final Collection< NamespaceNode > namespaceNodes = cache.getNamespaces();
        final NamespaceNode[] array = namespaceNodes.toArray( new NamespaceNode[ 0 ] );
        final Collection<NamespaceNode> expectedNamespaceNodes = Collections.singleton( createNamespaceNode() );
        final NamespaceNode[] expectedArray = expectedNamespaceNodes.toArray( new NamespaceNode[ 0 ] );

        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expectedArray, array );
    }

    @Test
    public void findPrefixTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String expectedPrefix = TNS_PREFIX;
        final String namespace = TNS;
        cache.findPrefix( expectedPrefix, namespace );

        final String prefix = cache.findPrefix( namespace ).getPrefix();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedPrefix, prefix );
    }

    @Test
    public void findPrefixIsEmptyTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String prefix = cache.findPrefix( StringUtil.EMPTY, TNS );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, StringUtil.EMPTY, prefix );
    }

    @Test
    public void findNamespaceTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String prefix = "prefix";
        final String expectedNamespace = "http://namespace";
        cache.findPrefix( prefix, expectedNamespace );

        final String namespace = cache.findNamespace( prefix );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedNamespace, namespace );
    }

    @Test
    public void findSchemaTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final Schema expected = createSchema();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected.getTargetNamespace(), cache.findSchema( TNS ).getTargetNamespace() );
    }

    @Test
    public void findSchemaReturnsNullTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, cache.findSchema( null ) );
    }

    @Test
    public void findSchemaInCurrentSchemaReturnsNullTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );

        final Schema schema = cache.findSchemaInCurrentSchema( NAMESPACE_URI_MISSING );
        Assert.assertNull( AssertMessages.NULL_EXPECTED, schema );
    }

    @Test
    public void findSchemaInCurrentSchemaReturnsSchemaTest() {
        final Schema importedSchema = createSchema( TNS_NAMESPACE_URI_1, NAMESPACE_URI_1 );
        final Definitions definitions = createDefinitions( importedSchema );
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );

        final Schema schema = cache.findSchemaInCurrentSchema( NAMESPACE_URI_1 );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, schema );

    }

    @Test
    public void getMapTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String prefix = "prefix";
        final String namespace = "http://namespace";
        cache.findPrefix( prefix, namespace );

        final Map<String, String>  expectedMap = new LinkedHashMap<>();
        expectedMap.put( prefix, namespace );
        final Map<String, String> map = cache.getPrefixMap();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedMap, map );
    }

    @Test
    public void resolvePrefixWhenDefinitionsIsNullTest() {
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
        final String namespace = "http://localhost/prefix";
        final String expected = StringUtil.EMPTY;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, cache.resolvePrefix( namespace ) );
    }

    @Test
    public void resolvePrefixWhenDefinitionsIsNotNullTest() {
        final Definitions definitions = createDefinitions();
        final MembraneSOANamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        final String namespace = "http://localhost/prefix";
        final String expected = StringUtil.EMPTY;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, cache.resolvePrefix( namespace ) );
    }

    private Definitions createDefinitions() {
        return createDefinitions( null );
    }
    private Definitions createDefinitions( final Schema importedSchema ) {
        final Schema schema = createSchema();
        final Definitions definitions = new Definitions( TNS, NAME );
        final Definitions spied = Mockito.spy( definitions );
        definitions.setTargetNamespace( TNS );
        Mockito.doReturn( TNS_PREFIX ).when( spied ).getPrefix();
        Mockito.doReturn( schema ).when( spied ).getSchema( Mockito.anyString() );
        Mockito.doReturn( schema ).when( spied ).getSchemaLoadKnownSchemaIfNeeded( Mockito.anyString() );
        Mockito.when( schema.getImportedSchema( Mockito.anyString() ) ).thenReturn( importedSchema );
        return spied;
    }

    private NamespaceNode createNamespaceNode() {
        final NamespaceNode node = new NamespaceNode();
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );

        return node;
    }

    public static Include createInclude( final String namespaceUri ) {
        final Schema schema = new Schema();
        final Include include = Mockito.mock( Include.class );
        Mockito.when( include.getNamespaceUri() ).thenReturn( namespaceUri );
        Mockito.when( include.getSchema() ).thenReturn( schema );
        schema.setTargetNamespace( namespaceUri );
        return include;
    }

    private Schema createSchema() {
        return createSchema( TNS_PREFIX, TNS );
    }

    private Schema createSchema( final String prefix, final String tns ) {
        final Schema mocked = Mockito.mock( Schema.class );
        Mockito.when( mocked.getPrefix( tns ) ).thenReturn( prefix );
        return mocked;
    }
}
