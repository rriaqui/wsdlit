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

# Parchear os contratos remotos con contratos embebidos
Este escenario se deseñou como complemento ao procedemento de desenvolvemento que se realiza habitualmente,
para simplificar a tarefa de documentación dos servizos,
evitando commits da documentación innecesarios, erróneos ou incompletos,
funcionando como unha ferramenta de parcheado que permite construir a documentación corrixida sen necesidade de subir os cambios
ao repositorio do código do proxecto en cuestión.

É posible misturar a descarga de artefactos que conteñan os contratos a documentar e,
tras isto,
parchear algúns contratos para aplicar cambios sen necesidade de confirmar os cambios no proxecto no
administrador de código fonte (**SCM**),
construilo,
e desplegar o artefacto ao repositorio Maven.

Esta funcionalidade combina dúas modalidades de funcionamento do plugin,
a descarga dos artefactos que conteñen os contratos e o uso de contratos embebidos,
que poden funcionar independentemente unha da outra.

## Configuración do plugin

## Como evitar a aplicación dos parches
Como indicamos anteriormente,
a funcionalidade do parcheado existe como un complemento ao desenvolvemento,
para evitar commits innecesarios, erróneos ou incompletos,
pero unha vez que os cambios se confirman no **SCM** a aplicación dos parches non é necesaria nin desexable:

* A única funcionalidade dos parches se reserva para o tempo de desenvolvemento.
* A documentación do contrato que se proporciona a terceiros desenvolvedores debe xerarse a partires exclusivamente
  do contido dos mesmos,
  para que o desenvolvedor poida prescindir de consultar a documentación xerada e limitarse ao contido dos propios arquivos do contrato
  (con certas salvedades recollidas baixo a forma de documentación adicional),
  se así o desexa.

Non se precisa a configuración explícita da propiedade de parcheado nos proxectos Maven.
Por defecto o parcheado está habilitado para axustarse ao escenario de desenvolvemento local,
e para que se axuste ao escenario de construción nunha ferramenta de integración continua,
no que a documentación do contrato xa se considera rematada e os parches xa non son necesarios,
a configuración na ferramenta de integración continua deshabilita esta característica mediante unha propiedade adicional.

Podemos deshabilitar o parcheado mediante a propiedade [patchSkip](./generate-resources-mojo.html#patchSkip).

Cando se precisa deshabilitar o parcheado,
constrúa a documentación mediante:

```shell
mvn ... -Dwsdlit.patch.skip=true
```

**NOTA**
No sistema de construción continua de [EMPREGO](https://emprego.xunta.gal),
se deshabilita o parcheado dos contratos para todos os proxectos.
