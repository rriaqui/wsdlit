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

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import gal.xunta.amtega.wsdlit.core.engine.api.AbstractFileProcessor;
import gal.xunta.amtega.wsdlit.core.engine.api.Context;

import java.io.IOException;
import java.util.Objects;

/**
 * Abstract {@code FileProcessor} based on Freemarker.
 * All Freemarker-based file processors will inherit from this class to implement its logic.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractFreemarkerFileProcessor
extends AbstractFileProcessor {
    /**
     * The settings to be passed to the Freemarker.
     */
    private final Configuration configuration;

    /**
     * The Freemarker template that this file processor will use.
     */
    protected final Template template;

    /**
     * Construct a {@code AbstractFileProcessor} from the context and the relative path of the template and the files to be written.
     *
     * @param context the file processing execution context.
     * @param filename the path and relative name of the file to use as template and output, without extension.
     */
    protected AbstractFreemarkerFileProcessor(final Context context, final String filename ) {
        this( context, filename, filename );
    }

    /**
     * Construct a {@code AbstractFileProcessor} from the context,
     * the relative path of the template and the path of the file to be writte.
     *
     * @param context the file processing execution context.
     * @param templateFilename the template path to use in processing, without extension.
     * @param outputFilename the name of the file to write the result of the processing, without extension.
     */
    protected AbstractFreemarkerFileProcessor( final Context context, final String templateFilename, final String outputFilename ) {
        super( context, templateFilename, outputFilename );
        this.configuration = this.createInitialConfiguration();
        this.configuration.setClassForTemplateLoading( AbstractFileProcessor.class,  context.getTemplatesPath() );
        this.configuration.setDefaultEncoding( context.getOutputCharset().name() );
        try {
            this.template = this.configuration.getTemplate( this.templateFilename );

        } catch ( final IOException cause ) {
            throw new IllegalArgumentException( cause );
        }
    }

    /**
     * Create context-based Freemarker initial configuration.
     *
     * @return a {@code Configuration} instance created from certain context properties.
     */
    private Configuration createInitialConfiguration() {
        final Configuration cfg = new Configuration( Configuration.VERSION_2_3_30 );

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        cfg.setDefaultEncoding( this.getContext().getTemplateCharset().name() );

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // Don't log exceptions inside FreeMarker that it will throw at you anyway:
        cfg.setLogTemplateExceptions(false);

        // Wrap unchecked exceptions thrown during template processing into TemplateException-s:
        cfg.setWrapUncheckedExceptions(true);

        // Do not fall back to higher scopes when reading a null loop variable:
        cfg.setFallbackOnNullLoopVariable(false);

        return cfg;
    }

    /**
     * Returns the Freemarker configuration used by the engine associated with this FileProcessor.
     *
     * @return the Freemarker configuration this FileProcessor was configured with.
     */
    protected Configuration getConfiguration() {
        return this.configuration;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || this.getClass() != o.getClass() ) {
            return false;
        }
        if ( !super.equals( o ) ) {
            return false;
        }
        AbstractFreemarkerFileProcessor that = ( AbstractFreemarkerFileProcessor ) o;
        return template.getName().equals( that.template.getName() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), template.getName() );
    }
}
