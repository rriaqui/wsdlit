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

import com.predic8.wsdl.AbstractPortTypeMessage;
import com.predic8.wsdl.Message;
import com.predic8.wsdl.Part;
import gal.xunta.amtega.wsdlit.core.history.HistoryContext;
import gal.xunta.amtega.wsdlit.core.model.MessageNode;
import gal.xunta.amtega.wsdlit.core.model.PartNode;
import gal.xunta.amtega.wsdlit.membranesoa.util.ConverterUtil;

/**
 * Converter from MembraneSOA's {@code AbstractPortTypeMessage} to wsdlit {@code MessageNode}.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public class MessageConverter
extends AbstractConverter<AbstractPortTypeMessage, MessageNode> {
    /**
     * Constructs a {@code MessageConverter} from a specific history context.
     *
     * @param historyContext the specified history context.
     */
    public MessageConverter( final HistoryContext historyContext ) {
        super( historyContext );
    }

    @Override
    public MessageNode convert( final AbstractPortTypeMessage message ) {
        this.historyContext.clearHistory();
        if ( message == null ) {
            return null;
        }
        final MessageNode mi = new MessageNode();
        final Message m = message.getMessage();

        ConverterUtil.update( mi, message );
        for( final Part part : m.getParts() ) {
            final PartNode pi = ConverterManager.getInstance().convert( part );
            mi.add( pi );
        }

        return mi;
    }
}
