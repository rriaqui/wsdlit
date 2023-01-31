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

import java.util.Objects;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class MessageNodeTestCase
extends ModelTestCaseAbstract {
    @Test
    public void getPartsTest() {
        final MessageNode message = createInputMessage();
        final int expected = 3;
        message.add( createPartNode() );
        message.add( createPartNode() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, message.getParts().size() );
    }

    @Test
    public void hashCodeWhenNoPartsTest() {
        final MessageNode message = createInputMessage();
        final AbstractNode node = new AbstractNode( message.getName(), message.getNamespaceUri() ) {
        };
        node.setDocumentation( message.getDocumentation() );
        node.setPrefix( message.getPrefix() );

        final int expected = Objects.hash( node.hashCode(), message.getParts() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, message.hashCode() );
    }

    @Test
    public void hashCodeWhenPartsTest() {
        final MessageNode message = createInputMessage();
        final AbstractNode node = new AbstractNode( message.getName(), message.getNamespaceUri() ) {
        };
        node.setDocumentation( message.getDocumentation() );
        node.setPrefix( message.getPrefix() );
        message.add( createPartNode() );
        message.add( createPartNode() );
        final int expected = Objects.hash( node.hashCode(), message.getParts() );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, message.hashCode() );
    }
}
