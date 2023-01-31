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
 * A base class for XSD {@code modelGroup} elements,
 * providing a basic implementation of the {@code isModelGroup()} and {@code computeFullName} methods,
 * and adding the {@code isChoice()}, {@code isSequence()}, {@code isAll} and {@code isGroup()} abstract methods.
 *
 * @author rriaqui
 * @since 1.0
 */
public abstract class AbstractModelGroup
extends ElementNode {
    protected AbstractModelGroup( final ElementNodeBuilder builder ) {
        super( builder );
    }

    /**
     * Returns {@code true} when it models a {@code ChoiceNode}.
     *
     * @return {@code true} when it models a {@code ChoiceNode}.
     */
    public abstract boolean isChoice();

    /**
     * Returns {@code true} when it models a {@code SequenceNode}.
     *
     * @return {@code true} when it models a {@code SequenceNode}.
     */
    public abstract boolean isSequence();

    /**
     * Returns {@code true} when it models a {@code AllNode}.
     *
     * @return {@code true} when it models a {@code AllNode}.
     */
    public abstract boolean isAll();

    /**
     * Returns {@code true} when it models a {@code GroupNode}.
     *
     * @return {@code true} when it models a {@code GroupNode}.
     */
    public abstract boolean isGroup();

    @Override
    public final boolean isModelGroup() {
        return true;
    }

    @Override
    protected final String computeFullName() {
        return "(" + this.getName() + ")";
    }
}
