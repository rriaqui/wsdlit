<!--
  #%L
  wsdlit
  %%
  Copyright (C) 2021 - 2022 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
  %%
  This file is part of "wsdlit".
  
  "wsdlit" is free software: you can redistribute it and/or modify
  it under the terms of:
  European Union Public License, either Version 1.2 or – as soon
  they will be approved by the European Commission - subsequent versions of
  the EUPL;
  
  "wsdlit" is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  European Union Public License for more details.
  
  You may obtain a copy of tce European Union Public Licence at:
  http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
  #L%
  -->


# wsdlit
The `wsdlit` project was created with the aim of simplifying the conversion of the web service contracts described in
[Web Services Description Language (WSDL) version 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315),
in documents we call **Technical Instructions**,
by means of a Maven plugin that simplifies the steps to follow until obtaining the documentation in the final formats.

The **Technical Instructions** are documents that detail the ports,
operations and messages of the services,
taking advantage of existing documentation elements in XML (`xsd:annotation` and `xsd:documentation`).
`wsdlit` facilitates documentation work because:

* Automate the process of building and deploying documentation in various formats.
* Reduces the manual maintenance required when a contract is changed.
  Most of the documentation is written in the service contract file itself,
  which can be completed with other information written in independent files.
* Reduces the possibility of errors in the documentation, because `wsdlit`:
  * Generates a graphic image that hierarchically represents the structure of the messages.
  * Don't forget to document a port, operation or message, and don't forget to delete it when it disappears from the contract.
* Allows documentation to be written in a markup language, **Asciidoctor**, similar to **Markdown**:
  The documentation of the contract itself is readable even if read directly from the `wsdl` file.

Please see the plugin documentation [wsdlit-maven-plugin](wsdlit-maven-plugin/index.html) for more information.

## See an example of the generated documentation
The [wsdlit-example-api](wsdlit-example-api/index.html) module is an example project,
which generates very simple documentation that can be consulted [at this link](wsdlit-example-api/example/index.html).

## Limitations of the present version of wsdlit
By design, wsdlit currently has the following limitations:

* `wsdlit` supports contracts that follow the specification
  [Web Services Description Language version 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315).<br/>
  In the future we will add support for
  [Web Services Description Language version 2.0](http://www.w3.org/TR/wsdl20-primer/).
* `wsdlit` ignores data type attributes.<br/>
  By design decision, information recorded in attributes is ignored:
  all message information is exchanged as element values.
  As a consequence, `wsdlit` will not incorporate attribute handling.
* The formats in which the final documentation is generated are: `html5`, `epub`, `mobi` and `pdf`.<br/>
  As possible, we will incorporate other formats in the future.