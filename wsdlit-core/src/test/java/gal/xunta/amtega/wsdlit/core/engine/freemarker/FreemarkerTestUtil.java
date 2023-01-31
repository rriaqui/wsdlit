package gal.xunta.amtega.wsdlit.core.engine.freemarker;

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

import gal.xunta.amtega.wsdlit.core.model.APIDocument;
import gal.xunta.amtega.wsdlit.core.model.ServiceNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class FreemarkerTestUtil {
    private FreemarkerTestUtil() {}

    public static Map< String, Object > createModel() {
        final Map< String, Object > model = new HashMap<>();
        final ServiceNode service = new ServiceNode( "helloworld", "tns/hello/world", Collections.emptyList() );
        final List<ServiceNode> services = Collections.singletonList( service );
        final APIDocument api = new APIDocument( services );
        api.setTitle( "API title example" );
        api.setVersion( "1" );
        model.put( "document", api );

        return model;
    }
}
