package gal.xunta.amtega.wsdlit.maven.plugin;

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

import org.apache.maven.plugin.testing.MojoRule;
import org.junit.Rule;

import java.io.File;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractParameterTest {
    @Rule
    public final MojoRule mojoRule = new MojoRule() {
        @Override
        protected void before() {
        }

        @Override
        protected void after() {
        }
    };
    protected final File projectBuildDirectory = new File( getBaseDir(), "target" );

    protected File getPomFile() {
        return getPomFile( "pom.xml" );
    }

    protected File getPomFile( final String filename ) {
        final File file = getBaseDir();
        if ( filename.endsWith( ".xml" ) ) {
            return new File( file, filename );
        }
        return new File( file, filename + ".xml" );
    }

    protected abstract File getBaseDir();
}
