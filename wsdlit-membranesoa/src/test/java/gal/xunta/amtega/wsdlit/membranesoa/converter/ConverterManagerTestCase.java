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

import com.predic8.schema.GroupRef;
import gal.xunta.amtega.wsdlit.core.history.DefaultHistoryContext;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.APIDocument;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class ConverterManagerTestCase {
    private final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
    private final ConverterManager converterManager = ConverterManager.getInstance().initNamespacePrefixCache( cache );

    @Test
    public void convertEmptyListOfFilesTest() {
        final List< String > wsdls = Collections.emptyList();
        final APIDocument apiDocument = ConverterManager.getInstance().convert( wsdls );

        Assert.assertTrue( apiDocument.getServices().isEmpty() );
    }

    @Test
    public void groupRefConvertTest() {
        final GroupRef groupRef = GroupRefConverterTestCase.createGroupRef();
        final ElementNode node = converterManager.convert( groupRef );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, GroupRefConverterTestCase.MIN_OCCURRENCES_EXPECTED, node.getMinOccurs() );
    }

    @Test
    public void getHistoryManagerTest() {
        final HistoryContext historyContext = ConverterManager.getInstance().getHistoryContext();

        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, historyContext instanceof DefaultHistoryContext );
    }
}
