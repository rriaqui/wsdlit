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

import com.predic8.schema.Element;
import com.predic8.schema.Group;
import com.predic8.wsdl.WSDLElement;
import gal.xunta.amtega.wsdlit.core.model.AbstractNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;

/**
 * Class of conversion utilities for membranes.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ConverterUtil {
    private ConverterUtil() {
    }

    /**
     * Updates the specified {@code AbstractNode} with the name, namespace, documentation and prefix
     * from the specified membransoa {@code WSDLElement}.
     *
     * @param item the specified {@code AbstractNode} to be updated.
     * @param element the specified membrane {@code WSDLElement}.
     */
    public static void update( final AbstractNode item, final WSDLElement element ) {
        item.setName( element.getName() );
        item.setNamespaceUri( element.getNamespaceUri() );
        item.setDocumentation( DocumentationUtil.normalize( element.getDocumentation() ) );
        item.setPrefix( element.getPrefix() );
    }

    /**
     * Updates the specified {@code ElementNodeBuilder} with the name, namespace, documentation, prefix and occurrences
     * from the specified membranesoa {@code Group}.
     *
     * @param builder the specified {@code ElementNodeBuilder} to be updated.
     * @param group the specified membrane {@code Group}.
     */
    public static void update(final ElementNodeBuilder builder, final Group group ) {
        builder.setName( group.getName() );
        builder.setNamespaceUri( group.getNamespaceUri() );
        builder.setDocumentation( DocumentationUtil.normalize( group.getAnnotation() ) );
        builder.setPrefix( group.getPrefix() );
        builder.setMinOccurs( ( String ) group.getMinOccurs() );
        builder.setMaxOccurs( ( String ) group.getMaxOccurs() );
    }

    /**
     * Updates the specified {@code ElementNodeBuilder} with occurrences
     * from the specified membranesoa {@code Element}.
     *
     * @param builder the specified {@code ElementNodeBuilder} to be updated.
     * @param element the specified membrane {@code Element}.
     */
    public static void update(final ElementNodeBuilder builder, final Element element ) {
        builder.setMinOccurs( element.getMinOccurs() );
        builder.setMaxOccurs( element.getMaxOccurs() );
    }
}
