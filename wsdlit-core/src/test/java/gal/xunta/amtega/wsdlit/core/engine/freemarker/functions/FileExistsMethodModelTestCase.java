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

import freemarker.template.TemplateModelException;
import gal.xunta.amtega.wsdlit.core.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class FileExistsMethodModelTestCase {
    @Test( expected = TemplateModelException.class )
    public void noArgsTest()
    throws TemplateModelException {
        final FileExistsMethodModel function = new FileExistsMethodModel();

        function.exec(Collections.emptyList() );
    }

    @Test( expected = TemplateModelException.class )
    public void oneArgTest()
    throws TemplateModelException {
        final FileExistsMethodModel function = new FileExistsMethodModel();
        function.exec(Collections.singletonList( "src/test/resources" ) );
    }

    @Test
    public void execReturnsTrueTest()
    throws TemplateModelException {
        final FileExistsMethodModel function = new FileExistsMethodModel();
        final List<String> arguments = Arrays.asList( "plantilla-proba.ftl", "src/test/resources/templates/default/plantilla-proba.ftl" );
        final Boolean exists = ( Boolean ) function.exec( arguments );
        Assert.assertTrue( AssertMessages.TRUE_EXPECTED, exists );
    }

    @Test
    public void execReturnsFalseTest()
    throws TemplateModelException {
        final FileExistsMethodModel function = new FileExistsMethodModel();
        final List<String> arguments = Arrays.asList( "this-file-does-not-exists.xsd", "src/test/resources/es" );
        final Boolean exists = ( Boolean ) function.exec( arguments );
        Assert.assertFalse( AssertMessages.NOT_EQUALS_EXPECTED, exists );
    }

}
