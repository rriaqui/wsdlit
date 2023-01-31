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

# Frequently Asked Questions

---
**Content table**
<!-- MACRO{toc} -->
---

## About wsdlit

### Which is the meaning of wsdlit?
`wsdlit` comes from concatenation of `wsdl` and `it`.
Initially, the term `wsdl2it` was chosen as the project identifier,
but a limitation on the internal project identifier of a tool used in the development process,
advised to shorten such identifier to `wsdlit` to prevent the project from having two identifiers,
one short and one longer,
and unify project management between multiple tools (Jenkins, Nexus, Sonar, Redmine, OTRSS...).

### What is an IT?
A technical instruction (IT) is a document that groups together a summary of one or more web service contracts,
so that they constitute a reference for the use of the services, present functionalities,
data structures (messages), usage examples...

The technical statement is also called **API**, and this is the term we prefer to use in relation to `wsdlit`.

### What versions of the WSDL specification does wsdlit support?
This project was designed with the specification in mind
[Web Services Description Language version 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315).

The specification will also be supported in the future
[Web Services Description Language version 2.0](http://www.w3.org/TR/wsdl20-primer).

## Advantages of using wsdlit
There are several advantages obtained in relation to this issue.
Let's not forget that the person responsible for writing the documentation is always the person in charge of the development of the service,
so when several people/organizations/companies write documentation for the same entity (as is our case)
the possibility that the visual aspect,
structure or contents,
are different to a greater or lesser extent,
is high.

Below you will find some of the most important advantages.

* The same service documentation sources are used to generate the **API** in different formats.
  Currently, the supported formats are **HTML5**, **EPUB**, **MOBI** and **PDF**.
* Errors resulting from manual layout disappear (changes in data structures, omission of operations...).
* Inconsistencies in the structure of the documentation disappear.
* Visual inconsistencies in the documentation disappear.

### What is the quality of the documentation obtained?

The quality of the documentation largely depends on the effort the developer puts into documenting the service contract.
**Asciidoctor** is a rich enough markup language that the end result achieves one
great quality,
with support for multiple needs (tables, lists, tables of content, links, syntax highlighting, images, admonitions...),
but it's no use so much formatting power if at the end the documentation
of the service contracts is not sufficient for the use for which it is intended,
which is that any developer is able to use the functionalities of the service in question without need
to request clarifications about it.

## Why doesn't wsdlit support...?
This project was designed with the needs of our organization's projects in mind,
so the treatment of those aspects relevant to them was codified,
always from the point of view of designing simple contracts,
without many of the "goodies" that the **xsd** spec allows.