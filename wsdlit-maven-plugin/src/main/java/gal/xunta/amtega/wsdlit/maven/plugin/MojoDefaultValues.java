package gal.xunta.amtega.wsdlit.maven.plugin;

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

/**
 * Default values for the parameters of the different mojos.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class MojoDefaultValues {
    /**
     * Directory used by the plugin to read template files used for generating the Asciidoctor source files.
     */
    public static final String TEMPLATES_DIR_SOURCE = "/templates/default";
    public static final String MAVEN_PROJECT_POM_TARGET = "pom.xml";
    public static final String MAVEN_PROJECT_POM_SOURCE = "mavenProject/pom.xml";
    public static final String MAVEN_PROJECT_ASSEMBLY_XML_SOURCE = "mavenProject/assembly/wsdlit-guide.xml";
    public static final String MAVEN_PROJECT_ASSEMBLY_XML_TARGET = "src/assembly/wsdlit-guide.xml";

    public static final String SOURCE_DIRECTORY_TARGET = "src/main/asciidoc";

    public static final String WSDL_DIRECTORY_SOURCE = "src/main/wsdlit";

    public static final String API_DOCUMENTO_PROVIDER = "gal.xunta.amtega.wsdlit.membranesoa.spi.MembraneSOAAPIDocumentProvider";

    private MojoDefaultValues() {}
}
