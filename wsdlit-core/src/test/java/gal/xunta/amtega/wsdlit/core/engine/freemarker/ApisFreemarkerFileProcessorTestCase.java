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
public class ApisFreemarkerFileProcessorTestCase {

    @Test( expected = ProcessException.class )
    public void bodyCausesIOExceptionWhenOutputPathDoesNotExistsTest()
    throws ProcessException {
        final String outputPath = "/output/path/does/not/exists";
        final Map< String, Object > model = new HashMap<>();
        final ApisFreemarkerFileProcessor affp = createApisFreemarkerFileProcessor( outputPath );
        affp.body( model );
    }

    @Test( expected = ProcessException.class )
    public void bodyCausesTemplateExceptionTest()
        throws ProcessException {
        final Map< String, Object > model = new HashMap<>();
        final ApisFreemarkerFileProcessor affp = createApisFreemarkerFileProcessor();
        affp.getConfiguration().setObjectWrapper( createObjectWrapper() );
        affp.body( model );
    }

    @Test
    public void bodyTest()
    throws ProcessException {
        final String outputPath = "target/tests/ApisFreemarkerFileProcessorTestCase/bodyTest";
        final Map< String, Object > model = FreemarkerTestUtil.createModel();
        final ApisFreemarkerFileProcessor affp = new ApisFreemarkerFileProcessor(
            createContext( outputPath )
        );
        affp.body( model );

        final File expected = new File( new File( outputPath ), "api/index.adoc" );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, expected.isFile() );
    }

    @Test
    public void processTest()
        throws ProcessException {
        final String outputPath = "target/tests/ApisFreemarkerFileProcessorTestCase/processTest";
        final Map< String, Object > model = FreemarkerTestUtil.createModel();
        final ApisFreemarkerFileProcessor affp = new ApisFreemarkerFileProcessor(
            createContext( outputPath )
        );
        affp.process( model );

        final File expected = new File( new File( outputPath ), "api/index.adoc" );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, expected.isFile() );
    }

    private ApisFreemarkerFileProcessor createApisFreemarkerFileProcessor() {
        return new ApisFreemarkerFileProcessor( new Context() );
    }

    private ApisFreemarkerFileProcessor createApisFreemarkerFileProcessor( String output ) {
        return new ApisFreemarkerFileProcessor( createContext( output ) );
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
