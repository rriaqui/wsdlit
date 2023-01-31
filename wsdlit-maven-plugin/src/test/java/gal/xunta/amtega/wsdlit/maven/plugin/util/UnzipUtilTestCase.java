package gal.xunta.amtega.wsdlit.maven.plugin.util;

/*-
 * #%L
 * AMTEGA WsdlIT Maven Plugin
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

import gal.xunta.amtega.wsdlit.maven.plugin.AssertMessages;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class UnzipUtilTestCase {
    private static final File OUTPUT_DIR = new File( "target/test-classes/unzip" );
    private static final File HELLO_WORLD_ZIP_FILE = new File( OUTPUT_DIR, "../gal/xunta/amtega/wsdlit/maven/plugin/util/UnzipUtilTestCase/helloworld.zip" );

    @BeforeClass
    public static void ensureOutputDirExists()
    throws Exception {
        OUTPUT_DIR.mkdirs();
        if ( ! OUTPUT_DIR.exists() ) {
            throw new IOException( "Non puden crear o directorio " + OUTPUT_DIR );
        }
    }

    @Test
    public void unzipHelloWorldZipTest()
        throws IOException {
        final File outputEmptyZipDir = Files.createTempDirectory( OUTPUT_DIR.toPath(), "helloworld" ).toFile();
        UnzipUtil.unzip( HELLO_WORLD_ZIP_FILE, outputEmptyZipDir );
        final File[] files = outputEmptyZipDir.listFiles();
        final File mDir = new File( outputEmptyZipDir, "m");
        final File[] expectedFiles = new File[] { mDir };
        Assert.assertArrayEquals( "Erro nos arquivos esperados en " + outputEmptyZipDir, expectedFiles, files );
    }

    @Test
    public void unzipHelloWorldZipContainsFileTest()
        throws IOException {
        final File outputEmptyZipDir = Files.createTempDirectory( OUTPUT_DIR.toPath(), "helloworld" ).toFile();
        UnzipUtil.unzip( HELLO_WORLD_ZIP_FILE, outputEmptyZipDir );
        final File mDir = new File( outputEmptyZipDir, "m");
        final File[] files = mDir.listFiles();
        final File[] expectedFiles = new File[] { new File( mDir, "hello.txt" ) };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expectedFiles, files );
    }

    @Test( expected = IOException.class )
    public void unzipWhenMIsFileTest()
        throws IOException {
        final File outputEmptyZipDir = Files.createTempDirectory( OUTPUT_DIR.toPath(), "helloworld" ).toFile();
        final File mDir = new File( outputEmptyZipDir, "m");
        // Creamos un arquivo de nome 'm' para provocar o IOException
        mDir.createNewFile();
        UnzipUtil.unzip( HELLO_WORLD_ZIP_FILE, outputEmptyZipDir );
        Assert.fail( "Esperábase un IOException por non poder crear o directorio 'm' por existir como arquivo" );
    }

    @Test
    public void unzipWhenMIsDirectoryTest()
        throws IOException {
        final File outputEmptyZipDir = Files.createTempDirectory( OUTPUT_DIR.toPath(), "helloworld" ).toFile();
        final File mDir = new File( outputEmptyZipDir, "m");
        // Creamos un arquivo de nome 'm' para provocar o IOException
        final boolean createdMDir = mDir.mkdir();
        Assume.assumeTrue( createdMDir );
        UnzipUtil.unzip( HELLO_WORLD_ZIP_FILE, outputEmptyZipDir );

        final File[] files = mDir.listFiles();
        final File[] expectedFiles = new File[] { new File( mDir, "hello.txt" ) };
        Assert.assertArrayEquals( AssertMessages.ARRAY_EQUALS_EXPECTED, expectedFiles, files );
    }
}
