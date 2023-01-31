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

<#import "/libs/documentation.ftl" as documentation>
<#import "/libs/icons.ftl" as icons>
<#import "/libs/tree.ftl" as tree>

<#macro portTypeProcessor portType>
== ${portType.name}

<@documentation.write portType/>

<#list portType.operations as operation>
<@operationProcessor operation/>
</#list>
</#macro>

<#macro operationProcessor operation>
=== ${operation.name}

<@documentation.writeFirstParagraphAsAdmonition element=operation/> +
A operación responde ao patrón *${operation.type}*.
<@documentation.writeAllButFirstParagraph operation/>

<@writeParametersDocumentation operation/>
<@faultsProcessor faults=operation.faults/>
</#macro>

<#macro messageProcessor message title defaultDocumentation="WARNING: Documentación ausente">
==== ${title}: ${message.name} (mensaxe ${message.type})

<@documentation.write element=message defaultDocumentation=defaultDocumentation/>

<#list message.parts as part>
===== Part: ${part.name} (${part.definedByElement?string("element", "type")} ${part.type})
<#if message.parts?size gt 1 || part.documentation?has_content>
<@documentation.write part/>
</#if>

.Representación da estrutura xerárquica de datos do part
<@tree.showPartAsTree part/>

<#assign counter=1/>
<#list part.element.children>
.Descrición dos elementos do part da mensaxe
ifndef::backend-pdf[]
[%header%autowidth.stretch.small.column-nowrap-2,cols="^.^,.^a,^.^,.^a"]
endif::[]
ifdef::backend-pdf[]
[%header.stretch.small.column-nowrap-2,cols="^.^1,.^5,^.^1,.^14a"]
endif::[]
|===
||Nome|#|Descrición
    <#items as child>
        <@elementProcessor child/>
    </#items>
|===
</#list>
</#list>

</#macro>

<#macro faultsProcessor faults>
<#list faults as fault><@messageProcessor message=fault title="Fault"/>

</#list></#macro>

<#macro elementProcessor element>
|${counter}|<#compress>${element.fullName} <@icons.cyclicReferenceIcon element.isCyclicReference()/></#compress>|${element.occurrences}|<#if element.isModelGroup()>${(element.documentation)}<#else>`${element.type}` ${(element.documentation?has_content)?string( element.documentation, "\n\nWARNING: Documentación ausente" )}</#if>
<#assign counter=counter + 1/>
<#list element.children>
<#items as child>
<@elementProcessor child/>
</#items>
</#list>
</#macro>

<#macro writeParametersDocumentation operation>
    <#if operation.type == "ONE_WAY">
        <#assign defaultInputDocumentation = "Parámetro da operación que contén a mensaxe recibida."/>
        <@messageProcessor message=operation.inputMessage title="Parámetro input" defaultDocumentation=defaultInputDocumentation/>
    <#elseif operation.type == "REQUEST_RESPONSE" >
        <#assign defaultInputDocumentation = "Parámetro da operación que contén a mensaxe recibida."/>
        <#assign defaultOutputDocumentation = "Parámetro da operación que contén a mensaxe que se enviará como resposta."/>
        <@messageProcessor message=operation.inputMessage title="Parámetro input" defaultDocumentation=defaultInputDocumentation/>
        <@messageProcessor message=operation.outputMessage title="Parámetro output" defaultDocumentation=defaultOutputDocumentation/>
    <#elseif operation.type == "SOLICIT_RESPONSE">
        <#assign defaultInputDocumentation = "Parámetro da operación que contén a mensaxe recibida."/>
        <#assign defaultOutputDocumentation = "Parámetro da operación que contén a mensaxe que se enviará como solicitude."/>
        <@messageProcessor message=operation.outputMessage title="Parámetro output" defaultDocumentation=defaultOutputDocumentation/>
        <@messageProcessor message=operation.inputMessage title="Parámetro input" defaultDocumentation=defaultInputDocumentation/>
    <#elseif operation.type == "NOTIFICATION">
        <#assign defaultOutputDocumentation = "Parámetro da operación que contén a mensaxe que se enviará como notificación."/>
        <@messageProcessor message=operation.outputMessage title="Parámetro output" defaultDocumentation=defaultOutputDocumentation/>
    </#if>
</#macro>
