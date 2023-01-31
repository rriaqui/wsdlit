package gal.xunta.amtega.wsdlit.core.converter.processor;

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

import gal.xunta.amtega.wsdlit.core.converter.AbstractConversionContext;
import gal.xunta.amtega.wsdlit.core.model.ServiceNode;

/**
 * Class that processes a conversion.
 *
 *  <p>
 *      When it is necessary to perform certain actions that only have a place in a specific context
 *  </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public interface PostConversionProcessor<T extends AbstractConversionContext<T> > {

    /**
     * Post processes a {@code ServiceNode} after it is generated.
     *
     * @param serviceNode the converted {@code ServiceNode}.
     * @param conversionContext the conversion context from which the {@code ServiceNode} was created.
     */
    void process(ServiceNode serviceNode, T conversionContext );
}
