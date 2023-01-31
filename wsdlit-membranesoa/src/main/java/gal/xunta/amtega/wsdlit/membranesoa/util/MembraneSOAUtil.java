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
import com.predic8.schema.SchemaComponent;
import com.predic8.soamodel.KnownSchemas;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.WSDLParser;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;

/**
 * Miscellaneous utilities related to the MembraneSOA classes.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class MembraneSOAUtil {
    static {
        KnownSchemas.getDocs().put( "http://www.w3.org/2001/XMLSchema", "xml-schema-1.0.xsd" );
        KnownSchemas.getDocs().put( "http://www.w3.org/XML/1998/namespace", "xml-2001-03.xsd" );
        KnownSchemas.getDocs().put( "http://docs.oasis-open.org/wsbpel/2.0/plnktype", "ws-bpel_plnktype.xsd" );
    }

    private MembraneSOAUtil() {}

    /**
     * Returns a {@code Definitions} corresponding to the service contract found at the specified uri.
     *
     * @param uri the specified uri where the service contract is located.
     * @return a {@code Definitions} corresponding to the service contract found at the specified uri.
     */
    public static Definitions readWSDLFile( final String uri ) {
        final WSDLParser parser = new WSDLParser();
        return parser.parse( uri );
    }

    /**
     * Creates a {@code ElementNodeBuilder} from the specified {@code SchemaComponent}.
     *
     * @param schemaComponent the specified {@code SchemaComponent}.
     * @return a {@code ElementNodeBuilder} from the specified {@code SchemaComponent}.
     */
    public static ElementNodeBuilder createBuilder( final SchemaComponent schemaComponent ) {
        final Annotation annotation = schemaComponent.getAnnotation();
        final String name = schemaComponent.getName();
        final String targetNamespaceUri = schemaComponent.getNamespaceUri();
        final String prefix = schemaComponent.getPrefix();

        return new ElementNodeBuilder( name, targetNamespaceUri )
            .setPrefix( prefix )
            .setDocumentationIfEmpty( DocumentationUtil.normalize( annotation ) );
    }
}
