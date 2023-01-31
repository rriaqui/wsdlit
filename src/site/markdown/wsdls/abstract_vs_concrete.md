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


# Abstract Contracts vs Concrete Contracts
Throughout this documentation we will mention abstract contracts,
leaving concrete contracts aside,
but why this preference for abstract contracts?

## Differences between concrete contracts and abstract contracts
* Abstract contracts define services only in relation to data types and messages.
* Concrete contracts define as an endpoint that implements a service that interacts with the rest of the world,
that is,
with other services.

Or using a similarity to **Java** (though not literal),
we can think when we talk about abstract contracts in **interfaces**,
and when we talk about concrete contracts in the classes that **implement** the interfaces,
although the comparison is not completely equivalent.

Consumers of the services need,
first of all,
know what operations a service offers
and what structure the messages it supports have.
Where the service is located or what medium it uses for communication is not as important as the above information.

## The preference for abstract contracts
Abstract contracts also enable the execution of services,
at least on certain service buses,
like [OpenESB](https://www.open-esb.net/),
and have the advantage that they allow the implementation to be changed without consumers being affected,
since it is the bus itself that is responsible for locating a service (concrete contract) that implements the abstract contract,
and therefore
able to resolve the consumer's request.

Precisely because of the above, the expression **that implements a service that interacts with the rest of the world** makes sense.
The **rest of the world** in the service bus is all those communications that are carried out between elements that are outside it,
while the inside of the world are the services implemented in the bus itself,
whatever type they are (bpels or pojos)...

Or in other words:

> A service that implements an abstract contract can consume another service that implements another abstract contract within the same bus,
by means of the abstract contract of the second
(without the need to indicate which implementation of the service it is, or where it is located or the means of communication).