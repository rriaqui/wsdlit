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
import com.predic8.schema.ModelGroup;
import com.predic8.schema.SchemaComponent;
import com.predic8.schema.TypeDefinition;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;
import groovy.namespace.QName;

/**
 * Converter from MembraneSOA's {@code Derivation} to wsdlit {@code ElementNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class DerivationConverter
extends AbstractConverter<Derivation, ElementNode> {
    /**
     * Constructs a {@code DerivationConverter} for the specified history context.
     *
     * @param historyContext the specified history context.
     */
    public DerivationConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public ElementNode convert( final Derivation derivation ) {
        final QName qname = derivation.getBase();
        final TypeDefinition typeDefinition = derivation.getSchema().findType( qname );
        final ElementNodeBuilder builder = MembraneSOAUtil.createBuilder( typeDefinition );
        final SchemaComponent model = derivation.getModel();
        // We set the prefix from wsdl, not from schema where the type is defined
        builder.setPrefix( qname.getPrefix() );
        if ( model instanceof ModelGroup ) {
            final ModelGroup modelGroup = ( ModelGroup ) model;
            final ElementNode theModelGroup = ConverterManager.getInstance().convert( modelGroup );
            if ( theModelGroup.size() > 0 ) {
                builder.add( theModelGroup );
            }
        }
        final ElementNode elementNode = ConverterManager.getInstance().convert( typeDefinition );
        builder.addAll( 0, elementNode );
        return builder.build();
    }
}
