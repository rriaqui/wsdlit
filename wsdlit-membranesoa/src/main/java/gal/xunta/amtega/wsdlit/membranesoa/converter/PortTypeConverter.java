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

import com.predic8.wsdl.Operation;
import com.predic8.wsdl.PortType;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.OperationNode;
import gal.xunta.amtega.wsdlit.core.model.PortTypeNode;
import gal.xunta.amtega.wsdlit.membranesoa.Messages;
import gal.xunta.amtega.wsdlit.membranesoa.util.ConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter from MembraneSOA's {@code PortType} to wsdlit {@code PortTypeNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class PortTypeConverter
extends AbstractConverter<PortType, PortTypeNode> {
    private final Logger logger = LoggerFactory.getLogger( PortTypeConverter.class );

    /**
     * Constructs a {@code PortTypeConverter} from a specific history context.
     *
     * @param historyContext the specified history context.
     */
    public PortTypeConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public PortTypeNode convert( final PortType portType ) {
        this.logger.info( Messages.I18N.getString( "PortTypeConverter.start" ), portType.getName() );
        final long startedAt = System.currentTimeMillis();
        try {
            final List<OperationNode> operations = new ArrayList<>();

            for (final Operation operation : portType.getOperations()) {
                operations.add(ConverterManager.getInstance().convert(operation));
            }
            final PortTypeNode pti = new PortTypeNode(operations);
            ConverterUtil.update(pti, portType);
            return pti;
        } finally {
            final long duration = System.currentTimeMillis() - startedAt;
            this.logger.info( Messages.I18N.getString( "PortTypeConverter.end" ), portType.getName(), duration );
        }
    }
}
