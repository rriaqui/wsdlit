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

# Document remote contracts
In this example we will see how the project is configured to generate the documentation from the content of artifacts
existing in remote repositories.

## Plugin configuration
The web service contracts that you want to document will be configured inside `artifacts`.
Each `artifact` represents an existing artifact in a repository,
whose structure will respond to the format [resources4consumers](../r4c/index.html).
It is possible that the content of the `artifact` responds to another structure,
but it is left as an exercise to the reader.

```xml
<project>
     ...
     <properties>
         <wsdlit.version>${project.version}</wsdlit.version>
     </properties>
     ...
     <build>
         <plugins>
             <plugin>
                 <groupId>${project.groupId}</groupId>
                 <artifactId>${project.artifactId}</artifactId>
                 <version>${wsdlit.version}</version>
                 <executions>
                     <execution>
                         <id>generate-sources-goal</id>
                         <goals>
                             <goal>descriptor-download</goal>
                             <goal>artifact-download</goal>
                             <goal>generate-resources</goal>
                             <goal>generate-sources</goal>
                             <goal>generate-maven-project</goal>
                         </goals>
                         <configuration>
                             ...
                             <!--
                                 The Maven coordinates of the artifacts that contain the contracts to be documented.
                                 They will respect the format `groupId:artifactId[:packaging[:classifier]]:version`.
                             -->
                             <artifacts>
                                 <artifact>gal.xunta.amtega.wsdlit.examples:wsdlit-examples-greeting:zip:r4c:1.0.0</artifact>
                                 <artifact>gal.xunta.amtega.wsdlit.examples:wsdlit-examples-helloworld:zip:r4c:1.0.0</artifact>
                             </artifacts>
                             ...
                         </configuration>
                     </execution>
                 </executions>
             </plugin>
         </plugins>
     </build>
     ...
</project>
```
