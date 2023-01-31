package gal.xunta.amtega.wsdlit.membranesoa.diff;

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

import com.predic8.soamodel.Difference;
import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wsdl.diff.WsdlDiffGenerator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that obtains the differences between two specific versions of the same contract.
 *
 * @author rriaqui
 * @since 1.1.0
 */
public class DiffTool {
    private final WSDLParser parser = new WSDLParser();

    /**
     * Returns the list of differences between two specified versions of the same contract.
     *
     * @param oldVersionInputStream the specified old version of the contract.
     * @param newVersionInputStream the specified new version of the contract.
     * @return the list of differences.
     */
    public List<Difference> getDifferences( final InputStream oldVersionInputStream, final InputStream newVersionInputStream ) {
        final Definitions oldVersion = parser.parse( oldVersionInputStream );
        final Definitions newVersion = parser.parse( newVersionInputStream );
        final WsdlDiffGenerator diffGen = new WsdlDiffGenerator( oldVersion, newVersion );
        return diffGen.compare();
    }

    /**
     * Returns the essential list of differences between two versions of the same contract specified by their
     * {@code InputStream}.
     *
     * <p>
     *      A difference is considered non-essential when it does not contain information about the change,
     *      for example, when it is used as an indication of a grouping.
     * </p>
     *
     * @param oldVersionInputStream the specified old version of the contract.
     * @param newVersionInputStream the specified new version of the contract.
     * @return the essential list of differences.
     */
    public List<Difference> getEssentialDifferences( final InputStream oldVersionInputStream, final InputStream newVersionInputStream ) {
        final List<Difference> differences = getDifferences( oldVersionInputStream, newVersionInputStream );
        return getEssentialDifferences( differences );
    }

    /**
     * Returns the essential list of differences from the specified list of differences.
     *
     * <p>
     *      A difference is considered non-essential when it does not contain information about the change,
     *      for example, when it is used as an indication of a grouping.
     * </p>
     *
     * @param differences specified list of differences.
     * @return the essential list of differences.
     */
    public List<Difference> getEssentialDifferences(final List<Difference> differences ) {
        final List<Difference> simpleDifferences = new ArrayList<>();

        for( final Difference difference : differences ) {
            final List<Difference> children = difference.getDiffs();
            if ( children.isEmpty() ) {
                simpleDifferences.add( difference );
            } else {
                simpleDifferences.addAll( getEssentialDifferences( children ) );
            }
        }
        return simpleDifferences;
    }
}
