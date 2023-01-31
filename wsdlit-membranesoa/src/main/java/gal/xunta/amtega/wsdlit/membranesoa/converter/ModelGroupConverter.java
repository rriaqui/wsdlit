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

import com.predic8.schema.GroupRef;
import com.predic8.schema.ModelGroup;
import com.predic8.schema.SchemaComponent;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.*;
import gal.xunta.amtega.wsdlit.core.model.util.ModelUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;

/**
 * Converter from MembraneSOA's {@code ModelGroup} to wsdlit {@code AbstractModelGroup}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class ModelGroupConverter
extends AbstractConverter<ModelGroup, AbstractModelGroup>{
    /**
     * Constructs a {@code ModelGroupConverter} for the specified history context.
     *
     * @param historyContext the specified history context.
     */
    public ModelGroupConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public AbstractModelGroup convert( final ModelGroup modelGroup ) {
        final ElementNodeBuilder builder = MembraneSOAUtil.createBuilder( modelGroup );

        ModelUtil.updateOccurrences( builder, modelGroup.getMinOccurs(), modelGroup.getMaxOccurs() );

        for ( final SchemaComponent sc : modelGroup.getParticles() ) {
            if ( sc instanceof ModelGroup ) {
                final ElementNode theModelGroup = this.convert( ( ModelGroup ) sc );
                if ( theModelGroup.size() > 0 ) {
                    builder.add( theModelGroup );
                }
            } else if ( sc instanceof GroupRef ) {
                final ElementNode node = ConverterManager.getInstance().convert( ( GroupRef ) sc );
                builder.add( node );
            } else {
                builder.add( ConverterManager.getInstance().convert( sc ) );
            }
        }

        return createModelGroup( modelGroup, builder );
    }

    protected AbstractModelGroup createModelGroup( final ModelGroup modelGroup, final ElementNodeBuilder builder ) {
        final ModelGroupEnum modelGroupEnum = ModelGroupEnum.valueOf( modelGroup );
        switch ( modelGroupEnum ) {
            case ALL:
                return new AllNode( builder );
            case SEQUENCE:
                return new SequenceNode( builder );
            case CHOICE:
                return new ChoiceNode( builder );
            default:
                throw new IllegalArgumentException( "Tipo de modelGroup descoñecido " + modelGroup );
        }
    }
}
