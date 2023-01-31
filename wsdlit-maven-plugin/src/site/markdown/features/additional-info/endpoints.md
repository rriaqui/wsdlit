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

# Endpoints

In this section we will document how to indicate the different ways to consume the service other than the direct consumption of the endpoint.

---
*Table of Contents*
<!-- MACRO{toc} -->
---

## Layout

```
endpoints (1)
|- index.adoc (2)
```

1. The name of the directory under which the documentation related to the consumption of the service will be created.
2. The main file.

### The file index.adoc

Usually this file is enough to indicate that it can be consumed in some way.
For example,
the following is an example of how to indicate what can be consumed via `HTTPBC`:

```
[%header%autowidth.stretch,cols="^.^h,a"]
|===
|Binding|Remarks
|HTTPBC|http://server:port/operationid/id
|===
```

The rendering result is similar to:

___
| Binding | Observations                     |
|---------|----------------------------------|
| HTTP | http://server:port/operationid/id   |
___

## FAQ

### Why isn't this information added directly to the service documentation?

When designing a service contract, its abstract contract must be designed first.
The introduction chapter of the specification [WSDL 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315#_introduction)
explains in simple terms some very important concepts
(throughout the specification, the reader will be able to find more precise definitions of them):

* **Message**
> An abstract, typed definition of the data being communicated.

* **Binding**
> A concrete protocol and data format specification for a particular port type.

* **Service**
> A collection of related endpoints.

It also explains the difference between abstract and concrete messages.
* [Abstract vs Concrete messages](https://www.w3.org/TR/2001/NOTE-wsdl-20010315#_abstract-v)
> Message definitions are always considered to be an abstract definition of the message content.
> A message binding describes how the abstract content is mapped into a concrete format.

And on the difference between abstract and concrete contracts (wsdls) we find the following links:

* [Oracle - What is the difference between Abstract and concrete WSDL](https://community.oracle.com/tech/apps-infra/discussion/2511760/what-is-the-difference-between-abstract-and-concrete -wsdl#:~:text=Abstract%20WSDL%20consists%20of%20the,(http%2Cjms)%20details.)
* [TIBCO - Abstract and Concrete WSDL Documents](https://docs.tibco.com/pub/business-studio-bpm-edition/3.9.0/doc/html/GUID-4225DA77-5EBC-4D18-B4C7-5E2530672D69 .html)

The previous definitions and comparisons are very relevant because we traditionally think of consuming services through messages
specifically formatted for bindings, traditionally **SOAP** or **REST**,
assimilating the term **service** to **web service**,
which leads many developers to not realize the separation that exists between a message and the necessary encapsulation
to be able to transmit that message to a specific endpoint using a specific protocol,
almost always thinking of servers that expose web services by means of SOAP/REST encapsulated in the HTTP/HTTPS protocol.

But beyond the servers that expose web services, there are other types of servers,
such as the [Enterprise Service Bus](https://en.wikipedia.org/wiki/Enterprise_service_bus),
which allow the same service associated with multiple endpoints to be deployed.
Nothing prevents the service coded for example as a **BPEL** to be consumed by saving a message to a JMS queue
or by means of a URL using REST or SOAP, or even by saving the message in a file, by **FTP**, or from **emails** received.
But the most important thing is that the base implementation of **BPEL** does not need to know whether the message arrives through a **SOAP** port or
**REST**, or in other words:

* The service does not need to fight with the particularities of the bindings.
* The service always receives an **abstract message**.
* Within the **Enterprise Service Bus** the service consumes other services by means of abstract contracts.
  The **bindings** allow the communication of the **Enterprise Service Bus** with the outside.