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

import java.io.File;

/**
 * Abstract implementation of a {@code APIDocumentProvider} that provides the basic conversion functionality
 * from a list of {@code wsdl} files to a {@code APIDocument}.
 * Different suppliers will extend this class.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public abstract class AbstractAPIDocumentProvider
implements APIDocumentProvider {
    /**
     * Directory to search for contracts when the specified contract file is not found
     * by means of a relative route.
     */
    private File sourcesDirectory = null;

    @Override
    public File getSourcesDirectory() {
        if ( this.sourcesDirectory != null ) {
            return this.sourcesDirectory;
        }
        return null;
    }

    @Override
    public final APIDocumentProvider setSourcesDirectory( final File sourcesDirectory ) {
        this.sourcesDirectory = sourcesDirectory;
        return this;
    }
}
