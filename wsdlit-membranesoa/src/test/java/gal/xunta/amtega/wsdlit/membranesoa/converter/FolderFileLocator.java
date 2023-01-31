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

import java.io.File;

/**
 * Localiza recursos que se atopan nunha carpeta
 * @author rriaqui
 *
 */
public class FolderFileLocator
implements FileLocator {
	private final File folder;

	public FolderFileLocator( final File parentFolder, final String folder ) {
		this.folder = new File( parentFolder, folder );
	}

	public FolderFileLocator( final File parentFolder, final Class<?> clazz ) {
		this.folder = new File( parentFolder, clazz.getSimpleName() );
	}

	@Override
	public File locate( final String filename ) {
		return new File( this.folder, filename );
	}

	@Override
	public File locate( final Class<?> clazz ) {
		return new File( this.folder, clazz.getSimpleName() );
	}
}
