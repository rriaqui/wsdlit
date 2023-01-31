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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utilities related to {@code java.nio.file.Path},
 * how to ensure that the parent directory exists
 * or copy only the non-whaling directories.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class PathUtil {
    private PathUtil() {}

    /**
     * Copy the directories that have some file.
     *
     * @param source the path to copy.
     * @param target the path where the files will be copied.
     * @throws IOException if any error occurs.
     */
    public static void copyFilesOnly( final Path source, final Path target )
    throws IOException {
        if ( source.toFile().isDirectory() ) {
            try ( final Stream<Path> stream = Files.walk( source ) ) {
                final List<Path> pathsToCopy = stream.collect( Collectors.toList() );
                for ( final Path pathToCopy : pathsToCopy ) {
                    final Path pathRelative = target.resolve( source.relativize( pathToCopy ) );
                    copyFileOnly( pathToCopy, pathRelative );
                }
            }
        }
    }

    /**
     * Copies a file to a new path,
     * overwriting it.
     *
     * @param source the file to copy.
     * @param target the new path where the file will be copied.
     * @throws IOException if any error occurs .
     */
    public static void copyFileOnly( final Path source, final Path target )
    throws IOException {
        Objects.requireNonNull( source );
        Objects.requireNonNull( target );
        if ( source.toFile().exists() && ( ! target.toFile().isDirectory() ) ) {
            ensureParentDirectoryExists( target );
            Files.copy( source, target, StandardCopyOption.REPLACE_EXISTING );
        }
    }

    /**
     * Create the parent directory of a path if it does not exist.
     *
     * @param path the parent directory of a path if it does not exist.
     * @throws IOException if any error occurs.
     */
    public static void ensureParentDirectoryExists( final Path path )
    throws IOException {
        final Path parent = path.getParent();
        if ( parent != null && ! parent.toFile().exists() ) {
            Files.createDirectories( parent );
        }
    }
}
