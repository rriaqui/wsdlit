<#--
 #%L
 wsdlit-core
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

<#import "/libs/documentation.ftl" as doc>
<#import "/libs/icons.ftl" as icons>
<#import "/libs/processor.ftl" as processor>
= ${api.name}

<@doc.write api/>

<@chapterChangeList/>

<@chapterCompatibility api.differences/>
<#list api.portTypes as portType>
<@processor.portTypeProcessor portType=portType/>
</#list>

<@chapterBAM/>
<@chapterEndpoints/>
<@chapterSecurity/>
<@chapterExamples/>
<@chapterNamespaces api/>
<@chapterLinks/>
<@chapterOther/>

<#macro includeIfExists name atEnd="">
    <#local filename=name?absolute_template_name(freemarker_output_filename)/>
    <#local theFileExists = fileExists( name, freemarker_output_filename )/>
    <#if theFileExists>
<#nested/>
include::./${name}[]
<#if atEnd?has_content>
${atEnd}
</#if>
    </#if>
</#macro>

<#macro chapterChangeList>
<@includeIfExists name="./changelist/index.adoc" atEnd="*LENDA*

[%autowidth%header.small,cols=\"^,\"]
|===
|Cambio|Combinación de iconas que indican a compatibilidade e tipo do cambio.
|{icon-false}|O cambio non é compatible coa versión anterior.
|{icon-true}|O cambio é compatible coa versión anterior.
|{icon-bug}|Correción dun bug.
|{icon-delete}|Se eliminou algo (un elemento, unha operación, un porto...).
|{icon-new}|Se engadiu algo (un elemento, unha operación, un porto...).
|{icon-modify}|Se modificou a funcionalidade.
|{icon-rename}|Cambio de nome (dun elemento, dunha operación, dun porto...).
|{icon-version}|Cambio de versión do esquema como consecuencia dos cambios.
|===">
== Listado de cambios ==
</@includeIfExists>
</#macro>

<#macro chapterCompatibility differences>
<#if diffSkip?? && !diffSkip>
== Compatibilidade do contrato
<#assign safeDifferences = 0/>
<#assign unsafeDifferences = 0/>
<@renderDifferences differences "*"/>

.Resume
* *Modificacións compatibles*: ${safeDifferences}
* *Modificacións non compatibles*: ${unsafeDifferences}
</#if>
</#macro>

<#macro renderDifferences differences indent="">
   <#list differences>
        <#items as difference>
${indent} ${difference.message}
<@renderDifferences difference.differences indent + "*"/>
<#assign safeDifferences = safeDifferences + difference.countSafeDifferences()/>
<#assign unsafeDifferences = unsafeDifferences + difference.countUnsafeDifferences()/>
        </#items>
    </#list>
</#macro>

<#macro chapterBAM>
<@includeIfExists "./bam/index.adoc">
== Rexistro no BAM

Este servizo rexistra no BAM o resultado da execución das operacións segundo o seguinte contido:

</@includeIfExists>
</#macro>

<#macro chapterEndpoints>
<@includeIfExists "./endpoints/index.adoc">
== Endpoints

Este servizo se pode consumir tamén mediante:

</@includeIfExists>
</#macro>

<#macro chapterSecurity>
<@includeIfExists "./security/index.adoc">
== Seguridade

</@includeIfExists>
</#macro>

<#macro chapterExamples>
<@includeIfExists "./examples/index.adoc">
== Exemplos

A continuación pode atopar algúns exemplos relacionados co consumo das mensaxes.

</@includeIfExists>
</#macro>

<#macro chapterLinks>
<@includeIfExists "./links/index.adoc">
== Enlaces de interese

A continuación se amosan unha serie de enlaces que poden resultar do seu interese.

</@includeIfExists>
</#macro>

<#macro chapterOther>
<@includeIfExists "./other/index.adoc">
== Outra información

A continuación pode atopar outra información relevante que, polo seu contido, non puido clasificarse en ningunha das seccións anteriores.

</@includeIfExists>
</#macro>

<#macro chapterNamespaces api>
== Namespaces

A continuación se amosa a lista de namespaces dos esquemas usados neste servizo.
Por cada namespace se amosa a descrición do mesmo e o valor dos atributos `elementFormDefault` e `attributeFormDefault`;
os provedores procurarán que nos contratos que deseñan o valor de ambos atributos esté establecido a `qualified`,
aínda que o valor do `attributeFormDefault` pode quedar sen establecerse (`unqualified`) se non se usan atributos no esquema.

[%header%autowidth.stretch.small.nowrap,cols="^.^,,^.^,^.^"]
|===
|Prefixo|Namespace|E|A
<#list api.namespaces as namespace>
|${namespace.prefix}|${namespace.namespaceUri} +
${namespace.documentation!""}|<@icons.formDefault namespace.elementFormDefault/>|<@icons.formDefault namespace.attributeFormDefault/>
</#list>

|===
</#macro>
