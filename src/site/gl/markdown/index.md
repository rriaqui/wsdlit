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

# wsdlit
O proxecto `wsdlit` creouse co obxectivo de simplificar a conversión dos contratos de servizos web descritos segundo
[Web Services Description Language (WSDL) version 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315),
en documentos que denominamos **Instrucións Técnicas**,
por medio dun plugin Maven que simplifique os pasos a seguir ata obter a documentación nos formatos finais.

As **Instrucións Técnicas** son documentos que detallan os portos,
operacións e mensaxes dos servizos,
aproveitando os elementos de documentación existentes en XML (`xsd:annotation` e `xsd:documentation`). 
`wsdlit` facilita a labor de documentación porque:

* Automatiza o proceso de construción e despregue da documentación en varios formatos.
* Reduce o mantemento manual requerido cando se cambia un contrato.
  A maior parte da documentación se escribe no propio arquivo do contrato do servizo,
  que se pode completar con outra información escrita en arquivos independentes.
* Reduce a posibilidade de erros na documentación, porque `wsdlit`:
  * Xera unha imaxe gráfica que representa xerarquicamente a estrutura das mensaxes.
  * Non esquece documentar un porto, operación ou mensaxe, nin esquece eliminalo cando desaparece do contrato.
* Permite escribir a documentación nunha linguaxe de marcado, **Asciidoctor**, similar a **Markdown**:
  A documentación do propio contrato é lexible aínda que se lea directamente do arquivo `wsdl`. 

Por favor, consulte a documentación do plugin [wsdlit-maven-plugin](wsdlit-maven-plugin/index.html) para máis información.

## Ver un exemplo da documentación xerada
O módulo [wsdlit-example-api](wsdlit-example-api/index.html) é un proxecto de exemplo,
que xera unha documentación moi sinxela que se pode consultar [neste enlace](wsdlit-example-api/example/index.html).

## Limitacións da presente versión de wsdlit
Por deseño, `wsdlit` presenta actualmente as seguintes limitacións:

* `wsdlit` soporta contratos que sigan a especificación
  [Web Services Description Language version 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315).<br/>
  No futuro incorporaremos o soporte para
  [Web Services Description Language version 2.0](http://www.w3.org/TR/wsdl20-primer/).
* `wsdlit` ignora os atributos dos tipos de datos.<br/>
  Por decisión de deseño, a información rexistrada en atributos se ignora:
  toda a información das mensaxes se intercambia como valores dos elementos.
  Como consecuencia, `wsdlit` non incorporará o tratamento de atributos.
* Os formatos nos que se xera a documentación final son: `html5`, `epub`, `mobi` e `pdf`.<br/>
  A medida que sexa posible, incorporaremos outros formatos no futuro.
