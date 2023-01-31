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

import java.io.File;
import java.io.InputStream;

public abstract class AbstractTestCase {
    public static final String VERSION_1 = "serviceV1.wsdl";
    public static final String VERSION_2 = "serviceV2.wsdl";
    protected final DiffTool diffTool = new DiffTool();
    protected final InputStream getInputStream( final String filename ) {
        final String resource = getClass().getSimpleName() + File.separator + filename;
        return DiffToolTestCase.class.getResourceAsStream( resource );
    }

    protected final InputStream getInputStreamV1() {
        return getInputStream( VERSION_1 );
    }

    protected final InputStream getInputStreamV2() {
        return getInputStream( VERSION_2 );
    }
}
