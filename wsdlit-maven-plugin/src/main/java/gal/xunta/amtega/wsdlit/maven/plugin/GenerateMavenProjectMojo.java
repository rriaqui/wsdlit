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

import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import gal.xunta.amtega.wsdlit.maven.plugin.util.StringEscapeUtil;
import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.ArtifactUtil;
import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.DeploymentRepositorySyntaxUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.model.interpolation.MavenBuildTimestamp;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.aether.repository.RemoteRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Properties;

/**
 * Create a Maven project to build the documentation.
 *
 * <p>
 *      The process of building the final products is articulated based on a Maven project created exclusively for
 *      convert the files generated in the previous stages to the final documentation formats.
 *      This way we isolate the construction process without the need to create complicated configurations
 *      and/or profiles in the original Maven project.
 *      The file {@code pom.xml} is created from a template conveniently configured to support the process
 *      of conversion.
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
@Mojo( name="generate-maven-project", defaultPhase = LifecyclePhase.COMPILE )
public class GenerateMavenProjectMojo
extends AbstractWsdlITMojo {
    @Parameter( defaultValue = "${plugin}", readonly = true ) // Maven 3 only
    protected PluginDescriptor plugin;

    /**
     * Directory in which the Maven project to be generated will be created.
     */
    @Parameter( name="mavenProjectOutputDirectory", property = "wsdlit.mavenProjectOutputDirectory", defaultValue = "${project.build.directory}/generated-sources/maven", required = true )
    protected File mavenProjectOutputDirectory;

    /**
     * The {@code groupId} value of the new Maven project.
     */
    @Parameter( name = "groupId", property = "wsdlit.api.groupId", defaultValue = "${project.groupId}", required = true )
    protected String groupId;

    /**
     * The {@code artifactId} value of the new Maven project.
     */
    @Parameter( name = "artifactId", property = "wsdlit.api.artifactId", defaultValue = "${project.artifactId}-it", required = true )
    protected String artifactId;

    /**
     * The {@code version} value of the new Maven project.
     */
    @Parameter( name="version", property = "wsdlit.api.version", defaultValue = "${project.version}", required = true )
    protected String version;

    /**
     * The coordinates of the theme to be applied to the generation / conversion process.
     */
    @Parameter( name="theme", property="wsdlit.theme.artifact", defaultValue = "${plugin.groupId}:wsdlit-default-theme:zip:${plugin.version}" )
    protected String theme;

    /**
     * Name of the organization that owns the documentation.
     */
    @Parameter( name="organization", property="wsdlit.api.organization", defaultValue = "${project.organization.name}" )
    protected String organization;

    /**
     * Main author of the documentation.
     */
    @Parameter( name = "author", property = "wsdlit.api.author", defaultValue = "${project.developers[0].name}" )
    protected String author;

    /**
     * The author's contact email address.
     */
    @Parameter( name = "email", property = "wsdlit.api.email", defaultValue = "${project.developers[0].email}" )
    protected String email;

    /**
     * Documentation copyright.
     */
    @Parameter( name="copyright", property = "wsdlit.api.copyright" )
    protected String copyright;

    /**
     * Repository in which the documentation will be deployed.<br>
     * Format: {@code id::url}
     *
     * <dl>
     *      <dt>id</dt>
     *      <dd>The {@code id} is used to select the correct credentials from the {@code settings.xml} file.</dd>
     *      <dt>url</dt>
     *      <dd>The location of the repository where the documentation will be deployed.</dd>
     * </dl>
     *
     * <b>Note: </b> The {@code id:layout:url} format is not supported because Maven 3 only supports the Maven 2 repository layout.
     *
     * @since 2.0
     */
    @Parameter( name = "deploymentRepository", property = "wsdlit.deploymentRepository" )
    protected String deploymentRepository;

    /**
     * The alternative repository that will be used to deploy the documentation
     * when the project has a {@code SNAPSHOT} version.<br>
     * Format: {@code id::url}
     *
     * <p>Takes precedence over {@link #deploymentRepository}.</p>
     *
     * @since 2.0
     */
    @Parameter( name = "altSnapshotDeploymentRepository", property = "wsdlit.altSnapshotDeploymentRepository" )
    protected String altSnapshotDeploymentRepository;

    /**
     * The alternative repository that will be used to deploy the documentation
     * when the project has a release version.<br>
     * Format: {@code id::url}
     *
     * <p>Takes precedence over {@link #deploymentRepository}.</p>
     *
     * @since 2.0
     */
    @Parameter( name = "altReleaseDeploymentRepository", property = "wsdlit.altReleaseDeploymentRepository" )
    protected String altReleaseDeploymentRepository;

    @Parameter( property = "wsdlit.api.revDate", defaultValue = "${build.timestamp}" )
    protected String revDate;

    @Parameter(defaultValue = "${session.request.startTime}", readonly = true)
    protected Date timestamp;

    protected RemoteRepository remoteRepository;

    @Override
    public void execute()
    throws MojoExecutionException {
        this.remoteRepository = DeploymentRepositorySyntaxUtil.getDeploymentRepository(
            this.version, this.altSnapshotDeploymentRepository, this.altReleaseDeploymentRepository, this.deploymentRepository
        );
        try {
            copySourceDirectory();
            copyTemplateProject();
            updatePom();

        } catch ( final IOException cause ) {
            final String message = Messages.I18N.format( Messages.COPYING_FILES_TO_ERROR_KEY, this.mavenProjectOutputDirectory.getAbsolutePath() );
            getLog().error( message, cause );
            throw new MojoExecutionException( message, cause );
        }
    }

    protected void copySourceDirectory()
    throws IOException {
        final File adocTarget = new File( this.mavenProjectOutputDirectory, MojoDefaultValues.SOURCE_DIRECTORY_TARGET );
        getLog().info( Messages.I18N.format( Messages.COPYING_FILES_FROM_TO_KEY, this.sourceDirectory, adocTarget ) );

        if ( ! adocTarget.exists() && ! adocTarget.mkdirs() ) {
            final String message = Messages.I18N.format( Messages.MKDIR_ERROR_KEY, adocTarget.getAbsolutePath() );
            getLog().error( message );
            throw new IOException( message );
        }
        FileUtils.copyDirectory( this.sourceDirectory, adocTarget );
        getLog().info( Messages.I18N.format( Messages.COPYING_FILES_FROM_TO_ENDED_KEY, this.sourceDirectory, adocTarget ) );
    }

    protected void copyTemplateProject()
    throws IOException {
        copyFileFromTemplate( MojoDefaultValues.MAVEN_PROJECT_POM_SOURCE, MojoDefaultValues.MAVEN_PROJECT_POM_TARGET );
        copyFileFromTemplate( MojoDefaultValues.MAVEN_PROJECT_ASSEMBLY_XML_SOURCE, MojoDefaultValues.MAVEN_PROJECT_ASSEMBLY_XML_TARGET );
    }

    /**
     * Copies the files and directories from the source to the target.
     * The target is calculated as {@code new File( this.mavenProjectOutputDirectory, target ); }.
     *
     * @param source the source of files to copy.
     * @param target the target of the copy.
     * @throws IOException in case of an error copying or not being able to create the parent target when it does not exist.
     */
    private void copyFileFromTemplate( final String source, final String target )
    throws IOException {
        final String templateSource = this.templatesDirectory + "/" + source;
        final InputStream is = this.getClass().getResourceAsStream( templateSource );
        final File targetFile = new File( this.mavenProjectOutputDirectory, target );
        final File parentFile = targetFile.getParentFile();

        if ( is == null ) {
            throw new IOException( Messages.I18N.format( Messages.FILE_DOES_NOT_EXISTS_KEY, templateSource ) );
        }

        if( !parentFile.exists() && ! parentFile.mkdirs() ) {
            throw new IOException( Messages.I18N.format( Messages.MKDIR_ERROR_KEY, parentFile.getAbsolutePath() ) );
        }
        Files.copy( is, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING );
    }

    /**
     * Updates the created {@code pom.xml}.
     *
     * @throws IOException in case of any error writing the new {@code pom.xml} file.
     */
    protected void updatePom()
    throws IOException {
        final File pomTarget = new File ( this.mavenProjectOutputDirectory, "pom.xml" );
        final String pomContent = IOUtils.toString( pomTarget.toURI(), StandardCharsets.UTF_8 );
        final String pomContentReplaced = replacePomContent( pomContent );

        FileUtils.writeStringToFile( pomTarget, pomContentReplaced, StandardCharsets.UTF_8 );
    }

    protected String replacePomContent( final String original ) {
        final String repositoryUrl = this.remoteRepository.getUrl();
        final String repositoryId = this.remoteRepository.getId();
        final String fixedTheme = ArtifactUtil.toMavenDependencyPluginCoordinates( this.theme );
        final String fixedRevDate = StringUtil.defaultIfBlank( this.revDate, this.fixTimeStampToZeroZoneId() );

        return
            original
                .replace( "{{it.groupId}}", this.groupId )
                .replace( "{{it.artifactId}}", this.artifactId )
                .replace( "{{it.version}}", this.version )
                .replace( "{{wsdlit-maven-plugin.version}}", this.plugin.getVersion() )
                .replace( "{{wsdlit-theme-artifact}}", StringEscapeUtil.escapeXml( fixedTheme ) )
                .replace( "{{wsdlit-api-author}}", StringEscapeUtil.escapeXml( this.author ) )
                .replace( "{{wsdlit-api-email}}", StringEscapeUtil.escapeXml( this.email ) )
                .replace( "{{wsdlit-api-organization}}", StringEscapeUtil.escapeXml( this.organization ) )
                .replace( "{{wsdlit-api-copyright}}", StringEscapeUtil.escapeXml( this.copyright ) )
                .replace( "{{wsdlit.site.repository.url}}", repositoryUrl )
                .replace( "{{wsdlit.site.server.id}}", repositoryId )
                .replace( "{{wsdlit-api-revDate}}", fixedRevDate );
    }

    private String fixTimeStampToZeroZoneId() {
        Properties interpolationProperties = new Properties();
        interpolationProperties.setProperty( "maven.build.timestamp.format", "yyyy-MM-dd'T'HH:mm:ss'Z'" );
        MavenBuildTimestamp timestamp = new MavenBuildTimestamp( new Date(), interpolationProperties);
        return timestamp.formattedTimestamp();
    }
}
