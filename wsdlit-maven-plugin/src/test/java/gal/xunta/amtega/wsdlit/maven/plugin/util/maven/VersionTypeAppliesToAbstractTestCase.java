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

/**
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class VersionTypeAppliesToAbstractTestCase {
    protected static final Artifact DEVELOPMENT_ARTIFACT = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2.0.0-SNAPSHOT" );
    protected static final Artifact ALFA_ARTIFACT = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2.0.0-a0" );
    protected static final Artifact BETA_ARTIFACT = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2.0.0-b0" );
    protected static final Artifact RELEASE_CANDIDATE_ARTIFACT = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2.0.0-rc0" );
    protected static final Artifact PRODUCTION_ARTIFACT = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2.0.0" );
    protected static final Artifact PRODUCTION_ARTIFACT_2 = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2.0" );
    protected static final Artifact PRODUCTION_ARTIFACT_3 = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2" );
    protected static final Artifact UNKNOWN_ARTIFACT = new DefaultArtifact( "gal.xunta.amtega.wsdlit:wsdlit-test:2.0.0-Final" );

    protected final Artifact artifact;
    protected final boolean appliesToExpected;

    private final VersionType versionType;

    public VersionTypeAppliesToAbstractTestCase(final Artifact artifact, final boolean appliesToExpected, VersionType versionType) {
        this.artifact = artifact;
        this.appliesToExpected = appliesToExpected;
        this.versionType = versionType;
    }

    @Test
    public void appliesToWhenArtifactTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, appliesToExpected, versionType.appliesTo( artifact ) );
    }

    @Test
    public void appliesToWhenVersionTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, appliesToExpected, versionType.appliesTo( artifact.getVersion() ) );
    }

    @Test
    public void appliesToWhenStringTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, appliesToExpected, versionType.appliesTo( artifact.getVersion() ) );
    }
}
