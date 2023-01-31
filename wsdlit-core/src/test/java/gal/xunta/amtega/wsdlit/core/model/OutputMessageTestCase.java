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

import gal.xunta.amtega.wsdlit.core.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class OutputMessageTestCase {

    @Test
    public void sizeTest() {
        final SequenceNode sequenceNode = createSequence();
        final int sizeExpected = 2;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, sizeExpected, sequenceNode.size() );
    }

    @Test
    public void firstElementTest() {
        final SequenceNode sequenceNode = createSequence();
        final ElementNode node = sequenceNode.get( 0 );
        final String expectedName = "operationId";

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedName, node.getName() );
    }

    @Test
    public void secondElementTest() {
        final SequenceNode sequenceNode = createSequence();
        final ElementNode node = sequenceNode.get( 1 );
        final String expectedName = "service";

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expectedName, node.getName() );
    }

    private SequenceNode createSequence() {
        final ElementNodeBuilder builder = new ElementNodeBuilder()
            .setName( "sequence" )
            .add( new ElementNodeBuilder().setName( "operationId" ).build() )
            .add( new ElementNodeBuilder().setName( "service").build() );
        return new SequenceNode( builder );
    }
}
