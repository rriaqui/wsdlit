package gal.xunta.amtega.wsdlit.membranesoa;

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

import com.predic8.schema.*;
import com.predic8.schema.restriction.BaseRestriction;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.WSDLParser;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.model.SequenceNode;
import groovy.namespace.QName;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public abstract class AbstractTestCase {
    public static final String ELEMENT_NAME = "element";
    public static final String ELEMENT_TWO_NAME = "elementTwo";

    public static final String STRING_TYPE = "string";
    public static final String NAME = "name";
    public static final String TNS = "target/name/space/";
    public static final String PREFIX = "prefix";

    public static final String DOCUMENTATION_CONTENT = "Hello world";
    public static final QName QNAME = new QName( TNS, NAME, PREFIX );

    public static final String XSD_TNS = "http://www.w3.org/2001/XMLSchema";

    public static final String XSD_PREFIX = "xsd";

    public static final String XSD_STRING_TYPE = XSD_PREFIX + ":" + STRING_TYPE;

    public static final QName XSD_STRING_QNAME = new QName( XSD_TNS, STRING_TYPE, XSD_PREFIX );


    protected static final String TNS_CONVERTER = "http://localhost.localdomain/wsdlit/test/converter";
    protected static final String TNS_PREFIX = "tns";

    protected final WSDLParser wsdlParser = new WSDLParser();
    protected final Definitions definitionsConverter = wsdlParser.parse( "src/test/resources/converter/converter.wsdl" );
    protected final Schema schemaConverter = definitionsConverter.getSchema( TNS_CONVERTER );



    public static ComplexType createComplexType() {
        final Annotation annotation = createAnnotation( DOCUMENTATION_CONTENT );
        final ComplexType type = Mockito.mock( ComplexType.class );
        Mockito.when( type.getName() ).thenReturn( NAME );
        Mockito.when( type.getNamespaceUri() ).thenReturn( TNS );
        Mockito.when( type.getQname() ).thenReturn( QNAME );
        Mockito.when( type.getAnnotation() ).thenReturn( annotation );
        Mockito.when( type.getPrefix() ).thenReturn( PREFIX );
        return type;
    }

    public static QName createQName() {
        return new QName( TNS, NAME, PREFIX );
    }


    public static TypeDefinition createTypeDefinition() {
        final QName qname = createQName();
        final TypeDefinition typeDefinition = Mockito.mock( TypeDefinition.class );
        Mockito.when( typeDefinition.getQname() ).thenReturn( qname );
        return typeDefinition;
    }

    public static Schema createSchema() {
        final TypeDefinition typeDefinition = createTypeDefinition();
        final Schema schema = Mockito.mock( Schema.class );
        Mockito.when( schema.getType( Mockito.any( QName.class ) ) ).thenReturn( typeDefinition );
        return schema;
    }

    public static ModelGroup createSequence() {
        final Annotation annotation = createAnnotation();
        final Sequence sequence = Mockito.mock( Sequence.class );
        final List< SchemaComponent > particles = Collections.singletonList( createElement() );

        Mockito.when( sequence.getAnnotation() ).thenReturn( annotation );
        Mockito.when( sequence.getParticles() ).thenReturn( particles );
        Mockito.when( sequence.getNamespaceUri() ).thenReturn( Sequence.getNAMESPACE() );
        Mockito.when( sequence.getPrefix() ).thenReturn( XSD_PREFIX );

        return sequence;
    }

    public static ModelGroup createSequence( final String tns, final String prefix ) {
        final Annotation annotation = createAnnotation();
        final Sequence sequence = Mockito.mock( Sequence.class );
        final List< SchemaComponent > particles = Collections.singletonList( createElement() );

        Mockito.when( sequence.getAnnotation() ).thenReturn( annotation );
        Mockito.when( sequence.getParticles() ).thenReturn( particles );
        Mockito.when( sequence.getNamespaceUri() ).thenReturn( tns );
        Mockito.when( sequence.getPrefix() ).thenReturn( prefix );

        return sequence;
    }

    public static SimpleType createSimpleType() {
        final Annotation annotation = createAnnotation( DOCUMENTATION_CONTENT );
        final SimpleType type = Mockito.mock( SimpleType.class );
        Mockito.when( type.getName() ).thenReturn( NAME );
        Mockito.when( type.getNamespaceUri() ).thenReturn( TNS );
        Mockito.when( type.getQname() ).thenReturn( QNAME );
        Mockito.when( type.getAnnotation() ).thenReturn( annotation );
        Mockito.when( type.getPrefix() ).thenReturn( PREFIX );
        return type;
    }

    public static SimpleType createSimpleTypeRestriction() {
        final SimpleType type = createSimpleType();
        final BaseRestriction restriction = new BaseRestriction();

        restriction.setBase( XSD_STRING_QNAME );
        Mockito.when( type.getRestriction() ).thenReturn( restriction );
        return type;
    }

    public static SimpleType createSimpleTypeList() {
        final SchemaList schemaList = new SchemaList();
        final SimpleType type = createSimpleType();

        schemaList.setItemType( XSD_STRING_TYPE );

        Mockito.when( type.getList() ).thenReturn( schemaList );
        return type;
    }

    public SimpleType createSimpleTypeUnion() {
        final Union union = Mockito.mock( Union.class );
        final SimpleType type = createSimpleType();

        Mockito
            .when( union.getMemberTypes() )
            .thenReturn( Collections.singletonList( XSD_STRING_QNAME ) );

        Mockito
            .when( type.getUnion() )
            .thenReturn( union );
        return type;
    }

    public static Annotation createAnnotation() {
        return createAnnotation( DOCUMENTATION_CONTENT );
    }

    public static Annotation createAnnotation( final String doc ) {
        final Annotation annotation = new Annotation();
        final Documentation documentation = new Documentation();
        documentation.setContent( doc );
        final Annotation spied = Mockito.spy( annotation );

        Mockito
            .doReturn( Collections.singletonList( documentation ) )
            .when( spied ).getDocumentations();
        return spied;
    }

    public static Element createElement() {
        return createElementString( NAME );
    }

    public static Element createElementString( final String name ) {
        return createElement( name, XSD_STRING_QNAME, XSD_PREFIX, XSD_TNS );
    }

    public static Element createElement( final String name, final QName type, final String prefix, final String tns ) {
        final Annotation annotation = createAnnotation();
        final Schema schema = createSchema();
        final Element element = Mockito.mock( Element.class );
        Mockito.when( element.getName() ).thenReturn( name );
        Mockito.when( element.getType() ).thenReturn( type );
        Mockito.when( element.getPrefix() ).thenReturn( prefix );
        Mockito.when( element.getNamespaceUri() ).thenReturn( tns );
        Mockito.when( element.getSchema() ).thenReturn( schema );
        Mockito.when( element.getAnnotation() ).thenReturn( annotation );
        return element;
    }


    public static ElementNode createElementNodeString() {
        return createElementNodeString(NAME, XSD_TNS, XSD_PREFIX );
    }

    public static ElementNode createElementNodeString( final String name, final String tns, final String prefix ) {
        return
            new ElementNodeBuilder( name, tns )
                .setDocumentation( DOCUMENTATION_CONTENT )
                .setPrefix( prefix )
                .setType( XSD_STRING_TYPE )
                .build();
    }

    public static SequenceNode createSequenceNode() {
        final ElementNodeBuilder builder =
            new ElementNodeBuilder()
                .add( createElementNodeString() );
        final SequenceNode node = new SequenceNode( builder );
        node.setNamespaceUri( XSD_TNS );
        node.setPrefix( XSD_PREFIX );
        node.setDocumentation( DOCUMENTATION_CONTENT );
        return node;
    }

    public static SequenceNode createSequenceNode( final String name, final String tns, final String prefix ) {
        return createSequenceNode( tns, prefix, Collections.singletonList( createElementNodeString( name, tns, prefix ) ) );
    }

    public static SequenceNode createSequenceNode( final String tns, final String prefix, List< ElementNode > children ) {
        final ElementNodeBuilder builder =
            new ElementNodeBuilder()
                .addAll( children );
        final SequenceNode node = new SequenceNode( builder );
        node.setNamespaceUri( tns );
        node.setPrefix( prefix );
        return node;
    }

}
