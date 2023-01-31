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

O proceso de construción da documentación
=========================================

O proceso de construción se realiza en dúas etapas diferenciadas,
na que a primeira se corresponde cun proxecto físico real escrito por nós,
e a segunda etapa se corresponde coa construción do proxecto que xera automaticamente `wsdlit`.

A estas dúas etapas de construción se suma unha terceira adicada ao despregue da documentación xerada a un servidor `webdav`.

* [Etapa 1: Procesamento dos contratos](stage-1.md)
* [Etapa 2: Construción da documentación](stage-2.md)
* [Etapa 3: Despregue da documentación](stage-3.md)

## Configuración da contorna de desenvolvemento

### Construción rápida da documentación
Cremos interesante tamén coñecer como podemos [configurar](./configuration.md) a nosa contorna de desenvolvemento
para lograr unha construción o máis áxil posible durante a escritura da documentación,
deshabilitando por defecto a construción de certos formatos da documentación.
