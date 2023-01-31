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

# List of contract changes

The change list of an IT is nothing more than an enumeration of the significant changes that a service contract undergoes over the immediately previous version,
regardless of the motivations that lead to such changes
(eg new coding rules),
because these motivations are not relevant to the consumer of the service.

The enumeration of changes will be made from the functional point of view,
without going into technical or internal details,
which are not relevant to consumers of the services.
The list of changes can affect contract documentation in multiple ways:
the contract itself (appearance or deletion of operations) and/or the data schemas involved in defining the messages of the operations.

---
*Table of Contents*
<!-- MACRO{toc} -->
---

## Layout

```
change list (1)
|- index.adoc (2)
```

1. The name of the directory under which the documentation will be created.

2. The main file.

### The file index.adoc

All relevant changes will be detailed,
regardless of whether it is a bug fix,
of a new operation or the modification of a message,
respecting the following format:

```
List of changes (08-01-2021):

[%header%autowidth.stretch.small,cols="^.^h,^.^h,^.^a,a"]
|===
|Element|Name|Change|Remarks
|Schema|{icon-version}|Version change
|PortType|managerPortType|{icon-delete}|
|PortType|newPortType|{icon-new}|
|Operation|create|{icon-modify}|
|Operation|remove|{icon-modify}|Incoming message.
|Operation|modify|{icon-modify}|New Faults. Output message.
|Operation|find|{icon-rename}|`find`.
|Operation|modify|{icon-new}|
|Operation|accept|{icon-bug}|SQL error when saving ...
|===
```

Combine the following icons to show supported changes, separating them with a white space.

| Encoding | Icon | Compatible | Description |
|----------------|-------------------------------- ----------------------|------------|------------- -------------------------------------------------- ---|
| `icon-bug` | <span class="fa fa-bug icon-green"></span> | Yes | Bug fix. |
| `icon-new` | <span class="fa fa-check-circle icon-green"></span> | Yes | Creation of a contract node (operation, port, element...). |
| `icon-delete` | <span class="fa fa-times-circle icon-red"></span> | No | Deleting a contract node (operation, port, element...). |
| `icon-modify` | <span class="fa fa-pencil-square-o icon-red"></span> | No | Modification of a port, operation, node... |
| `icon-version` | <span class="fa fa-code-work icon-red"></span> | No | Modification of the version of the contract scheme. |

The color of the icon indicates whether the change is supported or not.

* **<span style="color:red">In red color</span>** the changes we consider not compatible.
* **<span style="color: green">In green color</span>** changes that we consider supported.

The rendering result is similar to:

___
**List of changes (08-01-2021)**

| Item | Name | Change | Observations |
|---------------|-------------------|------------ -------------------------------------------------- -------------|----------------------------------|
| **Scheme** | | <span class="fa fa-code-work icon-red">Schema version change</span> | Schema version change. |
| **PortType** | `managerPortType` | <span class="fa fa-times-circle icon-red">Deleted</span> | |
| **PortType** | `newPortType` | <span class="fa fa-check-circle icon-green">New</span> | |
| **Operation** | `create` | <span class="fa fa-pencil-square-o icon-red">Modified</span> | |
| **Operation** | `modify` | <span class="fa fa-pencil-square-o icon-red">Modified</span> | |
| **Operation** | `delete` | <span class="fa fa-times-circle icon-red">Deleted</span> | |
| **Operation** | `search` | <span class="fa fa-pencil-square-o icon-red">Renamed</span> | Previously called `find`. |
| **Operation** | `modify` | <span class="fa fa-check-circle icon-green">New</span> | New Faults. Output message. |
| **Operation** | `accept` | <span class="fa fa-bug icon-green">Fix</span>