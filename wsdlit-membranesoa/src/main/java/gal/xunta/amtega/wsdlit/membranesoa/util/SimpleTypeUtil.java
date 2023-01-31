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

import com.predic8.schema.SimpleType;
import groovy.namespace.QName;

import java.util.stream.Collectors;

/**
 * Utilities related to SimpleType.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class SimpleTypeUtil {
    private SimpleTypeUtil() {}

    /**
     * Returns the data type defined in {@code SimpleType}.
     *
     * <p>
     *      Calculating the data type can be problematic if it has been defined as a union of different types,
     *      so the implemented algorithm is as follows:
     * </p>
     *
     * <ol>
     *      <li>If not defined as a list, the type defined for the element is returned.</li>
     *      <li>If a constraint was defined, the type of the constraint is returned.</li>
     *      <li>If defined as a {@code union},
     *          the list of detected types is returned separated by a pipe {@code |}.</li>
     * </ol>
     *
     * <p>If only one data type has been defined, for example {@code xsd:string},
     * that will be the value returned by this method.</p>
     *
     * <p>If it was defined by a {@code union} in which data types existed
     * {@code xsd:string} and {@code xsd:int}, for example, we would get text similar to:
     * {@code xsd:string|xsd:int}.</p>
     *
     * @param simpleType the {@code SimpleType} from which the data type is intended to be computed.
     * @return the data type defined in {@code SimpleType}.
     */
    public static String getType( final SimpleType simpleType ) {
        if ( simpleType.getList() != null ) {
            return simpleType.getList().getItemType();
        }
        if( simpleType.getRestriction() != null ) {
            return simpleType.getRestriction().getBase().getQualifiedName();
        }
        if ( simpleType.getUnion() != null ) {
            // Comprobamos se todos os simpleType do union son do mesmo tipo
            return simpleType.getUnion().getMemberTypes()
                       .stream()
                       .map( QName::getQualifiedName )
                       .collect( Collectors.joining( "|" ) );
        }
        return simpleType.getBuildInTypeName();
    }
}
