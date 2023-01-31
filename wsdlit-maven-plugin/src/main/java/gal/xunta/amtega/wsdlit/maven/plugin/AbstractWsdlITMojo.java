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

import gal.xunta.amtega.wsdlit.maven.plugin.diff.DiffActionOnError;
import gal.xunta.amtega.wsdlit.maven.plugin.util.PathUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract Mojo for wsdlit Mojos.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractWsdlITMojo
extends AbstractMojo {
    /**
     * Suffix to be added to the normalized name of the artifact into which the previous version of the artifact will be unpacked.
     */
    protected static final String DIFF_DIRECTORY_SUFFIX = ".diff";


    /**
     * Directory in which the wsdl files indicated by relative paths are found.
     *
     * <p>
     *      This directory acts as a way to simplify the location of the files designated in the parameter
     *      {@code wsdls/wsdl} when specified relatively.
     * </p>
     */
    @Parameter( property = "wsdlit.directory", defaultValue = MojoDefaultValues.WSDL_DIRECTORY_SOURCE, required = true )
    protected File wsdlDirectory;

    /**
     * Directory where the asciidoctor files are gemerated.
     */
    @Parameter( property = "wsdlit.sourceDirectory", defaultValue = "${project.build.directory}/generated-sources/adoc", required = true )
    protected File sourceDirectory;

    /**
     * Directory containing the templates that will be used to create the Maven project.
     */
    @Parameter( property = "wsdlit.templatesDirectory", defaultValue = MojoDefaultValues.TEMPLATES_DIR_SOURCE, required=true )
    protected String templatesDirectory;

    /**
     * Directory where all wsdl files will be copied.
     *
     * <p>
     *      All the wsdl files that are downloaded by means of the descriptors and those that exist in the directory
     *      indicated by parameters {@code ${wsdlit.sourceDirectory}} will be copied to this directory to unify their
     *      processing.
     * </p>
     */
    @Parameter( property = "wsdlit.sources.directory", defaultValue="${project.build.directory}/wsdlit", required=true )
    protected File sourcesDirectory;

    /**
     * The maven project.
     */
    @Parameter(readonly = true, defaultValue="${project}")
    protected MavenProject project;

    /**
     * Set this to @{code true} to bypass creation of the difference report.
     */
    @Parameter( property="wsdlit.diff.skip", defaultValue = "true" )
    protected boolean diffSkip;

    /**
     * Action to be taken when an error is detected during processes related to differences between contracts,
     * including download tasks for previous versions.
     */
    @Parameter( property="wsdlit.diff.on.error", defaultValue = "BUILD_BREAK" )
    protected DiffActionOnError diffOnError;

    /**
     * File that stores the relationship between an artifact and its previous version
     */
    @Parameter( defaultValue = "${project.build.directory}/wsdlit.diff.properties", readonly = true )
    protected File diffPropertiesFile;

    /**
     * Copy the additional files present in the {@code wsdlit} directory to the api generation directory.
     *
     * @throws MojoExecutionException on error.
     */
    protected void copyAdditionalFiles()
    throws MojoExecutionException {
        if ( this.sourcesDirectory.isDirectory() ) {
            try( Stream<Path> list = Files.list( this.sourcesDirectory.toPath() ) ) {
                final List<Path> paths = list.collect( Collectors.toList() );

                for ( final Path path : paths ) {
                    final Path source = new File( path.toFile(), "wsdlit" ).toPath();
                    copyDirToApi( source );
                }

            } catch( final IOException cause ){
                throw new MojoExecutionException( cause.getMessage(), cause );
            }
        }
    }

    protected void copyDirToApi( final Path source )
    throws IOException {
        if ( source.toFile().isDirectory() ) {
            final Path target = new File( this.sourceDirectory, "api" ).toPath();
            PathUtil.copyFilesOnly( source, target );
        }
    }
}
