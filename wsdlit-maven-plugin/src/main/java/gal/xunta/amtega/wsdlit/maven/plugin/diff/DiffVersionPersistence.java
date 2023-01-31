package gal.xunta.amtega.wsdlit.maven.plugin.diff;

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
import gal.xunta.amtega.wsdlit.maven.plugin.version.ArtifactRelations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author rriaqui
 * @since 2.0.0
 */
public class DiffVersionPersistence {
    private static final Logger LOGGER = LoggerFactory.getLogger( DiffVersionPersistence.class );
    private final DiffActionOnError diffOnError;

    /**
     * Constructs a {@code DiffVersionPersistence} with the specified value of the action to execute
     * if any error is encountered while writing the file.
     *
     * @param diffOnError the specified value of the action to execute in case an error is encountered while writing the file.
     */
    public DiffVersionPersistence( final DiffActionOnError diffOnError ) {
        this.diffOnError = diffOnError;
    }

    /**
     * Writes a properties file that maps the original artifact against the artifact of the previous version.
     *
     * <p>
     * current_group:artifactId:classifier:packaging:version.1=previous_group:artifactId:classifier:packaging:version.1
     * current_group:artifactId:classifier:packaging:version.2=previous_group:artifactId:classifier:packaging:version.2
     * </p>
     *
     * @param relations instance of the object that manages the relations between artifacts.
     * @param target where the properties will be written.
     * @return {@code true} if the configuration file was written successfully.
     *
     * @throws IOException if there is an error writing the file.
     */
    public boolean writePreviousVersionPropertiesFile(final ArtifactRelations relations, final File target )
    throws IOException {
        try {
            relations.store( target );
            return true;

        } catch ( final IOException cause ) {
            final String message = Messages.I18N.format( Messages.FILE_WRITING_ERROR, target.getAbsolutePath() );
            if ( this.diffOnError == DiffActionOnError.BUILD_BREAK) {
                LOGGER.error( message, cause.getLocalizedMessage() );
                throw new IOException( message, cause );
            } else {
                LOGGER.warn( message, cause );
                return false;
            }
        }
    }
}
