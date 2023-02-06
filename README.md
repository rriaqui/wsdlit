# wsdlit [![GitHub](https://img.shields.io/github/license/amtega/wsdlit?label=License&color=green)](https://spdx.org/licenses/EUPL-1.2.html) [![Léame en galego](https://img.shields.io/badge/Léame%20en-galego-blue)](https://github.com/amtega/wsdlit/blob/main/README.gl.md)

**Workflow status**

[![Build](https://github.com/amtega/wsdlit/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/build.yml)
[![Reproducible Build](https://github.com/amtega/wsdlit/actions/workflows/reproducible-build.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/reproducible-build.yml)
[![Cross Build](https://github.com/amtega/wsdlit/actions/workflows/cross-build.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/cross-build.yml)
[![gh-pages Build](https://github.com/amtega/wsdlit/actions/workflows/gh-pages.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/gh-pages.yml)
[![Codecov Build](https://github.com/amtega/wsdlit/actions/workflows/codecov.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/codecov.yml)
[![Codecov](https://img.shields.io/codecov/c/github/amtega/wsdlit/branch/main?label=Coverage)](https://codecov.io/github/amtega/wsdlit/branch/main)

**Some relevant metrics (branch/main)**

[![Files](https://www.aschey.tech/tokei/github/amtega/wsdlit?label=Files&category=files)](https://github.com/amtega/wsdlit)
[![Lines of code](https://www.aschey.tech/tokei/github/amtega/wsdlit?label=Lines%20of%20code)](https://github.com/amtega/wsdlit)
[![Comments](https://www.aschey.tech/tokei/github/amtega/wsdlit?label=Comments&category=Comments)](https://github.com/amtega/wsdlit)
[![Code size](https://img.shields.io/github/languages/code-size/amtega/wsdlit.svg)](https://github.com/amtega/wsdlit)
[![Last commit](https://img.shields.io/github/last-commit/amtega/wsdlit.svg)](https://github.com/amtega/wsdlit/commits/main)
---

`wsdlit` is a project that creates documentation in `HTML`, `PDF`, `EPUB` and `MOBI` formats 
from the documentation embedded in `wsdl` files (service contracts),
to which other additional documentation can be added such as examples of messages,
security documentation,
endpoints...

The main motivation behind the creation of this project,
was to provide a tool that alleviated the effort required to write a quality document,
avoiding inconsistencies in style and content,
respecting the fidelity to the service contracts
and within the process of continuous delivery of Employment:

Developers will write service documentation using the **AsciiDoctor** syntax to enrich the final visual appearance,
and `wsdlit` will be responsible for creating the final document always following the same line of corporate style,
generating content that is always updated and in line with the service contract.

## License

This code is under the [European Union Public License 1.2](https://spdx.org/licenses/EUPL-1.2.html).

Review the contents of the [NOTICE](https://github.com/amtega/wsdlit/blob/main/NOTICE) file for the required notices and attributions.

## Features

`wsdlit` has the following relevant features:

* The final product is the documentation generated in various formats.* Supports the use of themes, with the aim of homogenizing the visual aspect of an organization.
* Allows writing documentation in `AsciiDoctor` format.
* The `wsdl` documentation itself remains easily readable.
* The `wsdl` file itself is used as a starting point, to create the service documentation.
* Supports the use of themes, to configure the visual appearance of an organization's documents.
* Allows grouping multiple services in the same documentation.

## Documentation

See the project documentation [at this link](https://amtega.github.io/wsdlit).

## How to build this project

To build this project just run a standard Maven build.

### Build requirements

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

### Sign the artifacts

During the development of the plugin it is not necessary to sign the artifacts.
The signature is reserved for the release creation process.

The `wsdlit-sign-artifacts` profile is enabled when the `gpg.passphraseServerId` property has a value.
To assign value to this property, some options are:

* Create a `profile` with the property: `gpg.passphraseServerId` in the file `${HOME}/.m2/settings.xml`.
* Assign a value to the user property `gpg.passphraseServerId` on the command line:
  `mvn clean install -Dgpg.passphraseServerId=my-gpg-serverId`,
  and configure in the `${HOME}/.m2/settings.xml` file a `server` with the appropriate value in the `passphrase`.

### Building of project documentation

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

### Reproducible building

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
so we can write a command similar to:

```shell
bin/license-update-file-header.sh -DdryRun -Dlicense.verbose
```

## Download

Currently it is not possible to download the generated artifacts from any repository,
until we are done with the configuration on GitHub and Apache Maven Central.
