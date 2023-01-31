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

Configuración global
====================

## settings.xml
Durante a etapa literaria da documentación o único formato que pode resultar interesante dende o punto de vista do proceso de revisión é o `HTML5`,
evitando a xeración do resto de formatos,
para comprobar o máis axiña posible no navegador se a documentación se renderiza correctamente,
xa que cada formato se xera nunha fase e se consumen moitos recursos do ordenador en forma de CPU e memoria,
e os formatos adicionais precisan moito máis tempo de procesamento.

A continuación amosamos a lista de propiedades booleanas que poden resultar de interese para o desenvolvedor.

|Nome|Por defecto|Descrición|
|`wsdlit.convert.html5.skip`|`false`|Evita xeración do html5.|
|`wsdlit.convert.epub.skip`|`false`|Evita a xeración do epub.|
|`wsdlit.convert.mobi.skip`|`false`|Evita a xeración do mobi.|
|`wsdlit.convert.pdf.skip`|`false`|Evita a xeración do pdf.|

Salvo que sexa un requirimento do proxecto,
non é aconsellable configurar estas propiedades no `pom.xml`,
senón no arquivo `${HOME}/.m2/settings.xml`.

Un exemplo que configura a xeración unicamente da documentación en formato {HTML5},
desactivando a xeración do resto de formatos.

```xml
<!-- ... -->
<profiles>
    <profile>
        <id>wsdlit-maven-plugin</id>
        <properties>
            <wsdlit.convert.html5.skip>false</wsdlit.convert.epub.skip>
            <wsdlit.convert.epub.skip>true</wsdlit.convert.epub.skip>
            <wsdlit.convert.mobi.skip>true</wsdlit.convert.mobi.skip>
            <wsdlit.convert.pdf.skip>true</wsdlit.convert.pdf.skip>
        </properties>
    </profile>
</profiles>
<!-- ... -->
<activeProfiles>
<!-- ... -->
    <activeProfile>wsdlit-maven-plugin</activeProfile>
<!-- ... -->
</activeProfiles>
```
