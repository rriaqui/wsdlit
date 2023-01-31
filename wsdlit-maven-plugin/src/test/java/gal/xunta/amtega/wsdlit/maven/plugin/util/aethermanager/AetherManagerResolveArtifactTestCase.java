package gal.xunta.amtega.wsdlit.maven.plugin.util.aethermanager;

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
import gal.xunta.amtega.wsdlit.maven.plugin.DownloadException;
import gal.xunta.amtega.wsdlit.maven.plugin.test.TestVersion;
import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.AetherManager;
import org.apache.maven.plugin.logging.Log;
import org.eclipse.aether.RepositoryException;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.resolution.VersionRangeResult;
import org.eclipse.aether.resolution.VersionResult;
import org.eclipse.aether.version.Version;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class AetherManagerResolveArtifactTestCase {
    @Test
    public void resolveArtifactWhenVersionStartsWithParentheseTest()
    throws DownloadException, RepositoryException {
        final String coordinates = "wsdlit:wsdlit:(0.0.0,3.0.0)";
        final Artifact artifactToResolve = new DefaultArtifact( coordinates );
        final String versionExpected = "2.1.0";
        final Version highestVersion = new TestVersion( versionExpected );
        final VersionRangeResult versionRangeResultMocked = Mockito.mock( VersionRangeResult.class );
        final RepositorySystem repoMocked = Mockito.mock( RepositorySystem.class );
        final Log logMocked = Mockito.mock( Log.class );
        final AetherManager aetherManager = new AetherManager( repoMocked, null, Collections.emptyList(), logMocked );

        when( versionRangeResultMocked.getHighestVersion() ).thenReturn( highestVersion );
        when( repoMocked.resolveVersionRange( any(), any() ) ).thenReturn( versionRangeResultMocked );
        final String versionResolved = aetherManager.resolveArtifact( artifactToResolve ).getVersion();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, versionExpected, versionResolved );
    }

    @Test
    public void resolveArtifactWhenVersionStartsWithBracketTest()
    throws DownloadException, RepositoryException {
        final String coordinates = "wsdlit:wsdlit:[0.0.0,3.0.0)";
        final Artifact artifactToResolve = new DefaultArtifact( coordinates );
        final String versionExpected = "2.1.0";
        final Version highestVersion = new TestVersion( versionExpected );
        final VersionRangeResult versionRangeResultMocked = Mockito.mock( VersionRangeResult.class );
        final RepositorySystem repoMocked = Mockito.mock( RepositorySystem.class );
        final Log logMocked = Mockito.mock( Log.class );
        final AetherManager aetherManager = new AetherManager( repoMocked, null, Collections.emptyList(), logMocked );

        when( versionRangeResultMocked.getHighestVersion() ).thenReturn( highestVersion );
        when( repoMocked.resolveVersionRange( any(), any() ) ).thenReturn( versionRangeResultMocked );
        final String versionResolved = aetherManager.resolveArtifact( artifactToResolve ).getVersion();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, versionExpected, versionResolved );
    }

    @Test
    public void resolveArtifactTest()
    throws DownloadException, RepositoryException {
        final Artifact artifactToResolve = new DefaultArtifact( "wsdlit:wsdlit:3.0.0" );
        final String versionExpected = "3.0.0";
        final VersionResult versionResultMocked = Mockito.mock( VersionResult.class );
        final RepositorySystem repoMocked = Mockito.mock( RepositorySystem.class );
        final Log logMocked = Mockito.mock( Log.class );
        final AetherManager aetherManager = new AetherManager( repoMocked, null, Collections.emptyList(), logMocked );

        when( versionResultMocked.getVersion() ).thenReturn( versionExpected );
        when( repoMocked.resolveVersion( any(), any() ) ).thenReturn( versionResultMocked );
        final String versionResolved = aetherManager.resolveArtifact( artifactToResolve ).getVersion();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, versionExpected, versionResolved );
    }
}
