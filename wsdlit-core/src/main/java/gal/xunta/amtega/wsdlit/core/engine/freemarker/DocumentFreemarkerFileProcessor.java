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

import freemarker.template.TemplateException;
import gal.xunta.amtega.wsdlit.core.engine.api.Context;
import gal.xunta.amtega.wsdlit.core.engine.api.ProcessException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Map;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class DocumentFreemarkerFileProcessor
extends AbstractFreemarkerFileProcessor {

    /**
     * Constrúe unha instancia a partires dos valores especificados para o procesamento do contexto,
     * co valor da plantilla {@code index} e o nome do arquivo de saída {@code index}.
     *
     * @param context the processing context.
     */
    public DocumentFreemarkerFileProcessor( final Context context ) {
        super( context, "index" );
    }

    @Override
    public void body( final Map<String, Object> model )
    throws ProcessException {
        try (
            final OutputStream os = Files.newOutputStream( this.getOutputFile().toPath() );
            final Writer out = new OutputStreamWriter( os, this.getContext().getOutputCharset() )
        ) {
            this.template.process( model, out );
        } catch ( final IOException | TemplateException cause ) {
            throw new ProcessException( cause );
        }
    }
}
