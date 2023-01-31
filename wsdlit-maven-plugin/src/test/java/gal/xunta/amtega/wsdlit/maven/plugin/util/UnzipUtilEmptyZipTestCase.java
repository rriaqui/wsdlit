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
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class UnzipUtilEmptyZipTestCase {
    private static final File OUTPUT_DIR = new File( "target/test-classes/gal/xunta/amtega/wsdlit/maven/plugin/util/UnzipUtilEmptyZipTestCase" );
    private static final File DIR_NOT_EXISTS = new File( "/this/path/does/not/exists" );

    @Test( expected = IOException.class )
    public void unzipFileNotExistsTest()
        throws IOException {
        final File zipFile = new File( DIR_NOT_EXISTS, "gal/xunta/amtega/wsdlit/maven/plugin/util/UnzipUtilEmptyZipTestCase/empty.zip");
        final File outputEmptyZipDir = Files.createTempDirectory( OUTPUT_DIR.toPath(), "emptyzip" ).toFile();
        final File outputEmptyZip = new File( outputEmptyZipDir, "empty.zip.dir" );
        UnzipUtil.unzip( zipFile, outputEmptyZip );
    }

    @Test( expected = IOException.class )
    public void unzipEmptyZipInReadOnlyDirTest()
        throws IOException {
        final File zipFile = new File( OUTPUT_DIR, "gal/xunta/amtega/wsdlit/maven/plugin/util/UnzipUtilEmptyZipTestCase/empty.zip");
        final File outputEmptyZipDir = Files.createTempDirectory( DIR_NOT_EXISTS.toPath(), "emptyzip" ).toFile();
        final File outputEmptyZip = new File( outputEmptyZipDir, "empty.zip.dir" );
        UnzipUtil.unzip( zipFile, outputEmptyZip );
    }

    @Test
    public void unzipEmptyZipTest()
        throws IOException {
        final File zipFile = new File( OUTPUT_DIR, "empty.zip");
        final File outputEmptyZipDir = Files.createTempDirectory( OUTPUT_DIR.toPath(), "emptyzip" ).toFile();
        final File outputEmptyZip = new File( outputEmptyZipDir, "empty.zip.dir" );
        UnzipUtil.unzip( zipFile, outputEmptyZip );
        final File[] files = outputEmptyZip.listFiles();
        Assert.assertNull( AssertMessages.NULL_EXPECTED, files );
    }
}
