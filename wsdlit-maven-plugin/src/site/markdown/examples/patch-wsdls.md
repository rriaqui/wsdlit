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

# Patch remote contracts with embedded contracts
This scenario was designed as a complement to the development procedure that is usually carried out,
to simplify the task of documenting services,
avoiding unnecessary, erroneous or incomplete documentation commits,
functioning as a patching tool that allows you to build the corrected documentation without the need to upload the changes
to the code repository of the project in question.

It is possible to mix the download of artifacts containing the contracts to be documented and,
after that,
patch some contracts to apply changes without the need to confirm the changes in the project no
source code manager (**SCM**),
build it,
and deploy the artifact to the Maven repository.

This functionality combines two modes of operation of the plugin,
the downloading of artifacts containing the contracts and the use of embedded contracts,
which can work independently of each other.

## Plugin configuration

## How to avoid patching
As we indicated above,
patching functionality exists as a complement to development,
to avoid unnecessary, erroneous or incomplete commits,
but once the changes are committed to the **SCM** patching is neither necessary nor desirable:

* The only functionality of patches is reserved for development time.
* The contract documentation provided to third-party developers will be generated exclusively from their content,
  so that the developer can dispense with consulting the generated documentation and limit himself to the content of the contract files themselves (with certain caveats collected in the form of additional documentation),
  if he so wishes.

Explicit setting of the patching property is not required in Maven projects.
By default patching is enabled to fit the local development scenario,
and to fit the build scenario in a continuous integration tool,
where the contract documentation is already considered finished and the patches are no longer required,
the configuration in the continuous integration tool disables this feature via an additional property.

We can disable patching using the [patchSkip](./generate-resources-mojo.html#patchSkip) property.

When you need to disable patching,
build the documentation using:

```shell
mvn ... -Dwsdlit.patch.skip=true
```

**NOTE**
In the [EMPREGO](https://emprego.xunta.gal) continuous building system,
contract patching is disabled for all projects.