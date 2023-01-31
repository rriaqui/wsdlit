package gal.xunta.amtega.wsdlit.maven.plugin;

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

import gal.xunta.amtega.wsdlit.maven.plugin.util.PathUtil;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Patches the documentation files obtained during the download of goal artifact {@code artifact-download}.
 * <p>
 *      The current implementation includes the application of the following patches:
 * </p>
 *
 * <ul>
 *      <li>Patch the contracts with the contents of src/main/wsdlit.</li>
 *      <li>Patch additionalDocumentation files.</li>
 * </ul>
 *
 * @author rriaqui
 * @since 1.0.0
 */
@Mojo( name="generate-resources", defaultPhase = LifecyclePhase.GENERATE_RESOURCES )
public class GenerateResourcesMojo
extends AbstractWsdlITMojo {
    private static final Logger LOGGER = LoggerFactory.getLogger( GenerateResourcesMojo.class );

    /**
     * Set this to {@code true} to bypass patching offiles.
     */
    @Parameter( name="patchSkip", property = "wsdlit.patch.skip", defaultValue = "false" )
    protected boolean patchSkip;

    @Override
    public void execute()
    throws MojoExecutionException {
        patchContracts();
    }

    /**
     * Patches contracts by copying files from src/main/wsdlit to ${project.build.directory}/wsdlit}.
     *
     * @throws MojoExecutionException if any error occurs during patching.
     */
    protected void patchContracts()
    throws MojoExecutionException {
        if ( patchSkip ) {
            LOGGER.info( Messages.I18N.getString( Messages.PATCH_SKIPPED_KEY ) , this.wsdlDirectory.getPath() );
        } else {
            final Path source = this.wsdlDirectory.toPath();
            final Path target = this.sourcesDirectory.toPath();
            LOGGER.info( Messages.I18N.getString( Messages.COPYING_FILES_FROM_TO_KEY ), source, target );

            try {
                PathUtil.copyFilesOnly( source, target );
                LOGGER.info( Messages.I18N.getString( Messages.COPYING_FILES_FROM_TO_ENDED_KEY ), source, target );

            } catch ( final IOException cause ) {
                final String message = Messages.I18N.format( Messages.COPYING_FILES_FROM_TO_ERROR_KEY, source, target );
                throw new MojoExecutionException( message, cause );
            }
        }
    }
}
