package gal.xunta.amtega.wsdlit.membranesoa;

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

import gal.xunta.amtega.wsdlit.core.model.APIDocument;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.converter.APIDocumentConverter;
import gal.xunta.amtega.wsdlit.membranesoa.converter.ConverterManager;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class APIDocumentConverterTestCase {
	private final String path = "src/test/resources";
	private final File directory = new File( this.path );

	private final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
	private final ConverterManager converterManager = ConverterManager.getInstance().initNamespacePrefixCache( cache );


	@Test
	public void convertTest() {
		final List< String > uris = Collections.singletonList( "converter/converter.wsdl" );
		final APIDocumentConverter converter = new APIDocumentConverter( this.directory );
		final APIDocument document = converter.convert( uris );
        Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, document );
	}

	@Test
	public void convertAbsoluteUriTest() {
		final String filenamePath = "src/test/resources/converter/converter.wsdl";
		final String absoluteUri = new File( filenamePath ).getAbsolutePath();
		final APIDocumentConverter converter = converterManager.getApiDocumentConverter();
		final APIDocument api = converter.convert( Collections.singletonList( absoluteUri ) );

		Assert.assertNotNull( AssertMessages.NOT_NULL_EXPECTED, api );
	}
}
