package gal.xunta.amtega.wsdlit.maven.plugin.util.maven;

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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;


/**
 * @author rriaqui
 * @since 1.0.0
 */
@RunWith( Parameterized.class )
public class VersionTypeAppliesToUnknownTestCase
extends VersionTypeAppliesToAbstractTestCase {
    public VersionTypeAppliesToUnknownTestCase(final Artifact artifact, final boolean appliesToExpected ) {
        super( artifact, appliesToExpected, VersionType.UNKNOWN );
    }

    @Parameters
    public static Iterable< Object[] > data() {
        return Arrays.asList(
                new Object[][] {
                        { DEVELOPMENT_ARTIFACT, true },
                        { ALFA_ARTIFACT, true },
                        { BETA_ARTIFACT, true },
                        { RELEASE_CANDIDATE_ARTIFACT, true },
                        { PRODUCTION_ARTIFACT, true },
                        { PRODUCTION_ARTIFACT_2, true },
                        { PRODUCTION_ARTIFACT_3, true },
                        { UNKNOWN_ARTIFACT, true }
                }
        );
    }

}
