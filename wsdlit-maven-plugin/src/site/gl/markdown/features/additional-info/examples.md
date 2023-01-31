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

# examples: Exemplos das mensaxes

Como vimos na sección do layout estes exemplos figurarán baixo o directorio `examples`.
A forma máis eficiente de organización dos exemplos é por medio de arquivos independentes nos que o seu nome sexa o da operación e a extensión `adoc`.

Nos exemplos se evitará o encapsulamento dos binding,
xa que se trata de documentar os exemplos referidos aos contratos abstractos.

Supoñamos que o noso contrato posúe un porto coas operacións `sayHello` e `sayHi`.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Layout

A estrutura dos exemplos deste servizo podería ser:

```
examples (1)
|- index.adoc (2)
|- sayHello.adoc (3)
|- sayHi.adoc (3)
```

1. O nome do directorio baixo o que se gardarán os exemplos do servizo.
2. O arquivo principal.
3. Arquivos asociados directamente a un nome de operación, cos exemplos para cada unha delas.

### O arquivo index.adoc

Documento principal.
O seu contido podería ser simplemente unha liña por cada un dos arquivos de exemplo de operacións a engadir:

```
include::sayHello.adoc[]

include::sayHi.adoc[]
```

### O arquivo sayHello.adoc

Documento que amosa exemplos para a operación `sayHello`.
O seu contido podería ser:

```asciidoc
=== create

Exemplo de solicitude de saúdo.

==== Petición

[source.small,xml]
----
<greeting:SayHelloRequest xmlns:greeting="http://localhost.localdomain/wsdlit/example/helloworld">
    <greeting:name>Ramón</greeting:name>
</greeting:SayHelloRequest>
----

==== Resposta

[source.small,xml]
----
<greeting:SayHelloResponse xmlns:greeting="http://localhost.localdomain/wsdlit/example/helloworld">
    <greeting:message>Hola, Ramón</greeting:message>
</greeting:SayHelloResponse>
----
```

### O arquivo sayHi.adoc

O seu contido será similar ao visto para `sayHello.adoc`.
