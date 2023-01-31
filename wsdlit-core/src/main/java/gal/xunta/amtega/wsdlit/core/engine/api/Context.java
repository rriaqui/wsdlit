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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Processing context of data model processing.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class Context {
    /**
     * The path where the templates are located.
     */
    private final String templatesPath;

    /**
     * The encoding used to write files as a result of the processing.
     * The default encoding is @{code StandardCharsets.UTF_8}.
     */
    private Charset outputCharset = StandardCharsets.UTF_8;

    /**
     * The encoding used to read templates.
     * The default encoding is @{code StandardCharsets.UTF_8}.
     */
    private Charset templateCharset = StandardCharsets.UTF_8;

    /**
     * Directory where the files will be written.
     * The default value is {@code new File( System.getProperty( "java.io.tmpdir" ), "output" );}
     */
    private File outputPath = new File( System.getProperty( "java.io.tmpdir" ), "output" );

    /**
     * Constructs a {@code Context} with {@code templatesPath = "/templates/default"}.
     */
    public Context() {
        this( "/templates/default" );
    }

    /**
     * Constructs a {@code Context} with the specified {@code templatesPath}.
     *
     * @param templatesPath the path where the templates are located.
     */
    public Context( final String templatesPath ) {
        this.templatesPath = templatesPath;
    }

    /**
     * Returns the encoding with which the files will be generated.
     *
     * @return a {@code Charset} used to write files.
     */
    public Charset getOutputCharset() {
        return this.outputCharset;
    }

    /**
     * Returns the directory where the files will be written.
     *
     * @return the directory where the files will be written.
     */
    public File getOutputPath() {
        return this.outputPath;
    }

    /**
     * Returns the directory where the templates are located.
     *
     * @return the directory where the templates are located.
     */
    public String getTemplatesPath() {
        return this.templatesPath;
    }

    /**
     * Returns a {@code File} pointing to the output file
     * composed of the output directory and the name of a template.
     *
     * @param templateFilename the template for which you want to know the output file.
     * @return a {@code File} pointing to the output file.
     */
    public File getOutputFile( final String templateFilename ) {
        return new File( this.outputPath, templateFilename );
    }

    /**
     * Returns the encoding used to read the templates.
     *
     * @return the encoding used to read the templates.
     */
    public Charset getTemplateCharset() {
        return this.templateCharset;
    }

    /**
     * Sets the encoding used to write files.
     *
     * @param charset the encoding to use to write the files.
     *
     * @return a reference to this {@code Context}.
     */
    public Context setOutputCharset( final Charset charset ) {
        this.outputCharset = charset;
        return this;
    }

    /**
     * Sets the directory where the files will be written.
     *
     * @param outputPath the directory where the files will be generated.
     * @return a reference to this {@code Context}.
     */
    public Context setOutputPath( final File outputPath ) {
        this.outputPath = outputPath;
        return this;
    }

    /**
     * Sets the encoding used to read the templates.
     *
     * @param templateCharset the encoding used to read the templates.
     * @return a reference to this {@code Context}.
     */
    public Context setTemplateCharSet( final Charset templateCharset ) {
        this.templateCharset = templateCharset;
        return this;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || this.getClass() != o.getClass() ) {
            return false;
        }
        Context context = ( Context ) o;
        return
            templatesPath.equals( context.templatesPath )
                && outputCharset.equals( context.outputCharset )
                && templateCharset.equals( context.templateCharset )
                && outputPath.equals( context.outputPath );
    }

    @Override
    public int hashCode() {
        return Objects.hash( templatesPath, outputCharset, templateCharset, outputPath );
    }
}
