package gal.xunta.amtega.wsdlit.membranesoa.converter;

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

import gal.xunta.amtega.wsdlit.core.converter.AbstractConversionContext;
import gal.xunta.amtega.wsdlit.core.converter.Converter;
import gal.xunta.amtega.wsdlit.core.converter.processor.PostConversionProcessor;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.history.VoidHistoryContext;
import gal.xunta.amtega.wsdlit.core.model.NamespacePrefixCache;
import gal.xunta.amtega.wsdlit.core.util.StringUtil;
import groovy.namespace.QName;

/**
 * Abstract implementation of a membraneSOA-based converter.
 * Takes care of transforming the content of a membraneSOA class to a wsdlit class.
 *
 * @param <S> membraneSOA class (source).
 * @param <T> class of the data model (target).
 *          *
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class AbstractConverter<S, T>
implements Converter<S, T> {
    private NamespacePrefixCache cache;
    protected PostConversionProcessor<? extends AbstractConversionContext<?>> postConversionProcessor = null;
    protected final HistoryContext historyContext;

    /**
     * Constructs an instance of {@code AbstractCoverter} from a {@code VoidHistoryContext}.
     */
    protected AbstractConverter() {
        this.historyContext = new VoidHistoryContext();
    }

    /**
     * Constructs an {@code AbstractCoverter} from a specific {@code HistoryContext}.
     *
     * @param historyContext the specified history context.
     */
    protected AbstractConverter( final HistoryContext historyContext ) {
        this.historyContext = historyContext;
    }

    @Override
    public T convert( final S item, final boolean ignoreHistory ) {
        throw new IllegalAccessError( "Método non soportado" );
    }

    @Override
    public NamespacePrefixCache getNamespacePrefixCache() {
        return cache;
    }

    @Override
    public void setNamespacePrefixCache( final NamespacePrefixCache cache ) {
        this.cache = cache;
    }

    @Override
    public String findPrefix( final String prefix, final String namespaceUri ) {
        return this.cache.findPrefix( prefix, namespaceUri );
    }

    /**
     * Sets the conversion processor that runs when a conversion is finished.
     *
     * @param postConversionProcessor the conversion postprocessor.
     */
    public void setPostConversionProcessor( final PostConversionProcessor<? extends AbstractConversionContext<?>> postConversionProcessor ) {
        this.postConversionProcessor = postConversionProcessor;
    }

    /**
     * Returns the type related to the specified qname rewriting the prefix if neccesery,
     * according to the prefix cache - namespaces.
     *
     * <p>
     *      During the process of converting the membrane model to the wsdlit model,
     *      it is possible that the same prefix name appears associated with different namespaces,
     *      so it is not possible to rely on the prefix name of the qname.
     * </p>
     *
     * @param qname the specified qname.
     * @return if the qname is null, the empty string, otherwise the type related to the specified qname.
     */
    public String getType( final QName qname ) {
        if ( qname == null ) {
            return StringUtil.EMPTY;
        }
        return this.findPrefix( qname.getPrefix(), qname.getNamespaceURI() ) + ":" + qname.getLocalPart();
    }
}
