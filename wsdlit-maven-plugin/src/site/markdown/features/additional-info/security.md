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

# security: Securing the functionality of the contract

In IT's, internal details about the security of services cannot be disclosed,
as the charge with such code is needed
or that the security is based on a table named qual with fields `C1`, `C2` and `C3`.
All these details can represent a security breach in the application,
so the information about securing a service,
if necessary,
it will be very brief and devoid of technical details.

A contract has one or more ports,
and each port one or more operations.
Securitization is indicated globally in the contract,
there will not be a section per operation or per port.
However, the securitization documentation may include specific sections to indicate that such an operation requires securitization and under what conditions.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Layout

```
security (1)
|- index.adoc (2)
```

1. Name of the directory in which the summary of the securing of contract functionalities will be registered.
2. The main file.

### The file index.adoc

The content of the main file may vary based on documentation needs.
Operations that require securitization will normally be documented,
avoiding documenting those unsecured operations.
Below you will find an example of the contents of this file:

```
The following operations require the user to have certain roles on their profile:

[%header%autowidth.stretch,cols="h,a"]
|===
|Operation|Observations
|create|Requires ``templates'' and data creation user role.
|delete|Requires ``templates'' and data deletion user account.
|find|Requires the `templates` user role.
|===
```

The rendering is similar to the following:

___
The following operations require the user to have certain roles on their profile:

| Operation | Observations |
|-----------|------------------------------------------------ --------------------------|
| `create` | You need the `templates` and data creation user role. |
| `delete` | You need the `templates` and data deletion user account. |
| `find` | You need the `templates` user role. |
___