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

# Contratos abstractos vs Contratos concretos
Ao longo desta documentación mencionaremos os contratos abstractos,
deixando un pouco de lado os contratos concretos,
mais ¿por que esta preferencia polos contratos abstractos?

## Diferenzas entre contratos concretos e contratos abstractos
* Os contratos abstractos definen os servizos unicamente no relacionado cos tipos de datos e as mensaxes.
* Os contratos concretos definen como un endpoint que implementa un servizo que interactúa con resto do mundo,
é dicir, 
con outros servizos.

Ou usando unha semellanza con **Java** (aínda que non literal),
podemos pensar cando falamos de contratos abstractos nas **interfaces**,
e cando falamos de contratos concretos nas clases que **implementan** as interfaces,
aínda que a comparación non resulta completamente equivalente.

Os consumidores dos servizos precisan,
ante todo,
coñecer que operacións ofrece un servizo
e que estrutura poseen as mensaxes que admite.
Onde se atope o servizo ou que medio empregue para a comunicación non é tan importante como a información anterior.

## A preferenza polos contratos abstractos
Os contratos abstractos tamén permiten a execución de servizos,
a lo menos en certos buses de servizo,
como [OpenESB](https://www.open-esb.net/),
e posúen a vantaxe de que permiten cambiar a implementación sen que os consumidores se vexan afectados,
xa que é o propio bus o que se encarga de localizar un servizo (contrato concreto) que implemente o contrato abstracto,
e polo tanto,
capaz de resolver a petición do consumidor.

Precisamente polo anterior ten sentido a expresión **que implementa un servizo que interactúa co resto do mundo**.
O **resto do mundo** no bus de servizos son todas aquelas comunicacións que se realizan entre elementos que se atopan fora del,
mentres que o interior do mundo son os servizos implementados no propio bus,
sexan do tipo que sexan (bpels ou pojos)...

Ou con outras palabras:

> Un servizo que implementa un contrato abstracto pode consumir outro servizo que implementa outro contrato abstracto dentro do mesmo bus,
por medio do contrato abstracto do segundo
(sen necesidade de indicarlle nin de que implementación do servizo se trata, nin onde se atopa nin o medio de comunicación).
