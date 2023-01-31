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

import com.predic8.wsdl.Part;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.PartNode;
import gal.xunta.amtega.wsdlit.membranesoa.util.ConverterUtil;

/**
 * Converter from MembraneSOA's {@code Part} to wsdlit {@code PartNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class PartConverter
extends AbstractConverter<Part, PartNode> {
    /**
     * Constructs a {@code PartConverter} from a specific history context.
     *
     * @param historyContext the specified history context.
     */
    public PartConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public PartNode convert( final Part part ) {
        final PartNode pi = new PartNode();
        ConverterUtil.update( pi, part );

        if ( part.getType() != null ) {
            pi.setType( part.getTypePN().toString() );
            pi.setElement( ConverterManager.getInstance().convert( part.getType(), false ) );
            pi.setDefinedByElement( false );

        } else {
            pi.setType( part.getElementPN().toString() );
            pi.setElement( ConverterManager.getInstance().convert( part.getElement(), false ) );
            pi.setDefinedByElement( true );
        }

        return pi;
    }
}
