/*-
 * #%L
 * wsdlit
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

final CURRENT_VERSION = project.version
final CURRENT_VERSION_IS_SNAPSHOT = CURRENT_VERSION.endsWith( "-SNAPSHOT" )
final String OUTPUT_DIRECTORY_VALUE = project.build.directory
final File OUTPUT_DIRECTORY = new File ( OUTPUT_DIRECTORY_VALUE )
final VERSIONS =
    project
        .properties[ 'versionList' ]
        .findAll {
            it.endsWith( "-SNAPSHOT" ) == CURRENT_VERSION_IS_SNAPSHOT
        }
final FILE = new File( OUTPUT_DIRECTORY, "versions.js" )
final JS_VERSIONS_ADDITIONAL_CONTENT =
        '\nconst VERSIONS = "' + VERSIONS.join( ',' ) + '".split( "," );\n' +
        '\nwindow.addEventListener(\'load\', generateVersionSelector);\n'

FILE << JS_VERSIONS_ADDITIONAL_CONTENT
