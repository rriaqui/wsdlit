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

import java.util.Objects;

/**
 * An object representing the {@code part} element information of a wsdl document.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class PartNode
extends AbstractNode {
    private ElementNode element = new ElementNodeBuilder("blah", "blah")
            .setType("blah")
            .build();

    private String type;
    private boolean definedByElement = true;

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null ) {
            return false;
        }
        if ( this.getClass() != o.getClass() ) {
            return false;
        }
        if ( ! super.equals( o ) ) {
            return false;
        }

        final PartNode partNode = (PartNode) o;
        return definedByElement == partNode.definedByElement && Objects.equals(element, partNode.element) && Objects.equals(type, partNode.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), element, type, definedByElement );
    }

    /**
     * Returns {@code true} if it is defined by an element, otherwise returns {@code false}.
     *
     * @return {@code true} if it is defined by an element, otherwise returns {@code false}.
     */
    public boolean isDefinedByElement() {
        return this.definedByElement;
    }

    /**
     * Returns the {@code ElementNode} that defines the {@code part},
     * or {@code null} if the {@code part} was not defined by an {@code element}.
     *
     * <p>
     *     This value is always {@code null} when {@code isDefinedByElement == false}.
     * </p>
     *
     * @return the {@code ElementNode} that defines the {@code part},
     *         or {@code null} if the {@code part} was not defined by an {@code element}.
     *
     * @see #isDefinedByElement()
     */
    public ElementNode getElement() {
        return this.element;
    }

    /**
     * Returns the data type of the {@code part} when it was not defined by an {@code element},
     * or {@code null} otherwise.
     *
     * <p>
     *     This value is always {@code null} when {@code isDefinedByElement == false}.
     * </p>
     * @return the data type of the {@code part} when it was not defined by an {@code element},
     *         or {@code null} otherwise.
     *
     * @see #isDefinedByElement()
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the {@code ElementNode} that defines the {@code part}.
     *
     * @param element the {@code ElementNode} that defines the {@code part}.
     */
    public void setElement( final ElementNode element ) {
        this.element = element;
    }

    /**
     * Sets the data type of the {@code part}.
     *
     * @param type the data type of the {@code part}.
     */
    public void setType( final String type ) {
        this.type = type;
    }

    /**
     * Sets the {@code definedByElement} flag for this object to the indicated boolean value.
     *
     * @param definedByElement the new value for the definedByElement flag.
     */
    public void setDefinedByElement( final boolean definedByElement ) {
        this.definedByElement = definedByElement;
    }
}
