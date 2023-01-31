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

Stage 2: Documentation building
===============================

At this stage we are responsible for converting the sources of the documentation,
which we already have in `adoc` format,
to the desired formats,
building the Maven project generated in the previous step.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Stage requirements
* Linux.
* Maven 3.8.6 or higher.
* Java 8.
* Kindlegen 2.9, if you want to generate mobi files.
* The `kindlegen` binary must be found in the `PATH`.

**NOTE about Kindlegen**.
Amazon removed the download of this product from their website a while ago,
so we cannot provide a product download url.
If you do not have a downloaded copy of `kindlegen` set up the build as described in this documentation
to not generate the `mobi` format.

## Documentation conversion
This stage is initiated using the following command line:

```shell
mvn clean package -Pwsdlit-build-enabled -f target/generated-sources/maven/pom.xml
```

<blockquote class="warning">
     <p>It is essential to enable the `wsdlit-build-enabled` profile when generating the documentation at this stage.</p>
</blockquote>

## Skip or generate a format
It may be interesting at some point to force or skip the generation of a certain format from the command line.
For example,
before uploading the changes to a code management system, generating them in `PDF` or `EPUB` format will reveal possible unwanted defects.

We can force the generation of `HTML5` and `EPUB` formats from the command line,
regardless of the existing configuration in the `${HOME}/.m2/settings.xml` file:

```shell
mvn -f target/generated-sources/maven/pom.xml clean install -Pwsdlit-build-enabled \
-Dwsdlit.convert.html5.skip=true \
-Dwsdlit.convert.epub.skip=false \
-Dwsdlit.convert.mobi.skip=false \
-Dwsdlit.convert.pdf.skip=true
```

## Products obtained in this stage

### Intermediate products
They are the direct result of building `*.adoc` files in different formats.
They are not considered final products,
since there is no more relationship between them than their essence (to represent the documentation of an API),
and,
therefore,
they are disposable products not suitable for uploading to any documentation server.

These intermediates are generated in the `target/generated-sources/maven/target/wsdlit-build` directory,
which we will refer to in the following table by `target/wsdlit-build`,
that is to say,
the directory relative to the Maven project directory generated in [stage 1](./stage-1.md).

|Product|Directory/File|Description|
|--------|------------------|----------|
|html5|`target/wsdlit-build/html5`|The documentation in `HTML5` format.|
|epub|`target/wsdlit-buil/epub/${artifactId}.epub`|The documentation as an `epub` book.|
|mobi|`target/wsdlit-build/emobi/${artifactId}-${version}.mobi`|The documentation as a `mobi` book.|
|pdf|`target/wsdlit-build/pdf/${artifactId}-${version}.pdf`|The documentation as a `pdf` book.|

### The final product: The API
The API is the result of combining the intermediate products,
so that it is possible to consult the documentation through a browser and download the rest of the products.
It is precisely their contents that will be deployed in the documentation repositories.

We call this product final because it is what we really want to make available to consumers online (`HTML`),
in format suitable for printing (`PDF`) or suitable for reading in eReaders (`MOBI` or `EPUB`).

|Product|Directory/File| Description |
|--------|------------------|--------------------- -------------------------------------------------- -------------------------------------------------- ------------------------------------------|
|API|`target/wsdlit-guide`| The final product object of the generation of the documentation.<br/>Combines the intermediate products into an html5 version of the documentation from which it is possible to download them. |
|Index|`target/wsdlit-guide/wsdlit.html`| The documentation version index page. |
|Version|`target/wsdlit-guide/${version}`| The documentation of the version to be generated. |