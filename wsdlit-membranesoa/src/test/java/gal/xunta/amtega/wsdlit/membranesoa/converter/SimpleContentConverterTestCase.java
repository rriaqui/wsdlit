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

import com.predic8.schema.Derivation;
import com.predic8.schema.SimpleContent;
import com.predic8.schema.restriction.BaseRestriction;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import groovy.namespace.QName;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleContentConverterTestCase {
    private static final String NAME = "name";
    private static final String NAMESPACE_URI = "simple/content/uri";

    private static final String PREFIX = "sc";

    private static final String TYPE = PREFIX + ":" + NAME;

    @Test
    public void convertSimpleContentWithRestrictionTest() {
        final SimpleContentConverter converter = new SimpleContentConverter();
        final SimpleContent content = createWithRestriction();
        final ElementNode node = converter.convert( content );
        final ElementNode expected = new ElementNodeBuilder()
            .setName( NAME )
            .setPrefix( StringUtil.EMPTY )
            .setNamespaceUri( NAMESPACE_URI )
            .setType( TYPE )
            .build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    @Test
    public void convertSimpleContentWithDerivationTest() {
        final SimpleContentConverter converter = new SimpleContentConverter();
        final SimpleContent content = createWithDerivation();
        final ElementNode node = converter.convert( content );
        final ElementNode expected = new ElementNodeBuilder()
            .setName( NAME )
            .setPrefix( StringUtil.EMPTY )
            .setNamespaceUri( NAMESPACE_URI )
            .setType( TYPE )
            .build();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, node );
    }

    public SimpleContent create() {
        final SimpleContent content = Mockito.mock( SimpleContent.class );
        Mockito.when( content.getName() ).thenReturn( NAME );
        Mockito.when( content.getNamespaceUri() ).thenReturn( NAMESPACE_URI );
        return content;
    }

    public SimpleContent createWithDerivation() {
        final Derivation derivation = createDerivation();
        final SimpleContent content = Mockito.mock( SimpleContent.class );
        Mockito.when( content.getName() ).thenReturn( NAME );
        Mockito.when( content.getNamespaceUri() ).thenReturn( NAMESPACE_URI );
        Mockito.when( content.getDerivation() ).thenReturn( derivation );
        return content;
    }

    public SimpleContent createWithRestriction() {
        final BaseRestriction restriction = createRestriction();
        final SimpleContent content = Mockito.mock( SimpleContent.class );
        Mockito.when( content.getName() ).thenReturn( NAME );
        Mockito.when( content.getNamespaceUri() ).thenReturn( NAMESPACE_URI );
        Mockito.when( content.getRestriction() ).thenReturn( restriction );
        return content;
    }


    public BaseRestriction createRestriction() {
        final BaseRestriction restriction = Mockito.mock( BaseRestriction.class );
        final QName qname = new QName( NAMESPACE_URI, NAME, PREFIX );
        Mockito.when( restriction.getName() ).thenReturn( NAME );
        Mockito.when( restriction.getNamespaceUri() ).thenReturn( NAMESPACE_URI );
        Mockito.when( restriction.getBase() ).thenReturn( qname );
        return restriction;
    }

    public Derivation createDerivation() {
        final Derivation derivation = Mockito.mock( Derivation.class );
        final QName qname = new QName( NAMESPACE_URI, NAME, PREFIX );
        Mockito.when( derivation.getName() ).thenReturn( NAME );
        Mockito.when( derivation.getNamespaceUri() ).thenReturn( NAMESPACE_URI );
        Mockito.when( derivation.getBase() ).thenReturn( qname );
        return derivation;
    }
}
