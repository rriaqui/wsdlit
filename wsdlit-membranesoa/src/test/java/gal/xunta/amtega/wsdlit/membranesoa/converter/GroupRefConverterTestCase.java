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

import com.predic8.schema.Group;
import com.predic8.schema.GroupRef;
import com.predic8.schema.ModelGroup;
import com.predic8.schema.Schema;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.AbstractTestCase;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import groovy.namespace.QName;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

public class GroupRefConverterTestCase {
    public static final String GROUP_REF_QNAME_LOCAL_PART_EXPECTED = "localPart";
    public static final String GROUP_REF_NAME_EXPECTED = "theGroup";
    public static final String SCHEMA_TNS_EXPECTED = "test/groupRef";
    public static final String MIN_OCCURRENCES_EXPECTED = "5";
    public static final String MAX_OCCURRENCES_EXPECTED = "3";
    public static final String PREFIX_EXPECTED = "prefix";

    private final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
    private final ConverterManager converterManager = ConverterManager.getInstance().initNamespacePrefixCache( cache );
    private final GroupRefConverter converter = converterManager.getGroupRefConverter();

    @Test
    public void convertCheckChildrenSizeTest() {
        final GroupRef groupRef = createGroupRef();
        final ElementNode node = converter.convert( groupRef );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, 1, node.getChildren().size() );
    }

    @Test
    public void convertCheckMaxOcurencesTest() {
        final GroupRef groupRef = createGroupRef();
        final ElementNode node = converter.convert( groupRef );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MAX_OCCURRENCES_EXPECTED, node.getMaxOccurs() );
    }

    @Test
    public void convertCheckMinOcurencesTest() {
        final GroupRef groupRef = createGroupRef();
        final ElementNode node = converter.convert( groupRef );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MIN_OCCURRENCES_EXPECTED, node.getMinOccurs() );
    }

    public static GroupRef createGroupRef() {
        final QName qname = new QName( SCHEMA_TNS_EXPECTED, GROUP_REF_QNAME_LOCAL_PART_EXPECTED, PREFIX_EXPECTED );
        final GroupRef groupRef = new GroupRef();

        groupRef.setMinOccurs( MIN_OCCURRENCES_EXPECTED );
        groupRef.setMaxOccurs( MAX_OCCURRENCES_EXPECTED );
        groupRef.setName( GROUP_REF_NAME_EXPECTED );

        groupRef.setRef( qname );
        groupRef.setSchema( createSchema() );
        return groupRef;
    }

    private static Schema createSchema() {
        final Schema schema = new Schema( SCHEMA_TNS_EXPECTED );

        schema.setGroups( Collections.singletonList( createGroup() ) );
        return schema;
    }

    private static Group createGroup() {
        final Group group = new Group();
        final QName qname = new QName( SCHEMA_TNS_EXPECTED, GROUP_REF_QNAME_LOCAL_PART_EXPECTED );
        final ModelGroup sequence = AbstractTestCase.createSequence( SCHEMA_TNS_EXPECTED, PREFIX_EXPECTED );
        group.setQname( qname );
        group.setName( GROUP_REF_NAME_EXPECTED );

        group.setModel( sequence );

        final Group spied = Mockito.spy( group );
        Mockito.doReturn( SCHEMA_TNS_EXPECTED ).when( spied ).getNamespaceUri();
        Mockito.doReturn( PREFIX_EXPECTED ).when( spied ).getPrefix();

        return spied;
    }
}
