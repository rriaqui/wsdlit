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

Despregue da documentación
==========================

O despregue da documentación é o proceso consistente en subir a documentación xerada a un servidor `webdav`,
elección determinada polo soporte dun servidor que usamos en [EMPREGO](https://emprego.xunta.gal) con este fin,
que nos permite subir os arquivos de documentación, site, javadoc... xerados durante os procesos de construción.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Configuración do despregue
O despregue se configura mediante as seguintes propiedades de `wsdlit`:

| Propiedade                               | Descrición                                                                                                       |
|------------------------------------------|------------------------------------------------------------------------------------------------------------------|
| `wsdlit.deploymentRepository`            | Repositorio de despregue de documentación.                                                                       |
| `wsdlit.altSnapshotDeploymentRepository` | Repositorio alternativo de despregue para versións snapshot. Ten prioridade sobre `wsdlit.deploymentRepository`. |
| `wsdlit.altReleaseDeploymentRepository`  | Repositorio alternativo de despregue para versións finais. Ten prioridade sobre `wsdlit.deploymentRepository`.   |

## O formato do repositorio de despregue
As propiedades anteriores seguen o seguinte formato: `id::url`.
A elección do formato obedece unicamente ao criterio de ser compatible co formato das propiedades equivalentes (coas lóxicas salvedades) de `maven-deploy.plugin`.

id
: O `id` se usa para seleccionar as credenciais correctas dende o arquivo `settings.xml`.

url
: A localización do repositorio onde se despregará a documentación.

## Índice de versións da documentación: artifact-homepage
Para cada artefacto de documentación se despregan, ademáis, catro pequenos arquivos,
que permiten o cambio de versión rápida entre as versións da documentación do artefacto.
Estes arquivos son:

* `wsdlit.html`: para amosar unha páxina cunha cabeceira moi simple, co logo da organización, e unha lista de versións.
* `wsdlit.css`: estilos para `wsdlit.html`.
* `wsdlit.js`: javascript necesario para interactuar con Nexus e actualizar a páxina da documentación tras seleccionar unha versión.
* `wsdlit.png`: o logo da organización.

### Como persoalizar o seu contido
O tema que se emprega para renderizar a documentación xerada é o que contén estes arquivos,
no seu raiz, baixo o directorio de nome `artifact-homepage`.
Se se prefire outro mecanismo, basta con substituir o seu contido.

## Liña de comandos

### Como iniciar o despregue da documentación
Basta con executar:

```shell
mvn -f target/generated-sources/maven/pom.xml -Pwsdlit-build-enabled \
    org.codehaus.mojo:wagon-maven-plugin:upload@wsdlit-upload
```

### Como evitar o despregue de artifact-homepage
Se pode evitar o despregue dos arquivos engadindo a propiedade `wsdlit.upload.artifact-homepage.skip`,
dende a liña de comandos:

```shell
mvn -f target/generated-sources/maven/pom.xml -Pwsdlit-build-enabled \
    -Dwsdlit.upload.artifact-homepage.skip \
    org.codehaus.mojo:wagon-maven-plugin:upload@wsdlit-upload
```

### Como sobrescribir o valor de certas propiedades
É posible sobrescribir o valor de certas propiedades mediante propiedades de sistema.
A continuación atopará unha lista do nome das propiedades de maior utilidade no despregue que se poden sobrescribir.

* `wsdlit.site.repository.id`
* `wsdlit.site.repository.provider`
* `wsdlit.site.repository.url`

Para sobrescribir calquera delas basta con definila como propiedade de usuario na liña de comandos, por exemplo:

```shell
mvn -f target/generated-sources/maven/pom.xml -Pwsdlit-build-enabled \
    -Dwsdlit.site.repository.id=o-meu-server-id \
    -Dwsdlit.site.repository.url=https://o.meu.servidor/deployment/url \
    org.codehaus.mojo:wagon-maven-plugin:upload@wsdlit-upload
```

## FAQ

### Como configurar un protocolo distinto de webdav
O proceso de despregue se realiza mediante [Maven Wagon](https://maven.apache.org/wagon/index.html),
e o provedor se configura mediante a propiedade `wsdlit.site.repository.provider` (`dav` por defecto),
pero necesita estar configurado o [Wagon Provider](https://maven.apache.org/wagon/wagon-providers/index.html) que desexe.

Actualmente o proceso de despregue se define en `wsdlit-guide-parent/pom.xml`,
que por defecto so configura as dependencias para `webdav`,
asignando o valor `dav` á propiedade `wsdlit.site.repository.provider`,
polo que terá que modificar este arquivo para incluir as dependencias necesarias.
