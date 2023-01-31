package gal.xunta.amtega.wsdlit.core.engine.freemarker.functions;

/*-
 * #%L
 * wsdlit-core
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

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import gal.xunta.amtega.wsdlit.core.Messages;

import java.io.File;
import java.util.List;

/**
 * Freemarker function that checks if a file exists.
 *
 *  <p>
 *      It requires two arguments:
 *  </p>
 *
 *  <ol>
 *      <li>The relative path of the file to check.</li>
 *      <li>The working directory where the files are located.</li>
 *  </ol>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class FileExistsMethodModel
implements TemplateMethodModelEx {
    @Override
    public Object exec( final List list )
    throws TemplateModelException {
        if ( list.size() != 2 ) {
            throw new TemplateModelException(
                    Messages.I18N.getString( Messages.MISSING_DIRECTORY_AND_OR_FILE_PARAMETER_ERROR_KEY )
            );
        }

        final File workingDirectory = new File( list.get( 1 ).toString() ).getParentFile();
        final File file = new File( workingDirectory, list.get( 0 ).toString() );

        return file.exists();
    }
}
