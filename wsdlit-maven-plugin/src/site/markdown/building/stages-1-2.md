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

Stages 1 and 2
==============

It is possible to configure the **Maven** project so that stages 1 and 2 run one after the other,
and there is no need to launch stage 2 manually.
This new configuration is very simple,
and is supported by the `exec-maven-plugin` plugin.
You can see an example of this configuration by looking at the [wsdlit-example-api](../../wsdlit-example-api/index.md) module code.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Minimum configuration to run 'Stage 2' after 'Stage 1'
You can find below a minimal example of the configuration required to make [stage 2](./stage-2.md)
run after [stage 1](./stage-1.md) building is finished.
Of course,
it is possible to add more user properties as arguments than those shown in the example,
it all depends on the needs of the project in question.

```xml
<project>
     <build>
         <plugins>
             <!-- ... -->
             <plugin>
                 <groupId>gal.xunta.amtega.wsdlit</groupId>
                 <artifactId>wsdlit-maven-plugin</artifactId>
                 <!-- ... -->
             </plugin>
             <!-- ... -->
             <plugin>
                 <groupId>org.codehaus.mojo</groupId>
                 <artifactId>exec-maven-plugin</artifactId>
                 <executions>
                     <execution>
                         <id>wsdlit-run-stage-2</id>
                         <goals>
                             <goal>exec</goal>
                         </goals>
                         <phase>package</phase>
                         <configuration>
                             <executable>mvn</executable>
                             <arguments>
                                 <argument>package</argument>
                                 <argument>-f</argument>
                                 <argument>${project.build.directory}/generated-sources/maven/pom.xml</argument>
                                 <argument>-Pwsdlit-build-enabled</argument>
                                 <argument>-Dwsdlit.api.version=3.3.1</argument>
                             </arguments>
                             <!--
                                 We redirect the standard and error console to this file.
                             -->
                             <outputFile>${project.build.directory}/generated-sources/maven/build.log</outputFile>
                         </configuration>
                     </execution>
                 </executions>
             </plugin>
         </plugins>
     </build>
</project>
```
