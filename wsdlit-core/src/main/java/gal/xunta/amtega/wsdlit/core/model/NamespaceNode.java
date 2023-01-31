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

import gal.xunta.amtega.wsdlit.core.util.FormDefault;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;

import java.util.Locale;
import java.util.Objects;

/**
 * A class for modelling namespaces declared and used in the service.
  *
 * @author rriaqui
 * @since 1.0
 */
public class NamespaceNode
extends AbstractNode {
    private static final String UNQUALIFIED_FORM_DEFAULT = "unqualified";
    private static final String QUALIFIED_FORM_DEFAULT = "qualified";

    private String version = "";
    private FormDefault elementFormDefault = FormDefault.NONE;
    private FormDefault attributeFormDefault = FormDefault.NONE;

    @Override
    public boolean equals( final Object o ) {
        if ( this == o ) {
            return true;
        }
        if (
            o == null
                || this.getClass() != o.getClass()
                || ! super.equals( o )
        ) {
            return false;
        }
        final NamespaceNode that = ( NamespaceNode ) o;
        return
            version.equals( that.version )
                && elementFormDefault == that.elementFormDefault
                && attributeFormDefault == that.attributeFormDefault;
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), version, elementFormDefault, attributeFormDefault );
    }

    /**
     * Returns the normalized value of the {@code attributeFormDefault} in the service.
     *
     * @return the normalized value of the {@code attributeFormDefault} in the service.
     */
    public FormDefault getAttributeFormDefault() {
        return this.attributeFormDefault;
    }

    /**
     * Returns the normalized value of the {@code elementFormDefault} in the service.
     *
     * @return the normalized value of the {@code elementFormDefault} in the service.
     */
    public FormDefault getElementFormDefault() {
        return this.elementFormDefault;
    }

    /**
     * Returns the service version.
     *
     * @return the service version.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Returns the version of the service preceded by <b>v</b> preceding it if it is not empty,
     * and {@code ""} if the version is empty
     *
     * @return the version of the service preceded by <b>v</b> preceding it if it is not empty,
     *         and {@code ""} if the version is empty
     */
    public String getLongVersion() {
        if ( this.version.isEmpty() ) {
            return "";
        }
        return "v" + this.version;
    }

    /**
     * Sets the version of the service.
     *
     * @param version the version of the service.
     * @return a reference to this {@code NamespaceNode}.
     */
    public NamespaceNode setVersion( final String version ) {
        if ( version != null ) {
            this.version = version.trim();
        }
        return this;
    }

    /**
     * Sets the value of the {@code attributeFormDefault}.
     *
     * @param attributeFormDefault the value of the {@code attributeFormDefault}.
     */
    public void setAttributeFormDefault( final String attributeFormDefault ) {
        this.attributeFormDefault = FormDefault.from( formDefaultToBooleanAsString( attributeFormDefault ) );
    }

    /**
     * Sets the value of the {@code elementFormDefault}.
     *
     * @param elementFormDefault the value of the {@code elementFormDefault}.
     */
    public void setElementFormDefault( final String elementFormDefault ) {
        this.elementFormDefault = FormDefault.from( formDefaultToBooleanAsString( elementFormDefault ) );
    }

    /**
     * Returns {@code "true"} when the specified {@code formDefault = "qualified"},
     *         {@code "false"} when the specified {@code formDefault = "unqualified"}
     *         and {@code ""} otherwise.
     * The value of the specified formDefault is compared in insensitive case.
     *
     * @param formDefault the specified formDefault value to be tested.
     * @return {@code "true"} when the specified {@code formDefault = "qualified"},
     *         {@code "false"} when the specified {@code formDefault = "unqualified"}
     *         and {@code ""} otherwise.
     */
    public static String formDefaultToBooleanAsString( final String formDefault ) {
        final String normalized = StringUtil.normalize( formDefault ).toLowerCase( Locale.ROOT );
        if ( QUALIFIED_FORM_DEFAULT.equals( normalized ) ) {
            return "true";
        }
        if ( UNQUALIFIED_FORM_DEFAULT.equals( normalized ) ) {
            return "false";
        }
        return "";
    }
}
