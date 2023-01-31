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

In this chapter we document how a service registers its activity in BAM,
in order for consumers of the service to know, in case of need,
which codes should be searched in the BAM database according to the operations that record information in said record.

This information will be written condensed into a file,
so it is necessary to indicate the operation associated with the registered code.

---
*Table of Contents*
<!-- MACRO{toc} -->
---

## Layout

```
bam (1)
|- index.adoc (2)
```

1. The name of the directory under which the documentation will be created.
2. The main file.

## The file index.adoc

This file is usually sufficient to document how and which values the service records its operations in the BAM.

The content of this file will be similar to:

```asciidoc
[%header%autowidth.stretch.small,cols="^.^h,^.^m,^.^m,a"]
|===
|Operation|Code|Error|Details
|create|4496669|true|This operation records the result of the same in the BAM, with the code <code>4496669</code> (`Generation of a person's PIN`), along with the following information:

* `IDOPERATION`: Operation identifier.
* `PERSONA`: `nif` in `TNNNNNNNNL` format of the person if requested by `NIF`.
* `PERSONA`: `percod` of the person if requested by `percod`.
* `ERROR`: Error code returned (only in case of error).
|===
```

The rendering result is similar to:

<blockquote>
<table>
     <head>
         <tr>
             <th>Operation</th>
             <th>Code</th>
             <th>Error</th>
             <th>Details</th>
         </tr>
     </head>
     <tbody>
         <tr>
              <td><code>create</code></td>
              <td><code>4496669</code></td>
              <td><code>true</code></td>
              <td>This operation registers the result of it in the BAM, with the code <code>4496669</code> (<b>Generation of a person's PIN</b>),
together with the following information:

<ul>
     <li><code>IDOPERATION</code>: Operation identifier.</li>
     <li><code>PERSONA</code>: <code>nif</code> in <code>TNNNNNNNNL</code> format of the person if requested by <code>nif</code></li>
     <li><code>IDPERSONA</code>: <code>percod</code> of the person if requested by <code>percod</code>.</li>
     <li><code>ERROR</code>: <code>true</code> to indicate that the service terminated with errors, <code>false</code> if it terminated successfully.</li>
</ul>
              </td>
         </tr>
     </tbody>
</table>
</blockquote>

In the previous table we mentioned as relevant data that the **Error** column will indicate the value saved in the error field
which is stored in the **BAM** when an error is detected in the service.
This value can only be `true` or `false`.
The expected value for logging an error is `true`.