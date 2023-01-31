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

/**
 * Enumeration of the different {@code ModelGroup} supported.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public enum ModelGroupEnum {
    NONE,
    ALL,
    CHOICE,
    SEQUENCE,
    GROUP;

    /**
     * Returns {@code true} if the type of the model group is {@code ALL}.
     *
     * @return {@code true} if the type of the model group is {@code ALL}.
     */
    public boolean isAll() {
        return ALL == this;
    }

    /**
     * Returns {@code true} if the type of the model group is {@code CHOICE}.
     *
     * @return {@code true} if the type of the model group is {@code CHOICE}.
     */
    public boolean isChoice() {
        return CHOICE == this;
    }

    /**
     * Returns {@code true} if the type of the model group is unknown.
     *
     * @return {@code true} if the type of the model group is unknown.
     */
    public boolean isNone() {
        return NONE == this;
    }

    /**
     * Returns {@code true} if the type of the model group is {@code SEQUENCE}.
     *
     * @return {@code true} if the type of the model group is {@code SEQUENCE}.
     */
    public boolean isSequence() {
        return SEQUENCE == this;
    }

    /**
     * Returns {@code true} if the type of the model group is {@code GROUP}.
     *
     * @return {@code true} if the type of the model group is {@code GROUP}.
     */
    public boolean isGroup() { return GROUP == this; }

    /**
     * Returns the {@code ModelGroupEnum} corresponding to the specified {@code SchemaComponent},
     * or {@code NODE} if the subtype cannot be determined.
     *
     * @param schemaComponent the specified schema component to be tested.
     * @return the {@code ModelGroupEnum} corresponding to a {@code SchemaComponent},
     *         or {@code NODE} if the subtype cannot be determined.
     */
    public static ModelGroupEnum valueOf( final SchemaComponent schemaComponent ) {
        if ( schemaComponent instanceof Sequence ) {
            return SEQUENCE;
        }
        if ( schemaComponent instanceof Choice ) {
            return CHOICE;
        }
        if ( schemaComponent instanceof All ) {
            return ALL;
        }
        if ( schemaComponent instanceof Group ) {
            return GROUP;
        }
        return NONE;
    }
}
