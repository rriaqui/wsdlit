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

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ConverterExceptionTestCase {
    private static final String MESSAGE = "Erro descoñecido";
    private static final Exception CAUSE = new NullPointerException( "O valor de code non pode ser nulo" );

    @Test
    public void emptyConstructorTest() {
        final ConverterException exception = new ConverterException();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, exception );
    }

    @Test
    public void messageConstructorTest() {
        final ConverterException exception = new ConverterException( MESSAGE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MESSAGE, exception.getMessage() );
    }

    @Test
    public void exceptionConstructorTest() {
        final ConverterException cause = new ConverterException( CAUSE );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, CAUSE, cause.getCause() );
    }

    @Test
    public void messageAndCauseConstructorTest() {
        final ConverterException cause = new ConverterException( MESSAGE, CAUSE );
        Assert.assertTrue( "Esperábase unha excepción cun valor diferente.",
        CAUSE.equals( cause.getCause() )
                && MESSAGE.equals( cause.getMessage() )
        );
    }
}
