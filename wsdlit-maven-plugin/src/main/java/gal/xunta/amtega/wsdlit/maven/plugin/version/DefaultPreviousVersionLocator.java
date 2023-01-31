package gal.xunta.amtega.wsdlit.maven.plugin.version;

/*-
 * #%L
 * AMTEGA WsdlIT Maven Plugin
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

import org.eclipse.aether.artifact.Artifact;

import java.util.Collections;
import java.util.List;

/**
 * Default locator of the previous version of the artifact that contains the service contract,
 * that searches for the previous production version, with no other business rules.
 *
 * <p>
 *      This default locator simply searches for the previous production version based on the artifact version
 * </p>
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class DefaultPreviousVersionLocator
implements PreviousArtifactLocator {
    @Override
    public List<Artifact> listOfPossibleArtifacts( final Artifact artifact ) {
        final String previousVersion = "[," + artifact.getVersion() + ")";
        return Collections.singletonList( artifact.setVersion( previousVersion ) );
    }

    @Override
    public boolean appliesTo( final Artifact artifact ) {
        return artifact != null;
    }
}
