package gal.xunta.amtega.wsdlit.maven.plugin.version;

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

import gal.xunta.amtega.wsdlit.maven.plugin.AssertMessages;
import gal.xunta.amtega.wsdlit.maven.plugin.test.TestVersion;
import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.VersionType;
import org.eclipse.aether.version.Version;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Casos de proba para o filtrador de versións para PRODUCTION
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class ProductionVersionFilterTestCase {
    @Test
    public void sameEnvironmentVersionsTest() {
        final List<Version> versions = Arrays.asList(
                new TestVersion( "2.1.0" ),
                new TestVersion( "2.0.0" ),
                new TestVersion( "1.9.0" )
        );
        final String expected = "2.1.0";
        final List<Version> filteredVersions = versions
                .stream()
                .filter( new VersionFilter( VersionType.PRODUCTION ) )
                .collect( Collectors.toList() );
        final Version firstVersion = filteredVersions.get( 0 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, firstVersion.toString() );
    }

    @Test
    public void onBottomTest() {
        final List<Version> versions = Arrays.asList(
                new TestVersion( "2.1-SNAPSHOT" ),
                new TestVersion( "2.1.0-rc0" ),
                new TestVersion( "2.0.0" )
        );
        final String expected = "2.0.0";
        final List<Version> filteredVersions = versions.stream().filter( new VersionFilter( VersionType.PRODUCTION ) ).collect( Collectors.toList() );
        final Version firstVersion = filteredVersions.get( 0 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, firstVersion.toString() );
    }

    @Test
    public void onMiddleTest() {
        final List<Version> versions = Arrays.asList(
                new TestVersion( "2.1-SNAPSHOT" ),
                new TestVersion( "2.0.0" ),
                new TestVersion( "2.0.0-rc0" )
        );
        final String expected = "2.0.0";
        final List<Version> filteredVersions = versions.stream().filter( new VersionFilter( VersionType.PRODUCTION ) ).collect( Collectors.toList() );
        final Version firstVersion = filteredVersions.get( 0 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, firstVersion.toString() );
    }

    @Test
    public void onTopTest() {
        final List<Version> versions = Arrays.asList(
                new TestVersion( "2.1.0" ),
                new TestVersion( "2.1-SNAPSHOT" ),
                new TestVersion( "2.0.0" ),
                new TestVersion( "2.0.0-rc0" )
        );
        final String expected = "2.1.0";
        final List<Version> filteredVersions = versions.stream().filter( new VersionFilter( VersionType.PRODUCTION ) ).collect( Collectors.toList() );
        final Version firstVersion = filteredVersions.get( 0 );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, firstVersion.toString() );
    }

    @Test
    public void notFoundTest() {
        final List<Version> versions = Arrays.asList(
                new TestVersion( "2.1.0-rc0" ),
                new TestVersion( "2.1-SNAPSHOT" ),
                new TestVersion( "2.0.0-SNAPSHOT" ),
                new TestVersion( "2.0.0-rc0" )
        );
        final List<Version> filteredVersions = versions.stream().filter( new VersionFilter( VersionType.PRODUCTION ) ).collect( Collectors.toList() );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, filteredVersions.isEmpty() );
    }
}
