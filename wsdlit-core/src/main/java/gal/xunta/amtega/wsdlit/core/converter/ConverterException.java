package gal.xunta.amtega.wsdlit.core.converter;

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
 * Base exception for conversion process.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class ConverterException
extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public ConverterException() {
        super();
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConverterException( final Exception cause ) {
        super( cause );
    }

    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized,
     * and may subsequently be initialized by a call to {@code Throwable.initCause(java.lang.Throwable)}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the {@code Throwable.getMessage()} method.
     */
    public ConverterException( final String message ) {
        super( message );
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@code Throwable.getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@code Throwable.getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConverterException( final String message, final Exception cause ) {
        super( message, cause );
    }
}
