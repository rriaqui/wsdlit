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
final STAGING_DIRECTORY = findRootModule( project ).build.directory + "/staging/" + project.version

project.properties.setProperty( 'stagingDirectory', STAGING_DIRECTORY )

final String rootModuleBaseDir = findRootModule( project ).basedir
final String currentModuleBaseDir = project.basedir
final String relativePath = ( currentModuleBaseDir.substring( rootModuleBaseDir.length() ) )

project.properties[ 'moduleRelativePath' ] = relativePath
log.info( "[RRIAQUI] rootModuleBaseDir = $rootModuleBaseDir")
log.info( "[RRIAQUI] currentModuleBaseDir = $currentModuleBaseDir" )
log.info( "[RRIAQUI] relativePath = " + relativePath )

def findRootModule( project ) {
    module = project
    while ( ( module?.parent?.basedir ) != null ) {
        module = module.parent
    }
    return module
}
