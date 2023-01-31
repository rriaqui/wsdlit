package gal.xunta.amtega.wsdlit.core.util;

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
public class FormDefaultOtherTestCase {
    @Test
    public void fromFalseTest() {
        final FormDefault formDefault = FormDefault.from( "false" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.UNQUALIFIED, formDefault );
    }

    @Test
    public void fromNoneTest() {
        final FormDefault formDefault = FormDefault.from( "none" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.NONE, formDefault );
    }

    @Test
    public void fromNullTest() {
        final FormDefault formDefault = FormDefault.from( null );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.NONE, formDefault );
    }

    @Test
    public void fromTrueTest() {
        final FormDefault formDefault = FormDefault.from( "true" );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.QUALIFIED, formDefault );
    }


    @Test
    public void valueOfFalseTest() {
        final FormDefault formDefault = FormDefault.valueOf( Boolean.FALSE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.UNQUALIFIED, formDefault );
    }

    @Test
    public void valueOfNullTest() {
        final FormDefault formDefault = FormDefault.valueOf( (Boolean) null );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.NONE, formDefault );
    }

    @Test
    public void valueOfTrueTest() {
        final FormDefault formDefault = FormDefault.valueOf( Boolean.TRUE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, FormDefault.QUALIFIED, formDefault );
    }
}
