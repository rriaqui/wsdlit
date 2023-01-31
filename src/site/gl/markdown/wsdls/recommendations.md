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

# Recomendacións

## Definición de contratos

### Documente o prefixo a usar co esquema de datos
Documente o prefixo recomendado a usar co esquema de datos na propia documentación do contrato do servizo,
para que os consumidores uniformicen o seu uso nos distintos contratos nos que precisen consumir o seu servizo.

### Defina os esquemas como 'elementFormDefault=qualified'
Cando os esquemas se definen como `elementFormDefault=unqualified`,
pérdese a orixe da definición do elemento,
o que pode levar a confusión cando se traballa con múltiples contratos e/ou esquemas de datos.

### Evite o acomplamento entre contratos
Os contratos dependerán o menos posible de tipos definidos noutros contratos.
A nosa recomendación é definir todos os datos que precisa un servizo deseñado por nós sen máis dependencias externas que as dun posible core,
evitando o acoplamento do noso contrato ao doutros servizos que se produce cando empregamos tipos de datos definidos noutros servizos.

O principal obxectivo desta recomendación é a de minimizar o impacto dos cambios realizados en terceiros contratos,
para evitar que os consumidores dos nosos servizos se vexan obrigados a modificar os seus clientes  porque un terceiro servizo cambiou o seu contrato.

### Evite intentar convertirse nun experto en esquemas XML
Na inmensa maioría de casos non merece a pena tratar de convertirse nun experto en esquemas XML,
ou mellor dito,
non compensa.
É preferible a escritura de esquemas con estruturas sinxelas e ben coñecidas,
que o uso de grupos de sustitucións, referencias, etc.

**Canto máis sinxela sexa a estrutura do esquema,
máis sinxelo será o seu mantemento**.

## A sintaxe da documentación

### Asciidoctor
A documentación dos contratos admite a sintaxe **Asciidoctor**,
mais a nosa recomendación é a de ser moi prudente no uso da sintaxe de Asciidoctor.
O principal obxectivo da documentación é a lexibilidade,
incluso do código fonte do propio contrato.

### Os elementos de marcado recomendados
Como a linguaxe de marcado de Asciidoctor é tremendamente extensa,
e o que necesitamos é resaltar pequenos aspectos da documentación,
é necesario cumprir as seguintes regras para manter a lexibilidade da documentación orixinal do servizo:

* Procure restrinxir o marcado do texto á negrita, cursiva, suliñado, código, fin de liña, listas e definicións e parágrafos.
* Procure usar listas sinxelas.
* Evite o uso de táboas.
* Empregue o menor número posible de marcados.
* Non engada exemplos na documentación do contrato.
* Evite o uso de atributos de Asciidoctor na documentación.
* Evite o resto de marcados Asciidoctor.

## Lectura recomendada
O autor principal do software [Jenkins](https://www.jenkins.io/),
Kohsuke Kawaguchi,
redactou fai anos unha serie de recomendacións sobre o que se podería facer ou non cos esquemas XML,
que merece a pena ler aínda que non se esté dacordo co seu contido:

[W3C XML Schema: DOs and DON'Ts](https://www.kohsuke.org/xmlschema/XMLSchemaDOsAndDONTs.html).
