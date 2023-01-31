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
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;


public class DocumentationUtilNormalizeReturnsEmptyTestCase {
    @Test
    public void normalizeAnnotationWhenAnnotationIsNullThenEmptyTest() {
        final Annotation annotation = null;
        final String expected = StringUtil.EMPTY;

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, DocumentationUtil.normalize( annotation ) );
    }

    @Test
    public void normalizeAnnotationWhenDocumentationIsEmptyThenTest() {
        final Annotation annotation = new Annotation();
        final String expected = StringUtil.EMPTY;

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, DocumentationUtil.normalize( annotation ) );
    }

    @Test
    public void normalizeDefinitionsWhenIsNullTest() {
        final Definitions definitions = null;
        final String expected = StringUtil.EMPTY;

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, DocumentationUtil.normalize( definitions ) );
    }

    @Test
    public void normalizeListOfDocumentationWhenListIsEmptyThenTest() {
        final List<Documentation> list = Collections.emptyList();
        final String expected = StringUtil.EMPTY;

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, DocumentationUtil.normalize( list ) );
    }

    @Test
    public void normalizeListOfDocumentationWhenListIsNullThenTest() {
        final List< Documentation > list = null;
        final String expected = StringUtil.EMPTY;

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, expected, DocumentationUtil.normalize( list ) );
    }

    @Test
    public void normalizeDocumentationWhenIsNullTest() {
        final com.predic8.wsdl.Documentation documentation = null;
        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, StringUtil.EMPTY, DocumentationUtil.normalize( documentation ) );
    }
}
