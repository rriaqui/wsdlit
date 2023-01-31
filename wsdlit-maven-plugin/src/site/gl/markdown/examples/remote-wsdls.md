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

# Documentar contratos remotos
Neste exemplo veremos como se configura o proxecto para xerar a documentación a partires do contido de artefactos
existentes en repositorios remotos.

## Configuración do plugin
  Os contratos dos servizos web que se desexen documentar se configurarán dentro de `artifacts`.
  Cada `artifact` representa un artefacto existente nun repositorio,
  cuxa estrutura responderá ao formato [resources4consumers](../r4c/index.html).
  É posible que o contido do `artifact` responda a outra estrutura,
  pero se deixa como exercizo ao lector.

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
                                As coordenadas Maven dos artefactos que conteñen os contratos a documentar.
                                Respectarán o formato `groupId:artifactId[:packaging[:classifier]]:version`.
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
