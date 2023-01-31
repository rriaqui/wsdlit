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

import com.predic8.wsdl.Fault;
import com.predic8.wsdl.Input;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Output;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.OperationNode;
import gal.xunta.amtega.wsdlit.membranesoa.util.ConverterUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.InputOutputParameterUtil;
import gal.xunta.amtega.wsdlit.membranesoa.util.OperationUtil;

/**
 * Converter from MembraneSOA's {@code Operation} to wsdlit {@code OperationNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class OperationConverter
extends AbstractConverter<Operation, OperationNode> {
    /**
     * Constructs an {@code OperationConverter} from a specific history context.
     *
     * @param historyContext the specified history context.
     */
    public OperationConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public OperationNode convert( final Operation operation ) {
        final Input input = operation.getInput();
        final Output output = operation.getOutput();
        final OperationNode oi = new OperationNode();
        oi.setType( OperationUtil.computeOperationType( operation ) );
        ConverterUtil.update( oi, operation );

        InputOutputParameterUtil.updateInputParameterName( input, oi.getType(), operation );
        InputOutputParameterUtil.updateOutputParameterName( output, oi.getType(), operation );

        oi.setInputMessage( ConverterManager.getInstance().convert( input ) );
        oi.setOutputMessage( ConverterManager.getInstance().convert( output ) );

        for( final Fault fault : operation.getFaults() ) {
            oi.add( ConverterManager.getInstance().convert( fault ) );
        }
        return oi;
    }
}
