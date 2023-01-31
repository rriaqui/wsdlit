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

The process of documentation building
=====================================

The building process is carried out in two distinct stages,
in which the first one corresponds to a real physical project written by us,
and the second stage corresponds to the build of the project that automatically generates `wsdlit`.

To these two stages of building is added a third dedicated to the deployment of the generated documentation to a `webdav` server.

* [Stage 1: Contract processing](stage-1.md)
* [Stage 2: Documentation building](stage-2.md)
* [Stage 3: Deployment of documentation](stage-3.md)

## Setting up the development environment

### Rapid documentation building
We think it is also interesting to know how we can [configure](./configuration.md) our development environment
to achieve a build as agile as possible during the writing of the documentation,
disabling by default the build of certain formats of the documentation.