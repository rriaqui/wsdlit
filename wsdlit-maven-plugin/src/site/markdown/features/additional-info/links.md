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

# Additional contract links

We will use this type of additional information when it is necessary to add information in the form of external links about any aspect that affects our service
and consider of interest.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Layout

```
links (1)
|- index.adoc (2)
```

1. The name of the directory under which the documentation will be created.

2. The main file.

### The file index.adoc

Its content will consist of a list that looks similar to:

```
* https://github.com/f4b6a3/uuid-creator[UUID Creator]: A Java library for generating and handling RFC-4122 UUIDs.
* https://tools.ietf.org/html/rfc4122[RFC-4122]: A Universally Unique IDentifier (UUID) URN Namespace.
* https://datatracker.ietf.org/doc/html/draft-peabody-dispatch-new-uuid-format[RFC-4122-Draft]: New UUID Formats - draft-peabody-dispatch-new-uuid-format- 01
```

The rendering result is similar to:

___
* [UUID Creator](https://github.com/f4b6a3/uuid-creator): A Java library for generating and handling RFC-4122 UUIDs.
* [RFC-4122](https://tools.ietf.org/html/rfc4122): A Universally Unique IDentifier (UUID) URN Namespace.
* [RFC-4122-Draft](https://datatracker.ietf.org/doc/html/draft-peabody-dispatch-new-uuid-format): New UUID Formats - draft-peabody-dispatch-new-uuid- format-01
___