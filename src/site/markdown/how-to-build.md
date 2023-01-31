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

# How to build the project
To build this project just run a standard Maven build.
## Build requirements

To build `wsdlit` it is necessary:

* Java 8.
* Maven [3.8.6, 4).
* Linux or MacOS.

**NOTES**

* Building on Windows should work, but the scripts present in the `bin` directory do not support Windows.

### Maven Profiles

Several profiles are defined in the `pom.xml` file,
which are enabled in specific scenarios,
to make the development process as fast as possible:

* `jacoco`: to generate the coverage files;
  is enabled using `-Pjacoco`.
  Enabled on:
  * `.github/workflows/codecov.yml`.
* `wsdlit-example-api-enabled`: to generate the example documentation;
  is enabled with `-Dwsdlit-example-api-enabled`.
  Enabled on:
  * `.github/workflows/build.yml`.
* `wsdlit-license-update-file-header-enable`: update license file headers;
  is enabled manually using `-Pwsdlit-license-update-file-header-enable`.
  Enabled on:
  * `bin/licenseupdate-file-header.sh`.

### Building

Just use the following standard Maven build command line:

*Build without Integration tests*

```shell
mvn clean package
```

*Build with coverage*

```shell
mvn clean package -Pjacoco
```

*Build with example API (wsdlit-example-api module)*

```
mvn clean install -Dwsdlit-example-api-enabled
```

## Sign the artifacts

During the development of the plugin it is not necessary to sign the artifacts.
The signature is reserved for the release creation process.

The `wsdlit-sign-artifacts` profile is enabled when the `gpg.passphraseServerId` property has a value.
To assign value to this property, some options are:

* Create a `profile` with the property: `gpg.passphraseServerId` in the file `${HOME}/.m2/settings.xml`.
* Assign a value to the user property `gpg.passphraseServerId` on the command line:
  `mvn clean install -Dgpg.passphraseServerId=my-gpg-serverId`,
  and configure in the `${HOME}/.m2/settings.xml` file a `server` with the appropriate value in the `passphrase`.

## Building of project documentation

`wsdlit` embeds documentation to be uploaded to GitHub (branch `gh-pages`),
to which the documentation of an example project is incorporated as an example of the product.
For this reason, an `mvn site site:stage` is not sufficient for a complete generation,
and it is necessary to first execute an `mvn install` and then a `mvn site site:stage` and finally a `mvn post-site`,
which generates the `target/gh-pages` directory with the content to be uploaded to GitHub.

For ease of maintenance, the `bin/gh-pages-create.sh` script has been created,
which is in charge of the documentation generation process.

```shell
bin/gh-pages-create.sh
```

or

```shell
mvn install
mvn site site:stage
mvn post-site
```

Once the build is complete, the documentation can be consulted in `target/gh-pages`.

## Reproducible building

It is possible to verify that the project meets the Reproducible Build requirement described in the [Maven](https://maven.apache.org/guides/mini/guide-reproducible-builds.html) documentation.
The script that automates the checking process is `bin/test-reproducible-build.sh`.

```shell
bin/test-reproducible-build.sh
```
If the script completes successfully, then we have a reproducible build,
but if it ends with an error it will be due to one of the following causes:

* A build error in `maven clean install`.
* An error in verifying or generating the Reproducible build report (`mvn clean verify artifact:compare`).
* Building is not reproducible.

### Updating the license in the project files

To insert and update the files with the project license it is necessary to activate the `wsdlit-license-update-file-header-enable` profile.
This process should be part of the release procedure.

```shell
bin/license-update-file-header.sh
```

The script supports parameters,
so we can execute a command similar to:

```shell
bin/license-update-file-header.sh -DdryRun -Dlicense.verbose
```
