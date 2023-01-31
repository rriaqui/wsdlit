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

# AMTEGA WsdlIT Maven Plugin
The **WsdlIT** plugin is used to generate a Maven project with the contract documentation sources of one or more web services.
These sources are generated in `doc` format ([Asciidoctor](https://asciidoctor.org)),
which are converted to final formats such as **HTML5**, **EPUB**, **MOBI** or **PDF**,
thanks to the generated Maven project inheriting from `wsdlit-guide-parent`,
a pom generated as part of the **wsdlit** project,
which is reused in generation through inheritance because it contains a rather complex configuration,
and which uses the Maven plugin [asciidoctor-maven-plugin](https://docs.asciidoctor.org/maven-tools/latest/}asciidoctor-maven-plugin) as a building tool.

The main feature of `wsdlit` is that it is responsible for describing the contract in detail based on its own documentation,
detailing `ports`, `operations`, `messages` and `faults`,
starting from the documentation itself contained in the `.wsdls` and `.xsd` files.

The internal structure of the messages is exhaustively detailed,
automatically expanding `elements`, `complexTypes` and `ComplexContent`,
which eliminates the possibility of human error in your documentation.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Overview of goals
The wsdlit plugin contains several goals, linked to certain phases of the [Maven](https://maven.apache.org) life cycle,
and therefore executed automatically during their respective phases.

| Goal                                                                | Phase                | Description                                                                                                                                             |
|---------------------------------------------------------------------|----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| [wsdlit:descriptor-download](./descriptor-download-mojo.html)       | `generate-sources`   | Not used yet.                                                                                                                                           |
| [wsdlit:artifact-download](./artifact-download-mojo.html)           | `process-sources`    | Download the artifacts that contain the contracts you want to document.                                                                                 |
| [wsdlit:generate-resources](./generate-resources-mojo.html)         | `generate-resources` | Patch the documentation generation files obtained during the artifact download from the [wsdlit:artifact-download](./artifact-download-mojo.html) goal. |
| [wsdlit:generate-sources](./generate-sources-mojo.html)             | `process-resources`  | Create the sources of the documentation (`.adoc` files) based on the service contracts.                                                                 |
| [wsdlit:generate-maven-project](./generate-maven-project-mojo.html) | `compile`            | Generate the Maven project that will be used to build the documentation using AsciiDoctor.                                                              |

## Usage

General instructions on how to use the plugin can be found on the [usage page](./usage.html).
Some more specific use cases are described in the examples below.

In case you miss any functionality or discover a bug,
you can complete a feature request or bug report in the incident management application.

When creating a bug report please provide as complete a description of the bug as possible.
To fix bugs it is crucial that developers can reproduce their problem,
so attach to the issue full debug logs, and small demo projects  to reproduce it.
Of course,
we also support patches.
Contributors can clone the project from our code repository.

## Examples
To provide you with a better understanding of some use cases of the **WsdlIT** plugin,
we recommend taking a look at the following examples:

* [Document remote contracts](./examples/remote-wsdls.html).
* [Document embedded contracts](./examples/embedded-wsdls.html).
* [Patch remote contracts with embedded contracts](./examples/patch-wsdls.html).