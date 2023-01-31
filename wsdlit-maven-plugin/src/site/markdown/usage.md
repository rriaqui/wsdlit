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

# Use

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Configuration of the WsdlIT plugin
Basic configuration of the `wsdlit-maven-plugin` plugin.

```xml
<project>
     ...
     <properties>
         <!--
             By declaring the plugin version here instead of in the plugin itself,
             we can override it using a system property of identical name,
             -Dwsdlit.version=1.9.3 for example.
         -->
         <wsdlit.version>${project.version}</wsdlit.version>
     </properties>
     ...
     <build>
         <plugins>
             <plugin>
                 <groupId>${project.groupId}</groupId>
                 <artifactId>${project.artifactId}</artifactId>
                 <version>${wsdlit.version}</version>
                 <configuration>
                     <!-- Add any configuration you need here. -->
                 </configuration>
             </plugin>
         </plugins>
     </build>
     ...
</project>
```

## Basic configuration of the documentation
In this example we will see how to configure certain basic values of all documentation,
such as the title of the documentation, the author, the organization, the theme to be used, and the coordinates of the project to be generated.

```xml
<project>
     ...
     <properties>
         <wsdlit.version>${project.version}</wsdlit.version>
         <wsdlit.api.title><!-- The title of the API. --></wsdlit.api.version>
         <wsdlit.api.author><!-- The main author of the API. --></wsdlit.api.author>
         <wsdlit.api.organization><!-- The organization that owns the API. --></wsdlit.api.organization>
         <wsdlit.api.copyright><!-- The API copyright. --></wsdlit.api.copyright>
         <wsdlit.api.groupId><!-- The groupId of the Maven project to be generated. --></wsdlit.api.groupId>
         <wsdlit.api.artifactId><!-- The artifactId of the Maven project to be generated. --></wsdlit.api.artifactId>
         <wsdlit.api.version><!-- The version of the Maven project to be generated and the version of the API. --></wsdlit.api.version>
         <wsdlit.api.theme><!-- The Maven coordinates of the theme to use. --></wsdlit.api.theme>
         <wsdlit.releases.repository.id><!-- The releases repository identifier in settings.xml where the generated documentation will be deployed. --></wsdlit.releases.repository.id>
         <wsdlit.snapshots.repository.id><!-- The snapshots repository identifier in settings.xml where the generated documentation will be deployed. --></wsdlit.snapshosts.repository.id>
         <wsdlit.site.repository.url><!-- The url of the server where the documentation will be deployed. --></wsdlit.site.repository.url>
     </properties>
     ...
     <build>
         <plugins>
             <plugin>
                 <groupId>${project.groupId}</groupId>
                 <artifactId>${project.artifactId}</artifactId>
                 <version>${wsdlit.version}</version>
                 <configuration>
                     ...
                     <title><!--- The title of the API. --></title>
                     <author><!-- The main author of the API. --></author>
                     <organization><!-- The organization that owns the API. --></organization>
                     <copyright><!-- The API copyright. --></copyright>
                     <groupId><!-- The groupId of the Maven project to be generated. --></groupId>
                     <artifactId><!-- The artifactId of the Maven project to be generated. --></artifactId>
                     <version><!-- The maven project version to be generated and the API version. --></version>
                     <theme><!-- The Maven coordinates of the theme to use. --></theme>
                     <releasesRepositoryId><!-- The releases repository identifier in settings.xml where the generated documentation will be deployed. --></releasesRepositoryId>
                     <snapshotsRepositoryId><!-- The snapshots repository identifier in settings.xml where the generated documentation will be deployed. --></snapshotsRepositoryId>
                     <siteRepositoryUrl><!-- The url of the server where the documentation will be deployed. --></siteRepositoryUrl>
                     ...
                 </configuration>
             </plugin>
         </plugins>
     </build>
     ...
</project>
```

## How to specify the ordering of services, ports and operations
By default, services, ports, and operations are sorted alphabetically by name.

To respect the order in which services are configured under `sources`
and the order in which `portType` and `operation` are written in the contracts,
it is necessary to configure the sorting algorithm [sortServicesAlgorithm](./generate-sources-mojo.html#sortServicesAlgorithm)
with the value `NONE`.

### Plugin configuration

```xml
<project>
     ...
     <properties>
         <wsdlit.api.sort.algorithm>NONE</wsdlit.version>
     </properties>
     ...
</project>
```