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

# Uso

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Configuración do plugin WsdlIT
Configuración básica do plugin `wsdlit-maven-plugin`.

```xml
<project>
    ...
    <properties>
        <!--
            Declarando aquí a versión do plugin en vez de no propio plugin,
            podemos sobreescribila mediante unha propiedade de sistema de idéntico nome,
            -Dwsdlit.version=1.9.3, por exemplo.
        -->
        <wsdlit.version>${project.version}</wsdlit.version>
    </properties>
    ...
    <build>
        <plugins>
            <plugin>
                <groupId>${project.groupId}</groupId>
                <artifactId>${project.artifactId}</artifactId>
                <version>${wsdlit.version}</version>
                <configuration>
                    <!-- Engada aquí a configuración que necesite. -->
                </configuration>
            </plugin>
        </plugins>
    </build>
    ...
</project>
```

## Configuración básica da documentación
Neste exemplo veremos como configurar certos valores básicos de toda documentación,
como o título da documentación, o autor, a organización, o tema que se usará e as coordenadas do proxecto que se xerará.

```xml
<project>
    ...
    <properties>
        <wsdlit.version>${project.version}</wsdlit.version>
        <wsdlit.api.title><!-- O título da API. --></wsdlit.api.version>
        <wsdlit.api.author><!-- O autor principal da API. --></wsdlit.api.author>
        <wsdlit.api.organization><!-- A organización propietaria da API. --></wsdlit.api.organization>
        <wsdlit.api.copyright><!-- O copyright da API. --></wsdlit.api.copyright>
        <wsdlit.api.groupId><!-- O groupId do proxecto Maven que se xerará. --></wsdlit.api.groupId>
        <wsdlit.api.artifactId><!-- O artifactId do proxecto Maven que se xerará. --></wsdlit.api.artifactId>
        <wsdlit.api.version><!-- A version do proxecto Maven que se xerará e a versión da API. --></wsdlit.api.version>
        <wsdlit.api.theme><!-- As coordenadas Maven do tema a usar. --></wsdlit.api.theme>
        <wsdlit.releases.repository.id><!-- O identificador de repositorio releases en settings.xml onde se despregará a documentación xerada. --></wsdlit.releases.repository.id>
        <wsdlit.snapshots.repository.id><!-- O identificador do repositorio snapshots en settings.xml onde se despregará a documentación xerada. --></wsdlit.snapshosts.repository.id>
        <wsdlit.site.repository.url><!-- A url do servidor no que se despregará a documentación. --></wsdlit.site.repository.url>
    </properties>
    ...
    <build>
        <plugins>
            <plugin>
                <groupId>${project.groupId}</groupId>
                <artifactId>${project.artifactId}</artifactId>
                <version>${wsdlit.version}</version>
                <configuration>
                    ...
                    <title><!--- O título da API. --></title>
                    <author><!-- O autor principal da API. --></author>
                    <organization><!-- A organización propietaria da API. --></organization>
                    <copyright><!-- O copyright da API. --></copyright>
                    <groupId><!-- O groupId do proxecto Maven que se xerará. --></groupId>
                    <artifactId><!-- O artifactId do proxecto Maven que se xerará. --></artifactId>
                    <version><!-- A versión do proxecto maven que se xerará e a versión da API. --></version>
                    <theme><!-- As coordenadas Maven do tema a usar. --></theme>
                    <releasesRepositoryId><!-- O identificador de repositorio releases en settings.xml onde se despregará a documentación xerada. --></releasesRepositoryId>
                    <snapshotsRepositoryId><!-- O identificador de repositorio snapshots en settings.xml onde se despregará a documentación xerada. --></snapshotsRepositoryId>
                    <siteRepositoryUrl><!-- A url do servidor no que se despregará a documentación. --></siteRepositoryUrl>
                    ...
                </configuration>
            </plugin>
        </plugins>
    </build>
    ...
</project>
```

## Como especificar a ordeación dos servizos, portos e operacións
Por defecto, os servizos, portos e operacións se ordean alfabéticamente por nome.

Para respectar a orde na que se configuran os servizos baixo `sources`
e a orde na que se escriben os `portType` e `operation` nos contratos,
é necesario configurar o algoritmo de ordeación [sortServicesAlgorithm](./generate-sources-mojo.html#sortServicesAlgorithm)
co valor `NONE`.

### Configuración do plugin

```xml
<project>
    ...
    <properties>
        <wsdlit.api.sort.algorithm>NONE</wsdlit.version>
    </properties>
    ...
</project>
```
