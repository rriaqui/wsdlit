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

Global settings
===============

## settings.xml
During the literary stage of the documentation, the only format that can be interesting from the point of view of the review process is ``HTML5'',avoiding the generation of the rest of the formats,
to check as soon as possible in the browser whether the documentation renders correctly,
since each format is generated in one phase and consumes a lot of computer resources in the form of CPU and memory,
and additional formats require much more processing time.

Below is a list of boolean properties that may be of interest to the developer.

|Name|Default|Description|
|`wsdlit.convert.html5.skip`|`false`|Skip generation of html5.|
|`wsdlit.convert.epub.skip`|`false`|Skip the epub generation.|
|`wsdlit.convert.mobi.skip`|`false`|Skip mobi generation.|
|`wsdlit.convert.pdf.skip`|`false`|Skip the generation of the pdf.|

Unless it is a project requirement,
it is not advisable to set these properties in the `pom.xml`,
but in the file `${HOME}/.m2/settings.xml`.

An example that configures the generation of only the documentation in {HTML5} format,
disabling the generation of the rest of the formats.

```xml
<!-- ... -->
<profiles>
     <profile>
         <id>wsdlit-maven-plugin</id>
         <properties>
             <wsdlit.convert.html5.skip>false</wsdlit.convert.epub.skip>
             <wsdlit.convert.epub.skip>true</wsdlit.convert.epub.skip>
             <wsdlit.convert.mobi.skip>true</wsdlit.convert.mobi.skip>
             <wsdlit.convert.pdf.skip>true</wsdlit.convert.pdf.skip>
         </properties>
     </profile>
</profiles>
<!-- ... -->
<activeProfiles>
<!-- ... -->
     <activeProfile>wsdlit-maven-plugin</activeProfile>
<!-- ... -->
</activeProfiles>
```