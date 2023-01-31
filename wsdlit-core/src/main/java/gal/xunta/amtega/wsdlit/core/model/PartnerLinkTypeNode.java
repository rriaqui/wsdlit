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
 * An object representing the {@code partnerLinkType} element information of a wsdl document.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class PartnerLinkTypeNode
extends AbstractNode {
    private final List<RoleNode> roles = new ArrayList<>();

    /**
     * Appends a new role to the end of this object's role list.
     *
     * @param role the role to add.
     */
    public void add( final RoleNode role ) {
        this.roles.add( role );
    }

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if (
            o == null
                || this.getClass() != o.getClass()
                || ! super.equals( o )
        ) {
            return false;
        }
        final PartnerLinkTypeNode that = ( PartnerLinkTypeNode ) o;
        return roles.equals( that.roles );
    }

    /**
     * Returns the list of roles of this {@code PartnerLinkTypeNode}.
     *
     * @return the list of roles of this {@code PartnerLinkTypeNode}.
     */
    public List<RoleNode> getRoles() {
        return Collections.unmodifiableList( this.roles );
    }

    @Override
    public int hashCode() {
        return Objects.hash( roles, super.hashCode() );
    }
}
