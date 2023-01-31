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

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import gal.xunta.amtega.wsdlit.core.engine.api.Context;
import gal.xunta.amtega.wsdlit.core.engine.api.ProcessException;
import gal.xunta.amtega.wsdlit.core.engine.freemarker.functions.FileExistsMethodModel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Map;

/**
 * File processor that generates API documentation for a contract.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class ApiFreemarkerFileProcessor
extends AbstractFreemarkerFileProcessor {
    /**
     * Constructs an {@code ApiFreemarkerFileProcessor} with specified values for the processing context,
     * the template filename (without extension) and the output filename (without extension).
     *
     * @param context the processing context.
     * @param templateFilename the path and relative name of the file to use as a template, without extension.
     * @param outputFilename the path and relative name of the file to use as output, without extension.
     */
    public ApiFreemarkerFileProcessor( final Context context, final String templateFilename, final String outputFilename ) {
        super( context, templateFilename, outputFilename );
    }

    @Override
    public void body( final Map<String, Object> model )
    throws ProcessException {
        try (
            final OutputStream os = Files.newOutputStream( this.getOutputFile().toPath() );
            final Writer out = new OutputStreamWriter( os, this.getContext().getOutputCharset() )
        ) {
            final Environment env = template.createProcessingEnvironment( model, out );
            env.setOutputEncoding( this.getContext().getTemplateCharset().name() );
            env.setAutoFlush( true );
            model.put( "fileExists", new FileExistsMethodModel() );
            model.put( "freemarker_output_filename", this.getOutputFile().getCanonicalPath() );
            env.process();

        } catch ( final IOException | TemplateException cause ) {
            throw new ProcessException( cause );
        }
    }
}
