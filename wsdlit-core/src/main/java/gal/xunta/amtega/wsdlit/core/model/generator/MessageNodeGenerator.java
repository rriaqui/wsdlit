package gal.xunta.amtega.wsdlit.core.model.generator;

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

import gal.xunta.amtega.wsdlit.core.model.ElementNode;
import gal.xunta.amtega.wsdlit.core.model.MessageNode;
import gal.xunta.amtega.wsdlit.core.model.PartNode;

/**
 * Example message generator in XML format.
 *
 * @author rriaqui
 * @since 1.0
 */
public class MessageNodeGenerator {
    final ExampleBuilder builder = new ExampleBuilder();

    /**
     * Returns an example in XML corresponding to a {@link MessageNode}.
     *
     * @param message the message node.
     * @return a text with the example message.
     */
    public String generate( final MessageNode message ) {
        for( final PartNode part : message.getParts() ) {
            final ElementNode element = part.getElement();
            this.generate( element );
        }
        return this.builder.build();
    }

    /*
     * Generates the instance of the given node.
     *
     * @param element the element from which you want to generate its content.
     */
    private void generate( final ElementNode element ) {
        this.builder.start( element.getName() );
        for( ElementNode child : element.getChildren() ) {
            this.generate( child );
        }
        this.builder.close( element.getName() );
    }
}
