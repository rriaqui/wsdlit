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

Design of web service contracts
===============================

## What came first, the egg or the chicken?
When faced with the design of web service contracts a question we should ask ourselves is:

> Which comes first, the egg or the chicken?

Reformulated the question to the design of the contracts:

> What do we design first, the code or the service contract?

## Web service design techniques
There are two main techniques for designing a service contract,
in which the contract and the code face each other (**Contract First vs Code First**).

* **TOP-DOWN**: first the contract is coded, and from it the code that respects that contract is created.
* **BOTTOM-UP**: the code is coded first, and the contract is extracted from it.

## TOP-DOWN: Our choice
We advocate the **TOP-DOWN** approach,
for the following reasons:

* Many of these services are consumed within the scope of the application itself for which they are developed,
  but an important group of them are also consumed by third applications,
  encouraging their reuse in much wider business processes.
* As a result of communication between services created in different areas,
  an agreement is required between the consumed service and the consuming service,
  in order to establish a communication in which they can converse as equals.
  This agreement also means that the service consumed cannot change its contract without prior notice and without a period
  reasonable adaptation for consumer service.
* Services are consumed both by means of the abstract contract (within a service bus) and with the concrete contract:
  * Business services are designed to be exposed and consumed via a service bus, [OpenESB](https://open-esb.net).
    These business services can be backed up by backend services when needed.
  * When the consumer service and the service to be consumed are in the same OpenESB,
    the service to be consumed is consumed by means of the abstract contract.
  * When they do not reside in the same infrastructure, it is necessary to consume the service through the specific contract.