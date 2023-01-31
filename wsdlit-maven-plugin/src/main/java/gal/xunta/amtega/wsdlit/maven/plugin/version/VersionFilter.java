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

import gal.xunta.amtega.wsdlit.maven.plugin.util.maven.VersionType;
import org.eclipse.aether.version.Version;

import java.util.function.Predicate;

/**
 * Class that allows artifacts to be filtered by the specified version type
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class VersionFilter
implements Predicate<Version> {
    /*
     * The version type this filter applies tp.
     */
    private final VersionType versionType;

    /**
     * Constructs a version filter for the specified version type.
     *
     * @param versionType the specified version type.
     */
    public VersionFilter( final VersionType versionType) {
        this.versionType = versionType;
    }

    @Override
    public boolean test( final Version version ) {
        return this.versionType.appliesTo( version.toString() );
    }
}
