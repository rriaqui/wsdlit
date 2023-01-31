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
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import gal.xunta.amtega.wsdlit.core.model.util.ModelUtil;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ConverterUtilTestCase {
    @Test
    public void updateOcurrencesWhenGroupTest() {
        final String expected = "10";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        final Group group = Mockito.mock( Group.class );
        Mockito.when( group.getMinOccurs() ).thenReturn( expected );
        ConverterUtil.update( builder, group );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMinOccurs() );
    }

    @Test
    public void updateOcurrencesWhenElementCheckMinOccurrencesTest() {
        final String expected = "10";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        final Element element = Mockito.mock( Element.class );
        Mockito.when( element.getMinOccurs() ).thenReturn( expected );
        ConverterUtil.update( builder, element );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMinOccurs() );
    }

    @Test
    public void updateOcurrencesWhenElementCheckMaxOccurrencesTest() {
        final String expected = "10";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        final Element element = Mockito.mock( Element.class );
        Mockito.when( element.getMaxOccurs() ).thenReturn( expected );
        ConverterUtil.update( builder, element );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMaxOccurs() );
    }


    @Test
    public void updateMinOccursTest() {
        final String expected = "10";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        final Element element = Mockito.mock( Element.class );
        Mockito.when( element.getMinOccurs() ).thenReturn( expected );
        ConverterUtil.update( builder, element );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMinOccurs() );
    }

    @Test
    public void updateMaxOccursTest() {
        final String expected = "10";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        final Element element = Mockito.mock( Element.class );
        Mockito.when( element.getMaxOccurs() ).thenReturn( expected );
        ConverterUtil.update( builder, element );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMaxOccurs() );
    }

    @Test
    public void updateOccurrencesMinOccursNonNullTest() {
        final String expected = "10";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        ModelUtil.updateOccurrences( builder, expected, null );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMinOccurs() );
    }

    @Test
    public void updateOccurrencesMaxOccursNonNullTest() {
        final String expected = "10";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        ModelUtil.updateOccurrences( builder, null, expected );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMaxOccurs() );
    }

    @Test
    public void updateOccurrencesMinOccursNullTest() {
        final String expected = "1";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        ModelUtil.updateOccurrences( builder, null, null );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMinOccurs() );
    }

    @Test
    public void updateOccurrencesMaxOccursNullTest() {
        final String expected = "1";
        final ElementNodeBuilder builder = new ElementNodeBuilder();
        ModelUtil.updateOccurrences( builder, null, null );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMaxOccurs() );
    }
}
