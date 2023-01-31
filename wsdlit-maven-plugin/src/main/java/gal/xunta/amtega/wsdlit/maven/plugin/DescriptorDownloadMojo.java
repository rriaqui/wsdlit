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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.aether.artifact.Artifact;

import java.util.Collections;
import java.util.List;

/**
 * Download the documentation generation descriptor.
 *
 * <p>
 *      <b>WARNING</b> The descriptor and its processing are not yet available.
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
@Mojo( name="descriptor-download", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class DescriptorDownloadMojo
extends AbstractDownloadMojo {
    @Parameter( property="descriptors" )
    protected List<String> descriptors;

    private int downloadedDescriptors = 0;

    @Override
    public void execute()
        throws MojoExecutionException {
        try {
            // Este é un traballo en progreso, aínda non está rematado.
            this.downloadDescriptors();

        } catch ( final DownloadException cause ) {
            throw new MojoExecutionException( cause.getMessage(), cause );
        }
    }

    protected List< Artifact > downloadDescriptors()
    throws DownloadException {
        this.downloadedDescriptors = 0;
        if ( this.descriptors.isEmpty() ) {
            getLog().info( Messages.I18N.getString( Messages.DESCRIPTOR_REQUIRED_ERROR_KEY ) );
            return Collections.emptyList();
        } else {
            final List< Artifact > artifacts = this.artifactsDownload( this.descriptors );
            if ( artifacts.isEmpty() ) {
                final String message = "Descriptor: " + Messages.I18N.getString( Messages.ARTIFACT_REQUIRED_ERROR_KEY );
                throw new DownloadException( message );
            }
            this.downloadedDescriptors = artifacts.size();
            return artifacts;
        }
    }

    /**
     * Returns the number of descriptors downloaded in the last execution of {@code #downloadDescriptors()}.
     *
     * <p>
     *      This method is required for test cases in {@code DescriptorDownloadMojoTestCase}.
     * </p>
     *
     * @return the number of descriptors downloaded in the last execution of {@code #downloadDescriptors()}.
     */
    protected int getDownloadedDescriptors() {
        return this.downloadedDescriptors;
    }
}
