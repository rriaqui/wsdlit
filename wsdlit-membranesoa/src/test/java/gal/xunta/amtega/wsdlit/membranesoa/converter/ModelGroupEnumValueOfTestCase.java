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
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith( Parameterized.class )
public class ModelGroupEnumValueOfTestCase {
    private final ModelGroupEnum expected;
    private final SchemaComponent schemaComponent;

    public ModelGroupEnumValueOfTestCase(
        final ModelGroupEnum expected,
        final SchemaComponent schemaComponent
        ) {
        this.expected = expected;
        this.schemaComponent = schemaComponent;
    }

    @Test
    public void valueOfTest(){
        final ModelGroupEnum founded = ModelGroupEnum.valueOf( this.schemaComponent );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.expected, founded );
    }

    @Parameterized.Parameters
    public static Iterable< Object[] > data() {
        return Arrays.asList(
                new Object[][] {
                        { ModelGroupEnum.ALL, new All() },
                        { ModelGroupEnum.CHOICE, new Choice() },
                        { ModelGroupEnum.GROUP, new Group() },
                        { ModelGroupEnum.SEQUENCE, new Sequence() },
                        { ModelGroupEnum.NONE, null },
                        { ModelGroupEnum.NONE, new Element() },
                        { ModelGroupEnum.NONE, new Annotation() },
                        { ModelGroupEnum.NONE, new ComplexContent() },
                        { ModelGroupEnum.NONE, new ComplexType() },
                        { ModelGroupEnum.NONE, new Documentation() },
                        { ModelGroupEnum.NONE, new SimpleContent() },
                        { ModelGroupEnum.NONE, new SimpleType() },
                }
        );
    }
}
