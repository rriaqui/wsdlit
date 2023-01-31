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

# Información adicional dos contratos

A información adicional engade documentación ao contrato que non convén incluir nel.
orientando a documentación no mesmo á descrición do propio servizo e das funcionalidades que contén.
Lembremos que un consumidor pode ler directamente o wsdl para coñecer que funcionalidades ofrece un servizo,
e non convén sobrecargar dito documento con información que non é directamente útil para o lector.

A documentación adicional é opcional,
pero o desenvolvedor terá en conta que certas documentacións poden ser necesarias para os consumidores,
como a relacionada cos endpoints ou cos exemplos.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Layout

De momento, a documentación adicional se creará no mesmo módulo xa existente de nominado **resources4consumers** (**r4c**).

A estrutura se organiza de xeito que cada documentación adicional posúe o seu propio directorio para albergar ahí a documentación adicional.
A continuación pode ver un exemplo de organización da documentación adicional:

```
/
|- src
|  |- main
|  |  |- resources4consumers (1)
|  |  |  |- wsdlit (2)
|  |  |  |  |- valor_do_atributo_name_do_elemento_definitions_do_contrato_wsdl (3)
|  |  |  |  |  |- tipo (4)
|  |  |  |  |  |  |- index.adoc (5)
|  |  |  |  |  |  |- arquivo-1.adoc (6)
|  |  |  |  |  |  |- arquivo-N.adoc (6)
```

1. O directorio baixo o que se almacenan os recursos para consumidores.
2. O directorio baixo o que se creará a documentación adicional.
3. O nome do servizo para o que se crea a documentación.
4. O tipo de documentación adicional. Debe reflectir un dos valores recollidos na seguinte sección.
5. O arquivo principal.
   **wsdlit-maven-plugin** enlaza a este arquivo,
   e soamente a este,
   polo que dende este arquivo se engadirán os demáis.
6. Arquivos adicionais que deben ser engadidos dende o principal.

## Tipos de documentación adicional

### Resumo

No layout da sección anterior, se corresponde co elemento 4, o **tipo**.

| Tipo                            | Descrición                                                 |
|---------------------------------|------------------------------------------------------------|
| [bam](./bam.html)               | O rexistro das operacións no BAM                           |
| [changelist](./changelist.html) | O listado de cambios dun contrato                          |
| [examples](./examples.html)     | Exemplos de documentación segundo se explica máis adiante. |
| [endpoints](./endpoints.html)   | Outras formas de consumir o servizo.                       |
| [links](./links.html)           | Enlaces de interese.                                       |
| [other](./other.html)           | Outra información.                                         |
| [security](./security.html)     | A securización do servizo.                                 |
