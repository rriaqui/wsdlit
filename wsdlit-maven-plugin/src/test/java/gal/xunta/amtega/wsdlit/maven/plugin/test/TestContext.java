package gal.xunta.amtega.wsdlit.maven.plugin.test;

/*-
 * #%L
 * AMTEGA WsdlIT Maven Plugin
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

import gal.xunta.amtega.wsdlit.maven.plugin.MojoDefaultValues;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public final class TestContext {

    private final Path targetPath;
    private final Path testClassesPath;
    private final Path resourcesPath;
    private final Path outputPath;
    private final Path apiPath;
    private final Path apiVersionPath;
    private final Path sourcesDirectory;
    private final Path sourceDirectory;
    private final Path additionalFiles;

    private final Path wsdlDirectory;

    private final Path nonExistingDir = Paths.get( "/this/dir/doesnt/exists" );

    public TestContext( final Class< ? > clazz, final String resource ) {
        this.targetPath = new File( "target" ).getAbsoluteFile().toPath();
        this.testClassesPath = Paths.get( this.targetPath.toString(), "test-classes" );
        this.resourcesPath = Paths.get( this.testClassesPath.toString(), clazz.getName().replace( ".", File.separator ), resource );
        this.outputPath = Paths.get( this.targetPath.toString(), "temp", clazz.getSimpleName(), resource );
        this.apiPath = Paths.get( this.outputPath.toString(), "api" );
        this.apiVersionPath = Paths.get( this.apiPath.toString(), "1.0.0");
        this.sourceDirectory = Paths.get( this.resourcesPath.toString(), MojoDefaultValues.SOURCE_DIRECTORY_TARGET );
        this.sourcesDirectory = Paths.get( this.resourcesPath.toString(), "sourcesDirectory" );
        this.additionalFiles = Paths.get( this.outputPath.toString(), "serviceName/wsdlit" );

        this.wsdlDirectory = Paths.get( this.resourcesPath.toString(), MojoDefaultValues.WSDL_DIRECTORY_SOURCE );
    }

    public void deleteDirs()
    throws IOException {
        if ( this.outputPath.toFile().isDirectory() ) {
            FileUtils.deleteDirectory( this.outputPath.toFile() );
        }
    }

    public TestContext init()
    throws IOException {
        this.deleteDirs();
        Files.createDirectories( this.outputPath );
        return this;
    }

    public Path getTargetPath() {
        return this.targetPath;
    }

    public Path getTestClassesPath() {
        return this.testClassesPath;
    }

    public Path getResourcesPath() {
        return this.resourcesPath;
    }

    public Path getOutputPath() {
        return this.outputPath;
    }

    public Path getApiPath() {
        return this.apiPath;
    }

    public Path getApiVersionPath() {
        return this.apiVersionPath;
    }

    public Path getNonExistingDir() {
        return this.nonExistingDir;
    }

    public Path getSourceDirectory() {
        return this.sourceDirectory;
    }

    public Path getSourcesDirectory() {
        return this.sourcesDirectory;
    }

    public Path getAdditionalFiles() {
        return this.additionalFiles;
    }

    public Path getWsdlDirectory() {
        return this.wsdlDirectory;
    }
}
