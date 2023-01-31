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
import org.eclipse.aether.artifact.DefaultArtifact;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ArtifactRelationsTestCase {
    public static final String CURRENT_COORDINATES = "gal.xunta.amtega.wsdlit.test:wsdlit-test:jar:2.0.0";
    public static final String PREVIOUS_VERSION_COORDINATES = "gal.xunta.amtega.wsdlit.test:wsdlit-test:jar:1.5.4";

    @Test
    public void containsTest() {
        final ArtifactRelations relations = new ArtifactRelations();
        final DefaultArtifact currentArtifact = new DefaultArtifact( CURRENT_COORDINATES );
        final DefaultArtifact previousVersionArtifact = new DefaultArtifact( PREVIOUS_VERSION_COORDINATES );
        relations.setRelation( currentArtifact, previousVersionArtifact );

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, relations.contains( CURRENT_COORDINATES ) );
    }

    @Test
    public void containsArtifactTest() {
        final ArtifactRelations relations = new ArtifactRelations();
        final DefaultArtifact currentArtifact = new DefaultArtifact( CURRENT_COORDINATES );
        final DefaultArtifact previousVersionArtifact = new DefaultArtifact( PREVIOUS_VERSION_COORDINATES );
        relations.setRelation( currentArtifact, previousVersionArtifact );

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, relations.containsArtifact( currentArtifact ) );
    }


    @Test
    public void getRelationAsArtifactTest() {
        final ArtifactRelations relations = new ArtifactRelations();
        final DefaultArtifact currentArtifact = new DefaultArtifact( CURRENT_COORDINATES );
        final DefaultArtifact previousVersionArtifact = new DefaultArtifact( PREVIOUS_VERSION_COORDINATES );
        relations.setRelation( currentArtifact, previousVersionArtifact );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREVIOUS_VERSION_COORDINATES, relations.getRelation( currentArtifact ) );
    }

    @Test
    public void getRelationAsStringTest() {
        final ArtifactRelations relations = new ArtifactRelations();
        final DefaultArtifact currentArtifact = new DefaultArtifact( CURRENT_COORDINATES );
        final DefaultArtifact previousVersionArtifact = new DefaultArtifact( PREVIOUS_VERSION_COORDINATES );
        relations.setRelation( currentArtifact, previousVersionArtifact );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, PREVIOUS_VERSION_COORDINATES, relations.getRelation( CURRENT_COORDINATES ) );
    }

    @Test
    public void setRelationTest() {
        final ArtifactRelations relations = new ArtifactRelations();
        final DefaultArtifact currentArtifact = new DefaultArtifact( CURRENT_COORDINATES );
        final DefaultArtifact previousVersionArtifact = new DefaultArtifact( PREVIOUS_VERSION_COORDINATES );
        relations.setRelation( currentArtifact, previousVersionArtifact );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, 1, relations.size() );
    }

    @Test
    public void isEmptyReturnsFalseTest() {
        final ArtifactRelations relations = new ArtifactRelations();
        final DefaultArtifact currentArtifact = new DefaultArtifact( CURRENT_COORDINATES );
        final DefaultArtifact previousVersionArtifact = new DefaultArtifact( PREVIOUS_VERSION_COORDINATES );
        relations.setRelation( currentArtifact, previousVersionArtifact );

        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, relations.isEmpty() );
    }

    @Test
    public void isEmptyReturnsTrueTest() {
        final ArtifactRelations relations = new ArtifactRelations();

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, relations.isEmpty() );
    }
}
