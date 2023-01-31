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

# AMTEGA WsdlIT Maven Plugin
O plugin **WsdlIT** é usado para xerar un proxecto Maven coas fontes da documentación dos contratos dun ou máis servizos web.
Estas fontes se xeran en formato `adoc` ([Asciidoctor](https://asciidoctor.org)),
que son convertidas a formatos finais como **HTML5**, **EPUB**, **MOBI** ou **PDF**,
grazas a que o proxecto Maven xerado herda de `wsdlit-guide-parent`,
un pom xerado como parte do proxecto **wsdlit**,
que se reusa na xeración por medio da herdanza porque contén unha configuración bastante complexa,
e que emprega como ferramenta da construción o plugin Maven [asciidoctor-maven-plugin](https://docs.asciidoctor.org/maven-tools/latest/}asciidoctor-maven-plugin).

A principal característica de `wsdlit` é que se encarga de describir con detalle o contrato a partires da súa propia documentación,
detallando os `ports`, `operations`, `messages` e `faults`,
a partires da propia documentación contida nos arquivos `.wsdls` e `.xsd`.

A estrutura interna das mensaxes se detalla exhaustivamente,
expandindo automaticamente os `elements`, `complexTypes` e `ComplexContent`,
o que elimina a posibilidade de erro humano na súa documentación.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Visión xeral dos goals
O wsdlit plugin contén varios goals, enlazados a certas fases do ciclo de vida [Maven](https://maven.apache.org),
e, polo tanto, executados automaticamente durante as súas respectivas fases.

| Goal                                                                | Fase                     | Descrición                                                                                                                                                   |
|---------------------------------------------------------------------|--------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [wsdlit:descriptor-download](./descriptor-download-mojo.html)       | ```generate-sources```   | Sen uso todavía.                                                                                                                                             |
| [wsdlit:artifact-download](./artifact-download-mojo.html)           | ```process-sources```    | Descarga os artefactos que conteñen os contratos que se desexa documentar.                                                                                   |
| [wsdlit:generate-resources](./generate-resources-mojo.html)         | ```generate-resources``` | Parchea os arquivos de xeración da documentación obtidos durante a descarga de artefactos do goal [wsdlit:artifact-download](./artifact-download-mojo.html). |
| [wsdlit:generate-sources](./generate-sources-mojo.html)             | ```process-resources```  | Crea as fontes da documentación (arquivos ```.adoc```) a partires dos contratos dos servizos.                                                                |
| [wsdlit:generate-maven-project](./generate-maven-project-mojo.html) | ```compile```            | Xera o proxecto Maven que se usará para construir a documentación mediante AsciiDoctor.                                                                      |

## Uso
As instrucións xerais sobre como usar o plugin se poden atopar na [páxina de uso](./usage.html).
Algúns casos de uso máis específicos se describen nos exemplos que figuran a continuación.

En caso de que bote de menos algunha funcionalidade ou descubra algún bug,
pode completar unha solicitude de funcionalidade ou un informe de erro na aplicación de xestión de incidentes.

Ao crear un informe de erro proporcione unha descrición o máis completa que poida do bug.
Para corrixir os erros é determinante que os desenvolvedores poidan reproducir o seu problema,
polo que agraderecemos que se anexen os rexistros de depuración completos e pequenos proxectos de demostración adxuntos ao problema.
Por suposto, tamén admitimos parches.
Os contribuíntes poden clonar o proxecto dende o noso repositorio de código.

## Exemplos
Para proporcionarlle unha mellor comprensión dalgúns casos de uso do plugin **WsdlIT**,
recomendamos un vistazo aos seguintes exemplos:

* [Documentar contratos remotos](./examples/remote-wsdls.html).
* [Documentar contratos embebidos](./examples/embedded-wsdls.html).
* [Parchear contratos remotos con contratos embebidos](./examples/patch-wsdls.html).
