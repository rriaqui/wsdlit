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

# Resources for Consumers (r4c)

Os **Recursos para Consumidores** (r4c) non son máis que un concepto creado para distribuir e referirnos de forma sinxela
a un empaquetamento dos contratos dos servizos apto para o seu uso por `wsdlit-maven-plugin`,
e polos propios consumidores nos proxectos de desenvolvemento nos que se precisen consumir os servizos.

O empaquetamento soportado actualmente por `wsdlit` é `packaging=zip`.
Nos proxectos que usan `wsdlit` en Emprego engadimos o `classifier=r4c` para facilitar certas operacións internas nos nosos sistemas.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Layout

A estrutura de arquivos e directorios que se respectará á hora de empaquetar o `r4c` será a seguinte:

```
/
|- wsdlit (1)
|  |- artifactId (2)
|  |  |- endpoints (3)
|  |  |  |- index.adoc (4)
|  |  |- examples (3)
|  |  |  |- index.adoc (4)
|  |  |- links (3)
|  |  |  |- index.adoc (4)
|- wsdls (5)
|  |  |- contract.wsdl (6)
|  |  |- contract.xsd (7)
|  |  |  |- xsd (8)
|  |  |  |  |- external_service_1 (9)
|  |  |  |  |  |- external-xsd1.xsd (10)
|  |  |  |  |  |- external-xsd2.xsd (10)
```

1. `wsdlit`: Directorio para albergar a documentación adicional.
   O seu contido é **opcional**.
2. `artifactId`: O valor do `artifactId` no que se empaquetou o contrato.
3. `endpoints`, `examples` e `links`: Documentación adicional.
4. `index.adoc`: O arquivo principal da documentación adicional.
5. `wsdls`: Directorio para albergar o contrato do servizo.
6. `contract.wsdl`: O `wsdl` que define o contrato do servizo.
7. `contract.xsd`: Un hipotético `xsd` requerido polo `wsdl`.
   O seu contido é **opcional**.
8. `xsd`: Directorio para albergar os esquemas de datos adicionais,
   por exemplo,
   os definidos nun core.
9. `external_service_1`: Separamos os arquivos por servizo.
   O valor do nome do directorio é o valor do atributo `name` do contrato do servizo externo.
10. `external-xsd1.xsd`e `external-xsd2.xsd`: Arquivos `xsd` do servizo `external_service_1`.

## O directorio no que se copia o contido relevante dos R4C remotos 

Cando na configuración do plugin se especifica a descarga de artefactos **r4c**,
`wsdlit` intenta descargar e descomprimir o contido relevante nun directorio.
O nome do directorio no que se descomprime o seu contido adquire relevancia durante o proceso de desenvolvemento,
no que antes de subir os cambios ao **SCM** se xera a documentación mediante o parcheo dos contratos,
xa que o directorio onde se copiarán os arquivos e directorios do parcheo para cada servizo debe coincidir co directorio
no que se descomprimen os remotos.

Por iso é necesario ter en conta como se calcula o nome do directorio no que se colocarán os arquivos cos parches:

1. Partimos do valor do `artifactId` do artefacto.
2. Se conservan os seguintes caracteres (eliminándose o resto):
   * Letras (a-z, A-Z).
   * Números (0-9).
   * **_** (carácter de suliñado).
   * **.** (o punto).
   * **-** (o guión).
3. Se eliminan os caracteres **_**, **.** e/ou **-** que se atopen ao final do texto resultante.

A continuación varios exemplos do cálculo do nome do directorio:

| artifactId       | Directorio      |
|------------------|-----------------|
| `artifact-id`    | `artifact-id`   |
| `artifact-id-4`  | `artifact-id-4` |
| `artifact-id_`   | `artifact-id`   |
| `artifact-id_-_` | `artifact-id`   |

## FAQ

### ¿Por que empaquetar os contratos nun novo arquivo?

O empaquetamento nun novo arquivo obedece a varias razóns:

* É necesario que os contratos dos servizos respondan a unha estrutura uniforme, convención antes que configuración.
* Os consumidores do servizo poderán descargar un arquivo cunha estrutura estándar no que obterán os contratos do mesmo.
* Por razóns de seguridade, non se pode subministrar aos consumidores do servizo o `jar` ou `war` que se despregará no backend.

### ¿Por que se separa o directorio do contrato do directorio da información adicional?

Por unha cuestión práctica. Cada directorio está pensado para cumplir un obxectivo.

* `wsdlit`: engadir documentación á xerada automaticamente a partires do contido do contrato.
* `wsdls`: copiar o seu contido ao proxecto no que se pretende consumir o servizo.

### ¿wsdlit soporta outros empaquetamentos diferentes ao `zip`?

Actualmente o único empaquetamento soportado por `wsdlit` é o `zip`.
É unha decisión de deseño, outra vez convención antes que configuración, 
para evitar a necesidade de codificar distintos tipos de desempaquetamento,
un por cada tipo soportado,
e evitar que os consumidores se vexan obrigados a lidiar con tipos diferentes de empaquetamentos.
