package gal.xunta.amtega.wsdlit.membranesoa.diff;

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

import com.predic8.soamodel.Difference;
import gal.xunta.amtega.wsdlit.membranesoa.AssertMessages;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Probas unitarias que garanten o resultado esperado no caso de que o único que cambie entre dous contratos
 * sexa o targetNamespace.
 */
public class DefinitionsTargetNamespaceChangedTestCase
extends AbstractTestCase {
    @Test
    public void getDifferencesSizeTest() {
        final DiffTool diffTool = new DiffTool();
        final List<Difference> differences = diffTool.getDifferences(  getInputStreamV1(), getInputStreamV2()  );
        final int expected = 17;
        Assert.assertEquals( AssertMessages.SIZE_NOT_EXPECTED, expected, DifferenceUtil.sizeTotal( differences ) );
    }
}
