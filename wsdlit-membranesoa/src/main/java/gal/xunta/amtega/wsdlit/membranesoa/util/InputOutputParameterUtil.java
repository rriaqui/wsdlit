package gal.xunta.amtega.wsdlit.membranesoa.util;

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

import com.predic8.wsdl.Input;
import com.predic8.wsdl.Operation;
import com.predic8.wsdl.Output;
import gal.xunta.amtega.wsdlit.core.model.OperationType;

/**
 * Utilities related to input/output parameters of operations.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class InputOutputParameterUtil {
    private InputOutputParameterUtil() {}

    /**
     * Updates the specified input parameter name when it is not defined,
     * for the specified operation and the specified operationType.
     * It follows the set defaults in WSDL 1.1:
     *
     * <ul>
     *      <li><strong>REQUEST_RESPONSE</strong>: {@code ${operation_name}Request}.</li>
     *      <li><strong>SOLICIT_RESPONSE</strong>: {@code }${operation_name}Solicit}.</li>
     *      <li><strong>ONE_WAY</strong>: {@code ${operation_name}}.</li>
     * </ul>
     *
     * @param input the specified input message.
     * @param operationType the specified operation type.
     * @param operation the specified operation.
     */
    public static void updateInputParameterName( final Input input, final OperationType operationType, final Operation operation ) {
        if ( input != null && input.getName() == null ) {
            switch ( operationType ) {
                case REQUEST_RESPONSE:
                    input.setName( operation.getName() + "Request" );
                    break;
                case SOLICIT_RESPONSE:
                    input.setName( operation.getName() + "Solicit" );
                    break;

                case ONE_WAY:
                    input.setName( operation.getName() );
                    break;

                default:
                    break;
            }
        }
    }


    /**
     * Updates the specified output parameter name when it is not defined,
     * for the specified operation and the specified operationType.
     * It follows the set defaults in WSDL 1.1:
     *
     * <ul>
     *      <li><strong>REQUEST_RESPONSE</strong>: {@code ${operation_name}Request}.</li>
     *      <li><strong>SOLICIT_RESPONSE</strong>: {@code }${operation_name}Solicit}.</li>
     *      <li><strong>ONE_WAY</strong>: {@code ${operation_name}}.</li>
     * </ul>
     *
     * @param output the specified output message.
     * @param operationType the specified operation type.
     * @param operation the specified operation.
     */
    public static void updateOutputParameterName( final Output output, final OperationType operationType, final Operation operation ) {
        if ( output != null && output.getName() == null ) {
            switch ( operationType ) {
                case REQUEST_RESPONSE:
                case SOLICIT_RESPONSE:
                    output.setName( operation.getName() + "Response" );
                    break;

                case NOTIFICATION:
                    output.setName( operation.getName() );
                    break;

                default:
                    break;
            }
        }
    }
}
