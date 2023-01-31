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

<#macro showPartAsTree part>
<#assign counter=1/>
<#list part.element.children>
[plantuml, format="svg"]
----
skinparam Legend {
	BackgroundColor transparent
	BorderColor transparent
	FontName Courier
	FontSize 14
}
legend
${part.name} (${part.type})
    <#items as child><@showElementAsTree child "" /></#items>
end legend
----
</#list>
</#macro>

<#macro showElementAsTree element indent="">
${indent}|_ (${counter}) <#compress>${element.fullName} ${elementType(element)}</#compress>
    <#assign counter++/><#t/>
    <#list element.children><#t/>
        <#items as child><#t/>
            <@showElementAsTree child indent+"  "/><#t/>
        </#items><#t/>
    </#list><#t/>
</#macro>

<#function elementType element>
    <#if element.isModelGroup()>
        <#return ""/>
    <#else>
        <#return "(" + element.type + ")"/>
    </#if>
</#function>
