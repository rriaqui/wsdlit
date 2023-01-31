<#--
 #%L
 wsdlit-templates-demo
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

= API

Nos seguintes capítulos atopará a API de cada un dos contratos abstractos que se referenciaron no momento de construir esta documentación,
seguindo unha xerarquía moi clara centrada no contrato que se pretende documentar.
A documentación se extrae dos nodos `<documentation>` do mesmo arquivo do contrato,
amosándose un *WARNING* onde falte a documentación.

A continuación pode atopar un resume da xerarquía da documentación da API:

* Service. Se describe o contrato a partires da documentación do wsdl.
O identificador do contrato se extrae do atributo *name* do mesmo.
** PortType. Se describen un por un todos os *porType* do contrato.
*** Operation. Se describen unha por unha todas as *operation* do contrato.
**** input. Se describe o parámetro *input* da operación, se existe.
***** part. Se describen un por un os distintos *part* do parámetro.
**** output. Se describe o parámetro *output* da operación, se existe.
***** part. Se describen un por un os distintos *part* do parámetro.

[plantuml, format="svg"]
----
skinparam Legend {
	BackgroundColor transparent
	BorderColor transparent
	FontName Courier
	FontSize 14
}
legend
Service (1)
|_ PortType (2)
  |_ Operation (3)
    |_ input (4)
      |_ part (5)
    |_ output (6)
      |_ part (7)
end legend
----

. Se describe o servizo a partires da documentación do wsdl. O identificador do contrato se extrae do atributo *name* do mesmo.
. Se describen un por un todos os *porType* do contrato.
. Se describen unha por unha todas as *operation* do contrato.
. Se describe o parámetro *input* da operación, se existe.
. Se describen un por un os distintos *part* do parámetro.
. Se describe o parámetro *output* da operación, se existe.
. Se describen un por un os distintos *part* do parámetro.

<#list document.services as service>
include::${service.name}/index.adoc[leveloffset=+1]
</#list>
