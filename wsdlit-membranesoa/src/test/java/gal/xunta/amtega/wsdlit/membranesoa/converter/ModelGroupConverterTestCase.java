package gal.xunta.amtega.wsdlit.membranesoa.converter;

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

import com.predic8.schema.ComplexType;
import com.predic8.schema.ModelGroup;
import com.predic8.schema.Schema;
import com.predic8.wsdl.Definitions;
import gal.xunta.amtega.wsdlit.core.model.*;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import gal.xunta.amtega.wsdlit.membranesoa.model.MembraneSOANamespacePrefixCache;
import gal.xunta.amtega.wsdlit.membranesoa.util.MembraneSOAUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith( Parameterized.class )
public class ModelGroupConverterTestCase {
    private final NamespacePrefixCache cache = new MembraneSOANamespacePrefixCache();
    private final ConverterManager converterManager = ConverterManager.getInstance().initNamespacePrefixCache( cache );

    private final ModelGroupConverter converter = converterManager.getModelGroupConverter();

    private final String complexTypeName;
    private final String modelGroupNameExpected;

    public ModelGroupConverterTestCase( final String complexTypeName, final String modelGroupNameExpected ) {
        this.complexTypeName = complexTypeName;
        this.modelGroupNameExpected = modelGroupNameExpected;
    }

    @Test
    public void convertWhenSequenceTest() {
        final String filenamePath = "src/test/resources/converter/converter.wsdl";
        final Definitions definitions = MembraneSOAUtil.readWSDLFile( filenamePath );
        final Schema schema = definitions.getSchema( definitions.getTargetNamespace() );

        final ComplexType complexType = schema.getComplexType( this.complexTypeName );
        final AbstractModelGroup modelGroup = converter.convert( ( ModelGroup ) complexType.getModel() );

        Assert.assertEquals( AssertMessages.EQUALS_EXPECTED, this.modelGroupNameExpected, modelGroup.getName() );
    }

    @Parameterized.Parameters
    public static Collection< Object[] > data() {
        return Arrays.asList(
            new Object[][] {
                { "ModelGroupConverterChoiceTestCase", ChoiceNode.NAME },
                { "ModelGroupConverterAllTestCase", AllNode.NAME },
                { "ModelGroupConverterSequenceTestCase", SequenceNode.NAME },
            }
        );
    }
}
