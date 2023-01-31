package gal.xunta.amtega.wsdlit.core.model;

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

import gal.xunta.amtega.wsdlit.core.diff.DefaultDiffItem;
import gal.xunta.amtega.wsdlit.core.diff.DiffItem;

import java.util.Collections;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ModelTestCaseAbstract {
    protected static final String ANOTHER_NAME = "anotherName";
    protected static final String ANOTHER_TYPE = "anotherType";
    protected static final String API_NAME = "api";
    protected static final String API_TNS = "https://emprego.xunta.es/wsdlit/api/service";
    protected static final String DIFF_ITEM_MESSAGE = "DiffItemMessage";
    protected static final String DOCUMENTATION_TEMPLATE = "Nome de clase: %s";
    protected static final String ELEMENT_NODE_NAME = "elementNodeName";
    protected static final String ELEMENT_NODE_DOCUMENTATION = "Element node documentation";
    protected static final String ELEMENT_NODE_MIN_OCCURS = "1";
    protected static final String ELEMENT_NODE_MAX_OCCURS = "4";
    protected static final String ELEMENT_NODE_TYPE = "elementNodeType";
    protected static final String PART_NAME = "part";
    protected static final String PORT_TYPE_NAME = "portType";
    protected static final String TNS_PREFIX = "tns";
    protected static final String TNS = "http://emprego.xunta.es/wsdlit";
    protected static final String TYPE_EXPECTED = "tns:TheType";
    protected static final boolean DIFF_ITEM_BREAK_CONTRACT = true;

    protected AbstractNode createAbstractNode( final AbstractNode abstractNode ) {
        final AbstractNode node = new AbstractNode( abstractNode.getName(), abstractNode.getNamespaceUri() ) {
        };
        node.setPrefix( abstractNode.getPrefix() );
        node.setDocumentation( abstractNode.getDocumentation() );
        return node;
    }

    protected ServiceNode createApiNode() {
        return createApiNode(
            Collections.singletonList( createPortTypeNode() )
        );
    }

    protected ServiceNode createApiNode( final List< PortTypeNode > operations ) {
        final ServiceNode node = new ServiceNode( API_NAME, API_TNS, operations );
        return node;
    }

    protected PortTypeNode createPortTypeNode() {
        return createPortTypeNode( Collections.emptyList() );
    }

    protected PortTypeNode createPortTypeNode( final List< OperationNode > operations ) {
        return createPortTypeNode( PORT_TYPE_NAME, operations );
    }

    protected PortTypeNode createPortTypeNode( final String name, final List<OperationNode> operations ) {
        final PortTypeNode node = new PortTypeNode( operations );
        node.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, PortTypeNode.class.getSimpleName() ) );
        node.setName( name );
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );
        return node;
    }

    protected OperationNode createOperationNode() {
        return createOperationNode( "add" );
    }

    protected OperationNode createOperationNode( final String name ) {
        final OperationNode node = new OperationNode();
        node.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, OperationNode.class.getSimpleName() ) );
        node.setName( name );
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );
        node.setType( OperationType.REQUEST_RESPONSE );
        return node;
    }

    protected RoleNode createRoleNode() {
        final RoleNode node = new RoleNode();
        node.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, RoleNode.class.getSimpleName()) );

        node.setName( "myself" );
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );

        node.setOperation( this.createOperationNode() );
        return node;
    }

    protected DiffItem createDiffItem() {
        return new DefaultDiffItem( DIFF_ITEM_MESSAGE, DIFF_ITEM_BREAK_CONTRACT, Collections.emptyList() );
    }

    protected ElementNode createElementNode() {
        return createElementNodeBuilder().build();
    }

    protected ElementNodeBuilder createElementNodeBuilder() {
        final ElementNodeBuilder builder = new ElementNodeBuilder( ELEMENT_NODE_NAME, TNS );
        builder.setDocumentation( ELEMENT_NODE_DOCUMENTATION );
        builder.setMinOccurs( ELEMENT_NODE_MIN_OCCURS );
        builder.setMaxOccurs( ELEMENT_NODE_MAX_OCCURS );
        builder.setPrefix( TNS_PREFIX );
        builder.setType( ELEMENT_NODE_TYPE );
        return builder;
    }

    protected ElementNode createElementNodeWithChild() {
        return createElementNodeBuilder().
                add( createElementNodeBuilder().setName( ANOTHER_NAME ).build() )
                .build();
    }

    protected MessageNode createFaultMessage() {
        final MessageNode node = new MessageNode();
        node.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, MessageNode.class.getSimpleName()) );

        node.setName( "UnavailableFault" );
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );
        return node;
    }

    protected MessageNode createInputMessage() {
        final MessageNode node = new MessageNode();
        node.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, MessageNode.class.getSimpleName()) );

        node.setName( "addRequest" );
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );
        node.add( createPartNode() );
        return node;
    }

    protected MessageNode createInputMessageWithChildren() {
        final MessageNode node = new MessageNode();
        node.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, MessageNode.class.getSimpleName()) );

        node.setName( "addRequest" );
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );
        node.add( createPartNodeWithChildren() );
        return node;
    }

    protected NamespaceNode createNamespaceNode() {
        final NamespaceNode namespaceNode = new NamespaceNode();
        namespaceNode.setPrefix( TNS_PREFIX );
        namespaceNode.setNamespaceUri( TNS );
        namespaceNode.setAttributeFormDefault( "qualified" );
        namespaceNode.setElementFormDefault( "qualified" );
        return namespaceNode;
    }

    protected MessageNode createOutputMessage() {
        final MessageNode messageNode = new MessageNode();
        messageNode.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, MessageNode.class.getSimpleName()) );
        messageNode.setName( "addResponse" );
        messageNode.setPrefix( TNS_PREFIX );
        messageNode.setNamespaceUri( TNS );
        return messageNode;
    }

    protected PartNode createPartNode() {
        final PartNode partNode = new PartNode();
        partNode.setName( PART_NAME );
        partNode.setPrefix( TNS_PREFIX );
        partNode.setNamespaceUri( TNS );
        partNode.setType( TYPE_EXPECTED );
        partNode.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, PartNode.class.getSimpleName() ) );
        return partNode;
    }

    protected PartNode createPartNodeWithChildren() {
        final PartNode partNode = createPartNode();
        partNode.setElement(
            new ElementNodeBuilder()
            .setName( "element" )
            .setType( "xsd:integer" )
            .add(
                new ElementNodeBuilder()
                    .setName( "children")
                    .setType( "xsd:integer" )
                    .build()
            )
            .build()
        );
        return partNode;
    }
}
