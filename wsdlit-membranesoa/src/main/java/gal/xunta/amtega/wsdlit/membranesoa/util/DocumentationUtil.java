package gal.xunta.amtega.wsdlit.membranesoa.util;

/*-
 * #%L
 * wsdlit-membranesoa
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

import com.predic8.schema.Annotation;
import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;

import java.util.List;

/**
 * Utilities related to the {@code annotation} and {@code documentation} tags.
 *
 * <p>
 *     The normalized documentation of an Object is the value of the {@code Documentation}
 *     or the {@code Annotation.getDocumentation()} object of the membranesoa elements.
 *     When it has not been defined documentation for the element,
 *     the normalized documentation is the empty string.
 *
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class DocumentationUtil {

    private DocumentationUtil() {}

    /**
     * Returns the normalized documentation of the specified {@code Definitions}.
     *
     * @param definitions the specified {@code Definitions} which contains the documentation to normalize.
     * @return the normalized documentation of the specified {@code Definitions}.
     */
    public static String normalize( final Definitions definitions ) {
        if ( definitions == null ) {
            return StringUtil.EMPTY;
        }
        return normalize( definitions.getDocumentation() );
    }

    /**
     * Returns the normalized documentation of the specified {@code Annotation}.
     *
     * @param annotation the specified {@code Annotation} which contains the documentation to normalize.
     * @return the normalized documentation of the specified {@code Annotation}.
     */
    public static String normalize( final Annotation annotation ) {
        if ( annotation != null ) {
            return normalize( annotation.getDocumentations() );
        }
        return StringUtil.EMPTY;
    }

    /**
     * Returns the normalized documentation of the first element in the specified list.
     *
     * @param documentations the specified list.
     * @return the normalized documentation of the first element in the specified list.
     */
    public static String normalize( final List<com.predic8.schema.Documentation> documentations ) {
        if ( documentations == null || documentations.isEmpty() ) {
            return StringUtil.EMPTY;
        }
        return StringUtil.normalize( documentations.get( 0 ).getContent() );
    }

    /**
     * Returns the normalized documentation of specified {@code Documentation}.
     *
     * @param documentation the specified documentation to be normalized.
     * @return the normalized documentation of specified {@code Documentation}.
     */
    public static String normalize( final com.predic8.wsdl.Documentation documentation ) {
        if ( documentation != null ) {
            return StringUtil.normalize( documentation.getContent() );
        }
        return StringUtil.EMPTY;
    }
}
