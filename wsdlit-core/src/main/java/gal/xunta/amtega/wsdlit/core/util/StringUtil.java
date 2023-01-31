package gal.xunta.amtega.wsdlit.core.util;

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

import java.util.regex.Pattern;

/**
 * Utilities related to {@link String} and {@link CharSequence}
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class StringUtil {

    /**
     * The empty string.
     */
    public static final String EMPTY = "";

    /**
     * Regular expression pattern that allows you to clean white space at the end of a text string.
     */
    private static final Pattern CLEAN_WHITESPACE_AT_END = Pattern.compile( "(\\s)+\\z" );

    /**
     * Regular expression pattern that allows you to clean white space at the beginning of a text string.
     */
    private static final Pattern CLEAN_WHITESPACE_AT_START = Pattern.compile( "\\A(\\s)+" );

    private StringUtil() {}

    /**
     * Checks if a value is white and returns the default value,
     * or return original if non-white.
     *
     * @param str the value to check.
     * @param defaultStr the default value to return when the value to check is white.
     * @return the default value if the value to check is white, or the value to check otherwise.
     * @param <T> the type of value to return.
     */

    public static <T extends CharSequence> T defaultIfBlank( final T str, final T defaultStr ) {
        return isBlank( str ) ? defaultStr : str;
    }

    /**
     * Removes white space at the end of a text.
     *
     * @param input the text.
     * @return the text with trailing whitespace removed.
     */
    public static String removeWhitespaceAtEnd( final String input) {
        if ( input == null ) {
            return null;
        }
        return CLEAN_WHITESPACE_AT_END.matcher( input ).replaceAll( EMPTY );
    }

    /**
     * Removes white space at the beginning of a text.
     *
     * @param input the text.
     * @return the text with leading whitespace removed.
     */
    public static String removeWhitespaceAtStart( final String input) {
        if ( input == null ) {
            return null;
        }
        return CLEAN_WHITESPACE_AT_START.matcher( input ).replaceAll( EMPTY );
    }

    /**
     * Repeat a text as many times as indicated.
     *
     * <p>
     *      <strong>Caution</strong>,
     *      can have negative consequences on memory impact if the text is very long
     *      and/or the number of repetitions is large.
     * </p>
     *
     * @param str the text to repeat.
     * @param repeat the number of times to repeat.
     * @return the text with the given repetitions.
     * If the text is {@code null} then {@code null} is returned.
     * If the number of repetitions is less than {@code 1} the empty string is returned.
     */
    public static String multiply( final String str, final int repeat ) {
        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        final int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        final StringBuilder builder = new StringBuilder();
        for( int i=0; i < repeat; i++ ) {
            builder.append( str );
        }
        return builder.toString();
    }

    /**
     * Checks if a {@code CharSequence} is white.
     *
     * <p>A {@code CharSequence} is white if any of the following conditions are true:</p>
     *
     * <ul>
     *      <li>Its value is {@code null}.</li>
     *      <li>Its value contains only blank characters.</li>
     * </ul>
     *
     * @param charSequence the sequence of characters to check.
     * @return {@code true} when it contains nothing but blank characters or its value is {@code null}.
     */
    public static boolean isBlank( final CharSequence charSequence ) {
        final int length = length( charSequence );
        if ( length > 0 ) {
            for ( int i = 0; i < length; i++ ) {
                if (! Character.isWhitespace( charSequence.charAt( i ) ) ) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if a string of characters is empty.
     *
     * <p>
     *      A string of characters is empty when any of the following conditions are met:
     * </p>
     * <ul>
     *      <li>Its value is {@code null}.</li>
     *      <li>Does not contain any characters.</li>
     * </ul>
     *
     * @param charSequence the sequence of characters to check.
     * @return {@code true} when the string is null or its size is 0.
     */
    public static boolean isEmpty( final CharSequence charSequence ) {
        return ( charSequence == null ) || ( charSequence.length() == 0 );
    }

    /**
     * Checks if a string of characters is not white.
     *
     * @param charSequence the sequence of characters to check.
     * @return {@code true} when not white.
     *
     * @see #isEmpty(CharSequence)
     */
    public static boolean isNotBlank( final CharSequence charSequence ) {
        return ! isBlank( charSequence );
    }

    /**
     * Checks if a sequence of characters is not white.
     *
     * @param charSequence the sequence of characters to check.
     * @return {@code true} when not empty.
     *
     * @see #isEmpty(CharSequence)
     */
    public static boolean isNotEmpty( final CharSequence charSequence ) {
        return ! isEmpty( charSequence );
    }

    /**
     * Returns the number of characters in a sequence of characters.
     *
     * @param charSequence the character sequence.
     * @return {@code 0} if the string is {@code null},
     *         or the number of characters in the sequence.
     */
    public static int length( final CharSequence charSequence ) {
        return ( charSequence == null ) ? 0 : charSequence.length();
    }

    /**
     * Normalize a text by removing spaces at the beginning and end of a text.
     *
     * @param text the text to normalize.
     * @return {@code ""} if text is {@code null},
     *         or the text with leading and trailing spaces removed.
     */
    public static String normalize( final String text ) {
        if ( text != null ) {
            return text.trim();
        }
        return EMPTY;
    }

    /**
     * Returns the empty string if the text is white,
     * or the same text otherwise.
     *
     * @param text the text to check.
     * @return {@code ""} if the text is white, or the original text otherwise.
     */
    public static String emptyIfBlank( final String text ) {
        if ( isBlank( text ) ) {
            return EMPTY;
        }
        return text;
    }

    /**
     * Safely split this string according to occurrences of the regular expression.
     * If the string is blank ({@link #isBlank(CharSequence)} returns an empty array.
     *
     * @param regex the regular expression that delimits the occurrences.
     * @param text the text to split.
     * @return the array of strings calculated by dividing the text into occurrences of the regular expression.
     * or an empty array if the text is blank.
     */
    public static String[] split( final String regex, final String text ) {
        if ( isNotBlank( text ) ) {
            return text.split( regex );
        }
        return new String[] {};
    }
}
