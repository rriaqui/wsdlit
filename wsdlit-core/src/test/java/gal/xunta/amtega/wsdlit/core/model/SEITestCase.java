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
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class SEITestCase {
    private static final String SERVICE = "http://localhost/service";
    private static final String INTERFACE = "http://localhost/interface";
    private static final String ENDPOINT = "endpoint{TheEndPoint}";


    @Test
    public void getEndpointTest() {
        final SEI sei = new SEI( INTERFACE, ENDPOINT, SERVICE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, ENDPOINT, sei.getEndpoint() );
    }

    @Test
    public void getInterfaceTest() {
        final SEI sei = new SEI( INTERFACE, ENDPOINT, SERVICE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, INTERFACE, sei.getInterface() );
    }

    @Test
    public void getServiceTest() {
        final SEI sei = new SEI( INTERFACE, ENDPOINT, SERVICE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, SERVICE, sei.getService() );
    }
}
