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

# Sintaxe Asciidoctor
A continuación atopará algúns exemplos de como se codifica en sintaxe **Asciidoctor** os formatos máis típicos.
Para máis información, consulte a documentación oficial de Asciidoctor,
en [Syntax Quick Reference](https://docs.asciidoctor.org/asciidoc/latest/syntax-quick-reference/).

## Parágrafos
Un parágrafo non é máis que unha sucesión de unha ou máis liñas de texto consecutivo.
A continuación veremos distintos exemplos e o renderizado obtido, para tratar cuestións como claridade da escritura (punto e seguido) e novo parágrafo (punto e aparte).

### Claridade de escritura
**Asciidoctor** permite escribir as distintas liñas dun mesmo parágrafo unha a continuación da outra,
sen necesidade de escribir todo na mesma liña física:

**Codificación**

```asciidoc
Frase 1 do parágrafo 1.
Frase 2 do parágrafo 1.
```

**Renderizado**

> Frase 1 do parágrafo 1. Frase 2 do parágrafo 1.

### Novo parágrafo
Para escribir dous parágrafos diferentes, sepáreos por medio dunha liña en branco:

**Codificación**

```asciidoc
Frase 1 do parágrafo 1.
Frase 2 do parágrafo 1.

Frase 1 do parágrafo 2.
```

**Renderizado**

> Frase 1 do parágrafo 1. Frase 2 do parágrafo 1.
>
> Frase 1 do parágrafo 2.|

### Títulos de parágrafos
Para escribir títulos de parágrafos comezaremos cun punto unha liña, e a continuación o título do parágrafo.

**Codificación**

```asciidoc
.Título do parágrafo
Frase 1 do parágrafo 1.
Frase 2 do parágrafo 1.

Frase 1 do parágrafo 2.
```

**Renderizado**

> <span style="color: red; font-style: italic">Título do parágrafo</span>
> 
> Frase 1 do parágrafo 1. Frase 2 do parágrafo 1.
> 
> Frase 1 do parágrafo 2.

## Listas de elementos
Teña sempre a precaución de separar o primeiro elemento da lista do parágrafo anterior por medio dunha liña en branco.

### Listas desordeadas
Empregue o asterisco ao principio dunha liña para marcar un elemento dunha lista desordeada.

**Codificación**

```asciidoc
Planetas do sistema Solar:

* Mercurio.
* Venus.
* Tierra.
Marte
```

**Renderizado**

> Planetas do sistema Solar:
> 
> * Mercurio.
> * Venus.
> * Tierra.
> * Marte.
  
### Listas ordeadas
Empregue o punto ao principio dunha liña para marcar un elemento dunha lista desordeada.

**Codificación**

```asciidoc
Planetas do sistema Solar:

. Mercurio
. Venus.
. Tierra.
. Marte.
```

**Renderizado**

> Planetas do sistema Solar:
> 
> 1. Mercurio.
> 2. Venus.
> 3. Tierra.
> 4. Marte.

### Listas misturadas
Misture os caracteres de marcado de listas ordeadas e desordeadas.

Para crear un aniñamento de listas,
repita o/os caracteres de marcado do nivel de lista anterior e engada un novo carácter de marcado de lista.

Empregue o punto ao principio dunha liña para marcar un elemento dunha lista desordeada.

**Codificación**

```asciidoc
Galaxias:

. Vía Láctea.
.* Sistema Solar.
.** Mercurio.
.** Venus.
.** Tierra.
.** Marte.
```

**Renderizado**

> Galaxias:
> 
> 1. Vía láctea.
>    * Sistema Solar.
>    * Mercurio.
>    * Venus.
>    * Tierra.
>    * Marte.

## Sintaxe de texto e puntuación

### Negrita
O estilo de negrita se indica delimitando o texto a resaltar entre asteriscos.

**Codificación**

```asciidoc
Este é un *exemplo de texto* escrito en negrita.
```

**Renderizado**

> Este é un **exemplo de texto** escrito en negrita.

### Cursiva

O estilo de cursiva se indica delimitando o texto a resaltar entre signos de suliñado.

**Codificación**

```asciidoc
Este é un _exemplo de texto_ escrito en cursiva.
```

**Renderizado**

> Este é un _exemplo de texto_ escrito en cursiva.

### Monospace
Todo texto contido entre acento invertido se renderiza cunha fonte de ancho fixo.

**Codificación**

```asciidoc
Este é un `exemplo de texto` escrito como monospace.
```

**Renderizado**

> Este é un `exemplo de texto` escrito en negrita.

## Subíndices e superíndices
Os superíndices se indican delimitados polo acento circunflexo 
e os subíndices polo símbolo da tilde.

**Codificación**

```asciidoc
Este é un exemplo de superíndice: E=mc^2^.

Este é un exemplo de subíndice: H~2~O.
```

**Renderizado**

> Este é un exemplo de superíndice: E=mc<sup>2</sup>.
>
> Este é un exemplo de subíncide: H<sub>2</sub>O.

### Resaltado de texto
Para resaltar texto,
delimíteo co signo numérico

**Codificación**

```asciidoc
Este é un exemplo de #texto resaltado#.
```

**Renderizado**

> Este é un exemplo de <span style="background-color: lightyellow">texto resaltado</span>.

## Táboas
As táboas se delimitan por medio de `|===`.
Procure restrinxir o uso de táboas ao mínimo imprescindible,
e empregar a menor sintaxe posible para facilitar a lectura da documentación orixinal do wsdl.

### Táboa básica
**Codificación**

```asciidoc
|===
|Celda 1,1|Celda 1,2
|Celda 2,1|Celda 2,2
|===
```

**Renderizado**

> <table>
>    <tr><td>Celda 1,1</td><td>Celda 1,2</td></tr>
>    <tr><td>Celda 2,1</td><td>Celda 2,2</td></tr>
> </table>

### Táboa con cabeceira
**Codificación**
```asciidoc
[%header]
|===
|Celda 1,1|Celda 1,2
|Celda 2,1|Celda 2,2
|===
```

**Renderizado**

> | Celda 1,1 | Celda 1,2 |
> -----------|-----------|
> | Celda 2,1 | Celda 2,2 |

<blockquote>
    <table>
        <tr>
            <th>Celda 1,1</th>
            <th>Celda 1,2</th>
        </tr>
        <tr>
            <td>Celda 2,1</td>
            <td>Celda 2,2</td>
        </tr>
    </table>
</blockquote>

### Estilo das táboas
Para modificar o aspecto das táboas recurrimos a aplicar estilos,
por medio dunha liña que figura antes do delimitador de táboa.

A continuación pode ver algúns dos estilos máis utilizados:

| Estilo                   | Descrición                                                                                        |
|--------------------------|---------------------------------------------------------------------------------------------------|
| `[%header]`              | Amosa a primeira fila como unha cabeceira.                                                        |
| `[frame=none,grid=none]` | Táboa se bordes e celas sen bordes.                                                               |
| `[%autowith]`            | Axusta automaticamente o ancho das columnas para amosar o contido.                                |
| `[%autowidth.strech]`    | A táboa intenta ocupar o 100% do ancho dispoñible axustando automaticamente o ancho das columnas. |
| `[cols="5,3,2"]`         | A táboa posúe 3 columnas, con anchos relativos 5, 3 e 2.                                          |

## Admonition: avisos, advertencias, información...
Podemos describir os **admonition** como aquelas frases que se atopan fora do fluxo de contido e etiquetadas cunha prioridade,
co obxectivo de que o lector fixe a atención por uns instantes, por tratarse de avisos, advertencias, información adicional...

Existen cinco tipos de admonitions en **Asciidoctor**:

* `NOTE`. Proporciona información adicional que non se condiera vital.
* `TIP`. Proporciona un consello.
* `IMPORTANT`. Avisa ao lector de algo importante, que debe terse en conta.
* `CAUTION`. Advirte ao lector de que debe tomar precaucións.
* `WARNING`. Informa ao lector do perigo, dano ou consecuencias que poidan existir.

Aínda que admitimos os **admonition** como parte da documentación, recomendamos evitar o seu uso máis que para casos excepcionais.

### Sintaxe para un único parágrafo
Nesta sintaxe se escribe todo nun único parágrafo.

**Codificación**

```asciidoc
WARNING: Non execute nunca o comando `rm -Rf /`.
```

**Renderizado**

> **WARNING**: Non excute nunca o comando `rm -Rf /`

### Enlaces, anchors e referencias cruzadas
Consulte a propia documentación de [Asciidoctor - Links](https://docs.asciidoctor.org/asciidoc/latest/syntax-quick-reference/#links) para máis información.
