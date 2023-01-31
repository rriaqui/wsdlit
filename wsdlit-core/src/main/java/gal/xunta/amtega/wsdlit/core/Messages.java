package gal.xunta.amtega.wsdlit.core;

/*-
 * #%L
 * wsdlit-core
 * %%
 * Copyright (C) 2021 - 2023 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
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

import gal.xunta.amtega.wsdlit.core.i18n.I18n;

/**
 * Class for managing locale messages of wsdlit-core.
 *
 * @author rriaqui
 * @since 2.0.0
 */
public final class Messages {
    public static final String MISSING_DIRECTORY_AND_OR_FILE_PARAMETER_ERROR_KEY = "missing-directory-and-or-file-parameter-error";
    public static final String WSDL_PARSER_SPI_PROVIDER_NOT_FOUND_ERROR_KEY = "wsdl-parser-spi-provider-not-found-error";

    private Messages() {}

    public static final I18n I18N = new I18n( Messages.class );
}
