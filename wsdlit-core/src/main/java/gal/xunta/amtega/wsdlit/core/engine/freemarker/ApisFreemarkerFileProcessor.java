package gal.xunta.amtega.wsdlit.core.engine.freemarker;

/*-
 * #%L
 * wsdlit-core
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

import freemarker.template.TemplateException;
import gal.xunta.amtega.wsdlit.core.engine.api.Context;
import gal.xunta.amtega.wsdlit.core.engine.api.FileProcessor;
import gal.xunta.amtega.wsdlit.core.engine.api.ProcessException;
import gal.xunta.amtega.wsdlit.core.model.APIDocument;
import gal.xunta.amtega.wsdlit.core.model.ServiceNode;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * File processor based on Freemarker that generates the API of all services
 * <p>
 *      This file processor is responsible for generating a file in which the list of available services is indicated,
 *      the content of which will be found in {@code api/index.adoc}.
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class ApisFreemarkerFileProcessor
extends AbstractFreemarkerFileProcessor {
    /**
     * Construct an {@code ApisFreemarkerFileProcessor},
     * with the template name and the output filename value of {@code api/index}.
     *
     * @param context the file processing execution context.
     */
    public ApisFreemarkerFileProcessor( final Context context ) {
        super( context, "api/index" );
    }

    @Override
    public void body( final Map<String, Object> model )
    throws ProcessException {
        try (
            final OutputStream os = Files.newOutputStream( this.getOutputFile().toPath() );
            final Writer out = new OutputStreamWriter( os, this.getContext().getOutputCharset() )
        ) {
            this.template.process( model, out );
            this.processApis( model );
        } catch ( final IOException | TemplateException cause ) {
            throw new ProcessException( cause );
        }
    }

    /**
     * Process the API document ({@code APIDocument}) of the Freemarker data model whose key is {@code document},
     * iterating through each service using the {@code APIDocument.getServices()} method.
     *
     * @param model the Freemarker data model.
     * @throws ProcessException if an error occurs during the processing of data model.
     */
    private void processApis( final Map<String, Object> model )
    throws ProcessException {
        final APIDocument document = ( APIDocument ) model.get( "document" );
        for( final ServiceNode api : document.getServices() ) {
            final FileProcessor fp = new ApiFreemarkerFileProcessor( this.getContext(), "api/apiItem", "api/" + api.getName() + "/index" );
            final Map<String, Object> apiModel = new HashMap<>( model );
            apiModel.put( "api", api );
            fp.process( apiModel );
        }
    }
}
