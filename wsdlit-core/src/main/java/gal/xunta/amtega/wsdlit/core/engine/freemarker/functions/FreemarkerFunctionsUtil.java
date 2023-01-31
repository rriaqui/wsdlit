package gal.xunta.amtega.wsdlit.core.engine.freemarker.functions;

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

import java.util.Map;

/**
 * Class that adds to the Freemarker data model the list of existing functions.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class FreemarkerFunctionsUtil {
    private FreemarkerFunctionsUtil() {}

    /**
     * Add existing function list to Freemarker data model.
     *
     * @param model the Freemarker data model.
     */
    public static void addFunctions( final Map<String, Object> model ) {
        model.put( "fileExists", new FileExistsMethodModel() );
    }
}
