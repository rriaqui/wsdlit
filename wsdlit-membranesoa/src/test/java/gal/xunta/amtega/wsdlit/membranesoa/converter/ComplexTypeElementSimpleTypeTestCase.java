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

public class ComplexTypeElementSimpleTypeTestCase
extends ComplexTypeTestCaseAbstract {
	@Test
	public void getNamespaceEqualsToTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, TARGETNAMESPACE, this.node.getNamespaceUri() );
	}

	@Test
	public void getNameEqualsToComplexTypeTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.name, this.node.getName() );
	}

	@Test
	public void getTypeUndefinedTest() {
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "-", this.node.getType() );
	}

	@Test
	public void isEmptyEqualsToFalseTest() {
		Assert.assertFalse( AssertMessages.FALSE_EXPECTED, this.node.isEmpty() );
	}

	@Test
	public void firstElementTypeEqualsToParentSimpleTypeTest() {
		final ElementNode firstElement = this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "tns:ParentSimpleType", firstElement.getType() );
	}

	@Test
	public void firstElementGetNameTest() {
		final ElementNode firstElement = this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.firstName, firstElement.getName() );
	}

	@Test
	public void firstElementMinOccursEqualsToZeroTest() {
		final ElementNode firstElement = this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "0", firstElement.getMinOccurs() );
	}

	@Test
	public void firstElementMaxOccursEqualsToFourTest() {
		final ElementNode firstElement = this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "4", firstElement.getMaxOccurs() );
	}

	@Test
	public void firstElementIsEmptyEqualsToTrueTest() {
		final ElementNode firstElement = this.node.findByName( firstName );
		Assert.assertTrue( AssertMessages.TRUE_EXPECTED, firstElement.isEmpty() );
	}
}
