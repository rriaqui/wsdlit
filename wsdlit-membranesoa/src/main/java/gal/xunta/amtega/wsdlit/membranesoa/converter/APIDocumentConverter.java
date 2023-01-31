package gal.xunta.amtega.wsdlit.membranesoa.converter;

/*-
 * #%L
 * wsdlit-membranesoa
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

import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.core.model.APIDocument;
import gal.xunta.amtega.wsdlit.core.model.ServiceNode;
import gal.xunta.amtega.wsdlit.membranesoa.Messages;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that converts service contracts  to wsdlit {@code APIDocument}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class APIDocumentConverter
extends AbstractConverter<List<String>, APIDocument > {
    private final Logger logger = LoggerFactory.getLogger( APIDocumentConverter.class );

    /*
     * The path to the wsdls.
     * This path will be used if the paths of the wsdls to be converted are relative.
     */
    private final File directory;

    /**
     * Constructs an {@code APIDocumentConverter},
     * with no directory for relative service contract paths.
     */
    public APIDocumentConverter() {
        this( null );
    }

    /**
     * Constructs an {@code APIDocumentConverter} for the specified directory for relative service contract paths.
     *
     * <p>
     *     The {@code directory} will be used only when the path of the wsdl to be converted is relative.
     * </p>
     *
     * @param directory o directorio no que se atopan as fontes
     */
    public APIDocumentConverter( final File directory ) {
        super( null );
        this.directory = directory;
    }

    @Override
    public APIDocument convert( final List<String> uris ) {
        final long startedAt = System.currentTimeMillis();
        final List< ServiceNode > apis = new ArrayList<>();

        logger.info( Messages.I18N.getString( Messages.API_DOCUMENT_CONVERTER_START_KEY) );

        for( final String uri: uris ) {
            final String filenamePath;
            final File file = new File( uri );
            if ( !file.isFile() && ! file.isAbsolute() && this.directory != null ) {
                filenamePath = ( new File( this.directory, uri ) ).getAbsolutePath();
            } else {
                filenamePath = uri;
            }
            logger.info( Messages.I18N.format( Messages.WSDL_DOCUMENT_CONVERTER_START_KEY, filenamePath ) );
            final long wsdlProcessingStartedAt = System.currentTimeMillis();
            final Definitions definitions = MembraneSOAUtil.readWSDLFile( filenamePath );
            apis.add( ConverterManager.getInstance().convert(definitions) );
            final long wsdlProcessingDuration = System.currentTimeMillis() - wsdlProcessingStartedAt;
            logger.info( Messages.I18N.format( Messages.WSDL_DOCUMENT_CONVERTER_END_KEY, filenamePath, wsdlProcessingDuration ) );
        }
        final APIDocument apiDocument = new APIDocument( apis );
        final long duration = System.currentTimeMillis() - startedAt;
        logger.info( Messages.I18N.format( Messages.API_DOCUMENT_CONVERT_END_KEY, duration ) );

        return apiDocument;
    }
}
