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

import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class DownloadExceptionTest {
    @Test
    public void constructorStringTest() {
        final String expected = "Hello world";
        final DownloadException cause = new DownloadException( expected );
        assertEquals( AssertMessages.EQUALS_EXPECTED, expected, cause.getMessage() );
    }

    @Test
    public void constructorStringExceptionTest() {
        final String expected = "Hello world";
        final IllegalArgumentException subCause = new IllegalArgumentException();
        final DownloadException cause = new DownloadException( expected, subCause );
        assertTrue( AssertMessages.TRUE_EXPECTED,
            expected.equals( cause.getMessage() )
            && subCause.equals( cause.getCause() )
        );
    }

    @Test
    public void constructorArtifactExceptionTest() {
        final String coordinates =  "groupId:artifactId:1.0.0";
        final Artifact artifact = new DefaultArtifact( coordinates );
        final String expected = "Invalid artifact coordinates: groupId:artifactId:jar:1.0.0.";
        final IllegalArgumentException subCause = new IllegalArgumentException();
        final DownloadException cause = new DownloadException( artifact, subCause );
        assertTrue( AssertMessages.TRUE_EXPECTED,
            expected.equals( cause.getMessage() )
                && subCause.equals( cause.getCause() )
        );
    }

    @Test
    public void constructorArtifactArtifactResolutionExceptionAssertCauseTest() {
        final String coordinates =  "groupId:artifactId:1.0.0";
        final Artifact artifact = new DefaultArtifact( coordinates );
        final ArtifactRequest artifactRequest = new ArtifactRequest().setArtifact( artifact );
        final ArtifactResult artifactResult = new ArtifactResult( artifactRequest );
        final ArtifactResolutionException cause = new ArtifactResolutionException( Collections.singletonList( artifactResult ) );
        final DownloadException object = new DownloadException( artifact, cause );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, cause, object.getCause() );
    }

    @Test
    public void constructorArtifactArtifactResolutionExceptionAssertMessageTest() {
        final String coordinates =  "groupId:artifactId:1.0.0";
        final Artifact artifact = new DefaultArtifact( coordinates );
        final ArtifactRequest artifactRequest = new ArtifactRequest().setArtifact( artifact );
        final ArtifactResult artifactResult = new ArtifactResult( artifactRequest );
        final String expected = "Artifact not found: groupId:artifactId:jar:1.0.0.";
        final ArtifactResolutionException cause = new ArtifactResolutionException( Collections.singletonList( artifactResult ) );
        final DownloadException object = new DownloadException( artifact, cause );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, object.getMessage() );
    }
}
