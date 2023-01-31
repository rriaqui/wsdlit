package gal.xunta.amtega.wsdlit.core.util;

/*-
 * #%L
 * wsdlit-core
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

/**
 * Representation of the values that can be setted for {@code elementFormDefault}. and {@code attributeFormDefault}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public enum FormDefault {
    /**
     * Represents the {@code qualified} value.
     */
    QUALIFIED( true ),

    /**
     * Represents the {@code unqualified} value.
     */
    UNQUALIFIED( false ),

    /**
     * Represents the value non setted.
     */
    NONE( false );

    /**
     * The value expressed in terms of {@code true} or {@code false}, where:
     *
     * <ul>
     *     <li>{@code QUALIFIED = true}</li>
     *     <li>{@code UNQUALIFIED = false}</li>
     *     <li>{@code NONE = false}</li>
     * </ul>
     */
    private final boolean value;

    /**
     * Constructs a {@code FormDefault} with the specified boolean value.
     *
     * @param value the boolean value of this {@code FormDefault}.
     */
    FormDefault( final boolean value ) {
        this.value = value;
    }

    /**
     * Returns the boolean value of this {@code FormDefault}.
     *
     * @return the boolean value of this {@code FormDefault}.
     */
    public boolean asBoolean() {
        return this.value;
    }

    /**
     * Returns {@code true} if it is {@link #NONE}.
     *
     * @return {@code true} if it is {@link #NONE}.
     */
    public boolean isNone() {
        return this == NONE;
    }

    /**
     * Returns {@code true} if it is not {@link #NONE}.
     *
     * @return {@code true} if it is not {@link #NONE}.
     */
    public boolean isSet() {
        return this != NONE;
    }

    /**
     * Returns a {@code FormDefault} based on the specified value.
     *
     * @param value the {@code Boolean} value of the {@code FormDefault}.
     * @return {@link #NONE} if value is {@code null}
     * {@link #QUALIFIED} if the value is {@code Boolean.TRUE}
     * {@link #UNQUALIFIED} if the value is {@code Boolean.FALSE}
     */
    public static FormDefault valueOf( final Boolean value ) {
        return value != null ? valueOf(value.booleanValue()) : FormDefault.NONE;
    }

    /**
     * Returns a {@code FormDefault} based on the specified text value.
     *
     * @param name the name of the {@code FormDefault} enumeration.
     * @return
     * {@link #QUALIFIED} if the value is {@code true},
     * {@link #UNQUALIFIED} if the value is {@code false},
     * {@link #NONE} in all other cases.
     */
    public static FormDefault from( final String name ) {
        final String normalized = StringUtil.emptyIfBlank( name ).toLowerCase();
        if ( StringUtil.isBlank( normalized ) || normalized.equals( "none" ) ) {
            return NONE;
        }
        return valueOf( Boolean.valueOf( name ) );
    }

    /**
     * Returns a {@code FormDefault} based on the specified value.
     *
     * @param value the boolean value of {@code FormDefault}.
     * @return {@code TRUE} if the value is {@code true}
     * {@code FALSE} if the value is {@code false}
     */
    public static FormDefault valueOf( final boolean value ) {
        return ( value ) ? QUALIFIED : UNQUALIFIED;
    }
}
