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
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class DefaultPreviousVersionLocatorTestCase {
    private final DefaultPreviousVersionLocator locator = new DefaultPreviousVersionLocator();
    private final Artifact artifact = new DefaultArtifact( "gal.xunta.amtega.wsdlit.maven.plugin:wsdlit-maven-plugin:2.0.0" );
    private final List<Artifact> candidates = locator.listOfPossibleArtifacts( artifact );

    @Test
    public void listOfPossibleArtifactsSizeTest() {
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, 1, candidates.size() );
    }

    @Test
    public void listOfPossibleArtifactsVersionTest() {
        final Artifact candidate = candidates.get( 0 );
        final String versionExpected = "[,2.0.0)";
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, versionExpected, candidate.getVersion() );
    }

    @Test
    public void appliesToWhenArtifactReturnsTrueTest() {
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, locator.appliesTo( artifact ) );
    }

    @Test
    public void appliesToWhenNullReturnsFalseTest() {
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, locator.appliesTo( null ) );
    }
}
