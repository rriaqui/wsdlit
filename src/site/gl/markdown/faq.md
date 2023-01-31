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

# Frequently Asked Questions

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Acerca de wsdlit

### ¿Cal é o significado do termo wsdlit?

O termo `wsdlit` provén da concatenación de `wsdl` e `it`.
Inicialmente se escolleu como identificador do proxecto o termo `wsdl2it`,
mais unha limitación no identificador interno do proxecto dunha ferramenta usada no proceso de desenvolvemento,
aconsellou acortar dito identificador a `wsdlit` para evitar que o proxecto tivese dous identificadores,
un curto e outro máis longo,
e unificar a xestión do proxecto entre múltiples ferramentas (Jenkins, Nexus, Xestión de incidentes, CMDB...).

### ¿Que é unha instrución técnica (IT)?
Unha instrucción técnica (IT) é un documento que agrupa un resume dun ou varios contratos de servizos web,
de xeito que constitúen unha referencia para o uso dos servizos, funcionalidades presentes,
estruturas de datos (mensaxes), exemplos de uso... 

A instrución técnica tamén recibe o nome de **API**, e este é o termo que preferimos usar en relación con `wsdlit`.

### ¿Que versions da especificación WSDL soporta wsdlit?
Este proxecto se deseñou pensando na especificación
[Web Services Description Language version 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315).

Nun futuro soportarase tamén a especificación
[Web Services Description Language version 2.0](http://www.w3.org/TR/wsdl20-primer).

## Vantaxes de usar wsdlit
Son varias as vantaxes obtidas en relación a esta cuestión.
Non esquezamos que o responsable de redactar a documentación é sempre quen ten ao seu cargo o desenvolvemento do servizo,
polo que cando varias persoas/organizacións/empresas escriben documentación para unha mesma entidade (como é o noso caso)
a posibilidade de que o aspecto visual,
estrutura ou contidos,
sexan diferentes en maior ou menor medida,
é elevada.

A continuación atopará algunhas das vantaxes máis importantes.

* Se empregan as mesmas fontes de documentación dos servizos para xerar a **API** en diferentes formatos.
Actualmente, os formatos soportados son **HTML5**, **EPUB**, **MOBI** e **PDF**.
* Desaparecen os erros derivados do maquetado manual (cambios nas estruturas de datos, omisión de operacións...).
* Desaparecen as inconsistencias de estrutura da documentación.
* Desaparecenas inconsistencias visuais da documentación.

### ¿Cal é calidade da documentación obtida?
Como comprenderá o lector, `wsdlit` non é un software que faga maxia:
non adiviña que funcionalidade aporta un `operation` determinado do `wsdl`,
nin crea documentación para todos e cada un dos elementos dun `ComplexType`.

A calidade da documentación depende en boa medida do esforzo que o desenvolvedor adique a documentar o contrato do servizo.
**Asciidoctor** é unha linguaxe de marcado o suficientemente rica como para que o resultado final acade unha
grande calidade,
con soporte a múltiples necesidades (táboas, listas, táboas de contido, enlaces, resaltado de sintaxe, imaxes, admonitions...),
pero de nada serve tanta potencia de formateado se ao final a documentación
dos contratos dos servizos non é suficiente para o uso ao que vai destinada,
que é a de que calqueira desenvolvedor sexa capaz de usar as funcionalidades do servizo en cuestión sen necesidade
de solicitar aclaracións sobre a mesma.

## ¿Por que wsdlit non soporta...?
Este proxecto se deseñou pensando nas necesidades dos proxectos da nosa organización,
polo que se codificou o tratamento daqueles aspectos relevantes para eles,
sempre dende o punto de vista de deseñar contratos sinxelos,
sen moitas das "bondades" que permite a especificación **xsd**.
