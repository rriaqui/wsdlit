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

import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith( Parameterized.class )
public class ModelGroupEnumTestCase {
    private final ModelGroupEnum mg;
    private final boolean isAll;
    private final boolean isChoice;
    private final boolean isGroup;
    private final boolean isNone;
    private final boolean isSequence;

    public ModelGroupEnumTestCase(
            final ModelGroupEnum mg,
            final boolean isAll,
            final boolean isChoice,
            final boolean isGroup,
            final boolean isNone,
            final boolean isSequence
    ) {
        this.mg = mg;
        this.isAll = isAll;
        this.isChoice = isChoice;
        this.isGroup = isGroup;
        this.isNone = isNone;
        this.isSequence = isSequence;
    }

    @Test
    public void isAllTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.isAll, this.mg.isAll() );
    }

    @Test
    public void isChoiceTest(){
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.isChoice, this.mg.isChoice() );
    }

    @Test
    public void isModelGroupTest(){
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.isGroup, this.mg.isGroup() );
    }

    @Test
    public void isNoneTest(){
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.isNone, this.mg.isNone() );
    }

    @Test
    public void isSequenceTest(){
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.isSequence, this.mg.isSequence() );
    }

    @Parameterized.Parameters
    public static Iterable< Object[] > data() {
        return Arrays.asList(
                new Object[][] {
                        { ModelGroupEnum.ALL, true, false, false, false, false },
                        { ModelGroupEnum.CHOICE, false, true, false, false, false },
                        { ModelGroupEnum.GROUP, false, false, true, false, false },
                        { ModelGroupEnum.NONE, false, false, false, true, false },
                        { ModelGroupEnum.SEQUENCE, false, false, false, false, true }
                }
        );
    }
}
