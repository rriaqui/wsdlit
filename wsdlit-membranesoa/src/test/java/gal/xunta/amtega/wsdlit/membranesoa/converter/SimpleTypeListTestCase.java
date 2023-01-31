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

public class SimpleTypeListTestCase
extends SimpleTypeTestCaseAbstract {
	@Test
	public void getNamespaceTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TARGETNAMESPACE, this.node.getNamespaceUri() );
	}

	@Test
	public void getNameTestTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.name, this.node.getName() );
	}

	@Test
	public void getTypeTestTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "xsd:integer", this.node.getType() );
	}

	@Test
	public void noChildrenTestTest() {
		Assert.assertTrue( AssertMessages.TRUE_EXPECTED, this.node.isEmpty() );
	}
}
