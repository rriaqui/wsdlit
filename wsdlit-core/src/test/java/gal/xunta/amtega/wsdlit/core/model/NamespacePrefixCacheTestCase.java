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
import java.util.Map;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class NamespacePrefixCacheTestCase {
    public static final String NAME = "name";
    public static final String PREFIX = "prefix";
    public static final String TNS = "target/name/space";
    public static final String TNS_NOT_FOUND = "target/name/space/not/found";

    @Test
    public void resolvePrefixTest() {
        final NamespacePrefixCache cache = createNamespacePrefixCache();
        final String expected = StringUtil.EMPTY;
        final String value = cache.resolvePrefix( TNS );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void updateFormDefaultValuesTest() {
        final NamespacePrefixCache cache = createNamespacePrefixCache();
        final NamespaceNode node = createNamespaceNode();
        final NamespaceNode expected = createNamespaceNode();
        cache.updateFormDefaultValues( TNS_NOT_FOUND, node );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    public NamespaceNode createNamespaceNode() {
        final NamespaceNode node = new NamespaceNode();
        node.setNamespaceUri( TNS );
        node.setPrefix( PREFIX );
        node.setName( NAME );
        return node;
    }

    private NamespacePrefixCache createNamespacePrefixCache() {
        return new NamespacePrefixCache() {
            @Override
            public boolean containsPrefix( String prefix ) {
                return false;
            }

            @Override
            public boolean containsNamespaceUri( String namespaceUri ) {
                return false;
            }

            @Override
            public String createPrefix( String prefix ) {
                return null;
            }

            @Override
            public Collection< NamespaceNode > getNamespaces() {
                return null;
            }

            @Override
            public Map< String, String > getPrefixMap() {
                return null;
            }

            @Override
            public String findNamespace( String prefix ) {
                return null;
            }

            @Override
            public NamespaceNode findPrefix( String namespaceUri ) {
                return null;
            }

            @Override
            public String findPrefix( String prefix, String namespace ) {
                return null;
            }
        };
    }
}
