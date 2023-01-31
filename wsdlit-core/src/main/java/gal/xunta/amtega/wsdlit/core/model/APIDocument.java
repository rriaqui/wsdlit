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

import gal.xunta.amtega.wsdlit.core.sort.SortAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Class that bundles a list of {@code ServiceNode} into the API concept,
 * providing methods to set API-specific values such as title or version. *
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class APIDocument {
    /*
     * The API title.
     */
    private String title;

    /*
     * The API version.
     */
    private String version;

    /*
     * API build date.
     */
    private Date date = new Date();

    /*
     * Sorted list of API services.
     */
    private List< ServiceNode > services;

    /*
     * Unsorted list of API services.
     */
    private final List< ServiceNode > unsortedServices;

    /**
     * Constructs an {@code APIDocument} from the specified unsorted list of API services.
     *
     * @param unsortedServices the unsorted list of API services.
     */
    public APIDocument( final List< ServiceNode > unsortedServices ) {
        this.services = new ArrayList<>( unsortedServices );
        this.unsortedServices = Collections.unmodifiableList( unsortedServices );
    }

    /**
     * Returns the API build date.
     *
     * @return the API build date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the sorted list of API services.
     *
     * @return the sorted list of API services.
     */
    public List< ServiceNode > getServices() {
        return this.services;
    }

    /**
     * Returns the title of the API.
     *
     * @return the title of the API.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the API version.
     *
     * @return the API version.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the API build date.
     *
     * @param date the API build date.
     * @return a reference to this {@code APIDocument}.
     */
    public APIDocument setDate( final Date date ) {
        this.date = date;
        return this;
    }

    /**
     * Sets the API title.
     *
     * @param title the API title.
     * @return a reference to this {@code APIDocument}.
     */
    public APIDocument setTitle( final String title ) {
        this.title = title;
        return this;
    }

    /**
     * Sets the API version.
     *
     * @param version the API version.
     * @return a reference to this {@code APIDocument}.
     */
    public APIDocument setVersion( final String version ) {
        this.version = version;
        return this;
    }

    /**
     * Sorts the API services with the specified sort algorithm.
     *
     * @param algorithm the sort algorithm.
     */
    public synchronized void sort( final SortAlgorithms algorithm ) {
        this.services = new ArrayList<>( this.unsortedServices );
        if ( algorithm == SortAlgorithms.SORT_BY_NAME ) {
            Collections.sort( services );
        }
        for( final ServiceNode service : this.unsortedServices ) {
            service.sort( algorithm );
        }
    }
}
