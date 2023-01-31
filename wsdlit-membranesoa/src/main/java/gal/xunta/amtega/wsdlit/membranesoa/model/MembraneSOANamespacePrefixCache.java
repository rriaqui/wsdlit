package gal.xunta.amtega.wsdlit.membranesoa.model;

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

import com.predic8.schema.Include;
import com.predic8.schema.Schema;
import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.core.model.AbstractNamespacePrefixCache;
import gal.xunta.amtega.wsdlit.core.model.NamespaceNode;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;

/**
 * MembraneSOA implementation of the {@code AbstractNamespacePrefixCache} class.
 */
public final class MembraneSOANamespacePrefixCache
extends AbstractNamespacePrefixCache {
    private final Definitions definitions;

    /**
     * Constructs an empty {@code MembraneSOANamespacePrefixCache}.
     */
    public MembraneSOANamespacePrefixCache() {
        this( null );
    }

    /**
     * Constructs a {@code MembraneSOANamespacePrefixCache} from the specified {@code Definitions}.
     *
     * @param definitions the specified {@code Definitions}.
     */
    public MembraneSOANamespacePrefixCache( final Definitions definitions ) {
        super();
        this.definitions = definitions;
        if ( definitions != null ) {
            this.findPrefix( definitions.getPrefix(), definitions.getTargetNamespace() );
        }
    }

    @Override
    public void updateFormDefaultValues( final String namespaceUri, final NamespaceNode namespaceNode ) {
        if ( definitions != null ) {
            final Schema schema = findSchema( namespaceUri );
            if( schema != null ) {
                namespaceNode.setAttributeFormDefault( schema.getAttributeFormDefault() );
                namespaceNode.setElementFormDefault( schema.getElementFormDefault() );
            }
        }
    }

    @Override
    public String resolvePrefix( final String namespaceUri ) {
        if ( this.definitions != null ) {
            final Schema schema = definitions.getSchema( definitions.getTargetNamespace() );
            Object prefix = schema.getPrefix( namespaceUri );
            if ( prefix != null ) {
                return ( String ) prefix;
            }
        }
        return StringUtil.EMPTY;
    }

    /**
     * Finds the schema for the specified namespace,
     * and if it doesn't find it, it looks for the namespace in the current schema.
     *
     * @param namespace the specified namespace of the schema to be found.
     * @return the schema for the specified namespace,
     */
    public Schema findSchema( final String namespace ) {
        final Schema schema = definitions.getSchemaLoadKnownSchemaIfNeeded( namespace );
        if( schema == null ) {
            return findSchemaInCurrentSchema( namespace );
        }
        return schema;
    }

    /**
     * Finds the schema for the specified namespace in the current schema of the {@code Definitions}.
     *
     * @param namespace the specified namespace of the schema to be found.
     * @return the schema for the specified namespace in the current schema of the {@code Definitions}.
     */
    public Schema findSchemaInCurrentSchema( final String namespace ) {
        final Schema currentSchema = definitions.getSchema( definitions.getTargetNamespace() );
        final Schema schema = currentSchema.getImportedSchema( namespace );
        if ( schema != null ) {
            return schema;
        }
        return findSchemaInIncludesOfCurrentSchema( namespace );
    }

    /**
     * Finds the schema for the specified namespace in the includes of the current schema of the {@code Definitions}.
     *
     * @param namespace the specified namespace of the schema to be found.
     * @return the schema for the specified namespace in the includes of the current schema of the {@code Definitions}.
     */
    public Schema findSchemaInIncludesOfCurrentSchema( final String namespace ) {
        final Schema currentSchema = definitions.getSchema( definitions.getTargetNamespace() );

        for ( final Include include : currentSchema.getIncludes() ) {
            if ( namespace.equals( include.getNamespaceUri() ) ) {
                return include.getSchema();
            }
        }
        return null;
    }
}
