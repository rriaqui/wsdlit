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

import org.eclipse.aether.version.Version;

/**
 * @author rriaqui
 * @since 1.0.0
 */
public class TestVersion
implements Version {
    private final String version;

    public TestVersion( final String version ) {
        this.version = version == null ? "" : version;
    }

    public int compareTo(Version o) {
        return version.compareTo(o.toString());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + version.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if  (this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final TestVersion other = ( TestVersion ) obj;
        if ( version.isEmpty() ) {
            return other.version.isEmpty();
        }
        return version.equals( other.version );
    }

    @Override
    public String toString() {
        return version;
    }
}
