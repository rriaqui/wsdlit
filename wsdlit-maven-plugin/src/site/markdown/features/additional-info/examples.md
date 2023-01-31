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

# examples: Examples of the messages

As we saw in the layout section,
these examples will appear under the `examples` directory.
The most efficient way of organizing the examples is by means of independent files in which their name is that of the operation and the extension `adoc`.

In the examples encapsulation of the bindings will be avoided,
since it is about documenting the examples referring to the abstract contracts.

Suppose our contract owns a port with `sayHello` and `sayHi` operations.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Layout

The structure of examples of this service could be:

```
examples (1)
|- index.adoc (2)
|- sayHello.adoc (3)
|- sayHi.adoc (3)
```

1. The name of the directory under which the service examples will be saved.
2. The main file.
3. Files directly associated with an operation name, with examples for each of them.

### The file index.adoc

Main document.
Its content could simply be one line for each of the example files of operations to be added:

```
include::sayHello.adoc[]

include::sayHi.adoc[]
```

### The file sayHello.adoc

Document showing examples for the `sayHello` operation.
Its content could be:

```asciidoc
=== create

Example greeting request.

==== Petition

[source.small,xml]
----
<greeting:SayHelloRequest xmlns:greeting="http://localhost.localdomain/wsdlit/example/helloworld">
     <greeting:name>Ramon</greeting:name>
</greeting:SayHelloRequest>
----

==== Answer

[source.small,xml]
----
<greeting:SayHelloResponse xmlns:greeting="http://localhost.localdomain/wsdlit/example/helloworld">
     <greeting:message>Hello, Ramón</greeting:message>
</greeting:SayHelloResponse>
----
```

### The file sayHi.adoc

Its content will be similar to that seen for `sayHello.adoc`.