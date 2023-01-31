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

# Listado de cambios do contrato

A lista de cambios dunha IT non é máis que unha enumeración dos cambios significativos que sofre o contrato dun servizo sobre a versión inmediatamente anterior,
prescindindo das motivacións que levan a ditos cambios
(por exemplo, novas regras de codificación),
porque estas motivacións non son relevantes para o consumidor do servizo.

A enumeración de cambios se realizará dende o punto de vista funcional,
sen entrar en detalles técnicos ou internos,
que non son relevantes para os consumidores dos servizos.
A lista de cambios pode afectar á documentación dos contratos en múltiples vertientes:
o contrato en sí (aparición ou supresión de operacións) e/ou aos esquemas de datos que interveñen na definición das mensaxes das operacións.

---
*Táboa de contido*
<!-- MACRO{toc} -->
---

## Layout

```
changelist (1)
|- index.adoc (2)
```

1. O nome do directorio baixo o que se creará a documentación.

2. O arquivo principal.

### O arquivo index.adoc

Se detallarán todos os cambios relevantes,
independentemente de tratarse dunha correción de erros,
dunha nova operación ou a modificación dunha mensaxe,
respectando o seguinte formato:

```
Listado de cambios (01-08-2021):

[%header%autowidth.stretch.small,cols="^.^h,^.^h,^.^a,a"]
|===
|Elemento|Nome|Cambio|Observacións
|Schema|{icon-version}|Cambio de versión
|PortType|managerPortType|{icon-delete}|
|PortType|novoPortType|{icon-new}|
|Operation|crear|{icon-modify}|
|Operation|eliminar|{icon-modify}|Mensaxe de entrada.
|Operation|modificar|{icon-modify}|Novos Faults. Mensaxe de saída.
|Operation|buscar|{icon-rename}|`find`.
|Operation|modificar|{icon-new}|
|Operation|aceptar|{icon-bug}|SQL error ao gardar ...
|===
```

Combine as seguintes iconas para amosar os cambios soportados, separándoas cun espazo en branco.

| Codificación   | Icona                                                | Compatible | Descrición                                                        |
|----------------|------------------------------------------------------|------------|-------------------------------------------------------------------|
| `icon-bug`     | <span class="fa fa-bug icon-green"></span>           | Si         | Correción dun bug.                                                |
| `icon-new`     | <span class="fa fa-check-circle icon-green"></span>  | Si         | Creación dun nodo do contrato (operación, porto, elemento...).    |
| `icon-delete`  | <span class="fa fa-times-circle icon-red"></span>    | Non        | Eliminación dun nodo do contrato (operación, porto, elemento...). |
| `icon-modify`  | <span class="fa fa-pencil-square-o icon-red"></span> | Non        | Modificación dun porto, operación, nodo...                        |
| `icon-version` | <span class="fa fa-code-work icon-red"></span>       | Non        | Modificación da versión do esquema do contrato.                   |

A cor da icona indica se o cambio é compatible ou non.

* **<span style="color:red">En cor vermella</span>** os cambios que consideramos compatibles.
* **<span style="color: green">En cor verde</span>** os cambios que consideramos non compatibles.

O resultado da renderización é similar a:

___
**Listado de cambios (01-08-2021)**

| Elemento      | Nome              | Cambio                                                                     | Observacións                     |
|---------------|-------------------|----------------------------------------------------------------------------|----------------------------------|
| **Schema**    |                   | <span class="fa fa-code-work icon-red">Cambio de versión de esquema</span> | Cambio de versión do esquema.    |
| **PortType**  | `managerPortType` | <span class="fa fa-times-circle icon-red">Eliminado</span>                 |                                  |
| **PortType**  | `novoPortType`    | <span class="fa fa-check-circle icon-green">Novo</span>                    |                                  |
| **Operation** | `crear`           | <span class="fa fa-pencil-square-o icon-red">Modificado</span>             |                                  |
| **Operation** | `modificar`       | <span class="fa fa-pencil-square-o icon-red">Modificado</span>             |                                  |
| **Operation** | `eliminar`        | <span class="fa fa-times-circle icon-red">Eliminado</span>                 |                                  |
| **Operation** | `buscar`          | <span class="fa fa-pencil-square-o icon-red">Renomeado</span>              | Anteriormente denominada `find`. |
| **Operation** | `modificar`       | <span class="fa fa-check-circle icon-green">Novo</span>                    | Novos Faults. Mensaxe de saída.  |
| **Operation** | `accept`          | <span class="fa fa-bug icon-green">Arranxo</span>                          | SQL error ao gardar ...          |
___

No anterior renderizado, este é o significado das columnas da táboa:

* **Elemento**: indicará o nome do elemento onde se produxo o cambio.
  A priori non é posible predefinir todos os que se poden dar,
  pero indicamos que o valor fará referencia a onde se produce o cambio no contrato.
  Indicamos a continuación os que podemos prever neste momento.
    * **PortType**: indica un cambio nun `portType`: novo, eliminado ou cambiado de nome.
    * **Operation**: indica un cambio nunha `operation`: nova, eliminada,
      cambiada de nome ou que presenta algunha mensaxe (`INPUT`, `OUTPUT` ou `Fault`) nova ou modificada.
    * **PartnerLinkType**: indica un cambio nun `partnerLinkType`: novo, eliminado ou cambiado de nome.
* **Nome**: o `name` do elemento do contrato ao que facemos referencia (ou en branco en caso de Outro).
* **Compatible**: indica se enviando unha mensaxe en versión anterior do contrato aínda é compatible.
* **Tipo**: Tipo do cambio, sobre o contrato ou sobre algún dos seus elementos.
    * **Novo**.
    * **Eliminación**.
    * **Cambio**: cambio na definición do elemento.
    * **Renomeado**: cambio de nome do elemento. En **Observacións** se indicará o nome antigo.
    * **Bug**: Cando se corrixa un bug relacionado coa funcionalidade.
* **Observacións**: constará dun único parágrafo, e ser o máis curto posible, sen entrar en detalles técnicos.

En ningún caso os valores de **Elemento** ou **Tipo** están acotados:
engadiranse á lista anterior a medida que se detecte a súa necesidade.

## FAQ

### Durante canto tempo se mantén a lista de cambios

A lista de cambios figurará sempre na documentación. O único que cambia é o seu contido.

* A versión do proxecto cambia no terceiro díxito (cambios por correción de bugs).
  Se engadirá a lista de cambios anterior os bugs corrixidos na nova versión.
* A versión do proxecto cambia no segundo ou primeiro díxito (cambios por novas funcionalidades menores ou grandes cambios). A lista de cambios conterá unicamente as diferenzas respecto da versión anterior, e no seu caso os bugs corrixidos na nova versión.

Do anterior se deduce que en ningún caso se manterá na documentación do contrato a lista de cambios de todas as versións do mesmo.

### Que se documenta na lista de cambios

Partiremos sempre da premisa de non sobrecargar o contrato con información que non sexa útil para o consumidor,
resumindo os cambios sempre que sexa posible na documentación do propio contrato e detallándoa no elemento do contrato que sufriu cambios,
para evitar vernos obrigados a ler un documento completo para buscar cambios,
co risco de non asimilar algo ou de saltarnos algún aspecto importante que recolla un destes cambios.

Terase en conta á hora de documentar nun contrato unha lista de cambios que se informará ao consumidor do que sexa relevante para él,
prescindindo de toda información que non afecte estritamente aos cambios do contrato en sí. Por exemplo:

* A aparición dun `PortType` novo é motivo de documentación.
* O cambio de nome dunha `Operation` é motivo de documentación.
* A motivación do cambio de nome da `Operation` non é relevante para o consumidor.
