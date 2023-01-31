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

public class ComplexTypeSequenceElementComplexTypeTestCase
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
	public void firstElementGetTypeEqualsToParentComplexTypeTest() {
		final ElementNode sequenceElement = this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "tns:ParentComplexType", sequenceElement.getType() );
	}

	@Test
	public void firstElementGetNameTest() {
		final ElementNode sequenceElement= this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.firstName, sequenceElement.getName() );
	}

	@Test
	public void firstElementgetMinOccursEqualsTo0Test() {
		final ElementNode sequenceElement = this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "0", sequenceElement.getMinOccurs() );
	}

	@Test
	public void firstElementgetMaxOccursEqualsTo4Test() {
		final ElementNode sequenceElement = this.node.findByName( firstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "4", sequenceElement.getMaxOccurs() );
	}

	@Test
	public void firstElementIsEmptyEqualsToTrueTest() {
		final ElementNode sequenceElement = this.node.findByName( firstName );
		Assert.assertTrue( AssertMessages.TRUE_EXPECTED, sequenceElement.isEmpty() );
	}

	@Test
	public void firstElementIsEmptyEqualsToFalseTest() {
		final ElementNode sequenceElement = this.node.findByName( secondName );
		Assert.assertFalse( AssertMessages.FALSE_EXPECTED, sequenceElement.isEmpty() );
	}


	@Test
	public void subelementTypeTest() {
		final ElementNode sequenceElement = this.node.findByName( secondName );
		final ElementNode subElement = sequenceElement.findByName( secondFirstName );
		Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, "xsd:string", subElement.getType() );
	}
}
