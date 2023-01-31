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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class AllChoiceSequenceTestCase {
    protected static final String NAMESPACE = "http://emprego.xunta.es/wsdlit/maven/plugin/test";
    protected static final AllNode ALL = new AllNode( new ElementNodeBuilder("all", NAMESPACE ) );
    protected static final ChoiceNode CHOICE = new ChoiceNode( new ElementNodeBuilder( "choice", NAMESPACE ) );
    protected static final SequenceNode SEQUENCE_NODE = new SequenceNode( new ElementNodeBuilder( "sequence", NAMESPACE ) );

    protected static final GroupNode GROUP_NODE = new GroupNode( new ElementNodeBuilder( "group", NAMESPACE ) );

    private final AbstractModelGroup modelGroup;
    private final boolean allExpected;
    private final boolean choiceExpected;

    private final boolean groupExpected;
    private final boolean sequenceExpected;
    private final boolean modelGroupExpected;

    public AllChoiceSequenceTestCase(
            final AbstractModelGroup modelGroup,
            final boolean allExpected,
            final boolean choiceExpected,
            final boolean sequenceExpected,
            final boolean modelGroupExpected,
            final boolean groupExpected
    ) {
        this.modelGroup = modelGroup;
        this.allExpected = allExpected;
        this.choiceExpected = choiceExpected;
        this.groupExpected = groupExpected;
        this.sequenceExpected = sequenceExpected;
        this.modelGroupExpected = modelGroupExpected;
    }

    @Test
    public void isAllTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, allExpected, modelGroup.isAll() );
    }

    @Test
    public void isChoiceTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, choiceExpected, modelGroup.isChoice() );
    }

    @Test
    public void isGroupTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, groupExpected, modelGroup.isGroup() );
    }

    @Test
    public void isSequenceTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, sequenceExpected, modelGroup.isSequence() );
    }

    @Test
    public void isModelGroupTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, modelGroupExpected, modelGroup.isModelGroup() );
    }

    @Parameterized.Parameters
    public static Iterable< Object[] > data() {
        return Arrays.asList(
                new Object[][] {
                        { ALL, true, false, false, true, false },
                        { CHOICE, false, true, false, true, false },
                        { SEQUENCE_NODE, false, false, true, true, false },
                        { GROUP_NODE, false, false, false, true, true}
                }
        );
    }
}
