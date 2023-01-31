package gal.xunta.amtega.wsdlit.core.model.generator;

/*-
 * #%L
 * wsdlit-core
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

/**
 * Builder to create XML examples.
 * It is in an earlier stage.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class ExampleBuilder {
    private static final int INDENTATION_SPACES = 4;
    private final StringBuilder builder = new StringBuilder();
    private int level = 0;
    private String indentation = "";

    /**
     * Starts a new tag.
     *
     * <p>
     *     Every time a new tag is started,
     *     the indentation level increments.
     * </p>
     *
     * @param tagName the name of the tag.
     * @return a reference to this builder.
     */
    public ExampleBuilder start( final String tagName ) {
        this.builder
            .append( this.indentation )
            .append( '<' ).append( tagName ).append( ">\n" );
        this.incrementLevel();
        return this;
    }

    /**
     * Ends a tag.
     *
     * <p>
     *     Every time a tag is ended,
     *     the indentation level decreases.
     * </p>
     *
     * @param tagName the name of the tag.
     * @return a reference to this builder.
     */
    public ExampleBuilder close( final String tagName ) {
        this.decrementLevel();
        this.builder
            .append( this.indentation )
            .append( "</" ).append( tagName ).append( ">\n" );
        return this;
    }

    /**
     * Appends a text to the content of the last tag started.
     *
     * @param text the text to append to the last tag started.
     * @return a reference to this builder.
     */
    public ExampleBuilder append( final String text ) {
        this.builder
            .append( this.indentation )
            .append( text );
        return this;
    }

    /**
     * Increments the indentation level.
     */
    private void incrementLevel() {
        this.level++;
        this.indentation = StringUtil.multiply( " ", ExampleBuilder.INDENTATION_SPACES * this.level );
    }

    /**
     * Decrements de indentation level.
     */
    private void decrementLevel() {
        this.level--;
        this.indentation = StringUtil.multiply( " ", INDENTATION_SPACES * this.level );
    }

    /**
     * Returns the current indentation level.
     *
     * @return the current indentation level.
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Build the XML content.
     *
     * @return a text with the XML content.
     */
    public String build() {
        return this.builder.toString();
    }
}
