package gal.xunta.amtega.wsdlit.membranesoa.testutil;

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

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class StringHandler
extends Handler {
    private final StringBuilder output;

    public StringHandler( final StringBuilder capture ) {
        this.output = capture;
    }

    @Override
    public void publish( final LogRecord logRecord ) {
        this.output
                .append( getFormatter().formatMessage( logRecord ) )
                .append( "\n" );
    }

    @Override
    public void flush() {
        this.output.setLength( 0 );
    }

    @Override
    public void close() throws SecurityException {
        this.output.setLength( 0 );
    }
}
