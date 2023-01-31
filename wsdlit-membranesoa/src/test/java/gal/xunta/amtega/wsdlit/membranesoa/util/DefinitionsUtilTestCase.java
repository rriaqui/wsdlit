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

import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.core.model.PortTypeNode;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DefinitionsUtilTestCase {
    @Test
    public void getPortTypeTest() {
        final String filenamePath = "src/test/resources/converter/converter.wsdl";
        final Definitions definitions = MembraneSOAUtil.readWSDLFile( filenamePath );
        final List< PortTypeNode > portTypeNodes = DefinitionsUtil.getPortTypes( definitions );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, 1, portTypeNodes.size() );
    }

    @Test
    public void getPortTypeWhenDefinitionsIsNullTest() {
        final List< PortTypeNode > portTypeNodes = DefinitionsUtil.getPortTypes( null );

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, portTypeNodes.isEmpty() );
    }
}
