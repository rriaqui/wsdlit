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

import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

public class ComplexTypeSimpleTypeRestrictionEmbeddedTestCase
extends ComplexTypeTestCaseAbstract {
	@Test
	public void getNamespaceTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TARGETNAMESPACE, this.node.getNamespaceUri() );
	}

	@Test
	public void getNameTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.name, this.node.getName() );
	}

	@Test
	public void getTypeTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "-", this.node.getType() );
	}

	@Test
	public void oneChildrenTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, 1, this.node.getChildren().size() );
	}

	@Test
	public void childTypeTest() {
		final ElementNode child = this.node.get( 0 ).get( 0 );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "xsd:string", child.getType() );
	}

	@Test
	public void childNameTest() {
		final ElementNode child = this.node.get( 0 ).get( 0 );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.firstName, child.getName() );
	}
}
