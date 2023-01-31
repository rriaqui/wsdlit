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

Etapa 1: Procesamento dos contratos
===================================

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## O procesamento dos contratos

A primeira etapa consiste en procesar os contratos dos servizos e crear un proxecto Maven
coas fontes de documentación xeradas a partires dos contratos dos servizos.
Este novo proxecto Maven permitirá obter a documentación nos formatos finais `html5`, `epub`, `mobi` e/ou `pdf`.

Durante esta etapa,
se analizan os contratos dos servizos para crear unha estrutura xerárquica do seu contido,
detallando as mensaxes mediante a expansión dos elementos que as compoñen e xerando os distintos arquivos `.adoc`,
fontes da documentación que se empregarán na seguinte etapa na construción dos formatos desexados.

Esta etapa se inicia mediante a liña de comandos seguinte:

```shell
mvn clean package
```

## Produtos obtidos nesta etapa
O resultado deste proceso se atopará no directorio `target/generated-sources/maven`.
Se trata dun proxecto Maven que contén a documentación dos servizos web en formato `adoc`.  
