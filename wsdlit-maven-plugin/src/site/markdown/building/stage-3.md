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

Deployment of the documentation
===============================

Documentation deployment is the process of uploading the generated documentation to a `webdav` server,
choice determined by the support of a server we use at [EMPREGO](https://emprego.xunta.gal) for this purpose,
which allows us to upload the documentation files, site, javadoc... generated during the building processes.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Deployment settings
Deployment is configured using the following `wsdlit` properties:

| Property | Description |
|----------------------------------------|------ -------------------------------------------------- -------------------------------------------------- --------|
| `wsdlit.deploymentRepository` | Documentation deployment repository. |
| `wsdlit.altSnapshotDeploymentRepository` | Alternative deployment repository for snapshot versions. It takes precedence over `wsdlit.deploymentRepository`. |
| `wsdlit.altReleaseDeploymentRepository` | Alternate deployment repository for final releases. It takes precedence over `wsdlit.deploymentRepository`. |

## The format of the deployment repository
The above properties follow the following format: `id::url`.
The choice of format obeys only the criterion of being compatible with the format of the equivalent properties (with the logical caveats) of `maven-deploy.plugin`.

id
: The `id` is used to select the correct credentials from the `settings.xml` file.

url
: The location of the repository where the documentation will be deployed.

## Documentation version index: artifact-homepage
For each documentation artifact, four small files are also displayed,
which allow rapid versioning between artifact documentation versions.
These files are:

* `wsdlit.html`: to display a page with a very simple header, with the logo of the organization, and a list of versions.
* `wsdlit.css`: Styles for `wsdlit.html`.
* `wsdlit.js`: javascript required to interact with Nexus and update the documentation page after selecting a version.
* `wsdlit.png`: the logo of the organization.

### How to personalize your content
The theme used to render the generated documentation is the one that contains these files,
in its root, under the directory named `artifact-homepage`.
If another mechanism is preferred, simply replace its content.

## Command line

### How to start the deployment of the documentation
Just run:

```shell
mvn -f target/generated-sources/maven/pom.xml -Pwsdlit-build-enabled \
     org.codehaus.mojo:wagon-maven-plugin:upload@wsdlit-upload
```

### How to avoid artifact-homepage deployment
You can prevent the deployment of files by adding the `wsdlit.upload.artifact-homepage.skip` property,
from the command line:

```shell
mvn -f target/generated-sources/maven/pom.xml -Pwsdlit-build-enabled \
     -Dwsdlit.upload.artifact-homepage.skip \
     org.codehaus.mojo:wagon-maven-plugin:upload@wsdlit-upload
```

### How to overwrite the value of certain properties
It is possible to overwrite the value of certain properties using system properties.
Below you will find a list of the name of the most useful properties in the deployment that can be overridden.

* `wsdlit.site.repository.id`
* `wsdlit.site.repository.provider`
* `wsdlit.site.repository.url`

To override any of them just set it as a user property on the command line, for example:

```shell
mvn -f target/generated-sources/maven/pom.xml -Pwsdlit-build-enabled \
     -Dwsdlit.site.repository.id=my-server-id \
     -Dwsdlit.site.repository.url=https://o.meu.servidor/deployment/url \
     org.codehaus.mojo:wagon-maven-plugin:upload@wsdlit-upload
```

## FAQ

### How to configure a protocol other than webdav
The deployment process is done using [Maven Wagon](https://maven.apache.org/wagon/index.html),
and the provider is set using the `wsdlit.site.repository.provider` property (`dav` by default),
but the desired [Wagon Provider](https://maven.apache.org/wagon/wagon-providers/index.html) needs to be configured.

Currently the deployment process is defined in `wsdlit-guide-parent/pom.xml`,
which by default only configures the dependencies for `webdav`,
assigning the value `dav` to the `wsdlit.site.repository.provider` property,
so you will need to modify this file to include the necessary dependencies.