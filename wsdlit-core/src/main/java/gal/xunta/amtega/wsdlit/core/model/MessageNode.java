package gal.xunta.amtega.wsdlit.core.model;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A class for modelling operation messages and faults.
 *
 * <p>
 *      This item does not match to the XML Message, it is a simplification aimed at facilitating representation.
 * </p>
 *
 * @author rriaqui
 * @since 1.0
 */
public class MessageNode
extends AbstractNode {
    /*
     * List of the supported parts.
     */
    private final List<PartNode> parts = new ArrayList<>();

    /**
     * Appends the specified part at the end of the parts list.
     *
     * @param part the {@code part} to append at the end of the parts list..
     */
    public void add( final PartNode part ) {
        this.parts.add( part );
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if (
            o == null
                || this.getClass() != o.getClass()
                || !super.equals( o )
        ) {
            return false;
        }
        final MessageNode that = ( MessageNode ) o;
        return parts.equals( that.parts );
    }

    /**
     * Return the parts list.
     *
     * @return the parts list.
     */
    public List<PartNode> getParts() {
        return Collections.unmodifiableList( this.parts );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), parts );
    }
}
