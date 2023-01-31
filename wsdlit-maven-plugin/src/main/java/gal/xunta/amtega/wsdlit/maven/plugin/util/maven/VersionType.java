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

import org.eclipse.aether.artifact.Artifact;

import java.util.regex.Pattern;

/**
 * Enumeration of Maven artifact version types.
 *
 * @author rriaqui
 * @since 1.0
 */
public enum VersionType {
    DEVELOPMENT( Pattern.compile( "^(\\d+(?:.\\d+){0,3})-SNAPSHOT$" ) ),
    PRODUCTION( Pattern.compile( "^(\\d+(?:.\\d+){0,3})$" ) ),
    STAGE( Pattern.compile( "^(\\d+(?:.\\d+){0,3})-(a\\d+|b\\d+|rc\\d+)$" ) ),
    NOT_DEVELOPMENT( Pattern.compile( "^(.*)(?<!-SNAPSHOT)$" ) ),
    UNKNOWN( Pattern.compile( "^.*$" ) );

    private final Pattern pattern;

    /**
     * Constructs an enumeration version type from an specified {@code Pattern}
     * that allows you to check if a version fits it.
     *
     * @param pattern the specified {@code Pattern} which allows you to check if a version conforms to the version type.
     */
    VersionType(final Pattern pattern ) {
        this.pattern = pattern;
    }

    /**
     * Returns {@code true} if the version of the specified artifact matches the version type of the enumeration.
     *
     * @param artifact the specified artifact to be tested.
     * @return {@code true} if the version of the specified artifact matches the version type of the enumeration.
     */
    public boolean appliesTo( final Artifact artifact ) {
        return this.appliesTo( artifact.getVersion() );
    }

    /**
     * Returns {@code true} if specified version matches the version type of the enumeration.
     *
     * @param version the specified version to be tested.
     * @return {@code true} if the specified version matches the version type of the enumeration.
     */
    public boolean appliesTo( final String version ) {
        return this.pattern.matcher( version ).matches();
    }
}
