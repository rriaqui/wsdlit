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

# Document embedded contracts
In this example we will see how the project is configured to build the documentation from the existing wsdl's in the project itself,
without the need to download any contract from any repository,
that is to say,
to operate autonomously.

## The src/main/wsdlit directory
In this directory we will place each contract in a structure that will respond to the following format:

```
src
|- main
| |- wsdlit (1)
| | |- serviceName1 (2)
| | | |- wsdls (3)
| | | | |- contract.wsdl (4)
| | | | | |- schema1.xsd (5)
| | | | | |- xsd1 (6)
| | | | | | |- schema2.xsd (5)
| | |- serviceName2 (2)
| | | |- wsdls (3)
| | | | |- contract.wsdl (4)
...
```

1. `wsdlit` directory that will contain the service contracts.
2. `serviceName1`, `serviceName2` symbolic names of the services that will contain the relevant information about them.
3. `wsdls` name of the directory where the service contract is located.
4. `contract1.wsdl`, `contract2.wsdl` names of the `wsdl` files of the service contracts.
   They will be replaced by the real names of the contract files.
5. `schema1.xsd`, `schema2.xsd` names of `xsd` files required by the contract.
   They will be replaced by the actual file names.
6. `xsd1` allows the creation of subdirectories to better organize files.

The most important aspects of this structure are the following:

* Information related to services is found in the `src/main/wsdlit` directory.
* Each service has its information grouped in its own directory directly dependent on `src/main/wsdlit`.
* The service contract is located under the `wsdls` directory, within the service's own directory.
* The service contract must contain each and every one of the necessary files so that its parsing is correct.
* The creation of subdirectories within the `wsdls` folder is allowed to facilitate the organization of files.

## Plugin configuration
The web service contracts that you want to document will be configured inside `sources`.
Each `source` represents a web service contract, and as many as needed can be configured.
When generating the documentation,
the order in which the different `sources` are configured will be respected,
as long as the `wsdlit.api.sort.algorithm` property is set to `NONE`.

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
                     <sources>
                        <source>nomeDoServizo1/wsdls/contrato1.wsdl</source>
                        <source>nomeDoServizo2/wsdls/contrato2.wsdl</source>
                     </sources>
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