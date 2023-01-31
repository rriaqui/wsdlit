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

import com.predic8.schema.Derivation;
import com.predic8.schema.Extension;
import com.predic8.schema.SchemaComponent;
import com.predic8.schema.SimpleContent;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;

/**
 * Converter from MembraneSOA's {@code SimpleContent} to wsdlit {@code ElementNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class SimpleContentConverter
extends AbstractConverter<SimpleContent, ElementNode> {
    @Override
    public ElementNode convert( final SimpleContent item ) {
        final ElementNodeBuilder builder = MembraneSOAUtil.createBuilder( item );
        if ( item.getRestriction() != null ) {
            builder.setType( item.getRestriction().getBase().getQualifiedName() );
        }
        if ( item.getExtension() != null ) {
            final Extension extension = item.getExtension();
            builder.setType( extension.getBase().getQualifiedName() );
            if ( extension.getModel() != null ) {
                final SchemaComponent schemaComponent = extension.getModel();
                final ElementNode node = ConverterManager.getInstance().convert( schemaComponent );
                builder.addAll( node );
            }
        }
        if ( item.getDerivation() != null ) {
            final Derivation derivation = ( Derivation ) item.getDerivation();
            builder.setType( derivation.getBase().getQualifiedName() );
        }
        return builder.build();
    }
}
