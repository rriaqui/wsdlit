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
 * An object that neutrally models the different elements existing in a wsdl document.
 *
 * <p>
 *      Modeling is as simple as possible,
 *      with the aim of isolating most of {@code wsdlit}
 *      of the particularities of the xml classes that parse the wsdl and xsd documents.
 * </p>
 *
 * @author rriaqui
 * @since 1.0
 */
public interface Node {
    /**
     * Returns the documentation.
     *
     * @return the documentation.
     */
    String getDocumentation();

    /**
     * Returns the name preceded by the prefix (when it has a value),
     * or the name when the prefix is blank.
     * <p>
     *      When the value of the name is {@code name} and the value of the prefix is {@code prefix},
     *      the value of the fullName is {@code prefix:name}.
     * </p>
     *
     * <p>
     *      When the value of the name is {@code name} and the value of the prefix is blank,
     *      the value of the fullName is {@code name}.
     * </p>
     *
     *
     * @return the name preceded by the prefix (when it has a value),
     *         or the name when the prefix is blank.
     */
    String getFullName();

    /**
     * Returns the name.
     *
     * @return the name.
     */
    String getName();

    /**
     * Returns the namespace.
     *
     * @return the namespace.
     */
    String getNamespaceUri();

    /**
     * Returns the prefix of the namespace.
     *
     * @return the prefix of the namespace.
     */
    String getPrefix();

    /**
     * The qualified name.
     *
     * @return the qualified name.
     */
    String getQname();

    /**
     * Returns {@code true} if this node is part of cyclic structure.
     *
     * @return {@code true} if this node is part of cyclic structure.
     */
    boolean isCyclicReference();

    /**
     * Sets {@code cyclicReference} active when {@code true} .
     *
     * @param cyclicReference {@code cyclicReference} active when {@code true} .
     */
    void setCyclicReference( final boolean cyclicReference );

    /**
     * Sets the documentation.
     *
     * @param documentation the documentation.
     */
    void setDocumentation( String documentation );

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    void setName( String name );

    /**
     * Sets the prefix.
     *
     * @param prefix the prefix.
     */
    void setPrefix( String prefix );

    /**
     * Sets the namespace.
     *
     * @param targetNamespace the namespace.
     */
    void setNamespaceUri(String targetNamespace );
}
