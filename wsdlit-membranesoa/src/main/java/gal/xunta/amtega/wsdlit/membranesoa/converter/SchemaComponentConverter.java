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

import com.predic8.schema.*;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.ElementNode;

/**
 * Converter from MembraneSOA's {@code SchemaComponent} to wsdlit {@code ElementNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class SchemaComponentConverter
extends AbstractConverter<SchemaComponent, ElementNode> {
    /**
     * Constructs a {@code SchemaComponentConverter} from a specific history context.
     *
     * @param historyContext the specified history context.
     */
    public SchemaComponentConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public ElementNode convert( final SchemaComponent sc ) {
        if ( sc instanceof ModelGroup ) {
            return ConverterManager.getInstance().convert( ( ModelGroup) sc );
        } else if ( sc instanceof ComplexType ) {
            return ConverterManager.getInstance().convert( ( ComplexType ) sc );
        } else if ( sc instanceof SimpleType ) {
            return ConverterManager.getInstance().convert( ( SimpleType ) sc );
        } else if ( sc instanceof Element ) {
            return ConverterManager.getInstance().convert( ( Element ) sc );
        } else if ( sc instanceof Group ) {
            return ConverterManager.getInstance().convert( ( Group ) sc );
        } else if ( sc instanceof GroupRef ) {
            return ConverterManager.getInstance().convert( ( GroupRef ) sc );
        } else {
            throw new IllegalArgumentException( sc.getName() );
        }
    }
}
