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

import gal.xunta.amtega.wsdlit.maven.plugin.AssertMessages;
import gal.xunta.amtega.wsdlit.maven.plugin.test.TestVersion;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.version.Version;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class VersionUtilTestCase {
    @Test
    public void isNotSnapshotWhenSnapshotVersionReturnsFalseTest() {
        final TestVersion version = new TestVersion( "1.0.0-SNAPSHOT" );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, VersionUtil.isNotSnapshot( version ) );
    }

    @Test
    public void isNotSnapshotWhenReleaseVersionReturnsTrueTest() {
        final TestVersion version = new TestVersion( "1.0.0" );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, VersionUtil.isNotSnapshot( version ) );
    }

    @Test
    public void isSnapshotWhenFinalVersionReturnsFalseTest() {
        final TestVersion version = new TestVersion( "1.0.0" );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, VersionUtil.isSnapshot( version ) );
    }

    @Test
    public void versionsLowerThanTest() {
        final Artifact artifact = new DefaultArtifact( "gal.xunta.amtega.wsdlit.maven.plugin:myV2CA:zip:2.0.0" );
        final Artifact expected = new DefaultArtifact(
            artifact.getGroupId(),
            artifact.getArtifactId(),
            artifact.getClassifier(),
            artifact.getExtension(),
            "[,2.0.0)");
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, VersionUtil.versionsLowerThan( artifact ) );
    }

    @Test
    public void findPreviousArtifactFromCandidatesTest() {
        final List< Version > versions = createVersionList();
        final List< Artifact > candidates =
            versions
                .stream()
                .map( version -> new DefaultArtifact( "groupId:artifactId:" + version.toString()  ) )
                .collect( Collectors.toList() );

        final Artifact expected = new DefaultArtifact( "groupId:artifactId:1.1.0" );
        final Optional< Artifact > optional = VersionUtil.findHighestArtifactFromVersion( candidates );
        if ( optional.isPresent() ) {
            final Artifact artifact = optional.get();
            Assert.assertEquals( "As versións deberían coincidir.", expected.getVersion(), artifact.getVersion() );
        } else {
            Assert.fail( "Esperábase un artefacto." );
        }
    }

    @Test
    public void findHighestArtifactFromVersionReturnsEmptyWhenCandidatesListIsNull() {
        final Optional< Artifact > artifact = VersionUtil.findHighestArtifactFromVersion( null, VersionType.STAGE );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, artifact.isPresent() );
    }

    @Test
    public void findHighestArtifactFromVersionReturnsEmptyWhenCandidatesListIsEmpty() {
        final Optional< Artifact > artifact = VersionUtil.findHighestArtifactFromVersion( Collections.emptyList(), VersionType.STAGE );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, artifact.isPresent() );
    }

    private static List<Version> createVersionList() {
        return
            Arrays.asList(
                new TestVersion( "1.0.0-SNAPSHOT" ),
                new TestVersion( "1.0.1" ),
                new TestVersion( "1.2.0-SNAPSHOT" ),
                new TestVersion( "1.1.0-rc1" ),
                new TestVersion( "1.1.0" ),
                new TestVersion( "1.0.2" ),
                new TestVersion( "1.1.0-rc0" )
            );
    }
}
