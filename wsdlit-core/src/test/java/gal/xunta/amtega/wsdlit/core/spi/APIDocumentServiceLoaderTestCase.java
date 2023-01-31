package gal.xunta.amtega.wsdlit.core.spi;

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
public class APIDocumentServiceLoaderTestCase {
    @Test
    public void defaultProviderTest() {
        final APIDocumentServiceLoader loader = new APIDocumentServiceLoader();
        final APIDocumentProvider provider = loader.getDefaultProvider();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, provider );
    }

    @Test
    public void getProviderOrDefaultReturnsDefaultWhenNoProviderFoundedTest() {
        final APIDocumentServiceLoader loader = new APIDocumentServiceLoader();
        final APIDocumentProvider provider = loader.getProviderOrDefault( "non.existant.spi.provider" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, NoopAPIDocumentProvider.class, provider.getClass() );
    }

    @Test
    public void getProviderOrDefaultReturnsDefaultWhenDefaultProviderFoundedTest() {
        final APIDocumentServiceLoader loader = new APIDocumentServiceLoader();
        final APIDocumentProvider provider = loader.getProviderOrDefault( "spi.gal.xunta.amtega.wsdlit.core.NoopAPIDocumentProvider" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, NoopAPIDocumentProvider.class, provider.getClass() );
    }
}
