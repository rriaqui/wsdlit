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

# Additional information on contracts

Additional information adds documentation to the contract that should not be included in it.
orienting the documentation therein to the description of the service itself and the functionalities it contains.
Let's remember that a consumer can directly read the wsdl to find out what features a service offers,
and it is not advisable to overload said document with information that is not directly useful to the reader.

Additional documentation is optional,
but the developer will take into account that certain documentation may be necessary for consumers,
such as related to endpoints or examples.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Layout

For now, additional documentation will be created in the same already existing **resources4consumers** (**r4c**) nominee module.

The structure is organized so that each additional documentation has its own directory to house the additional documentation there.
Below you can see an example of the organization of additional documentation:

```
/
|- src
| |- main
| | |- resources4consumers (1)
| | | |- wsdlit (2)
| | | | |- valor_do_attributo_name_do_element_definitions_do_contract_wsdl (3)
| | | | | |- type (4)
| | | | | | |- index.adoc (5)
| | | | | | |- file-1.adoc (6)
| | | | | | |- file-N.adoc (6)
```

1. The directory under which consumer resources are stored.
2. The directory under which the additional documentation will be created.
3. The name of the service for which the documentation is created.
4. The type of additional documentation. It must reflect one of the values listed in the next section.
5. The main file.
   **wsdlit-maven-plugin** links to this file,
   and only this one,
   so the others will be added from this file.
6. Additional files that must be added from the main one.

## Types of additional documentation

### Summary

In the layout of the previous section, it corresponds to element 4, the **type**.

| Type | Description |
|---------------------------------|-------------- --------------------------------------------|
| [bam](./bam.html)               | The registration of operations in the BAM |
| [changelist](./changelist.html) | The list of changes to a contract |
| [examples](./examples.html)     | Examples of documentation as explained below. |
| [endpoints](./endpoints.html)   | Other ways to consume the service. |
| [links](./links.html)           | Links of interest. |
| [other](./other.html)           | Other information |
| [security](./security.html)     | Securing the service. |