package gal.xunta.amtega.wsdlit.membranesoa.spi;

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

import gal.xunta.amtega.wsdlit.core.model.APIDocument;
import gal.xunta.amtega.wsdlit.core.spi.AbstractAPIDocumentProvider;
import gal.xunta.amtega.wsdlit.membranesoa.converter.APIDocumentConverter;

import java.util.List;

/**
 * Class that provides a converter from a list of wsdls to {@code APIDocument}.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public final class MembraneSOAAPIDocumentProvider
extends AbstractAPIDocumentProvider {
    @Override
    public APIDocument convert( final List<String> sources ) {
        final APIDocumentConverter converter = new APIDocumentConverter( this.getSourcesDirectory() );

        return converter.convert( sources );
    }
}
