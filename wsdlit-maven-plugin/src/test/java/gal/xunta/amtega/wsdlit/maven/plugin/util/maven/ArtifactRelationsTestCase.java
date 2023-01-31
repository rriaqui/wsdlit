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
import gal.xunta.amtega.wsdlit.maven.plugin.version.ArtifactRelations;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ArtifactRelationsTestCase {
    private static final String GROUP_ID = "gal.xunta.amtega.wsdlit.test";
    private static final String ARTIFACT_ID = "the-artifact";
    private static final String TYPE = "zip";
    private static final String CURRENT_VERSION = "2.1.0";
    private static final String PREVIOUS_VERSION = "2.0.0";


    private static final Artifact CURRENT_ARTIFACT = new DefaultArtifact( GROUP_ID, ARTIFACT_ID, TYPE, CURRENT_VERSION );
    private static final Artifact PREVIOUS_ARTIFACT = new DefaultArtifact( GROUP_ID, ARTIFACT_ID, TYPE, PREVIOUS_VERSION );
    private static final String CURRENT_COORDINATES = CURRENT_ARTIFACT.toString();
    private static final String PREVIOUS_COORDINATES = PREVIOUS_ARTIFACT.toString();

    private static final File BASE_FILE = new File("target/gal/xunta/amtega/wdslit/maven/plugin/util/ArtifactRelationsTestCase" );

    @Test
    public void saveTest()
    throws IOException {
        final ArtifactRelations relations = createArtifactRelations();
        final File saveToFile = new File( BASE_FILE, "saveTest.properties" );

        saveToFile.getParentFile().mkdirs();
        relations.store( saveToFile );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, saveToFile.exists() );
    }

    @Test
    public void loadTest()
    throws IOException {
        final ArtifactRelations relations = createArtifactRelations();
        final File saveToFile = new File( BASE_FILE, "loadTest.properties" );

        saveToFile.getParentFile().mkdirs();

        relations.store( saveToFile );

        final ArtifactRelations relationsLoaded = new ArtifactRelations();
        relationsLoaded.load( saveToFile );
        final String relation = relationsLoaded.getRelation( CURRENT_ARTIFACT );

        final Artifact artifact = new DefaultArtifact( relation );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREVIOUS_ARTIFACT, artifact );
    }

    @Test
    public void keySetTest() {
        final ArtifactRelations relations = createArtifactRelations();
        final Set<String> expectedSet = Collections.singleton( GROUP_ID + ":" + ARTIFACT_ID + ":" + TYPE + ":" + CURRENT_VERSION );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedSet, relations.keySet() );
    }

    @Test
    public void entrySetTest() {
        final ArtifactRelations relations = createArtifactRelations();
        final AbstractMap.SimpleEntry<Object, Object> simpleEntry =
            new AbstractMap.SimpleEntry<>( CURRENT_COORDINATES, PREVIOUS_COORDINATES );
        final Set<Map.Entry<Object,Object>> expectedSet = Collections.singleton( simpleEntry );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedSet, relations.entrySet() );
    }

    private ArtifactRelations createArtifactRelations() {
        final ArtifactRelations relations = new ArtifactRelations();
        relations.setRelation( CURRENT_ARTIFACT, PREVIOUS_ARTIFACT );
        return relations;
    }
}
