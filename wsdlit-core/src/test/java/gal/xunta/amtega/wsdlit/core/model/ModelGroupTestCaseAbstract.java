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

import gal.xunta.amtega.wsdlit.core.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class ModelGroupTestCaseAbstract {
    private final boolean all;
    private final boolean choice;
    private final boolean group;
    private final boolean sequence;

    protected ModelGroupTestCaseAbstract(
        final boolean all,
        final boolean choice,
        final boolean group,
        final boolean sequence
    ) {
        this.all = all;
        this.choice = choice;
        this.group = group;
        this.sequence = sequence;
    }

    @Test
    public void isAllTest() {
        final AbstractModelGroup mga = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.all, mga.isAll() );
    }

    @Test
    public void isChoiceTest() {
        final AbstractModelGroup mga = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.choice, mga.isChoice() );
    }

    @Test
    public void isGroupTest() {
        final AbstractModelGroup mga = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.group, mga.isGroup() );
    }

    @Test
    public void isModelGroupTest() {
        final AbstractModelGroup mga = create();
        Assert.assertTrue( AssertMessages.EQUALS_EXPECTED, mga.isModelGroup() );
    }

    @Test
    public void isSequenceTest() {
        final AbstractModelGroup mga = create();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.sequence, mga.isSequence() );
    }

    protected abstract AbstractModelGroup create();
}
