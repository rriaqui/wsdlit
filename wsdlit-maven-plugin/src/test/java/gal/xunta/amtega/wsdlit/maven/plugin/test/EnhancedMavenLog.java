package gal.xunta.amtega.wsdlit.maven.plugin.test;

/*-
 * #%L
 * AMTEGA WsdlIT Maven Plugin
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

import org.apache.maven.monitor.logging.DefaultLog;
import org.codehaus.plexus.logging.Logger;

/**
 * Implementación dun logger para o seu uso en probas,
 * para realizar comparacións.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class EnhancedMavenLog
extends DefaultLog {
    private final StringBuilder debugMessage = new StringBuilder();
    private final StringBuilder infoMessage = new StringBuilder();
    private final StringBuilder warningMessage = new StringBuilder();

    public EnhancedMavenLog( Logger logger ) {
        super( logger );
    }

    @Override
    public void debug( final CharSequence message ) {
        debugMessage
            .append( message )
            .append( System.lineSeparator() );
    }

    @Override
    public void warn( final CharSequence message ) {
        warningMessage
            .append( message )
            .append( System.lineSeparator() );
    }

    @Override
    public void info( final CharSequence message ) {
        infoMessage
            .append( message )
            .append( System.lineSeparator() );
    }

    public String getDebugMessage() {
        return this.debugMessage.toString();
    }

    public String getInfoMessage() {
        return this.infoMessage.toString();
    }
    public String getWarningMessage() {
        return this.warningMessage.toString();
    }

    public boolean debugMessageContains( final String value ) {
        return this.debugMessage.toString().contains( value );
    }
    public boolean infoMessageContains( final String value ) {
        return this.infoMessage.toString().contains( value );
    }

    public boolean warningMessageContains( final String value ) {
        return this.warningMessage.toString().contains( value );
    }
}
