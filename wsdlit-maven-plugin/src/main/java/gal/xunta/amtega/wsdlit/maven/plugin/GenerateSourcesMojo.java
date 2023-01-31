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

import gal.xunta.amtega.wsdlit.core.engine.api.Context;
import gal.xunta.amtega.wsdlit.core.engine.api.FileProcessorManager;
import gal.xunta.amtega.wsdlit.core.engine.api.ProcessException;
import gal.xunta.amtega.wsdlit.core.engine.freemarker.DefaultTemplatesFactory;
import gal.xunta.amtega.wsdlit.core.engine.freemarker.functions.FreemarkerFunctionsUtil;
import gal.xunta.amtega.wsdlit.core.model.APIDocument;
import gal.xunta.amtega.wsdlit.core.sort.SortAlgorithms;
import gal.xunta.amtega.wsdlit.core.spi.APIDocumentProvider;
import gal.xunta.amtega.wsdlit.core.spi.APIDocumentServiceLoader;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mojo for creating the documentation source ({@code .adoc}) files from the service contracts.
 * <p>
 *      Creates the contents of the directory structure and files to be used during the conversion
 *      to the desired formats based on the service contracts.
 * </p>
 *
 * @author rriaqui
 * @since 1.0
 */
@Mojo( name="generate-sources", defaultPhase = LifecyclePhase.PROCESS_RESOURCES )
public class GenerateSourcesMojo
extends AbstractWsdlITMojo {
    private static final Logger LOGGER = LoggerFactory.getLogger( GenerateResourcesMojo.class );

    /**
     * List of paths of the wsdl files from which you want to generate the documentation.
     *
     * <p>
     *      Each path can be absolute or relative.
     *      If it is relative,
     *      an absolute path will be obtained starting from the composition with the value
     *      of the {@code directory} parameter.
     * </p>
     */
    @Parameter( property = "wsdlit.sources", required = true )
    protected List<String> sources;

    /**
     * Title of the document.
     *
     * <p>
     *      If a title is not defined for the document,
     *      the default value is the description configured in the {@code pom.xml} file,
     *      ie {@code ${project.description}}.
     * </p>
     */
    @Parameter( name = "title", property = "wsdlit.api.title", defaultValue = "${project.description}" )
    protected String title;

    /**
     * Version of the document.
     *
     * <p>
     *      If a version is not defined for the document,
     *      the default value is the version configured in the file {@code pom.xml},
     *      ie {@code ${project.version}}.
     * </p>
     */
    @Parameter( name="version", property = "wsdlit.api.version", defaultValue = "${project.version}" )
    protected String version;

    /**
     * Sorting algorithm of the services to be rendered in the documentation.
     *
     * <p>
     *      Possible values are:
     * </p>
     * <ul>
     *      <li>{@code NONE} to respect the order present in the plugin configuration.</li>
     *      <li>{@code SORT_BY_NAME} to sort services by their name value.</li>
     * </ul>
     */
    @Parameter( property = "wsdlit.api.sort.algorithm", defaultValue = "SORT_BY_NAME", required = true )
    protected SortAlgorithms sortServicesAlgorithm;

    /**
     * The full qualified name of the class that will work as the wsdls to {@code APIDocumentConverter} document converter.
     */
    @Parameter( property="wsdlit.api.document.provider", defaultValue = MojoDefaultValues.API_DOCUMENTO_PROVIDER )
    protected String apiDocumentProvider;

    @Override
    public void execute()
    throws MojoExecutionException {
        final APIDocumentServiceLoader loader = new APIDocumentServiceLoader();
        final APIDocumentProvider provider = loader
            .getProviderOrDefault( this.apiDocumentProvider )
            .setSourcesDirectory( this.sourcesDirectory );

        LOGGER.info( Messages.I18N.getString( Messages.GENERATING_ADOC_FILES_KEY ) );
        final long startedAt = System.currentTimeMillis();
        try {
            final APIDocument document = provider.convert( this.sources );
            final Context context = new Context( this.templatesDirectory ).setOutputPath( this.sourceDirectory );
            final FileProcessorManager manager = new DefaultTemplatesFactory().createTemplatesManager( context );
            final Map<String, Object> model = new HashMap<>();

            document.setTitle( this.title ).setVersion( this.version );
            document.sort( this.sortServicesAlgorithm );

            model.put( "document", document );
            model.put( "diffSkip", this.diffSkip );
            FreemarkerFunctionsUtil.addFunctions( model );
            manager.process( model );
            final long duration = System.currentTimeMillis() - startedAt;
            LOGGER.info( Messages.I18N.format( Messages.GENERATING_ADOC_FILES_ENDED_KEY, duration ) );

        } catch ( final ProcessException cause ) {
            final String message = Messages.I18N.getString( Messages.GENERATING_ADOC_FILES_ERROR_KEY );
            LOGGER.error( message, cause );
            throw new MojoExecutionException( message, cause );
        }
    }
}
