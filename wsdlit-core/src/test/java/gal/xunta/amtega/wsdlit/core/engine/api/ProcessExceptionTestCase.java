package gal.xunta.amtega.wsdlit.core.engine.api;

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
public class ProcessExceptionTestCase {
    private static final String EXPECTED_MESSAGE = "Hello world";
    private static final Throwable EXPECTED_CAUSE = new NullPointerException();

    @Test
    public void defaultConstructorTest() {
        final ProcessException cause = new ProcessException();

        Assert.assertTrue(
            AssertMessages.TRUE_EXPECTED,
    cause.getMessage() == null
            && cause.getCause() == null
        );
    }

    @Test
    public void constructorWithMessageTest() {
        final ProcessException cause = new ProcessException( EXPECTED_MESSAGE );

        Assert.assertEquals(
            AssertMessages.EQUALS_EXPECTED, EXPECTED_MESSAGE, cause.getMessage()
        );
    }

    @Test
    public void constructorWithThrowableTest() {
        final ProcessException cause = new ProcessException( EXPECTED_CAUSE );

        Assert.assertEquals(
            AssertMessages.EQUALS_EXPECTED, EXPECTED_CAUSE, cause.getCause()
        );
    }

    @Test
    public void constructorWithMessageAndThrowableTest() {
        final ProcessException cause = new ProcessException( EXPECTED_MESSAGE, EXPECTED_CAUSE );

        Assert.assertTrue(
            AssertMessages.TRUE_EXPECTED,
    EXPECTED_MESSAGE.equals( cause.getMessage() )
            && EXPECTED_CAUSE.equals( cause.getCause() )
        );
    }
}
