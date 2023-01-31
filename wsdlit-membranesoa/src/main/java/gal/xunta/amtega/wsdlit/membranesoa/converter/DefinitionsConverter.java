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
import gal.xunta.amtega.wsdlit.core.model.ServiceNode;
import gal.xunta.amtega.wsdlit.membranesoa.util.DefinitionsUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.DocumentationUtil;

/**
 * Converter from MembraneSOA's {@code Definitions} to wsdlit {@code ServiceNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class DefinitionsConverter
extends AbstractConverter<Definitions, ServiceNode > {
    /**
     * Constructs a {@code ServiceNode}.
     */
    public DefinitionsConverter() {
        super(null);
    }

    @Override
    public ServiceNode convert( final Definitions definitions ) {
        final String name;
        final String namespaceUri;
        if ( definitions == null ) {
            name = null;
            namespaceUri = null;
        } else {
            name = definitions.getName();
            namespaceUri = definitions.getTargetNamespace();
        }
        final ServiceNode api = new ServiceNode( name, namespaceUri, DefinitionsUtil.getPortTypes( definitions ) );
        api.setDocumentation( DocumentationUtil.normalize( definitions ) );
        api.addAll( this.getNamespacePrefixCache().getNamespaces() );
        return api;
    }
}
