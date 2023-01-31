<!--
  #%L
  AMTEGA WsdlIT Maven Plugin
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

Stage 1: Contract processing
============================

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Contract processing
The first stage is to process the service contracts and create a Maven project
with the documentation sources generated from the service contracts.
This new Maven project will allow you to get the documentation in the final formats `html5`, `epub`, `mobi` and/or `pdf`.

During this stage,
service contracts are analyzed to create a hierarchical structure of their content,
detailing the messages by expanding the elements that make them up and generating the different `.adoc` files,
sources of the documentation that will be used in the next stage in the build of the desired formats.

This stage is initiated using the following command line:

```shell
mvn clean package
```

## Products obtained in this stage
The result of this process will be found in the `target/generated-sources/maven` directory.
It is a Maven project that contains the documentation of web services in `adoc` format.