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

import org.apache.maven.plugin.MojoExecutionException;
import org.eclipse.aether.artifact.Artifact;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

/**
 * @author rriaqui
 * @since 2.0.0
 */
public class DescriptorDownloadMojoTestCase {
    @Test
    public void downloadDescriptorsWhenListIsEmptyTest()
    throws DownloadException {
        final DescriptorDownloadMojo mojo = new DescriptorDownloadMojo();

        mojo.descriptors = Collections.emptyList();

        final List< Artifact > descriptors = mojo.downloadDescriptors();
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, descriptors.isEmpty() );
    }

    @Test( expected = DownloadException.class )
    public void downloadDescriptorsThrowsDownloadExceptionWhenListIsEmptyTest()
    throws DownloadException {
        final DescriptorDownloadMojo mojo = new DescriptorDownloadMojo();
        final DescriptorDownloadMojo spied;

        mojo.descriptors = Collections.singletonList( "groupId:artifactId:1.0.0" );
        spied = Mockito.spy( mojo );

        Mockito
            .doReturn( Collections.emptyList() )
            .when( spied ).artifactsDownload( mojo.descriptors );

        spied.downloadDescriptors();
    }

    @Test
    public void executeWhenListIsEmptyTest()
    throws MojoExecutionException {
        final DescriptorDownloadMojo mojo = new DescriptorDownloadMojo();

        mojo.descriptors = Collections.emptyList();
        mojo.execute();
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, 0, mojo.getDownloadedDescriptors() );
    }

    @Test( expected = MojoExecutionException.class )
    public void executeThrowsMojoExecutionExceptionWhenIncorrectCoordinatesTest()
    throws MojoExecutionException {
        final DescriptorDownloadMojo mojo = new DescriptorDownloadMojo();

        mojo.descriptors = Collections.singletonList(
            "groupId"
        );
        mojo.execute();
    }

    @Test( expected = MojoExecutionException.class )
    public void executeThrowsMojoExecutionExceptionWhenNoMatchedArtifactsTest()
    throws MojoExecutionException, DownloadException {
        final DescriptorDownloadMojo mojo = new DescriptorDownloadMojo();
        final DescriptorDownloadMojo spied = Mockito.spy( mojo );

        Mockito
            .doThrow( new DownloadException( "Artifacts not found" ) )
            .when( spied ).downloadDescriptors();
        spied.execute();
    }
}
