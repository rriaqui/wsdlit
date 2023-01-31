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

import gal.xunta.amtega.wsdlit.maven.plugin.Messages;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * Unzip utils.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class UnzipUtil {

    private UnzipUtil() {
    }

    /**
     * Unzip a specified zip file in a specific directory.
     *
     * @param file the specified zip file to unzip.
     * @param outputDir the specified directory.
     * @throws IOException if Slip vulnerability or an IO error is detected.
     */
    public static void unzip( final File file, final File outputDir )
    throws IOException {
        try ( final ZipFile zipFile = new ZipFile( file ) ) {
            final Enumeration< ? extends ZipEntry > entries = zipFile.entries();
            final Path outputPath = outputDir.toPath();
            while ( entries.hasMoreElements() ) {
                final ZipEntry zipEntry = entries.nextElement();
                final boolean isDirectory = zipEntry.getName().endsWith( File.separator );
                if ( !isDirectory ) {
                    final InputStream is = new BufferedInputStream( zipFile.getInputStream( zipEntry ) );
                    final Path newPath = resolveZipEntry( outputPath, zipEntry );
                    PathUtil.ensureParentDirectoryExists( newPath );
                    Files.copy( is, newPath, StandardCopyOption.REPLACE_EXISTING );
                    is.close();
                }
            }
        }
    }

    /**
     * Resolve the  {@link File} on the specified output directory
     * where the specified zip entry will be written,
     * and checking for SLIP vulnerability
     *
     * @param outputDir the specified output directory.
     * @param zipEntry the specified entry.
     * @return a {@code File} where the contents of the specified zip entry will be written.
     * @throws IOException if SLIP vulnerability is detected.
     *
     * @since 1.0.0
     */
    private static Path resolveZipEntry( final Path outputDir, final ZipEntry zipEntry )
    throws IOException {
        final Path resolved = outputDir.resolve( zipEntry.getName() );
        final Path normalizedPath = resolved.normalize();

        if ( !normalizedPath.startsWith( outputDir ) ) {
            throw new IOException(Messages.I18N.format( Messages.ZIP_SLIP_ERROR, zipEntry.getName() ) );
        }
        return normalizedPath;
    }
}
