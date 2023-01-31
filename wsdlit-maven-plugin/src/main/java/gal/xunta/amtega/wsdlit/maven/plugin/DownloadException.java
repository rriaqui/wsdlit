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

import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.resolution.ArtifactResolutionException;

/**
 * Throw to indicate that the download process finished with an error.
 *
 * @author rriaqui
 * @since 1.0
 */
public final class DownloadException
extends Exception {
    /**
     * Constructs a {@code DownloadException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@code Throwable.getMessage()} method).
     */
    public DownloadException( final String message ) {
        super( message );
    }

    /**
     * Constructs a {@code DownloadException} with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@code Throwable.getMessage()} method).
     * @param cause the cause
     *              (which is saved for later retrieval by the {@code Throwable.getMessage()} method)
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DownloadException( final String message, final Throwable cause ) {
        super( message, cause );
    }

    /**
     * Constructs a {@code DownloadException} when artifact coordinates were not valid.
     *
     * @param artifact the artifact.
     * @param cause the cause
     *              (which is saved for later retrieval by the {@code Throwable.getMessage()} method)
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DownloadException( final Artifact artifact, final Throwable cause ) {
        super( Messages.I18N.format( Messages.INVALID_ARTIFACT_COORDINATES_ERROR_KEY, artifact ), cause );
    }

    /**
     * Constructs a {@code DownloadException} when it was not possible to resolve an artifact.
     *
     * @param artifact the artifact.
     * @param cause the cause
     *              (which is saved for later retrieval by the {@code Throwable.getMessage()} method)
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DownloadException( final Artifact artifact, final ArtifactResolutionException cause ) {
        super( Messages.I18N.format( Messages.ARTIFACT_NOT_FOUND_ERROR_KEY, artifact ), cause );
    }
}
