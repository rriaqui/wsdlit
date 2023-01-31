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
final String OUTPUT_DIRECTORY_VALUE = project.build.directory
final String BUILDCOMPARE_FILENAME = "${project.artifactId}-${project.version}.buildcompare"
final File OUTPUT_DIRECTORY = new File ( OUTPUT_DIRECTORY_VALUE )
final File BUILDCOMPARE_FILE = new File( OUTPUT_DIRECTORY, BUILDCOMPARE_FILENAME)

log.info( "Checking if this build is reproducible." )
log.info( "* Checking for presence of 'ko=0' in ${BUILDCOMPARE_FILE}" )

if (BUILDCOMPARE_FILE.exists() ) {
    final String BUILDCOMPARE_CONTENT = BUILDCOMPARE_FILE.getText('UTF-8')
    if ( BUILDCOMPARE_CONTENT.indexOf( "ko=0" ) > -1 ) {
        log.info( "Build is reproducible." )
    } else {
        log.error( "Build is not reproducible." )
        throw new RuntimeException( "Build not reproducible" )
    }
} else {
    log.error( "File ${BUILDCOMPARE_FILE} doesn't exists." )
    throw new RuntimeException( "File ${BUILDCOMPARE_FILENAME} doesn't exists." )
}
