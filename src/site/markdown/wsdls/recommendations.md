<!--
  #%L
  wsdlit
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

# Recommendations

## Definition of contracts

### Document the prefix to use with the data schema
Document the recommended prefix to use with the data schema in the service contract documentation itself,
so that consumers standardize their use in the different contracts in which they need to consume their service.

### Define schemas as elementFormDefault=qualified
When schemas are defined as `elementFormDefault=unqualified`,
the origin of the element definition is lost,
which can lead to confusion when working with multiple contracts and/or data schemas.

### Avoid overlap between contracts
Contracts shall depend as little as possible on types defined in other contracts.
Our recommendation is to define all the data that a service designed by us needs without more external dependencies than those of a possible core,
avoiding the coupling of our contract to that of other services that occurs when we use data types defined in other services.

The main objective of this recommendation is to minimize the impact of changes made in third party contracts,
to prevent consumers of our services from being forced to change their clients because a third-party service has changed its contract.

### Avoid trying to become an XML schema expert
In the vast majority of cases it is not worth trying to become an expert in XML schemas,
or rather,
it doesn't make up for it.
It is preferable to write schemes with simple and well-known structures,
that the use of substitution groups, references, etc.

**The simpler the scheme structure,
the easier it will be to maintain**.

## The documentation syntax

### Asciidoctor
Contract documentation supports **Asciidoctor** syntax,
but our recommendation is to be very careful when using Asciidoctor's syntax.
The main goal of documentation is readability,
even from the source code of the contract itself.

### The recommended markup elements
As Asciidoctor's markup language is tremendously extensive,
and what we need is to highlight small aspects of the documentation,
it is necessary to comply with the following rules to maintain the readability of the original documentation of the service:

* Try to restrict the marking of the text to bold, italic, underline, code, end of line, lists and definitions and paragraphs.
* Try to use simple lists.
* Avoid using tables.
* Use as few bookmarks as possible.
* Do not add examples in the contract documentation.
* Avoid using Asciidoctor attributes in documentation.
* Avoid the rest marked Asciidoctor.

## Recommended reading
The main author of the [Jenkins](https://www.jenkins.io/) software,
Kohsuke Kawaguchi,
wrote a series of recommendations years ago about what could and could not be done with XML schemas,
which is worth reading even if you don't agree with its content:

[W3C XML Schema: DOs and DON'Ts](https://www.kohsuke.org/xmlschema/XMLSchemaDOsAndDONTs.html).
