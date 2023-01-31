package gal.xunta.amtega.wsdlit.maven.plugin.diff;

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
import gal.xunta.amtega.wsdlit.maven.plugin.test.TestContext;
import gal.xunta.amtega.wsdlit.maven.plugin.version.ArtifactRelations;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

/**
 * @author rriaqui
 * @since 2.0.0
 */
public class DiffVersionPersistenceTestCase {
    @Test
    public void storeTest()
    throws IOException {
        final TestContext testContext = new TestContext( DiffVersionPersistenceTestCase.class, "store" ).init();
        final File target = new File( testContext.getTestClassesPath().toFile(), "diffProperties.properties" );
        final DiffVersionPersistence persistence = new DiffVersionPersistence( DiffActionOnError.BUILD_BREAK);
        final ArtifactRelations relations = new ArtifactRelations();

        persistence.writePreviousVersionPropertiesFile( relations, target );

        Assert.assertTrue(
            String.format( AssertMessages.FILE_EXPECTED, target ),
            target.isFile()
        );
    }

    @Test( expected = IOException.class )
    public void storeWhenOnErrorThenBreakBuildTest()
        throws IOException {
        final TestContext testContext = new TestContext( DiffVersionPersistence.class, "storeWhenOnErrorThenBreakBuild" ).init();
        final DiffVersionPersistence persistence = new DiffVersionPersistence( DiffActionOnError.BUILD_BREAK);
        final ArtifactRelations relations = new ArtifactRelations();

        persistence.writePreviousVersionPropertiesFile( relations, testContext.getNonExistingDir().toFile() );
    }

    @Test
    public void storeWhenOnErrorThenContinueTest()
    throws IOException {
        final TestContext testContext = new TestContext( DiffVersionPersistence.class, "storeWhenOnErrorThenBreakBuild" ).init();
        final File target = testContext.getNonExistingDir().toFile();
        final DiffVersionPersistence persistence = new DiffVersionPersistence( DiffActionOnError.CONTINUE );
        final DiffVersionPersistence spied = Mockito.spy( persistence );
        final ArtifactRelations relations = new ArtifactRelations();

        final boolean wasWritten = spied.writePreviousVersionPropertiesFile( relations, target );
        Assert.assertFalse( AssertMessages.FALSE_EXPECTED, wasWritten );
    }
}
