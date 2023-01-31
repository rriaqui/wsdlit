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

/**
 *  A class for XSD {@code Group} elements.
 *
 * @since 1.0.0
 * @author rriaqui
 */
public final class GroupNode
extends AbstractModelGroup {
    public static final String NAME = "GROUP";

    /**
     * Constructs a {@code GroupNode} from the specified builder.
     *
     * @param builder the builder.
     */
    public GroupNode( final ElementNodeBuilder builder ) {
        super( builder.setName( GroupNode.NAME ) );
    }

    @Override
    public boolean isChoice() {
        return false;
    }

    @Override
    public boolean isSequence() {
        return false;
    }

    @Override
    public boolean isAll() {
        return false;
    }

    @Override
    public boolean isGroup() {
        return true;
    }
}
