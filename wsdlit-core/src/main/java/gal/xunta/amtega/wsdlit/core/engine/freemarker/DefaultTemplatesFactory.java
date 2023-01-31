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

import gal.xunta.amtega.wsdlit.core.engine.api.Context;
import gal.xunta.amtega.wsdlit.core.engine.api.FileProcessor;
import gal.xunta.amtega.wsdlit.core.engine.api.FileProcessorManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the initialization of the file processing engine.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class DefaultTemplatesFactory {
    /**
     * Create the default file processor manager.
     *
     * @param context the processing context.
     * @return the initialized file processor manager.
     */
    public FileProcessorManager createTemplatesManager( final Context context ) {
        final List<FileProcessor> processors = new ArrayList<>();
        processors.add( new DocumentFreemarkerFileProcessor( context ) );
        processors.add( new ApisFreemarkerFileProcessor( context ) );
        return new FileProcessorManager( processors );
    }
}
