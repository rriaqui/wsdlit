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

import com.predic8.schema.Group;
import com.predic8.schema.GroupRef;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.model.GroupNode;
import gal.xunta.amtega.wsdlit.core.model.util.ModelUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.ConverterUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;

/**
 * Converter from MembraneSOA's {@code GroupRef} to wsdlit {@code ElementNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class GroupRefConverter
extends AbstractConverter<GroupRef, ElementNode> {
    /**
     * Constructs a {@code GroupRefConverter} from a specific history context.
     *
     * @param historyContext the specified history context.
     */
    public GroupRefConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public ElementNode convert( final GroupRef groupRef ) {
        final Group group = groupRef.getSchema().getGroup( groupRef.getRef() );
        final ElementNode groupNode = ConverterManager.getInstance().convert( group );
        final ElementNodeBuilder builder = MembraneSOAUtil.createBuilder( groupRef );
        ConverterUtil.update( builder, group );
        ModelUtil.updateOccurrences( builder, groupRef.getMinOccurs(), groupRef.getMaxOccurs() );
        builder.addAll( groupNode );

        return new GroupNode( builder );
    }
}
