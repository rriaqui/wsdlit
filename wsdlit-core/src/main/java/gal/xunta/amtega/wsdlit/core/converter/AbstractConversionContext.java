package gal.xunta.amtega.wsdlit.core.converter;

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

/**
 * Abstract conversion context.
 *
 * @param <T> the type of the conversion context
 *
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractConversionContext< T extends AbstractConversionContext< T > > {
    /**
     * The relative uri to be processed.
     */
    private String uri;

    /**
     * The file of the service contract to be processed.
     */
    private File wsdlFile;

    /**
     * The buider.
     */
    protected AbstractConversionContext() {
    }

    /**
     * Returns the uri to be processed.
     *
     * @return the uri to be processed.
     */
    public String getUri() {
        return this.uri;
    }

    /**
     * Returns the wsdl file to be processed.
     *
     * @return the wsdl file to be processed.
     */
    public File getWsdlFile() {
        return this.wsdlFile;
    }

    /**
     * Sets the uri to be processed.
     *
     * @param uri the uri to be processed.
     */
    public void setUri( final String uri ) {
        this.uri = uri;
    }

    /**
     * Sets the wsdl file to be processed.
     *
     * @param wsdlFile the wsdl file to be processed.
     */
    public void setWsdlFile( final File wsdlFile ) {
        this.wsdlFile = wsdlFile;
    }
}
