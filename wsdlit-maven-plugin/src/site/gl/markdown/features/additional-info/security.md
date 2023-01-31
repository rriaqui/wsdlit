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

# security: A securización das funcionalidades do contrato

Nas IT's non se poden revelar detalles internos acerca da securización dos servizos,
como que se necesita o cargo co código tal
ou que a securización se basea nunha táboa de nome cual cos campos `C1`, `C2` e `C3`.
Todos estes detalles poden supór unha brecha de seguridade na aplicación,
polo que a información sobre a securización dun servizo,
en caso de ser necesaria,
será moi escueta e carente de detalles técnicos.

Un contrato posúe un ou máis portos,
e cada porto unha ou máis operacións.
A securización se indica de xeito global ao contrato,
non existirá unha sección por operación nin por porto.
Sen embargo a documentación da securización pode incluir apartados específicos para indicar que tal operación precisa securización e en que condicións.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Layout

```
security (1)
|- index.adoc (2)
```

1. Nome do directorio no que se rexistrará o resume da securización de funcionalidades do contrato.
2. O arquivo principal.

### O arquivo index.adoc

O contido do arquivo principal pode variar en función das necesidades de documentación.
Normalmente se documentarán as operacións que requiren securización,
evitando documentar aquelas operacións non securizadas.
A continuación atopará un exemplo do contido deste arquivo:

```
As seguintes operacións precisan de que o usuario posúa certos cargos no seu perfil:

[%header%autowidth.stretch,cols="h,a"]
|===
|Operación|Observacións
|create|Precisa o cargo de usuario de `templates` e creación de datos.
|delete|Precisa o cargo de usuario de `templates` e borrado de datos.
|find|Precisa o cargo de usuario de `templates`.
|===
```

A renderización é similar á seguinte:

___
As seguintes operacións precisan de que o usuario posúa certos cargos no seu perfil:

| Operación | Observacións                                                   |
|-----------|----------------------------------------------------------------|
| `create`  | Precisa o cargo de usuario de `templates` e creación de datos. |
| `delete`  | Precisa o cargo de usuario de `templates` e borrado de datos.  |
| `find`    | Precisa o cargo de usuario de `templates`.                     |
___
