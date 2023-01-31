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
 * An object representing the {@code sequence} element information of a xsd document.
 *
 * @since 1.0.0
 * @author rriaqui
 */
public final class SequenceNode
extends AbstractModelGroup {
    public static final String NAME = "SEQUENCE";

    /**
     * Constructs a {@code SequenceNode} from the specified builder.
     *
     * @param builder the builder.
     */
    public SequenceNode( final ElementNodeBuilder builder ) {
        super( builder.setName( SequenceNode.NAME ) );
    }

    @Override
    public boolean isChoice() {
        return false;
    }

    @Override
    public boolean isSequence() {
        return true;
    }

    @Override
    public boolean isAll() {
        return false;
    }

    @Override
    public boolean isGroup() {
        return false;
    }
}
