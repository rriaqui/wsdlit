package gal.xunta.amtega.wsdlit.core.engine.api;

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

import java.io.File;
import java.util.Objects;


/**
 * Abstract file processor.
 * Provides basic functionality for specific {@code FileProcessor} implementations.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractFileProcessor
implements FileProcessor {
    /**
     * The execution context of the data model processing.
     */
    protected final Context context;

    /**
     * Name of the output file in which the result of the processing will be written.
     */
    protected final String outputFilename;

    /**
     * The {@code File} associated with the value of {@code outputFilename}.
     */
    protected final File outputFile;

    /**
     * Template used to generate the documentation.
     */
    protected final String templateFilename;

    /**
     * Constructs a file processor using specified context, template filename and output filename.
     *
     * @param context the processing context of the data model.
     * @param templateFilename the template path to use in processing, without extension.
     * @param outputFilename the name of the file to write the result of the processing, without extension.
     */
    protected AbstractFileProcessor( final Context context, final String templateFilename, final String outputFilename ) {
        this.context = context;
        this.outputFilename = outputFilename + ".adoc";
        this.templateFilename = templateFilename + ".ftl";
        this.outputFile = context.getOutputFile( this.outputFilename );
        this.outputFile.getParentFile().mkdirs();
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public String getOutputFilename() {
        return outputFilename;
    }

    @Override
    public String getTemplateFilename() {
        return this.templateFilename;
    }

    @Override
    public File getOutputFile() {
        return this.outputFile;
    }
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || this.getClass() != o.getClass() ) {
            return false;
        }
        AbstractFileProcessor that = ( AbstractFileProcessor ) o;
        return
            context.equals( that.context )
                && outputFilename.equals( that.outputFilename )
                && templateFilename.equals( that.templateFilename );
    }

    @Override
    public int hashCode() {
        return Objects.hash( context, outputFilename, templateFilename );
    }
}
