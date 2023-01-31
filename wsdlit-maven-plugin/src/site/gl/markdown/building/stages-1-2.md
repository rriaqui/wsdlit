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

Etapas 1 e 2
============

É posible configurar o proxecto **Maven** para que as etapas 1 e 2 se executen unha tras outra,
e non sexa necesario lanzar manualmente a etapa 2.
Esta nova configuración é moi sinxela,
e se apoia no plugin `exec-maven-plugin`.
Pode ver un exemplo desta configuración consultando o código do módulo [wsdlit-example-api](../../wsdlit-example-api/index.md).

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Configuración mínima para executar a 'Etapa 2' trala 'Etapa 1'
Pode atopar a continuación un exemplo mínimo da configuración requerida para lograr que a [etapa 2](./stage-2.md)
se execute tras rematar a construción da [etapa 1](./stage-1.md).
Por suposto,
é posible engadir máis propiedades de usuario como argumentos que as que aparecen no exemplo,
todo depende das necesidades do proxecto en cuestión.

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
                                Rediriximos a consola estándar e de erros a este arquivo.
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
