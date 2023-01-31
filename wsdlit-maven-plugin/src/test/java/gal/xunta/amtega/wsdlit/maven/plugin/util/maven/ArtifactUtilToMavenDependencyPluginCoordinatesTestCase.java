package gal.xunta.amtega.wsdlit.maven.plugin.util.maven;

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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class ArtifactUtilToMavenDependencyPluginCoordinatesTestCase {
    private final String coordinates;
    private final String expected;

    public ArtifactUtilToMavenDependencyPluginCoordinatesTestCase( final String coordinates, final String expected ) {
        this.coordinates = coordinates;
        this.expected = expected;
    }

    @Test
    public void convertTest() {
        final String fixedCoordinates = ArtifactUtil.toMavenDependencyPluginCoordinates( this.coordinates );
        Assert.assertEquals( "As coordenadas en formato maven-dependency-plugin non son as esperadas", this.expected, fixedCoordinates );
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "groupId:artifactId:1.0.0", "groupId:artifactId:1.0.0" },
            { "groupId:artifactId:extension:1.0.0", "groupId:artifactId:1.0.0:extension" },
            { "groupId:artifactId:extension:classifier:1.0.0", "groupId:artifactId:1.0.0:extension:classifier" }
        });
    }
}
