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

import com.predic8.schema.Schema;
import com.predic8.schema.SchemaParser;

import java.io.File;

public abstract class TestCaseAbstract {
	public static final String TARGETNAMESPACE = "http://emprego.xunta.es/test/simple";
	private final File resourcesFolder = new File( "src/test/resources" );
	private final FileLocator fileLocator;
    protected final Schema schema;
	protected final SchemaParser parser = new SchemaParser();

    public TestCaseAbstract() {
    	this.fileLocator = new FolderFileLocator( this.resourcesFolder, this.getClass() );
    	this.schema = this.getSchema( "schema.xsd" );
    }

    public TestCaseAbstract( final String folder ) {
    	this.fileLocator = new FolderFileLocator( this.resourcesFolder, folder );
    	this.schema = this.getSchema( this.getClass().getSimpleName().replace( "TestCase", "" ) + ".xsd" );
    }

    public Schema getSchema( final String filename ) {
		final File file = this.fileLocator.locate( filename );
		return this.parser.parse( file.getAbsolutePath() );
	}
}
