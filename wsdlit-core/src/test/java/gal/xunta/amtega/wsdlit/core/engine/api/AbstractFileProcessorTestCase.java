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

import java.io.File;
import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AbstractFileProcessorTestCase {
    @Test
    public void constructorTest() {
        final AbstractFileProcessor fileProcessor = createFileProcessor();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, fileProcessor );
    }

    @Test
    public void getContextTest() {
        final AbstractFileProcessor fileProcessor = createFileProcessor();
        final Context context = fileProcessor.getContext();
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, context );
    }

    @Test
    public void getOutputFileTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final File outputPath = new File( System.getProperty( "java.io.tmpdir" ), "output" );
        final File expected = new File( outputPath, "template.adoc" );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, afp.getOutputFile() );
    }
    @Test
    public void getOutputFilenameTest() {
        final AbstractFileProcessor fileProcessor = createFileProcessor();
        final String expected = "template.adoc";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, fileProcessor.getOutputFilename() );
    }

    @Test
    public void getTemplateFilenameTest() {
        final AbstractFileProcessor fileProcessor = createFileProcessor();
        final String expected = "template.ftl";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, fileProcessor.getTemplateFilename() );
    }

    @Test
    public void equalsToItselfTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, afp, afp );
    }

    @Test
    public void equalsReturnsFalseWhenCompareToDifferentClassTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final Object object = "Hello world";
        final boolean value = afp.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToNullTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final Object object = null;
        final boolean value = afp.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenContextIsDifferentTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final AbstractFileProcessor notExpected = createFileProcessor(
            new Context( "another/path" ),
            "index", "index"
        );
        Assert.assertNotEquals( AssertMessages.FALSE_EXPECTED, notExpected, afp );
    }

    @Test
    public void equalsReturnsFalseWhenTemplateFilenameIsDifferentTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final AbstractFileProcessor notExpected = createFileProcessor(
            new Context(),
            "differentTemplateFilename", "index"
        );
        Assert.assertNotEquals( AssertMessages.FALSE_EXPECTED, notExpected, afp );
    }

    @Test
    public void equalsReturnsFalseWhenOutputFilenameIsDifferentTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final AbstractFileProcessor notExpected = createFileProcessor(
            new Context(),
            "index", "outputFilenameIsDifferent"
        );
        Assert.assertNotEquals( AssertMessages.FALSE_EXPECTED, notExpected, afp );
    }

    @Test
    public void equalsReturnsTrueWhenCompareToItselfTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final AbstractFileProcessor secondary = afp;
        Assert.assertSame( AssertMessages.SAME_EXPECTED, secondary, afp );
    }

    @Test
    public void equalsReturnsTrueWhenCompareToSameDataTest() {
        final AbstractFileProcessor afp = createFileProcessor();
        final AbstractFileProcessor secondary = createFileProcessor();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, secondary, afp );
    }

    @Test
    public void hashCodeTest() {
        final Context context = new Context();
        final String outputFilename = "template.adoc";
        final String templateFilename = "template.ftl";

        final AbstractFileProcessor afp = createFileProcessor();
        final int value = afp.hashCode();
        final int expected = Objects.hash( context, outputFilename, templateFilename );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    public AbstractFileProcessor createFileProcessor() {
        return createFileProcessor( new Context(), "template", "template" );
    }

    public AbstractFileProcessor createFileProcessor(
        final Context context,
        final String templateFilename,
        final String outputFilename
    ) {
        return new AbstractFileProcessor( context,templateFilename, outputFilename) {};
    }
}
