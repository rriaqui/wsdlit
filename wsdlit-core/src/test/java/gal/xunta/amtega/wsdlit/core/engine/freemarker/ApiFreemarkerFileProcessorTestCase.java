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

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModelException;
import gal.xunta.amtega.wsdlit.core.AssertMessages;
import gal.xunta.amtega.wsdlit.core.engine.api.Context;
import gal.xunta.amtega.wsdlit.core.engine.api.ProcessException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class ApiFreemarkerFileProcessorTestCase {

    @Test( expected = ProcessException.class )
    public void bodyThrowsIOExceptionWhenOutputPathDoesNotExistsTest()
    throws ProcessException {
        final String outputPath = "/output/path/does/not/exists";
        final Map< String, Object > model = new HashMap<>();
        final ApiFreemarkerFileProcessor affp = createApiFreemarkerFileProcessor( outputPath );
        affp.body( model );
    }

    @Test( expected = ProcessException.class )
    public void bodyThrowsTemplateExceptionTest()
    throws ProcessException {
        final Map< String, Object > model = new HashMap<>();
        final ApiFreemarkerFileProcessor affp = createApiFreemarkerFileProcessor();
        affp.getConfiguration().setObjectWrapper( createObjectWrapper() );
        affp.body( model );
    }

    @Test
    public void bodyTest()
        throws ProcessException {
        final String outputPath = "target/tests/ApiFreemarkerFileProcessorTestCase/bodyTest";
        final Map< String, Object > model = FreemarkerTestUtil.createModel();
        final ApiFreemarkerFileProcessor affp = new ApiFreemarkerFileProcessor(
            createContext( outputPath ),
            "index", "index"
        );
        affp.body( model );

        final File expected = new File( new File( outputPath ), "index.adoc" );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, expected.isFile() );
    }

    private ApiFreemarkerFileProcessor createApiFreemarkerFileProcessor() {
        return new ApiFreemarkerFileProcessor( new Context(), "plantilla-proba", "/output/path/does/not/exists/target/output/index" );
    }

    private ApiFreemarkerFileProcessor createApiFreemarkerFileProcessor( final String output ) {
        return new ApiFreemarkerFileProcessor( createContext( output ), "plantilla-proba", "/output/path/does/not/exists/target/output/index" );
    }

    private Context createContext( final String output ) {
        final Context context = new Context();
        context.setOutputPath( new File( output ) );
        return context;
    }

    private ObjectWrapper createObjectWrapper() {
        return obj -> {
            throw new TemplateModelException( "Non soportado" );
        };
    }
}
