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
import java.nio.file.Path;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class PathUtilTestCase {
    @Test
    public void copyDirTest()
    throws IOException {
        final Path source = new File( "src/test/resources-non-filtered/gal/xunta/amtega/wsdlit/maven/plugin/util/PathUtilTestCase" ).toPath();
        final Path target = new File( "target/deleteme/PathUtilTestCase" ).toPath();
        final Path helloworld = new File( source.toFile(), "helloworld.txt" ).toPath();
        final Path hiAgain = new File( source.toFile(), "subdir/hiAgain.txt" ).toPath();

        PathUtil.copyFilesOnly( source, target );
        Assert.assertTrue(
            AssertMessages.TRUE_EXPECTED,
    Files.exists( helloworld )
            && Files.exists( hiAgain )
        );
    }

    @Test
    public void copyDirDontCopyWhenSourceDoesNotExistsTest()
    throws IOException {
        final Path source = new File( "src/test/resources/gal/xunta/amtega/wsdlit/maven/plugin/util/PathUtilTestCase" ).toPath();
        final Path target = new File( "target/deleteme/PathUtilTestCase" ).toPath();
        final Path helloworld = new File( source.toFile(), "helloworld.txt" ).toPath();
        final Path hiAgain = new File( source.toFile(), "subdir/hiAgain.txt" ).toPath();

        PathUtil.copyFilesOnly( source, target );
        Assert.assertTrue(
            AssertMessages.TRUE_EXPECTED,
    ( ! Files.exists( helloworld ) )
            && ( ! Files.exists( hiAgain ) )
        );
    }
}
