package gal.xunta.amtega.wsdlit.core.engine.api;

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
 * The class {@code ProcessException} and its subclasses are a form of Exception that indicates problems
 * processing the data model.
 *
 * @since 1.0.0
 * @author rriaqui
 */
public class ProcessException
extends Exception {
    /**
     * Construct a {@code ProcessException} with {@code null} as the error detail message.
     */
    public ProcessException() {
        super();
    }

    /**
     * Constructs a new {@code ProcessException} with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ProcessException( final Throwable cause ) {
        super( cause );
    }

    /**
     * Constructs a new {@code ProcessException} with the specified detail message.
     * The cause is not initialized,
     * and may subsequently be initialized by a call to {@code Throwable.initCause(java.lang.Throwable)}.
     *
     * @param message the detail message.
     *                The detail message is saved for later retrieval by the {@code Throwable.getMessage()} method.
     */
    public ProcessException( final String message ) {
        super( message );
    }

    /**
     * Constructs a new {@code ProcessException} with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@code Throwable.getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@code Throwable.getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ProcessException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
