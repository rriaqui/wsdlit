package gal.xunta.amtega.wsdlit.core.engine.freemarker;

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

import freemarker.template.Configuration;
import gal.xunta.amtega.wsdlit.core.AssertMessages;
import gal.xunta.amtega.wsdlit.core.engine.api.AbstractFileProcessor;
import gal.xunta.amtega.wsdlit.core.engine.api.Context;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractFreemarkerFileProcessorTestCase {
    @Test
    public void constructorWithContextStringTest() {
        final Context context = new Context();
        final AbstractFreemarkerFileProcessor freemarkerFileProcessor = new AbstractFreemarkerFileProcessor(
            context,
            "plantilla-proba"
        ) {};

        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, freemarkerFileProcessor.getContext() );
    }
    @Test
    public void constructorWithContextStringStringTest() {
        final Context context = new Context();
        final AbstractFreemarkerFileProcessor freemarkerFileProcessor = new AbstractFreemarkerFileProcessor(
                context,
                "plantilla-proba",
                "target/arquivo-de-saida.txt"
        ) {};

        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, freemarkerFileProcessor.getContext() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void constructorWhenTemplateFileNotExistsThrowsIOExceptionTest() {
        final Context context = new Context( "/este/path/non/existe" );
        final AbstractFreemarkerFileProcessor freemarkerFileProcessor = new AbstractFreemarkerFileProcessor(
                context,
                "esta/plantilla/non/existe",
                "target/arquivo-de-saida.txt"
        ) {};
    }

    @Test
    public void equalsReturnsFalseWhenComparedToDifferentClassTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final Object object = "Hello world";
        final boolean value = affp.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsToItselfTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, affp, affp );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToNullTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final Object object = null;
        final boolean value = affp.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenSuperIsNotTheSameTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final AbstractFreemarkerFileProcessor object = createAbstractFreemarkerFileProcessor( "index", "target/index" );
        final boolean value = affp.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenTemplateFilenameIsNotTheSameTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final AbstractFreemarkerFileProcessor object = createAbstractFreemarkerFileProcessor( "api/index", "index" );
        final boolean value = affp.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsTrueWhenComparedToItselfTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final AbstractFreemarkerFileProcessor object = affp;

        Assert.assertSame( AssertMessages.SAME_EXPECTED, affp, object );
    }

    @Test
    public void equalsReturnsTrueWhenComparedToSameDataTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final AbstractFreemarkerFileProcessor object = createAbstractFreemarkerFileProcessor();
        final boolean value = affp.equals( object );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, value );
    }

    @Test
    public void getConfigurationReturnsNotNullTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final Configuration config = affp.getConfiguration();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, config );
    }

    @Test
    public void hashCodeTest() {
        final AbstractFreemarkerFileProcessor affp = createAbstractFreemarkerFileProcessor();
        final AbstractFileProcessor afp = new AbstractFileProcessor(
            new Context(),
            "index",
            "index"
        ) {};
        final int expected = Objects.hash( afp.hashCode(), affp.getTemplateFilename() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, affp.hashCode() );
    }

    public AbstractFreemarkerFileProcessor createAbstractFreemarkerFileProcessor() {
        return createAbstractFreemarkerFileProcessor(
            "index", "index"
        );
    }

    public AbstractFreemarkerFileProcessor createAbstractFreemarkerFileProcessor(
        final String templateFilename, final String outputFilename
    ) {
        final Context context = new Context();
        return new AbstractFreemarkerFileProcessor(
            context, templateFilename, outputFilename
        ) {};
    }
}
