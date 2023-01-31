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

import gal.xunta.amtega.wsdlit.core.engine.freemarker.functions.FreemarkerFunctionsUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * File processor manager.
 * Process a data model with a list of file processors.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class FileProcessorManager {
    /**
     * List of file processors.
     */
    private final List<FileProcessor> fileProcessors;

    /**
     * Build a file processor manager with the list of {@code FileProcessor}. to process the data model.
     *
     * @param processors list of {@code FileProcessor} to process the data model.
     */
    public FileProcessorManager( final List<FileProcessor> processors ) {
        this.fileProcessors = Collections.unmodifiableList( processors );
    }

    /**
     * Returns the list of {@code FileProcessor}.
     *
     * @return the list of {@code FileProcessor}.
     */
    public List<FileProcessor> getList() {
        return this.fileProcessors;
    }

    /**
     * Process a data model.
     *
     * @param model the data model to process.
     * @throws ProcessException if there is any problem processing the data model.
     */
    public void process( final Map<String, Object> model )
    throws ProcessException {
        FreemarkerFunctionsUtil.addFunctions( model );
        for( final FileProcessor fileProcessor: fileProcessors ) {
            fileProcessor.process( model );
        }
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || this.getClass() != o.getClass() ) {
            return false;
        }
        FileProcessorManager that = ( FileProcessorManager ) o;
        return fileProcessors.equals( that.fileProcessors );
    }

    @Override
    public int hashCode() {
        return Objects.hash( fileProcessors );
    }
}
