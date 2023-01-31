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

import java.util.*;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class FileProcessorManagerTestCase {

    @Test
    public void constructorTest() {
        final List< FileProcessor > fileProcessors = createFileProcessorList();
        final FileProcessorManager fpm = new FileProcessorManager( fileProcessors );
        final FileProcessor[] expected = {
            createFileProcessor( "index" ),
            createFileProcessor( "service" )
        };
        final FileProcessor[] value = fpm.getList().toArray( new FileProcessor[0] );

        Assert.assertArrayEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void equalsToItselfTest() {
        final FileProcessorManager fpm = createFileProcessorManager();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, fpm, fpm );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToDifferentClassTest() {
        final FileProcessorManager fpm = createFileProcessorManager();
        final Object object = "Hello world";
        final boolean value = fpm.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenComparedToNullTest() {
        final FileProcessorManager fpm = createFileProcessorManager();
        final Object object = null;
        final boolean value = fpm.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsFalseWhenDifferentFileProcessorsClassTest() {
        final FileProcessorManager fpm = createFileProcessorManager();
        final List< FileProcessor > fileProcessors = Collections.emptyList();
        final FileProcessorManager object = new FileProcessorManager( fileProcessors );

        final boolean value = fpm.equals( object );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, value );
    }

    @Test
    public void equalsReturnsTrueWhenComparedToItselfTest() {
        final FileProcessorManager fpm = createFileProcessorManager();
        final FileProcessorManager secondary = fpm;
        Assert.assertSame( AssertMessages.SAME_EXPECTED, fpm, secondary );
    }

    @Test
    public void hashCodeTest() {
        final List<FileProcessor> fileProcessors = createFileProcessorList();
        final FileProcessorManager fpm = new FileProcessorManager( fileProcessors );

        final int expected = Objects.hash( fileProcessors );
        final int value = fpm.hashCode();

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, value );
    }

    @Test
    public void processTest()
    throws ProcessException {
        final FileProcessorManager fpm = createFileProcessorManager();
        final Map<String, Object> model = new HashMap<>();

        fpm.process( model );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, fpm );
    }

    private List<FileProcessor> createFileProcessorList() {
        final List<FileProcessor> fileProcessors = Arrays.asList(
            createFileProcessor( "index" ),
            createFileProcessor( "service" )
        );
        return fileProcessors;
    }

    private FileProcessor createFileProcessor( final String filename ) {
        return new AbstractFileProcessor(
            new Context(),
            filename,
            filename
        ) {};
    }

    public FileProcessorManager createFileProcessorManager() {
        final List<FileProcessor> fileProcessors = createFileProcessorList();
        return new FileProcessorManager( fileProcessors );
    }
}
