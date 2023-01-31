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
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class ArtifactUtilNormalizedArtifactNameWithSuffixTestCase {
    private final Artifact artifact;
    private final String expected;
    private final String suffix;
    private final String outputDirectory;

    public ArtifactUtilNormalizedArtifactNameWithSuffixTestCase(
        final String expected, final Artifact artifact, final String suffix, final String outputDirectory
    ) {
        this.artifact = artifact;
        this.expected = expected;
        this.outputDirectory = outputDirectory;
        this.suffix = suffix;
    }

    @Test
    public void normalizedArtifactNameTest() {
        final String normalizedArtifactName = ArtifactUtil.normalizedArtifactName( this.artifact, this.suffix );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.expected, normalizedArtifactName );
    }

    @Test
    public void getOutputDirectoryTest() {
        final File baseDirectory = new File( "target" );
        final File outputDirectory = ArtifactUtil.getOutputDirectory( baseDirectory, artifact, "" );
        final File expected = new File( baseDirectory, this.outputDirectory );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, outputDirectory );
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { "artifact-name",  new DefaultArtifact( "gal.xunta.amtega.wsdlit:artifact-name:1.0.0" ), null, "artifact-name" },
            { "artifact-name",  new DefaultArtifact( "gal.xunta.amtega.wsdlit:artifact-name:1.0.0" ), "", "artifact-name" },
            { "artifact-name.diff",  new DefaultArtifact( "gal.xunta.amtega.wsdlit:artifact-name:1.0.0" ), ".diff", "artifact-name" },
            { "artifact-name",  new DefaultArtifact( "gal.xunta.amtega.wsdlit:artifact-name:1.0.0" ), null, "artifact-name" }
        });
    }
}
