package gal.xunta.amtega.wsdlit.core.i18n;

/*-
 * #%L
 * wsdlit-core
 * %%
 * Copyright (C) 2021 - 2023 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class I18nGetBaseBundleNameTestCase {
    @Test
    public void getBaseBundleNameWhenDefaultConstructor() {
        final I18n i18n = new I18n();
        final String expected = I18n.class.getName();

        Assert.assertEquals( expected, i18n.getBaseBundleName() );
    }

    @Test
    public void getBaseBundleNameWhenConstructsWithTestCase() {
        final I18n i18n = new I18n( I18nGetBaseBundleNameTestCase.class );
        final String expected = I18nGetBaseBundleNameTestCase.class.getName();

        Assert.assertEquals( expected, i18n.getBaseBundleName() );
    }

    @Test
    public void getBaseBundleNameWhenConstructsWithPath() {
        final I18n i18n = new I18n( "i18n-demo" );
        final String expected = "i18n-demo";

        Assert.assertEquals( expected, i18n.getBaseBundleName() );
    }
}
