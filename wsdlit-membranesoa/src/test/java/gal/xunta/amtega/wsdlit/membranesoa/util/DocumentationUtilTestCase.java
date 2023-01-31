package gal.xunta.amtega.wsdlit.membranesoa.util;

/*-
 * #%L
 * wsdlit-membranesoa
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

import com.predic8.schema.Annotation;
import com.predic8.schema.Documentation;
import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;


public class DocumentationUtilTestCase {
    private static final String EXPECTED_DOCUMENTATION_CONTENT = "Hello";
    @Test
    public void normalizeAnnotationTest() {
        final Annotation annotation = Mockito.mock( Annotation.class );
        final List<Documentation> documentations = Collections.singletonList( createDocumentation() );
        Mockito.when( annotation.getDocumentations() ).thenReturn( documentations );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, EXPECTED_DOCUMENTATION_CONTENT, DocumentationUtil.normalize( annotation ) );
    }

    @Test
    public void normalizeDefinitionsTest() {
        final Definitions definitions = Mockito.mock( Definitions.class );
        final com.predic8.wsdl.Documentation documentation = new com.predic8.wsdl.Documentation();
        final String expected = "A documentación";

        documentation.setContent( expected );
        Mockito.when( definitions.getDocumentation() )
               .thenReturn( documentation );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, DocumentationUtil.normalize( definitions ) );
    }

    @Test
    public void normalizeListOfDocumentationTest() {
        final List<Documentation> list = Collections.singletonList( createDocumentation() );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, EXPECTED_DOCUMENTATION_CONTENT, DocumentationUtil.normalize( list ) );
    }

    @Test
    public void normalizeDocumentationTest() {
        final com.predic8.wsdl.Documentation documentation = new com.predic8.wsdl.Documentation();
        documentation.setContent( EXPECTED_DOCUMENTATION_CONTENT );
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, EXPECTED_DOCUMENTATION_CONTENT, DocumentationUtil.normalize( documentation ) );
    }

    private Documentation createDocumentation() {
        final Documentation documentation = new Documentation();
        documentation.setContent( EXPECTED_DOCUMENTATION_CONTENT );
        return documentation;
    }
}
