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

<#--
    documentation.ftl

    Funcións relacionadas coa escritura de documentación
-->

<#macro write element defaultDocumentation="WARNING: Documentación ausente">
<@writeFirstParagraphAsAdmonition element=element defaultDocumentation=defaultDocumentation/>

<@writeAllButFirstParagraph element/>
</#macro>

<#macro writeFirstParagraphAsAdmonition element defaultDocumentation="WARNING: Documentación ausente">
<#list element.documentation?matches("^([^\n]+)") as chunk>
${chunk}
<#break>
<#else>
${defaultDocumentation}
</#list>
</#macro>

<#macro writeAllButFirstParagraph element>
<#if element.documentation?has_content>
<#list element.documentation?split( "\n" )>
    <#items as chunk>
    <#if ( chunk?counter > 1 )>
${chunk}
    </#if>
    </#items>
</#list>
</#if>
</#macro>
