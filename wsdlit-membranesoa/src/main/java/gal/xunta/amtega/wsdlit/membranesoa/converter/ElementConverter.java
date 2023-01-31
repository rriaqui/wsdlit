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
import gal.xunta.amtega.wsdlit.core.model.util.ModelUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.DocumentationUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;

/**
 * Converter from MembraneSOA's {@code Element} to wsdlit {@code ElementNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class ElementConverter
extends AbstractConverter<Element, ElementNode> {
    /**
     * Constructs an {@code ElementNode} for the specified history context.
     *
     * @param historyContext the specified history context.
     */
    public ElementConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public ElementNode convert( final Element element ) {
        return this.convert(element, false);
    }

    @Override
    public ElementNode convert( final Element element, final boolean ignoreHistory ) {
        final TypeDefinition embeddedType = element.getEmbeddedType();
        final ElementNodeBuilder eib = createBuilder( element );

        if ( embeddedType instanceof SimpleType ) {
            final ElementNode child = ConverterManager.getInstance().convert( embeddedType );
            eib.setType( child.getType() );

        }
        if ( embeddedType instanceof ComplexType ) {
            final ElementNode children = ConverterManager.getInstance().convert(embeddedType);
            eib.addAll( children );

        }
        if ( element.getType() != null ) {
            final TypeDefinition typeDefinition = element.getSchema().getType( element.getType() );
            final Annotation annotation = typeDefinition.getAnnotation();
            final HistoryItem item;
            if ( ! ignoreHistory ) {
                item = this.historyContext.pushHistory( element.getNamespaceUri(), element.getName() );
            } else {
                item = this.historyContext.pushHistory( "", "" );
            }

            eib
                    .setPrefix( this.findPrefix( element.getPrefix(), element.getNamespaceUri() ) )
                    .setType( element.getType().getQualifiedName() )
                    .setType( this.getType( element.getType() ) )
                    .setDocumentationIfEmpty( DocumentationUtil.normalize( annotation ) );

            if ( this.historyContext.contains( typeDefinition.getQname().getNamespaceURI(), typeDefinition.getName() ) ) {
                eib.setCyclicReference( true );
            } else {
                final ElementNode children = ConverterManager.getInstance().convert( typeDefinition );
                eib.addAll( children );
            }
            if ( !ignoreHistory ) {
                this.historyContext.popHistory( item );
            }
        }

        ModelUtil.updateOccurrences( eib, element.getMinOccurs(), element.getMaxOccurs() );
        return eib.build();
    }

    private static ElementNodeBuilder createBuilder( final Element element ) {
        /*
            Comprobamos se se trata dun elemento definido por medio dunha referencia, é dicir, algo similar a:
            <xs:element ref="namespace:elementoReferenciado"/>
         */
        if ( element.getRef() != null ) {
            final Element referencedElement = element.getSchema().getElement( element.getRef() );
            final ElementNode elementNode = ConverterManager.getInstance().convert( referencedElement );
            final ElementNodeBuilder eib = MembraneSOAUtil.createBuilder( referencedElement );
            eib.setType( elementNode.getType() );
            eib.setDocumentation( elementNode.getDocumentation() );
            eib.addAll( elementNode );
            return eib;
        }
        return MembraneSOAUtil.createBuilder( element );
    }
}
