package gal.xunta.amtega.wsdlit.core.model;

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

/**
 * An object representing the {@code SEI} (Service, Endpoint and Interface).
 *
 * @author rriaqui
 * @since 1.0
 */
public class SEI {
    private final String service;
    private final String endpoint;
    private final String interfaze;

    /**
     * Constructs a {@code SEI} with empty values.
     */
    public SEI() {
        this.service = "";
        this.endpoint = "";
        this.interfaze = "";
    }

    /**
     * Constructs a {@code SEI} with the specified values.
     *
     * @param interfaze the specified valur for the interface component.
     * @param endpoint the specified valur for the endpoint interface..
     * @param service the specified valur for the service component.
     */
    public SEI( final String interfaze, final String endpoint, final String service ) {
        this.interfaze = interfaze;
        this.endpoint = endpoint;
        this.service = service;
    }

    /**
     * Returns the {@code endpoint} value of this {@code SEI}.
     *
     * @return the {@code endpoint} value of this {@code SEI}.
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Returns the {@code interface} value of this {@code SEI}.
     *
     * @return the {@code interface} value of this {@code SEI}.
     */
    public String getInterface() {
        return interfaze;
    }

    /**
     * Returns the {@code service} value of this {@code SEI}.
     *
     * @return the {@code service} value of this {@code SEI}.
     */
    public String getService() {
        return service;
    }
}
