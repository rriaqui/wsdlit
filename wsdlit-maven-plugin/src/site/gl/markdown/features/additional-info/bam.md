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

# Business Activity Monitoring (BAM)

Neste capítulo documentamos como un servizo rexistra a súa actividade no BAM,
co fin de que os consumidores do servizo saiban, en caso de necesidade, 
que códigos deben buscar na base de datos do BAM segundo as operacións que rexistran información en dito rexistro.

Esta información se escribirá condensada nun arquivo,
polo que é preciso indicar a operación asociada ao código rexistrado.

---
*Táboa de contido*
<!-- MACRO{toc} -->
---

## Layout

```
bam (1)
|- index.adoc (2)
```

1. O nome do directorio baixo o que se creará a documentación.
2. O arquivo principal.

## O arquivo index.adoc

Normalmente basta con este arquivo para documentar como e con que valores o servizo rexistra as súas operacións no BAM.

O contido deste arquivo será similar a:

```asciidoc
[%header%autowidth.stretch.small,cols="^.^h,^.^m,^.^m,a"]
|===
|Operación|Código|Error|Detalles
|create|4496669|true|Esta operación rexistra o resultado da mesma no BAM, co código <code>4496669</code> (`Xeración do PIN dunha persoa`), xunto coa seguinte información:

* `IDOPERACION`: Identificador de operación.
* `PERSONA`: `nif` en formato `TNNNNNNNNL` da persoa en caso de solicitarse por `NIF`.
* `IDPERSONA`: `percod` da persoa en caso de solicitarse por `percod`.
* `ERROR`: Código de erro devolto (só en caso de error).
|===
```

O resultado da renderización é similar a:

<blockquote>
<table>
    <thead>
        <tr>
            <th>Operación</th>
            <th>Código</th>
            <th>Erro</th>
            <th>Detalles</th>
        </tr>
    </thead>
    <tbody>
        <tr>
             <td><code>create</code></td>
             <td><code>4496669</code></td>
             <td><code>true</code></td>
             <td>Esta operación rexistra o resultado da mesma no BAM, co código <code>4496669</code> (<b>Xeración do PIN dunha persoa</b>),
xunto coa seguinte información:

<ul>
    <li><code>IDOPERACION</code>: Identificador de operación.</li>
    <li><code>PERSONA</code>: <code>nif</code> en formato <code>TNNNNNNNNL</code> da persoa en caso de solicitarse por <code>nif</code></li>
    <li><code>IDPERSONA</code>: <code>percod</code> da persoa en caso de solicitarse por <code>percod</code>.</li>
    <li><code>ERROR</code>: <code>true</code> para indicar que o servizo rematou con erros, <code>false</code> se rematou correctamente.</li>
</ul>
             </td>
        </tr>
    </tbody>
</table>
</blockquote>

Na táboa anterior mencionamos como dato relevante que na columna **Erro** se indicará o valor gardado no campo de erro
que se rexistra no **BAM** cando se detecta un erro no servizo.
Este valor só pode ser `true` ou `false`.
O valor esperado para rexistrar un erro é `true`.
