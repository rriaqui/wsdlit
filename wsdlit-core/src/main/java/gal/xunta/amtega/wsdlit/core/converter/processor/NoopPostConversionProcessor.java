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

import gal.xunta.amtega.wsdlit.core.converter.VoidAbstractConversionContext;
import gal.xunta.amtega.wsdlit.core.model.ServiceNode;

/**
 * Post conversion processor that does nothing.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class NoopPostConversionProcessor
implements PostConversionProcessor< VoidAbstractConversionContext > {
    @Override
    public void process(final ServiceNode serviceNode, final VoidAbstractConversionContext conversionContext ) {
        // Este postprocesador non fai nada (No Operation Post Conversion Process)
    }
}
