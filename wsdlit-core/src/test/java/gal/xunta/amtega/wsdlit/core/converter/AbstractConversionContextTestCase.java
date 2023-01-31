package gal.xunta.amtega.wsdlit.core.converter;

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

import java.io.File;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractConversionContextTestCase {
    private static final String URI = "http://gal.xunta.amtega/wsdlit";
    private static final File WSDL_FILE = new File( "target/wsdls/service.wsdl" );

    @Test
    public void getUriTest() {
        final AbstractConversionContext< VoidAbstractConversionContext > abstractConversionContext = createConversionContext();
        Assert.assertEquals( AssertMessages.URL_NOT_EXPECTED, URI, abstractConversionContext.getUri() );
    }

    @Test
    public void getWsdlFileTest() {
        final AbstractConversionContext<VoidAbstractConversionContext> abstractConversionContext = createConversionContext();
        Assert.assertEquals( AssertMessages.FILE_NOT_EXPECTED, WSDL_FILE, abstractConversionContext.getWsdlFile() );
    }

    private AbstractConversionContext<VoidAbstractConversionContext> createConversionContext() {
        final AbstractConversionContext<VoidAbstractConversionContext> abstractConversionContext = new VoidAbstractConversionContext();
        abstractConversionContext.setUri( URI );
        abstractConversionContext.setWsdlFile( WSDL_FILE );
        return abstractConversionContext;
    }
}
