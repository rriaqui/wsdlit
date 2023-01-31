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
import java.util.Map;

/**
 * Processor that transforms a data map and writes it to a file.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public interface FileProcessor {
    /**
     * Returns the {@code File} to which {@code FileProcessor} will write.
     *
     * @return the {@code File} to which {@code FileProcessor} will write.
     *
     * @since 1.0.0
     */
    File getOutputFile();

    /**
     * Returns the file as {@code String} to which {@code FileProcessor} will write
     *
     * @return the file as {@code String} to which {@code FileProcessor} will write
     *
     * @since 1.0.0
     */
    String getOutputFilename();

    /**
     * Returns the template filename this {@code FileProcessor} will use.
     *
     * @return the template filename this {@code FileProcessor} will use.
     *
     * @since 1.0.0
     */
    String getTemplateFilename();

    /**
     * Process a template with a data model.
     *
     *  <p>
     *      Processing the template must go through the following steps:
     *  </p>
     *
     *  <ol>
     *      <li>start: processing starts, initializing the necessary resources.</li>
     *      <li>body: The data model is processed.</li>
     *      <li>end: Processing ends, freeing previously initialized resources.
     *          Should always run, at the end of processing.
     *      </li>
     *  </ol>
     *
     * @param model the data model to process.
     *
     * @throws ProcessException if an error occurs during any of the steps.
     */
    default void process( final Map<String, Object> model ) throws ProcessException {
        try {
            this.start( model );
            this.body( model );
        } finally {
            this.end( model );
        }
    }

    /**
     * Starts processing the template; can be useful for initializing resources.
     *
     * @param model the data model.
     *
     * @throws ProcessException if an error occurs during processing.
     */
    default void start( final Map<String, Object> model ) throws ProcessException {}

    /**
     * Process the template with the data model.
     *
     * @param model the data model.
     *
     * @throws ProcessException if an error occurs during processing.
     */
    default void body( final Map<String, Object> model ) throws ProcessException {}

    /**
     * Ends data processing.
     * At this stage it is necessary to release the previously initialized resources.
     *
     * @param model the data model.
     */
    default void end( final Map<String, Object> model ) {}

    /**
     * Returns the context in which template processing is executed.
     *
     * @return the context in which template processing is executed.
     */
    Context getContext();
}
