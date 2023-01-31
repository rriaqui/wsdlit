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

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class UnzipUtilSlipTestCase {
    private static final File OUTPUT_DIR = new File( "target/test-classes/unzip" );
    private static final File SLIP_ZIP_FILE = new File( OUTPUT_DIR, "../gal/xunta/amtega/wsdlit/maven/plugin/util/UnzipUtilSlipTestCase/slip.zip" );

    @BeforeClass
    public static void ensureOutputDirExists()
    throws Exception {
        OUTPUT_DIR.mkdirs();
        if ( ! OUTPUT_DIR.exists() ) {
            throw new IOException( "Non puden crear o directorio " + OUTPUT_DIR );
        }
    }

    @Test( expected = IOException.class )
    public void unzipHelloWorldZipTest()
        throws IOException {
        final File outputEmptyZipDir = Files.createTempDirectory( OUTPUT_DIR.toPath(), "helloworld" ).toFile();
        UnzipUtil.unzip( SLIP_ZIP_FILE, outputEmptyZipDir );
    }
}
