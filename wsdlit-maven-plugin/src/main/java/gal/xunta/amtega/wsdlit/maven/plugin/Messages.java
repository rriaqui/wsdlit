package gal.xunta.amtega.wsdlit.maven.plugin;

/*-
 * #%L
 * AMTEGA WsdlIT Maven Plugin
 * %%
 * Copyright (C) 2021 - 2023 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
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

import gal.xunta.amtega.wsdlit.core.i18n.I18n;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public final class Messages {
    public static final I18n I18N = new I18n( Messages.class );

    public static final String ARTIFACT_DOWNLOAD_REQUIRED_ERROR_KEY = "artifact-download-required-error";
    public static final String ARTIFACT_DOWNLOAD_KEY = "artifact-download";
    public static final String ARTIFACT_DOWNLOAD_END_KEY = "artifact-download-end";
    public static final String ARTIFACT_NOT_FOUND_ERROR_KEY = "artifact-not-found-error";
    public static final String ARTIFACT_REQUIRED_ERROR_KEY = "artifact-required-error";
    public static final String ARTIFACT_VERSION_ERROR_KEY = "artifact-version-error";
    public static final String ARTIFACT_RESOLVED_KEY = "artifact-resolved";
    public static final String CANDIDATES_LIST_IS_EMPTY_KEY = "candidates-list-is-empty";
    public static final String COPYING_FILES_FROM_TO_KEY = "copying-files-from-to";
    public static final String COPYING_FILES_FROM_TO_ENDED_KEY = "copying-files-from-to-ended";
    public static final String COPYING_FILES_FROM_TO_ERROR_KEY = "copying-files-from-to-error";
    public static final String COPYING_FILES_TO_ERROR_KEY = "copying-files-to-error";
    public static final String DEFLATING_FILE_END = "deflating-file-end";
    public static final String DEFLATING_ERROR_KEY = "deflating-error";
    public static final String DEFLATING_FILE = "deflating-file";
    public static final String DEPLOYMENT_REPOSITORY_KEY = "deployment-repository";
    public static final String DESCRIPTOR_REQUIRED_ERROR_KEY = "descriptor-required-error";
    public static final String FILE_DOES_NOT_EXISTS_KEY = "file-does-not-exist-error";
    public static final String FILE_WRITING_ERROR = "file-writing-error";
    public static final String GENERATING_ADOC_FILES_KEY = "generating-adoc-files";
    public static final String GENERATING_ADOC_FILES_ENDED_KEY = "generating-adoc-files-ended";
    public static final String GENERATING_ADOC_FILES_ERROR_KEY = "generating-adoc-files-error";
    public static final String INVALID_ARTIFACT_COORDINATES_ERROR_KEY = "invalid-artifact-coordinates-error";
    public static final String MKDIR_ERROR_KEY = "mkdir-error";
    public static final String PATCH_SKIPPED_KEY = "patch-skipped";
    public static final String PREVIOUS_VERSION_CANDIDATES_LIST_KEY = "previous-version-candidates-list";
    public static final String PREVIOUS_VERSION_CANDIDATES_MISSING_KEY = "previous-version-candidates-missing";
    public static final String NULL_ARTIFACTS_ARE_NOT_ALLOWED_KEY = "null-artifacts-are-not-allowed";
    public static final String ZIP_SLIP_ERROR = "zip-slip-error";

    private Messages() {}
}
