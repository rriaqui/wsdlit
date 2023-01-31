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

# Documentar contratos embebidos
Neste exemplo veremos como se configura o proxecto para construir a documentación a partires dos wsdl's existentes no propio proxecto,
sen necesidade de descargar ningún contrato de ningún repositorio,
é dicir,
para funcionar de forma autónoma.

## O directorio src/main/wsdlit
Neste directorio colocaremos cada contrato nunha estrutura que responderá ao seguinte formato:

```
src
|- main
|  |- wsdlit (1)
|  |  |- serviceName1 (2)
|  |  |  |- wsdls (3)
|  |  |  |  |- contract.wsdl (4)
|  |  |  |  |  |- schema1.xsd (5)
|  |  |  |  |  |- xsd1 (6)
|  |  |  |  |  |  |- schema2.xsd (5)
|  |  |- serviceName2 (2)
|  |  |  |- wsdls (3)
|  |  |  |  |- contract.wsdl (4)
...
```

1. `wsdlit` directorio que conterá os contratos dos servizos.
2. `serviceName1`, `serviceName2` nomes simbólicos dos servizos que conterán a información relevante dos mesmos.
3. `wsdls` nome do directorio no que se atopa o contrato do servizo.
4. `contract1.wsdl`, `contract2.wsdl` nomes dos arquivos `wsdl` dos contratos dos servizos.
   Substituiranse polos nomes reais dos arquivos dos contratos.
5. `schema1.xsd`, `schema2.xsd` nomes de arquivos `xsd` que precisa o contrato.
   Substituiranse polos nomes reais dos arquivos.
6. `xsd` se permite a creación de subdirectorios para organizar mellor os arquivos.

Os aspectos máis importantes desta estrutura son os seguintes:

* A información relacionada cos servizos se atopa no directorio `src/main/wsdlit`.
* Cada servizo posúe a súa información agrupada nun directorio propio directamente dependente de `src/main/wsdlit`.
* O contrato do servizo se atopa baixo o directorio `wsdls`, dentro do directorio propio do servizo.
* O contrato do servizo debe constar de todos e cada un dos arquivos necesarios para que o seu parseo sexa correcto.
* Se permite a creación de subdirectorios dentro da carpeta `wsdls` para facilitar a organización dos arquivos.

## Configuración do plugin
Os contratos dos servizos web que se desexen documentar se configurarán dentro de `sources`.
Cada `source` representa un contrato dun servizo web, e se poden configurar tantos como se necesiten.
Á hora de xerar a documentación,
se respetará a orde na que se configuren os distintos `source`,
sempre e cando se configure a propiedade `wsdlit.api.sort.algorithm` co valor `NONE`.

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
