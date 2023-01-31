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

import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import groovy.namespace.QName;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class AbstractConverterTestCase {
    private static final String LOCAL_PART = "localPart";
    private static final String NAMESPACE_URI = "gal.xunta.amtega.wsdlit.example.uri";
    private static final String PREFIX = "p";

    @Test( expected = IllegalAccessError.class )
    public void convertTest() {
        final AbstractConverter<String, Integer> converter = createAbstractConverter();
        converter.convert( "1", false );
    }

    @Test
    public void getTypeWhenQNameIsNullTest() {
        final AbstractConverter<String, Integer> converter = createAbstractConverter();
        final String expected = StringUtil.EMPTY;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, converter.getType( null ) );
    }

    @Test
    public void getTypeTest() {
        final AbstractConverter<String, Integer> converter = createAbstractConverter();
        final AbstractConverter<String, Integer> spied = Mockito.spy( converter );
        final String expected = PREFIX + ":" + LOCAL_PART;
        final QName qname = new QName( NAMESPACE_URI, LOCAL_PART );
        Mockito.doReturn( PREFIX ).when( spied ).findPrefix( Mockito.anyString(), Mockito.anyString() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, spied.getType( qname ) );
    }

    @Test
    public void setPostConversionProcessorTest() {
        final AbstractConverter<String, Integer> converter = createAbstractConverter();

        converter.setPostConversionProcessor( null );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, converter );
    }

    public AbstractConverter<String, Integer> createAbstractConverter() {
        return new AbstractConverter< String, Integer >() {
            @Override
            public Integer convert( String item ) {
                return null;
            }
        };
    }
}
