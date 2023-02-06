# wsdlit [![GitHub](https://img.shields.io/github/license/amtega/wsdlit?label=License&color=green)](https://spdx.org/licenses/EUPL-1.2.html) [![Readme in english](https://img.shields.io/badge/Readme%20in-english-blue)](https://github.com/amtega/wsdlit/blob/main/README.md)

**Estado dos fluxos de traballo**

[![Build](https://github.com/amtega/wsdlit/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/build.yml)
[![Reproducible Build](https://github.com/amtega/wsdlit/actions/workflows/reproducible-build.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/reproducible-build.yml)
[![Cross Build](https://github.com/amtega/wsdlit/actions/workflows/cross-build.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/cross-build.yml)
[![gh-pages Build](https://github.com/amtega/wsdlit/actions/workflows/gh-pages.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/gh-pages.yml)
[![Codecov Build](https://github.com/amtega/wsdlit/actions/workflows/codecov.yml/badge.svg?branch=main)](https://github.com/amtega/wsdlit/actions/workflows/codecov.yml)
[![Codecov](https://img.shields.io/codecov/c/github/amtega/wsdlit/branch/main?label=Coverage)](https://codecov.io/github/amtega/wsdlit/branch/main)

**Algunhas métricas relevantes (branch/main)**

[![Files](https://www.aschey.tech/tokei/github/amtega/wsdlit?label=Files&category=files)](https://github.com/amtega/wsdlit)
[![Lines of code](https://www.aschey.tech/tokei/github/amtega/wsdlit?label=Lines%20of%20code)](https://github.com/amtega/wsdlit)
[![Comments](https://www.aschey.tech/tokei/github/amtega/wsdlit?label=Comments&category=Comments)](https://github.com/amtega/wsdlit)
[![Code size](https://img.shields.io/github/languages/code-size/amtega/wsdlit.svg)](https://github.com/amtega/wsdlit)
[![Last commit](https://img.shields.io/github/last-commit/amtega/wsdlit.svg)](https://github.com/amtega/wsdlit/commits/main)
---

`wsdlit` é un proxecto que xera documentación en formato `HTML`, `PDF` `EPUB` e `MOBI`
a partires da documentación embebida  en arquivos `wsdl` (contratos de servizo),
á que se lle pode engadir outra documentación adicional coma exemplos das mensaxes,
documentación de seguridade...

A principal motivación trala creación deste proxecto,
foi a de proporcionar unha ferramenta que aliviase o esforzo que require escribir un documento de calidade,
evitando as incoherencias de estilo e contidos,
respectando a fidelidade ao contratos dos servizos
e dentro do proceso de entrega continua de Emprego:

Os desenvolvedores escribirán a documentación do servizo usando a sintaxe **AsciiDoctor** para enriquecer o aspecto visual final,
e `wsdlit` se encargará de crear o documento final seguindo sempre a mesma liña de estilo corporativo,
xerando un contido sempre actualizado e en liña co contrato do servizo.

## Licenza

Este código está baixo a [European Union Public License 1.2](https://spdx.org/licenses/EUPL-1.2.html).

Revise o contido do arquivo [NOTICE](https://github.com/amtega/wsdlit/blob/main/NOTICE) para as notificacións e atribucións requeridas.

## Características

`wsdlit` posúe as seguintes caracterísiticas relevantes:

* O produto final é a documentación xerada en varios formatos.
* Permite escribir a documentación en formato `AsciiDoctor`.
* A documentación do propio `wsdl` segue sendo facilmente lexible.
* Se utiliza como punto de inicio o propio arquivo `wsdl`, para crear a documetanción do servizo.
* Soporta o uso de temas, para configurar o aspecto visual dos documentos dunha organización.
* Permite agrupar múltiples servizos na mesma documentación.

## Documentación

Consulte a documentación do proxecto [neste enlace](https://amtega.github.io/wsdlit).

## Como construir este proxecto

Para construir este proxecto basta con executar unha construción estándar de Maven.

### Requisitos de construción

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

### Firmar os artefactos

Durante o desenvolvemento do plugin non é necesario firmar os artefactos.
A firma se reserva para o proceso de creación de releases.

O perfil `wsdlit-sign-artifacts` se activa cando a propiedade `gpg.passphraseServerId` ten valor.
Para asignar valor a esta propiedade, algunhas opcións son:

* Crear un `profile` coa propiedade: `gpg.passphraseServerId` no arquivo `${HOME}/.m2/settings.xml`.
* Asignar un valor á propiedade de usuario `gpg.passphraseServerId` na liña de comandos:
 `mvn clean install -Dgpg.passphraseServerId=my-gpg-serverId`,
  e configurar no arquivo `${HOME}/.m2/settings.xml` un `server` co valor axeitado na `passphrase`.

### Construción da documentación do proxecto

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

### Construción reproducible

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

### Actualización da licenza nos arquivos do proxecto

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

## Descarga

De momento non é posible descargar os artefactos xerados de ningún repositorio,
ata que rematemos coa configuración en GitHub e Apache Maven Central.
