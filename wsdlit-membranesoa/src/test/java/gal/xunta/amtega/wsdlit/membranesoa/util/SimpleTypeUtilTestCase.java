package gal.xunta.amtega.wsdlit.membranesoa.util;

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

import com.predic8.schema.SimpleType;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleTypeUtilTestCase {
    public static final String TYPE_NAME = "{string}name";

    @Test
    public void getTypeTest() {
        final SimpleType simpleType = Mockito.mock( SimpleType.class );
        Mockito.when( simpleType.getBuildInTypeName() ).thenReturn( TYPE_NAME );

        final String typeName = SimpleTypeUtil.getType( simpleType );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TYPE_NAME, typeName );
    }
}
