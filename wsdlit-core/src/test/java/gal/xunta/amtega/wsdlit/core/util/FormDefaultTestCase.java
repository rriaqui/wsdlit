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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class FormDefaultTestCase {
    private final FormDefault formDefault;
    private final boolean valueExpected;
    private final boolean setExpected;

    public FormDefaultTestCase(
            final FormDefault formDefault,
            final boolean setExpected,
            final boolean valueExpected
    ) {
        this.setExpected = setExpected;
        this.valueExpected = valueExpected;
        this.formDefault = formDefault;
    }

    @Test
    public void asBooleanTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, valueExpected, formDefault.asBoolean() );
    }

    @Test
    public void isNoneTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, ! setExpected, formDefault.isNone() );
    }

    @Test
    public void isSetTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, setExpected, formDefault.isSet() );
    }

    @Parameterized.Parameters
    public static Iterable< Object[] > data() {
        return Arrays.asList(
                new Object[][] {
                        { FormDefault.QUALIFIED, true, true },
                        { FormDefault.UNQUALIFIED, true, false },
                        { FormDefault.NONE, false, false }
                }
        );
    }
}
