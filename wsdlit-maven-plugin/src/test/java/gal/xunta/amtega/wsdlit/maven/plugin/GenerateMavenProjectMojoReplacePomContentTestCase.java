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

import gal.xunta.amtega.wsdlit.maven.plugin.test.TestContext;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.eclipse.aether.repository.RemoteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class GenerateMavenProjectMojoReplacePomContentTestCase {
    private static final String THEME_ARTIFACT = "gal.xunta.amtega.wsdlit:wsdlit-default-theme:1.0.0";
    private static final String IT_VERSION = "4.5.1-SNAPSHOT";
    private static final String PLUGIN_VERSION = "1.0.0";
    private static final String SERVER_ID = "server-id";
    public static final String SNAPSHOTS_REPOSITORY_URL = "http://localhost.localdomain/repository/api-snapshots";
    public static final String RELEASES_REPOSITORY_URL = "http://localhost.localdomain/repository/api-releases";

    public static final String REV_DATE = "2022-12-31T10:00:00Z";


    private final String template;
    private final String expected;

    public GenerateMavenProjectMojoReplacePomContentTestCase( final String template, final String expected ) {
        this.template = template;
        this.expected = expected;
    }

    @Test
    public void replacePomContentTest() {
        final TestContext testContext = new TestContext( GenerateMavenProjectMojoReplacePomContentTestCase.class, "replacePomContent" );
        final GenerateMavenProjectMojo mojo = createMojo( testContext, MojoDefaultValues.TEMPLATES_DIR_SOURCE, testContext.getOutputPath().toFile() );

        final String replaced = mojo.replacePomContent( this.template );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.expected, replaced );
    }

    @Parameterized.Parameters
    public static Collection< Object[] > data() {
        return Arrays.asList(
            new Object[][] {
                { "{{it.groupId}}", "groupId" },
                { "{{it.artifactId}}", "artifactId" },
                { "{{it.version}}", IT_VERSION },
                { "{{wsdlit-maven-plugin.version}}", PLUGIN_VERSION },
                { "{{wsdlit-theme-artifact}}", THEME_ARTIFACT},
                { "{{wsdlit-api-author}}", "author" },
                { "{{wsdlit-api-email}}", "email" },
                { "{{wsdlit-api-organization}}", "organization" },
                { "{{wsdlit-api-copyright}}", "copyright" },
                { "{{wsdlit.site.repository.url}}", SNAPSHOTS_REPOSITORY_URL },
                { "{{wsdlit.site.server.id}}", SERVER_ID },
                { "{{wsdlit-api-revDate}}", REV_DATE }
            }
        );
    }

    public static GenerateMavenProjectMojo createMojo( final TestContext testContext, final String templatesDirectory, final File mavenProjectOutputDirectory ) {
        final GenerateMavenProjectMojo mojo = new GenerateMavenProjectMojo();
        final PluginDescriptor pluginDescriptor = new PluginDescriptor();

        mojo.templatesDirectory = templatesDirectory;
        mojo.mavenProjectOutputDirectory = mavenProjectOutputDirectory;
        mojo.sourceDirectory = testContext.getSourceDirectory().toFile();
        mojo.sourcesDirectory = testContext.getSourcesDirectory().toFile();

        mojo.author = "author";
        mojo.organization = "organization";
        mojo.copyright = "copyright";
        mojo.groupId = "groupId";
        mojo.artifactId = "artifactId";
        mojo.email = "email";
        mojo.version = IT_VERSION;
        mojo.theme = THEME_ARTIFACT;

        mojo.altSnapshotDeploymentRepository = SERVER_ID + "::" + SNAPSHOTS_REPOSITORY_URL;
        mojo.altReleaseDeploymentRepository = SERVER_ID + "::" + RELEASES_REPOSITORY_URL;
        mojo.timestamp = new Date();
        mojo.revDate = REV_DATE;

        mojo.remoteRepository = new RemoteRepository.Builder(
            SERVER_ID,
            "default",
            SNAPSHOTS_REPOSITORY_URL
        ).build();

        mojo.plugin = pluginDescriptor;
        pluginDescriptor.setVersion( PLUGIN_VERSION );
        return mojo;
    }
}
