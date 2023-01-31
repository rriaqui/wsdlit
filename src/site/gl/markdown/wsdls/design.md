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

Deseño de contratos de servizos web
===================================

## ¿Que foi primeiro, o ovo ou a galiña?
Cando nos enfrentamos ao deseño de contratos de servizos web unha pregunta que nos deberíamos facer é:

> ¿Que vai primeiro, o ovo ou a galiña?

Reformulada a pregunta ao deseño dos contratos:

> ¿Que deseñamos primeiro, o código ou o contrato do servizo?

## Tećnicas de deseño dun servizo web
Existen dúas técnicas principais para deseñar un contrato dun servizo,
nas que se enfrontan o contrato e o código (**Contract First vs Code First**).

* **TOP-DOWN**: primeiro se codifica o contrato, e a partires del se crea o código que respecte ese contrato.
* **BOTTOM-UP**: se codifica en primeiro lugar o código, e a partires del se extrae o contrato.

## TOP-DOWN: A nosa eleción
Defendemos o enfoque **TOP-DOWN**,
polos seguintes motivos:

* Moitos destes servizos se consumen no ámbito da propia aplicación para a que se desenvolven,
  pero un grupo importante deles son consumidos tamén por terceiras aplicacións,
  fomentando a reutilización dos mesmos en procesos de negocio moito máis amplos.
* Como consecuencia da comunicación entre servizos creados en ámbitos distintos,
  é preciso un acordo entre o servizo consumido e o servizo consumidor,
  con fin de establecer unha comunicación na que poidan conversar de igual a igual.
  Este acordo, ademáis, significa que o servizo consumido non pode cambiar o seu contrato sen previo aviso e sen un período
  razoable de adaptación para o servizo consumidor.
* Os servizos se consumen tanto por medio do contrato abstracto (dentro dun bus de servizos) como co contrato concreto:
  * Os servizos de negocio se deseñan para expoñerse e consumirse por medio dun bus de servizos, [OpenESB](https://open-esb.net).
    Estes servizos de negocio se poden apoiar en servizos de backend cando así é necesario.
  * Cando o servizo consumidor e o servizo a consumir se atopan no mesmo OpenESB,
    o servizo a consumir se consume por medio do contrato abstracto.
  * Cando non residen na mesma infraestrutura, é necesario consumir o servizo por medio do contrato concreto.
