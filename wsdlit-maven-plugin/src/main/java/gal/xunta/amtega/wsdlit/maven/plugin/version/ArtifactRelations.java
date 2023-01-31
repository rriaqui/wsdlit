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

import org.eclipse.aether.artifact.Artifact;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Matches the coordinates of an artifact with the coordinates of its previous version,
 * and allows you to save and retrieve relationships in a file.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ArtifactRelations {
    /**
     * Stores the relations established between two artifact versions.
     */
    private final Properties properties = new Properties();

    /**
     * Returns {@code true} if the coordinates of the specified artifact contains a relation with another coordinates.
     *
     * @param artifact the specified artifact.
     * @return {@code true} if the version of the specified artifact contains a relation with another artifact.
     */
    public boolean containsArtifact( final Artifact artifact ) {
        return this.properties.containsKey( artifact.toString() );
    }

    /**
     * Returns {@code true} if the specified coordinates contains a relation with another coordinates.
     *
     * @param coordinates the specified coordinates to be tested.
     * @return {@code true} if the specified coordinates contains a relation with another coordinates.
     */
    public boolean contains( final String coordinates ) {
        return this.properties.containsKey( coordinates );
    }

    /**
     * Returns a {@code Set} of known artifact coordinates used as a key.
     *
     * @return a {@code Set} of known artifact coordinates used as a key.
     */
    public Set<String> keySet() {
        return this.properties.stringPropertyNames();
    }

    /**
     * Returns a {@code Set} of the known relations.
     *
     * @return a {@code Set} of the known relations.
     */
    public Set<Map.Entry<Object, Object>> entrySet() {
        return this.properties.entrySet();
    }

    /**
     * Stores a relation between the coordinates of the two specified artifacts.
     *
     * @param currentArtifact the primary specified artifact, whose coordinates will be used as the key.
     * @param previousVersionArtifact the child artifact, whose coordinates will be used as the value.
     */
    public void setRelation( final Artifact currentArtifact, final Artifact previousVersionArtifact ) {
        this.properties.setProperty( currentArtifact.toString(), previousVersionArtifact.toString() );
    }

    /**
     * Returns the coordinates of the artifact that is related to the specified specified,
     * or {@code null} if unrelated.
     *
     * @param artifact the specified artifact to be tested.
     * @return the coordinates of the related artifact or {@code null} if no relation exists.
     */
    public String getRelation( final Artifact artifact ) {
        return this.getRelation( artifact.toString() );
    }


    /**
     * Returns the coordinates of the artifact that is related to the specified,
     * or {@code null} if unrelated.
     *
     * @param coordinates the specified coordinates to be tested.
     * @return the coordinates of the related artifact or {@code null} if no relation exists.
     */
    public String getRelation( final String coordinates ) {
        return ( String ) this.properties.get( coordinates );
    }

    /**
     * Returns the number of known relations.
     *
     * @return the number of known relations.
     */
    public int size() {
        return this.properties.size();
    }

    /**
     * Stores known relations in a specified file.
     *
     * @param file the specified file.
     * @throws IOException if any error occurs.
     */
    public void store( final File file )
    throws IOException {
        try( final Writer writer = new FileWriter( file ) ) {
            this.properties.store( writer, "CurrentArtifact = PreviousVersionArtifact" );
        }
    }

    /**
     * Load known relations stored in a specified file.
     *
     * @param file the specified file.
     * @throws IOException if any error occurs.
     */
    public void load( final File file )
    throws IOException {
        try ( final Reader reader = new FileReader( file ) ) {
            this.properties.load( reader );
        }
    }

    /**
     * Returns {@code true} if it does not store relations between artifacts.
     *
     * @return {@code true} if it does not store relations between artifacts.
     */
    public boolean isEmpty() {
        return this.properties.isEmpty();
    }
}
