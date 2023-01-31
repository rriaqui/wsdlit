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
import gal.xunta.amtega.wsdlit.core.sort.SortAlgorithms;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class APIDocumentTestCase {
    private static final Date DATE = new Date();
    private static final String DOCUMENTATION = "Documentation";
    private static final String TITLE = "Title";
    private static final String VERSION = "1";

    @Test
    public void getDateTest() {
        final APIDocument wsdl = createApiDocument();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, DATE, wsdl.getDate() );
    }

    @Test
    public void getTitleTest() {
        final APIDocument wsdl = createApiDocument();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TITLE, wsdl.getTitle() );
    }

    @Test
    public void getVersionTest() {
        final APIDocument wsdl = createApiDocument();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, VERSION, wsdl.getVersion() );
    }

    @Test
    public void getApisIsEmptyTest() {
        final APIDocument wsdl = createApiDocument();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, wsdl.getServices().isEmpty() );
    }

    public APIDocument createApiDocument() {
        return createApiDocument( Collections.emptyList() );
    }

    public APIDocument createApiDocument( final List< ServiceNode > apis) {
        final APIDocument APIDocument = new APIDocument( apis );
        return APIDocument
            .setDate( DATE )
            .setTitle( TITLE )
            .setVersion( VERSION );
    }

    public List< ServiceNode > createUnsortedApis() {
        final List< ServiceNode > apis = new ArrayList<>();
        apis.add(
            new ServiceNode( "helloworld", "http://emprego.xunta.es/xsd/wsdlit/examples/helloworld", Collections.emptyList() )
        );

        apis.add(
            new ServiceNode( "greeting", "http://emprego.xunta.es/xsd/wsdlit/examples/greeting", Collections.emptyList() )
        );
        return apis;
    }

    @Test
    public void defaultSortTest() {
        final APIDocument api = createApiDocument( createUnsortedApis() );
        final String expected = "helloworld";

        final List< ServiceNode > services = api.getServices();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, services.get( 0 ).getName() );
    }

    @Test
    public void sortTest() {
        final APIDocument api = createApiDocument( createUnsortedApis() );
        final String expected = "helloworld";

        api.sort( SortAlgorithms.SORT_BY_NAME );
        final List< ServiceNode > services = api.getServices();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, services.get( 1 ).getName() );
    }

    @Test
    public void unsortTest() {
        final APIDocument api = createApiDocument( createUnsortedApis() );
        final String expected = "helloworld";

        api.sort( SortAlgorithms.SORT_BY_NAME );
        api.sort( SortAlgorithms.NONE );
        final List< ServiceNode > services = api.getServices();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, services.get( 0 ).getName() );
    }

}
