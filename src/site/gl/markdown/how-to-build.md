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

# Como construir o proxecto

Para construir este proxecto basta con executar unha construción estándar de Maven.

## Requisitos de construción

Para construir `wsdlit` é necesario:

* Java 8.
* Maven [3.8.6, 4).
* Linux ou MacOS.

**NOTAS**
* A construción en Windows debería funcionar, pero os scripts presentes no directorio `bin` non soportan Windows.
* A construción firma os artefactos mediante o plugin `maven-pgp-plugin`.

### Perfís Maven

No arquivo `pom.xml` se definen varios perfiles,
que se activan unicamente en escenarios concretos,
para que o proceso de desenvolvemento sexa o máis áxil posible:

* `jacoco`: para xerar os arquivos de cobertura;
  se activa mediante `-Pjacoco`.
  Activado en:
  * `.github/workflows/codecov.yml`.
* `wsdlit-example-api-enabled`: para xerar a documentación de exemplo;
  se activa con `-Dwsdlit-example-api-enabled`.
  Activado en:
  * `.github/workflows/build.yml`.
* `wsdlit-license-update-file-header-enable`: actualiza a cabeceira da licenza nos arquivos;
  se activa manualmente mediante `-Pwsdlit-license-update-file-header-enable`.
  Activado en:
  * `bin/licenseupdate-file-header.sh`.

### Construción do código

Basta coa seguinte liña de comandos estándar Maven de construción:

*Construción sen tests de integración*

```shell
mvn clean package
```

*Construción con cobertura*

```shell
mvn clean package -Pjacoco
```

*Construción coa API de exemplo (módulo wsdlit-example-api)*

```
mvn clean install -Dwsdlit-example-api-enabled
```

## Firmar os artefactos

Durante o desenvolvemento do plugin non é necesario firmar os artefactos.
A firma se reserva para o proceso de creación de releases.

O perfil `wsdlit-sign-artifacts` se activa cando a propiedade `gpg.passphraseServerId` ten valor.
Para asignar valor a esta propiedade, algunhas opcións son:

* Crear un `profile` coa propiedade: `gpg.passphraseServerId` no arquivo `${HOME}/.m2/settings.xml`.
* Asignar un valor á propiedade de usuario `gpg.passphraseServerId` na liña de comandos:
  `mvn clean install -Dgpg.passphraseServerId=my-gpg-serverId`,
  e configurar no arquivo `${HOME}/.m2/settings.xml` un `server` co valor axeitado na `passphrase`.

## Construción da documentación do proxecto

`wsdlit` incorpora documentación que se subirá a GitHub (rama `gh-pages`),
á que se incorpora a documentación dun proxecto de exemplo como exemplo do produto.
Por este motivo, un `mvn site site:stage` non é suficiente para unha xeración completa,
e é preciso primeiro executar un `mvn install` e a continuación un `mvn site site:stage` e finalmente un `mvn post-site`,
que xera o directorio `target/gh-pages` co contido a subir a GitHub.

Para facilitar o mantemento, creouse o script `bin/gh-pages-create.sh`,
que se encarga do proceso de xeración da documentación.

```shell
bin/gh-pages-create.sh
```

ou

```shell
mvn install
mvn site site:stage
mvn post-site
```

Unha vez rematada a construción, se pode consultar a documentación en `target/gh-pages`.

## Construción reproducible

É posible verificar se o proxecto cumple co requisito de Construción reproducible descrito na documentación [Maven](https://maven.apache.org/guides/mini/guide-reproducible-builds.html).
O script que automatiza o proceso de comprobación é `bin/test-reproducible-build.sh`.

```shell
bin/test-reproducible-build.sh
```
Se o script remata correctamente, entón temos unha construción reproducible,
pero se remata cun erro deberase a unha das seguintes causas:

* Un erro de construción en `maven clean install`.
* Un erro na verificación ou xeración do informe de Reproducible build (`mvn clean verify artifact:compare`).
* A construción non é reproducible.

## Actualización da licenza nos arquivos do proxecto

Para inserir e actualizar os arquivos coa licenza do proxecto é necesario activar o perfil `wsdlit-license-update-file-header-enable`.
Este proceso debe formar parte do procedemento de liberación de versións.

```shell
bin/license-update-file-header.sh
```

O script soporta parametros,
polo que podemos executar un comando similar a:

```shell
bin/license-update-file-header.sh -DdryRun -Dlicense.verbose
```
