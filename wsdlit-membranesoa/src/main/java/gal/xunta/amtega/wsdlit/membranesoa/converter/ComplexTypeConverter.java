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

import com.predic8.schema.*;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.history.HistoryItem;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;

/**
 * Converter from MembraneSOA's {@code ComplexType} to wsdlit {@code ElementNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ComplexTypeConverter
extends AbstractConverter<ComplexType, ElementNode> {
    /**
     * Constructs a {@code ComplexTypeConverter} for the specified history context.
     *
     * @param historyContext the specified history context.
     */
    public ComplexTypeConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public ElementNode convert( final ComplexType complexType ) {
        return this.convert( complexType, false );
    }

    @Override
    public ElementNode convert( final ComplexType complexType, final boolean ignoreHistory ) {
        final ElementNodeBuilder builder = MembraneSOAUtil.createBuilder( complexType );
        final SchemaComponent schemaComponent = complexType.getModel();
        final HistoryItem item;

        if ( ignoreHistory ) {
            item = this.historyContext.pushHistory( "", "" );
        } else {
            item = this.historyContext.pushHistory( complexType.getNamespaceUri(), complexType.getName() );
        }

        builder.setCyclicReference( item.isCyclicReference() );

        if ( builder.isNotCyclicReference() ) {
            if (schemaComponent instanceof ComplexContent) {
                final ElementNode node = ConverterManager.getInstance().convert((ComplexContent) schemaComponent);
                builder.addAll( node );
            }

            if (schemaComponent instanceof ModelGroup) {
                final ModelGroup modelGroup = (ModelGroup) schemaComponent;
                final ElementNode theModelGroup = ConverterManager.getInstance().convert(modelGroup);
                if ( theModelGroup.size() > 0 ) {
                    builder.add( theModelGroup );
                }
            }

            if (schemaComponent instanceof SimpleContent) {
                final ElementNode node = ConverterManager.getInstance().convert((SimpleContent) schemaComponent);
                builder.setType( node.getType() );
            }
        }

        if ( ! ignoreHistory ) {
            this.historyContext.popHistory( item );
        }
        return builder
            .setPrefix( this.findPrefix( complexType.getPrefix(), complexType.getNamespaceUri() ) )
            .build();
    }
}
