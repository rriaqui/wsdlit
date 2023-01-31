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

import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class VersionUtilArtifactComparatorNullArgumentsTestCase {
    private final Comparator< Artifact > comparator = VersionUtil.ARTIFACT_COMPARATOR;
    private static final Artifact SNAPSHOT_ARTIFACT = new DefaultArtifact( "groupId:artifactId:1.0.0-SNAPSHOT" );
    private static final Artifact ARTIFACT = new DefaultArtifact( "groupId:artifactId:1.0.0" );

    private final Artifact a1;
    private final Artifact a2;

    public VersionUtilArtifactComparatorNullArgumentsTestCase( final Artifact a1, final Artifact a2 ) {
        this.a1 = a1;
        this.a2 = a2;
    }

    @Test( expected = NullPointerException.class )
    public void compareToTest() {
        final int result = this.comparator.compare( this.a1, this.a2 );
        Integer.signum( result );
    }

    @Parameterized.Parameters
    public static Collection< Object[] > data() {
        return Arrays.asList(
            new Object[][] {
                { SNAPSHOT_ARTIFACT, null },
                { ARTIFACT, null },
                { null, SNAPSHOT_ARTIFACT },
                { null, ARTIFACT },
            }
        );
    }
}
