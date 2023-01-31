package gal.xunta.amtega.wsdlit.core.model;

/*-
 * #%L
 * wsdlit-core
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

import gal.xunta.amtega.wsdlit.core.AssertMessages;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractNamespacePrefixCacheTestCase {
    private static final String NAME = "name";
    private static final String PREFIX = "prefix";
    private static final String TNS = "target/namespace";

    private static final String PREFIX_NOT_IN_CACHE = "prefixNotInCache";
    private static final String TNS_NOT_IN_CACHE = "target/namespaceY/not/in/cache";

    @Test
    public void containsPrefixReturnsFalseTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();

        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, cache.containsPrefix( PREFIX_NOT_IN_CACHE ) );
    }

    @Test
    public void containsPrefixReturnsTrueTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, cache.containsPrefix( PREFIX ) );
    }

    @Test
    public void containsNamespaceUriReturnsFalseTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();

        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, cache.containsNamespaceUri( TNS_NOT_IN_CACHE ) );
    }

    @Test
    public void containsNamespaceUriReturnsTrueTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, cache.containsNamespaceUri( TNS ) );
    }

    @Test
    public void createPrefixReturnsSamePrefixWhenNotInCacheTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final String value = cache.createPrefix( PREFIX_NOT_IN_CACHE );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREFIX_NOT_IN_CACHE, value );
    }

    @Test
    public void createPrefixReturnsNewPrefixWhenAlreadyInCacheTrueTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final String expected = PREFIX + "0";
        final String value = cache.createPrefix( PREFIX );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void findPrefixWhenInCacheTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final NamespaceNode expected = createNamespaceNode();
        final NamespaceNode value = cache.findPrefix( TNS );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void findPrefixReturnsNullWhenNotInCacheTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();

        Assert.assertNull( AssertMessages.NULL_EXPECTED, cache.findPrefix( TNS_NOT_IN_CACHE ) );
    }

    @Test
    public void findNamespaceWhenInCacheTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final String value = cache.findNamespace( PREFIX );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TNS, value );
    }

    @Test
    public void findNamespaceReturnsNullWhenNotInCacheTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();

        Assert.assertNull( AssertMessages.NULL_EXPECTED, cache.findNamespace( TNS_NOT_IN_CACHE ) );
    }

    @Test
    public void findPrefixReturnsEmptyWhenPrefixIsNullTest() {
        final String expected = StringUtil.EMPTY;
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final String value = cache.findPrefix( null, null );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void findPrefixReturnsSamePrefixTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final String value = cache.findPrefix( PREFIX, TNS );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREFIX, value );
    }

    @Test
    public void findPrefixReturnsSamePrefixWhenNoPrefixAndNoNamespaceUriTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final String value = cache.findPrefix( PREFIX_NOT_IN_CACHE, TNS_NOT_IN_CACHE );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREFIX_NOT_IN_CACHE, value );
    }

    @Test
    public void findPrefixReturnsDifferentPrefixTest() {
        final String expected = PREFIX + "0";
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final String value = cache.findPrefix( PREFIX, TNS_NOT_IN_CACHE );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void getNamespacesTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final Collection<NamespaceNode> expected = Collections.singletonList( createNamespaceNode() );
        final Collection<NamespaceNode> value = cache.getNamespaces();

        assertThat( expected, containsInAnyOrder( value.toArray() ) );
    }

    @Test
    public void getPrefixMapSizeTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final Map<String, String> expected = new HashMap<>();
        final Map<String, String> value = cache.getPrefixMap();
        expected.put( PREFIX, TNS );

        assertThat( expected.size(), is( equalTo( value.size() ) ) );
    }

    @Test
    public void getPrefixMapKeysTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final Map<String, String> expected = new HashMap<>();
        final Map<String, String> value = cache.getPrefixMap();
        expected.put( PREFIX, TNS );

        Assert.assertArrayEquals(
            AssertMessages.ARRAY_EQUALS_EXPECTED,
            expected.keySet().toArray(),
            value.keySet().toArray()
        );
    }

    @Test
    public void getPrefixMapValuesTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCache();
        final Map<String, String> expected = new HashMap<>();
        final Map<String, String> value = cache.getPrefixMap();
        expected.put( PREFIX, TNS );

        Assert.assertArrayEquals(
            AssertMessages.ARRAY_EQUALS_EXPECTED,
            expected.values().toArray(),
            value.values().toArray()
        );
    }

    @Test
    public void resolvePrefixReturnsAPrefixTest() {
        final AbstractNamespacePrefixCache cache = createNamespacePrefixCacheEmpty();
        final String prefix = "prefix";
        final String newPrefix = cache.findPrefix( prefix, TNS );
        final String expected = "abc";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, newPrefix );
    }

    private AbstractNamespacePrefixCache createNamespacePrefixCache() {
        final AbstractNamespacePrefixCache cache = new AbstractNamespacePrefixCache() {
        };
        final NamespaceNode node = createNamespaceNode();
        cache.put( PREFIX, node );
        return cache;
    }

    private AbstractNamespacePrefixCache createNamespacePrefixCacheEmpty() {
        final AbstractNamespacePrefixCache cache = new AbstractNamespacePrefixCache() {
            @Override
            public String resolvePrefix( String namespaceUri ) {
                return "abc";
            }
        };
        return cache;
    }

    private NamespaceNode createNamespaceNode() {
        final NamespaceNode node = new NamespaceNode();
        node.setName( NAME );
        node.setPrefix( PREFIX );
        node.setNamespaceUri( TNS );
        return node;
    }
}
