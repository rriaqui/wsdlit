package gal.xunta.amtega.wsdlit.core.spi;

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

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * The {@code ServiceLoader} for {@code APIDocumentProvider}.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public final class APIDocumentServiceLoader {
    private final APIDocumentProvider defaultSPI;
    private final Map<String, APIDocumentProvider > providers;

    /**
     * Construct an instance of the {@code ServiceLoader} by retrieving the available providers.
     */
    public APIDocumentServiceLoader() {
        final ServiceLoader< APIDocumentProvider > localProviders = ServiceLoader.load( APIDocumentProvider.class );

        this.defaultSPI = localProviders.iterator().next();
        final Iterator< APIDocumentProvider > iterator = localProviders.iterator();
        this.providers = StreamSupport
            .stream( Spliterators.spliteratorUnknownSize( iterator, 0 ), false )
            .collect(
                Collectors.toMap( x -> x.getClass().getCanonicalName(), x -> x )
            )
        ;
    }

    /**
     * Gets the default implementation, the first one, returned by the {@code ServiceLoader}.
     *
     * @return the first {@code APIDocumentProvider} found in the {@code ServiceLoader}.
     */
    public APIDocumentProvider getDefaultProvider() {
        return this.defaultSPI;
    }

    /**
     * Returns the specified provider, if not found it returns the default provider.
     *
     * <p>
     *      The default provider is the one listed first in the list of providers.
     * </p>
     *
     * @param xmlSPI the name of the class that implements the interface.
     * @return the requested provider if it exists, or the default provider otherwise.
     */
    public APIDocumentProvider getProviderOrDefault( final String xmlSPI ) {
        return this.providers.getOrDefault( xmlSPI, this.defaultSPI );
    }
}
