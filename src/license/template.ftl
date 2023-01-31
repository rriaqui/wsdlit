<#--
  #%L
 License Maven Plugin
 %%
 Copyright (C) 2012 Codehaus, Tony Chemit
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
<#-- To render the third-party file.
 Available context :
 - dependencyMap a collection of Map.Entry with
   key are dependencies (as a MavenProject) (from the maven project)
   values are licenses of each dependency (array of string)
 - licenseMap a collection of Map.Entry with
   key are licenses of each dependency (array of string)
   values are all dependencies using this license
-->
<#function licenseFormat licenses>
    <#assign result = ""/>
    <#list licenses as license>
        <#assign result = result + " (" +license + ")"/>
    </#list>
    <#return result>
</#function>
<#function artifactFormat p>
    <#if p.name?index_of('Unnamed') &gt; -1>
        <#return p.artifactId + " (" + p.groupId + ":" + p.artifactId + ":" + p.version + " - " + (p.url!"no url defined") + ")">
    <#else>
        <#return p.name + " (" + p.groupId + ":" + p.artifactId + ":" + p.version + " - " + (p.url!"no url defined") + ")">
    </#if>
</#function>

<#if dependencyMap?size == 0>
The project has no dependencies.
<#else>
Lists of ${dependencyMap?size} third-party dependencies.
    <#list dependencyMap as e>
        <#assign project = e.getKey()/>
        <#assign licenses = e.getValue()/>
    ${licenseFormat(licenses)} ${artifactFormat(project)}
    </#list>
</#if>

About wsdlit images
===================

AMTEGA image (./src/site/resources/images/logo-amtega.svg) use is regulated by Decree "Decreto 112/2021, 22th July
about the use of the basic elements of the basic corporate identity of Xunta de Galicia".
AMTEGA image use is forbidden unless explicit approval.

    https://www.xunta.gal/dog/Publicados/2021/20210802/AnuncioG0595-270721-0001_es.html

The use of the EUPL licenses doesn't grant any rights under trademark law for
use of the AMTEGA logo and names. The use of any these items is subject to an
explicit approval of Xunta de Galicia.

CC BY 4.0 Internacional applies to following images in this project:

* src/site/resources/images/wsdlit-logo.png
* wsdlit-logo/src/resources-nonfiltered/ic-it-logo/ic-it-logo.svg
* wsdlit-logo/src/resources-nonfiltered/wsdlit-logo/wsdlit-logo.svg
* wsdlit-logo/src/resources-nonfiltered/bg-draft/bg-draft.svg
* wsdlit-default-theme/src/main/theme/resources-binary/pdf/images/bg-draft.svg
* wsdlit-default-theme/src/main/theme/resources-binary/pdf/images/ic-logo-header.png
* wsdlit-default-theme/src/main/theme/resources-binary/pdf/images/ic-logo-business.png
* wsdlit-default-theme/src/main/theme/resources-binary/artifact-homepage/wsdlit.png
