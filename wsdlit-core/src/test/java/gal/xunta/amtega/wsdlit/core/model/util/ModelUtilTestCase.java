package gal.xunta.amtega.wsdlit.core.model.util;

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

import gal.xunta.amtega.wsdlit.core.AssertMessages;
import gal.xunta.amtega.wsdlit.core.model.ElementNodeBuilder;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ModelUtilTestCase {
    private static final String DOCUMENTATION = "documentation";
    private static final String MAX_OCCURS = "unbound";
    private static final String MIN_OCCURS = "1";
    private static final String NAME = "name";
    private static final String PREFIX = "tns";
    private static final String TYPE = "type";
    private static final String TNS = "http://localhost/elementItemBuilderTestCase";

    @Test
    public void updateOccurrencesWhenMinOccursIsNotNullTest() {
        final ElementNodeBuilder builder = create();
        final Object minOccurs = "4";
        final Object maxOccurs = null;
        final String expected = "4";

        ModelUtil.updateOccurrences( builder, minOccurs, maxOccurs );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMinOccurs() );
    }

    @Test
    public void updateOccurrencesWhenMinOccursIsNullTest() {
        final ElementNodeBuilder builder = create();
        final Object minOccurs = null;
        final Object maxOccurs = null;

        ModelUtil.updateOccurrences( builder, minOccurs, maxOccurs );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MIN_OCCURS, builder.getMinOccurs() );
    }

    @Test
    public void updateOccurrencesWhenMaxOccursIsNotNullTest() {
        final ElementNodeBuilder builder = create();
        final Object minOccurs = null;
        final Object maxOccurs = "4";
        final String expected = "4";

        ModelUtil.updateOccurrences( builder, minOccurs, maxOccurs );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, builder.getMaxOccurs() );
    }

    @Test
    public void updateOccurrencesWhenMaxOccursIsNullTest() {
        final ElementNodeBuilder builder = create();
        final Object minOccurs = null;
        final Object maxOccurs = null;

        ModelUtil.updateOccurrences( builder, minOccurs, maxOccurs );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, MAX_OCCURS, builder.getMaxOccurs() );
    }

    public ElementNodeBuilder create() {
        final ElementNodeBuilder builder = new ElementNodeBuilder( NAME, TNS );
        builder.setDocumentation( DOCUMENTATION );
        builder.setMinOccurs( MIN_OCCURS );
        builder.setMaxOccurs( MAX_OCCURS );
        builder.setPrefix( PREFIX );
        builder.setType( TYPE );
        return builder;
    }
}
