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

Nesta sección documentaremos como indicar os distintos xeitos de consumir o servizo distintos ao consumo directo do endpoint do mesmo.

---
*Táboa de contido*
<!-- MACRO{toc} -->
---

## Layout

```
endpoints (1)
|- index.adoc (2)
```

1. O nome do directorio baixo o que se creará a documentación relacionada co consumo do servizo.
2. O arquivo principal.

### O arquivo index.adoc

Normalmente basta con este arquivo para indicar que pode ser consumido dalgún xeito.
Por exemplo,
a continuación figura un exemplo de como indicar que se pode consumir por medio de  HTTPBC`:

```
[%header%autowidth.stretch,cols="^.^h,a"]
|===
|Binding|Observacións
|HTTPBC|http://server:port/operationid/id
|===
```

O resultado da renderización é similar a:

___
| Binding | Observacións                      |
|---------|-----------------------------------|
| HTTP    | http://server:port/operationid/id |
___

## FAQ

### ¿Por que non se engade esta información directamente na documentación do servizo?

Cando se deseña o contrato dun servizo se debe deseñar en primeiro lugar o seu contrato abstracto.
O capítulo de introducción da especificación [WSDL 1.1](https://www.w3.org/TR/2001/NOTE-wsdl-20010315#_introduction)
explica en termos sinxelos algúns conceptos moi importantes
(ao longo da especificación poderá o lector atopar definicións máis precisas dos mesmos):

* **Message**
> An abstract, typed definition of the data being communicated.

* **Binding**
> A concrete protocol and data format specification for a particular port type.

* **Service**
> A collection of related endpoints.

Tamén explica a diferenza entre as mensaxes abstractas e concretas.
* [Abstract vs Concrete messages](https://www.w3.org/TR/2001/NOTE-wsdl-20010315#_abstract-v)
> Message definitions are always considered to be an abstract definition of the message content.
> A message binding describes how the abstract content is mapped into a concrete format.

E sobre a diferenza entre contratos (wsdls) abstractos e concretos atopamos os seguintes enlaces:

* [Oracle - What is the difference between Abstract and concrete WSDL](https://community.oracle.com/tech/apps-infra/discussion/2511760/what-is-the-difference-between-abstract-and-concrete-wsdl#:~:text=Abstract%20WSDL%20consists%20of%20the,(http%2Cjms)%20details.)
* [TIBCO - Abstract and Concrete WSDL Documents](https://docs.tibco.com/pub/business-studio-bpm-edition/3.9.0/doc/html/GUID-4225DA77-5EBC-4D18-B4C7-5E2530672D69.html)

As anteriores definicións e comparacións resultan moi relevantes porque tradicionalmente se pensa en consumir servizos mediante mensaxes
formatadas específicamente para bindings, tradicionalmente **SOAP** ou **REST**,
asimilando o termo **servizo** a **servizo web**,
o que leva a moitos desenvolvedores a non darse conta da separación que existe entre unha mensaxe e o encapsulado necesario
para poder transmitir esa mensaxe a un endpoint concreto mediante un protocolo determinado,
case sempre pensando en servidores que expoñen servizos web por medio de SOAP/REST encapsulado no protocolo HTTP/HTTPS.

Pero máis aló dos servidores que expoñen servizos web se atopan outro tipo de servidores,
coma os [Enterprise Service Bus](https://en.wikipedia.org/wiki/Enterprise_service_bus),
que permiten despregar un mesmo servizo asociado a múltiples endpoints.
Nada impide que o servizo codificado por exemplo como un **BPEL** se poida consumir gardando unha mensaxe nunha cola JMS
ou ben por medio dunha URL mediante REST ou SOAP, ou incluso gardando a mensaxe nun arquivo, por **FTP**, ou a partires de **emails** recibidos.
Pero o máis importante é que a implementación básica do **BPEL** non ten por qué coñecer se a mensaxe chega por un porto **SOAP** ou
**REST**, ou dito doutro modo:

* O servizo non necesita pelexar coas particularidades dos bindings.
* O servizo recibe sempre unha **mensaxe abstracta**.
* Dentro do **Enterprise Service Bus** o servizo consume outros servizos por medio de contratos abstractos.
Os **bindings** permiten a comunicación do **Enterprise Service Bus** co exterior. 
