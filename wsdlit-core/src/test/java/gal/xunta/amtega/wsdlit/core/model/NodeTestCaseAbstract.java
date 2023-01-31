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
 * @author rriaqui
 * @since 1.0.0
 */
public abstract class NodeTestCaseAbstract
extends ModelTestCaseAbstract {
    protected PartnerLinkTypeNode createPartnerLinkTypeNode() {
        final PartnerLinkTypeNode node = new PartnerLinkTypeNode();
        node.setDocumentation( String.format( DOCUMENTATION_TEMPLATE, PartnerLinkTypeNode.class.getSimpleName()) );
        node.setName( "ServicePartnerLinkType" );
        node.setPrefix( TNS_PREFIX );
        node.setNamespaceUri( TNS );
        node.add( createRoleNode() );
        return node;
    }

}
