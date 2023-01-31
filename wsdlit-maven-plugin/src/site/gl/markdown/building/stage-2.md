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

Etapa 2: Construción da documentación
=====================================

Nesta etapa nos encargamos de convertir as fontes da documentación,
que xa temos en formato `adoc`,
aos formatos desexados,
construindo o proxecto Maven xerado na etapa anterior.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Requisitos da etapa
* Linux.
* Maven 3.8.6 ou superior.
* Java 8.
* Kindlegen 2.9, se pretende xerar arquivos mobi.
* O binario `kindlegen` debe atoparse no `PATH`.

**NOTA sobre Kindlegen**.
Amazon retirou a descarga deste produto da súa web fai un tempo,
polo que non podemos indicar unha url de descarga do produto.
Se non dispón dunha copia descargada de `kindlegen` configure a construción como se indica nesta documentación
para non xerar o formato `mobi`.

## Conversión da documentación
Esta etapa se inicia mediante a liña de comandos seguinte:

```shell
mvn clean package -Pwsdlit-build-enabled -f target/generated-sources/maven/pom.xml
```

<blockquote class="warning">
    <p>É imprescindible habilitar o profile `wsdlit-build-enabled` á hora de xerar a documentación nesta fase.</p>
</blockquote>

## Omitir ou xerar un formato
Pode resultar interesante nun momento dado forzar ou omitir a xeración dun formato determinado dende a liña de comandos.
Por exemplo,
antes de subir os cambios a un sistema de xestión de código a xeración en no formato `PDF` ou `EPUB` nos revelará posibles defectos non desexados.

Podemos forzar a xeración dos formatos `HTML5` e `EPUB` dende a liña de comandos,
con independencia da configuración existente no arquivo `${HOME}/.m2/settings.xml`:

```shell
mvn -f target/generated-sources/maven/pom.xml clean install -Pwsdlit-build-enabled \
-Dwsdlit.convert.html5.skip=true \
-Dwsdlit.convert.epub.skip=false \
-Dwsdlit.convert.mobi.skip=false \
-Dwsdlit.convert.pdf.skip=true
```

## Produtos obtidos nesta etapa

### Produtos intermedios
Son os produtos resultado directo da construción dos arquivos `*.adoc` en distintos formatos.
Non se consideran produtos finais,
xa que non existe máis relación entre eles que a súa esencia (representar a documentación dunha API),
e,
polo tanto,
son produtos descartables non aptos para subir a ningún servidor de documentación.

Estes produtos intermedios se xeran no directorio `target/generated-sources/maven/target/wsdlit-build`,
ao que nos referiremos na seguinte táboa mediante `target/wsdlit-build`,
é dicir,
o directorio relativo ao directorio do proxecto Maven xerado na [etapa 1](./stage-1.md).

|Producto|Directorio/Arquivo|Descrición|
|--------|------------------|----------|
|html5|`target/wsdlit-build/html5`|A documentación en formato `HTML5`.|
|epub|`target/wsdlit-buil/epub/${artifactId}.epub`|A documentación como libro `epub`.|
|mobi|`target/wsdlit-build/emobi/${artifactId}-${version}.mobi`|A documentación como libro `mobi`.|
|pdf|`target/wsdlit-build/pdf/${artifactId}-${version}.pdf`|A documentación como libro `pdf`.|

### O produto final: A API
A API é o resultado de combinar os produtos intermedios,
de xeito que sexa posible consultar a documentación por medio dun navegador e descargar o resto de produtos.
Son precisamente os seus contidos os que se despregarán nos repositorios de documentación.

Denominamos a este produto como final porque é o que realmente desexamos poñer a disposición dos consumidores dun xeito online (`HTML`),
en formato axeitado para imprimir (`PDF`) ou apto para a súa lectura en eReaders (`MOBI` ou `EPUB`).

|Producto|Directorio/Arquivo| Descrición                                                                                                                                                        |
|--------|------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|API|`target/wsdlit-guide`| O produto final obxecto da xeración da documentación.<br/>Combina os produtos intermedios nunha versión html5 da documentación dende a que é posible descargalos. |
|Índice|`target/wsdlit-guide/wsdlit.html`| A páxina índice de versións da documentación.                                                                                                                     |
|Versión|`target/wsdlit-guide/${version}`| A documentación da versión a xerar.                                                                                                                               |
