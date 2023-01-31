package gal.xunta.amtega.wsdlit.core.diff;

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

import java.io.InputStream;
import java.util.List;

/**
 * Generator of differences between two versions of the same contract
 *
 * @author rriaqui
 * @since 2.0.0
 */
public interface DiffGenerator {
    /**
     * Returns the list of differences that exist between two versions of the same service contract.
     *
     * @param current the {@code InputStream} of the current (latest) version.
     * @param previous the {@code InputStream} of the previous version with which you want to compare.
     * @return the list of differences between both versions of the service contract.
     *
     * @since 2.0.0
     */
    List<DiffItem> getDifferences( InputStream current, InputStream previous );

    /**
     * Returns the list of essential differences that exist between two versions of the same service contract.
     *
     * @param current the {@code InputStream} of the current (latest) version.
     * @param previous the {@code InputStream} of the previous version with which you want to compare.
     * @return the list of essential differences between both versions of the service contract.
     *
     * @since 2.0.0
     */
    List<DiffItem> getEssentialDifferences( InputStream current, InputStream previous );
}
