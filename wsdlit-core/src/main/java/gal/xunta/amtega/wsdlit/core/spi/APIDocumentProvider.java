package gal.xunta.amtega.wsdlit.core.spi;

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

import gal.xunta.amtega.wsdlit.core.model.APIDocument;

import java.io.File;
import java.util.List;

/**
 * Class that provides a converter from a list of wsdls to {@code APIDocument}.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public interface APIDocumentProvider {
    /**
     * Starts the conversion process and returns an {@code APIDocument} with the structure
     * of the wsdlit template needed to generate the API documentation.
     *
     * @param sources the list of paths of the contracts to convert.
     * @return an {@code APIDocument} with the structure converted.
     */
    APIDocument convert(final List<String> sources );

    /**
     * Returns the directory in which contracts will be searched when not found.
     *
     * @return a {@code File} that points to the directory in which the contract will be searched.
     */
    File getSourcesDirectory();

    /**
     * Sets the directory in which contracts will be searched when not found.
     *
     * @param sourcesDirectory the directory to search for contracts.
     * @return a reference to the provider itself.
     */
    APIDocumentProvider setSourcesDirectory( final File sourcesDirectory );
}
