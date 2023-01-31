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

# Enlaces adicionais do contrato

Empregaremos este tipo de información adicional cando sexa necesario engadir información en forma de enlaces externos sobre calqueira aspecto que afecte ao noso servizo,
e o consideremos de interese.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Layout

```
links (1)
|- index.adoc (2)
```

1. O nome do directorio baixo o que se creará a documentación.

2. O arquivo principal.

### O arquivo index.adoc

O seu contido constará dunha lista cun aspecto similar a:

```
* https://github.com/f4b6a3/uuid-creator[UUID Creator]: A Java library for generating and handling RFC-4122 UUIDs.
* https://tools.ietf.org/html/rfc4122[RFC-4122]: A Universally Unique IDentifier (UUID) URN Namespace.
* https://datatracker.ietf.org/doc/html/draft-peabody-dispatch-new-uuid-format[RFC-4122-Draft]: New UUID Formats - draft-peabody-dispatch-new-uuid-format-01
```

O resultado da renderización é similar a:

___
* [UUID Creator](https://github.com/f4b6a3/uuid-creator): A Java library for generating and handling RFC-4122 UUIDs.
* [RFC-4122](https://tools.ietf.org/html/rfc4122): A Universally Unique IDentifier (UUID) URN Namespace.
* [RFC-4122-Draft](https://datatracker.ietf.org/doc/html/draft-peabody-dispatch-new-uuid-format): New UUID Formats - draft-peabody-dispatch-new-uuid-format-01
___
