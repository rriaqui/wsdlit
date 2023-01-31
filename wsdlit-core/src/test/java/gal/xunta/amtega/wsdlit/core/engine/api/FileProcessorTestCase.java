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
import java.util.Map;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class FileProcessorTestCase {

    @Test
    public void bodyTest()
    throws ProcessException {
        final FileProcessor fileProcessor = createFileProcessor();
        fileProcessor.body( null );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, fileProcessor );
    }

    @Test
    public void endTest() {
        final FileProcessor fileProcessor = createFileProcessor();
        fileProcessor.end( null );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, fileProcessor );
    }

    @Test
    public void startTest()
    throws ProcessException {
        final FileProcessor fileProcessor = createFileProcessor();
        fileProcessor.start( null );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, fileProcessor );
    }

    @Test
    public void processTest()
    throws ProcessException {
        final FileProcessor fileProcessor = createFileProcessor();
        fileProcessor.process( null );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, fileProcessor );
    }

    @Test( expected = ProcessException.class )
    public void processThrowsExceptionWhenStartThrowsExceptionTest()
    throws ProcessException {
        final FileProcessor fileProcessor = createFileProcessorStartThrowsException();
        fileProcessor.process( null );
    }

    private FileProcessor createFileProcessor() {
        return new FileProcessor() {
            @Override
            public File getOutputFile() {
                return null;
            }

            @Override
            public String getOutputFilename() {
                return null;
            }

            @Override
            public String getTemplateFilename() {
                return null;
            }

            @Override
            public Context getContext() {
                return null;
            }
        };
    }

    private FileProcessor createFileProcessorStartThrowsException() {
        return new FileProcessor() {
            @Override
            public void start( final Map<String, Object> model )
            throws ProcessException {
                throw new ProcessException( new IllegalArgumentException() );
            }

            @Override
            public File getOutputFile() {
                return null;
            }

            @Override
            public String getOutputFilename() {
                return null;
            }

            @Override
            public String getTemplateFilename() {
                return null;
            }

            @Override
            public Context getContext() {
                return null;
            }
        };
    }
}
