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
import gal.xunta.amtega.wsdlit.core.model.ServiceNode;

public class ComplexTypeTestCaseAbstract
extends TestCaseAbstract {
	protected final String name = "ComplexType";
	protected final String firstName = "first";
	protected final String secondFirstName = "second_1";
	protected final String secondName = "second";
	protected final ServiceNode serviceNode = ConverterManager.getInstance().convert( this.schema.getDefinitions() );
	protected final ElementNode node = ConverterManager.getInstance().convert( this.schema.getComplexType( this.name ) );

	public ComplexTypeTestCaseAbstract() {
		super( "complexType" );
	}
}
