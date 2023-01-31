package gal.xunta.amtega.wsdlit.membranesoa.converter;

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
import com.predic8.wsdl.*;
import gal.xunta.amtega.wsdlit.core.history.DefaultHistoryContext;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.*;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;

import java.util.List;

/**
 * Converter manager from all clases.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class ConverterManager {
    private static final ConverterManager INSTANCE = new ConverterManager();

    private final ComplexContentConverter complexContentConverter;
    private final ComplexTypeConverter complexTypeConverter;
    private final DefinitionsConverter definitionsConverter;
    private final DerivationConverter derivationConverter;
    private final ElementConverter elementConverter;
    private final GroupConverter groupConverter;
    private final GroupRefConverter groupRefConverter;
    private final MessageConverter messageConverter;
    private final ModelGroupConverter modelGroupConverter;
    private final OperationConverter operationConverter;
    private final PartConverter partConverter;
    private final PortTypeConverter portTypeConverter;
    private final SchemaComponentConverter schemaComponentConverter;
    private final SimpleContentConverter simpleContentConverter;
    private final SimpleTypeConverter simpleTypeConverter;
    private final APIDocumentConverter apiDocumentConverter;
    private final HistoryContext historyContext;

    /**
     * Constructs ConverterManager from the default history context.
     */
    public ConverterManager() {
        this( new DefaultHistoryContext() );
    }

    /**
     * Constructs {@code ConverterManager} from the specified history context.
     *
     * @param historyContext the specified history context.
     */
    public ConverterManager( final HistoryContext historyContext ) {
        this.apiDocumentConverter = new APIDocumentConverter();
        this.complexContentConverter = new ComplexContentConverter( historyContext );
        this.complexTypeConverter = new ComplexTypeConverter( historyContext );
        this.definitionsConverter  = new DefinitionsConverter();
        this.derivationConverter = new DerivationConverter( historyContext );
        this.elementConverter = new ElementConverter( historyContext );
        this.groupConverter = new GroupConverter( historyContext );
        this.groupRefConverter = new GroupRefConverter( historyContext );
        this.messageConverter = new MessageConverter( historyContext );
        this.modelGroupConverter = new ModelGroupConverter( historyContext );
        this.operationConverter = new OperationConverter( historyContext );
        this.partConverter = new PartConverter( historyContext );
        this.portTypeConverter = new PortTypeConverter( historyContext );
        this.schemaComponentConverter = new SchemaComponentConverter( historyContext );
        this.simpleContentConverter = new SimpleContentConverter();
        this.simpleTypeConverter = new SimpleTypeConverter();
        this.historyContext = historyContext;
    }

    /**
     * Converts a specified {@code Definitions} of MembraneSOA to the {@code ServiceNode} in wsdlit.
     *
     * @param definitions the specified {@code Definitions} to be converted.
     * @return the converted {@code ServiceNode} wsdlit.
     */
    public ServiceNode convert( final Definitions definitions ) {
        final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache( definitions );
        return this
            .initNamespacePrefixCache( cache )
            .definitionsConverter.convert( definitions );
    }

    public HistoryContext getHistoryContext() {
        return this.historyContext;
    }

    /**
     * Initializes the instance with the specified {@code NamespacePrefixCache}.
     *
     * @param cache the instance with the specified {@code NamespacePrefixCache}.
     * @return a reference to this builder.
     */
    public ConverterManager initNamespacePrefixCache( final NamespacePrefixCache cache ) {
        complexTypeConverter.setNamespacePrefixCache( cache );
        definitionsConverter.setNamespacePrefixCache( cache );
        derivationConverter.setNamespacePrefixCache( cache );
        elementConverter.setNamespacePrefixCache( cache );
        groupConverter.setNamespacePrefixCache( cache );
        groupRefConverter.setNamespacePrefixCache( cache );
        messageConverter.setNamespacePrefixCache( cache );
        modelGroupConverter.setNamespacePrefixCache( cache );
        operationConverter.setNamespacePrefixCache( cache );
        partConverter.setNamespacePrefixCache( cache );
        portTypeConverter.setNamespacePrefixCache( cache );
        schemaComponentConverter.setNamespacePrefixCache( cache );
        simpleContentConverter.setNamespacePrefixCache( cache );
        simpleTypeConverter.setNamespacePrefixCache( cache );
        simpleTypeConverter.setNamespacePrefixCache( cache );
        return this;
    }

    /**
     * Converts tne specified MembraneSOA {@code Element} to wsdlit {@code ElementNode},
     * without ignoring history.
     *
     * @param element the specified MembraneSOA {@code Element} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert(final Element element ) {
        return this.convert( element, false );
    }

    /**
     * Converts the specified MembraneSOA {@code Element} to wsdlit {@code ElementNode},
     * with the possibility to ignore the history conversion.
     *
     * @param element the specified MembraneSOA {@code Element} to be converted.
     * @param ignoreHistory set this to {@code true} to ignore the history conversion.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final Element element, final boolean ignoreHistory ) {
        return this.elementConverter.convert( element, ignoreHistory );
    }

    /**
     * Converts the specified MembraneSOA {@code AbstractPortTypeMessage} to wsdlit {@code MessageNode}.
     *
     * @param portTypeMessage the specified MembraneSOA {@code AbstractPortTypeMessage} to be converted.
     * @return the converted {@code MessageNode} wsdlit.
     */
    public MessageNode convert(final AbstractPortTypeMessage portTypeMessage ) {
        return this.messageConverter.convert( portTypeMessage );
    }

    /**
     * Converts the specified MembraneSOA {@code ComplexContent} to wsdlit {@code ElementNode}.
     *
     * @param complexContent the specified MembraneSOA {@code ComplexContent} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final ComplexContent complexContent ) {
        return this.complexContentConverter.convert( complexContent );
    }

    /**
     * Converts the specified MembraneSOA {@code Group} to wsdlit {@code ElementNode}.
     *
     * @param group the specified MembraneSOA {@code Group} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final Group group ) {
        return this.groupConverter.convert( group );
    }

    /**
     * Converts the specified MembraneSOA {@code GroupRef} to wsdlit {@code ElementNode}.
     *
     * @param groupRef the specified MembraneSOA {@code GroupRef} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final GroupRef groupRef ) {
        return this.groupRefConverter.convert( groupRef );
    }

    /**
     * Converts the specified MembraneSOA {@code ModelGroup} to wsdlit {@code ElementNode}.
     *
     * @param modelGroup the specified MembraneSOA {@code ModelGroup} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final ModelGroup modelGroup ) {
        return this.modelGroupConverter.convert( modelGroup );
    }

    /**
     * Converts the specified MembraneSOA {@code Operation} to wsdlit {@code OperationNode}.
     *
     * @param operation the specified MembraneSOA {@code Operation} to be converted.
     * @return the converted {@code OperationNode} wsdlit.
     */
    public OperationNode convert(final Operation operation ) {
        return this.operationConverter.convert( operation );
    }

    /**
     * Converts the specified MembraneSOA {@code Part} to wsdlit {@code PartNode}.
     *
     * @param part the specified MembraneSOA {@code Part} to be converted.
     * @return the converted {@code PartNode} wsdlit.
     */
    public PartNode convert( final Part part ) {
        return this.partConverter.convert( part );
    }

    /**
     * Converts the specified MembraneSOA {@code PortType} to wsdlit {@code PortTypeNode}.
     *
     * @param portType the specified MembraneSOA {@code PortType} to be converted.
     * @return the converted {@code PortTypeNode} wsdlit.
     */
    public PortTypeNode convert(final PortType portType ) {
        return this.portTypeConverter.convert( portType );
    }

    /**
     * Converts the specified MembraneSOA {@code Derivation} to wsdlit {@code ElementNode}.
     *
     * @param derivation the specified MembraneSOA {@code Derivation} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final Derivation derivation ) {
        return this.derivationConverter.convert( derivation );
    }

    /**
     * Converts the specified MembraneSOA {@code SchemaComponent} to wsdlit {@code ElementNode}.
     *
     * @param schemaComponent the specified MembraneSOA {@code SchemaComponent} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final SchemaComponent schemaComponent ) {
        return this.schemaComponentConverter.convert( schemaComponent );
    }

    /**
     * Converts the specified MembraneSOA {@code SimpleContent} to wsdlit {@code ElementNode}.
     *
     * @param simpleContent the specified MembraneSOA {@code SimpleContent} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final SimpleContent simpleContent ) {
        return this.simpleContentConverter.convert( simpleContent );
    }

    /**
     * Converts the specified MembraneSOA {@code TypeDefinition} to wsdlit {@code ElementNode}.
     *
     * @param typeDefinition the specified MembraneSOA {@code TypeDefinition} to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final TypeDefinition typeDefinition ) {
        return this.convert(typeDefinition, false);
    }

    /**
     * Converts the specified MembraneSOA {@code TypeDefinition} to wsdlit {@code ElementNode}.
     *
     * @param typeDefinition the specified MembraneSOA {@code TypeDefinition} to be converted.
     * @param ignoreHistory sets this to {@code true} to ignore the history conversion.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public ElementNode convert( final TypeDefinition typeDefinition, final boolean ignoreHistory ) {
        if ( typeDefinition instanceof ComplexType ) {
            return complexTypeConverter.convert( ( ComplexType ) typeDefinition, ignoreHistory );
        } else if ( typeDefinition instanceof SimpleType ) {
            return simpleTypeConverter.convert( ( SimpleType ) typeDefinition );
        } else {
            return ElementNode.NULL_ELEMENT;
        }
    }

    /**
     * Converts the specified list of wsdl documents (by path) to a wsdlit {@code APIDocument}.
     *
     * <p>
     *      This method allows different services to be grouped under the concept of the same API.
     * </p>
     *
     * @param uris the specified list of contracts to be converted.
     * @return the converted {@code ElementNode} wsdlit.
     */
    public APIDocument convert(List<String> uris ) {
        return this.apiDocumentConverter.convert( uris );
    }


    /**
     * Returns the {@code APIDocumentConverted}.
     *
     * @return the {@code APIDocumentConverted}.
     */
    public APIDocumentConverter getApiDocumentConverter() {
        return this.apiDocumentConverter;
    }

    /**
     * Returns the {@code SchemaComponentConverter}.
     *
     * @return the {@code SchemaComponentConverter}.
     */
    public SchemaComponentConverter getSchemaComponentConverter() {
        return this.schemaComponentConverter;
    }

    /**
     * Returns the {@code ComplexContentConverter}.
     *
     * @return the {@code ComplexContentConverter}.
     */
    public ComplexContentConverter getComplexContentConverter() {
        return this.complexContentConverter;
    }


    /**
     * Returns the {@code ComplexTypeConverter}.
     *
     * @return the {@code ComplexTypeConverter}.
     */
    public ComplexTypeConverter getComplexTypeConverter() {
        return this.complexTypeConverter;
    }

    /**
     * Returns the {@code GroupRefConverter}.
     *
     * @return the {@code GroupRefConverter}.
     */
    public GroupRefConverter getGroupRefConverter() {
        return this.groupRefConverter;
    }

    /**
     * Returns the {@code ModelGroupConverter}.
     *
     * @return the {@code ModelGroupConverter}.
     */
    public ModelGroupConverter getModelGroupConverter() {
        return this.modelGroupConverter;
    }

    /**
     * Returns the {@code PartConverted}.
     *
     * @return the {@code PartConverted}.
     */
    public PartConverter getPartConverter() {
        return this.partConverter;
    }

    /**
     * Returns the {@code ConverterManager}.
     *
     * @return the {@code ConverterManager}.
     */
    public static ConverterManager getInstance() {
        return INSTANCE;
    }
}
