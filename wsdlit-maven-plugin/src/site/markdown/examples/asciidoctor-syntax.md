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

# Syntax Asciidoctor
Below you will find some examples of how the most typical formats are coded in **Asciidoctor** syntax.
For more information, see the official Asciidoctor documentation,
in [Syntax Quick Reference](https://docs.asciidoctor.org/asciidoc/latest/syntax-quick-reference/).

## Paragraphs
A paragraph is nothing more than a succession of one or more lines of consecutive text.
Next we will see different examples and the rendering obtained, to deal with issues such as clarity of writing (full stop) and new paragraph (full stop).

### Clarity of writing
**Asciidoctor** allows you to write the different lines of the same paragraph one after the other,
without the need to write everything on the same physical line:

**Encoding**

```asciidoc
Sentence 1 of paragraph 1.
Sentence 2 of paragraph 1.
```

**Rendered**

> Sentence 1 of paragraph 1. Sentence 2 of paragraph 1.

### New paragraph
To write two different paragraphs, separate them with a blank line:

**Encoding**

```asciidoc
Sentence 1 of paragraph 1.
Sentence 2 of paragraph 1.

Sentence 1 of paragraph 2.
```

**Rendered**

> Sentence 1 of paragraph 1. Sentence 2 of paragraph 1.
>
> Sentence 1 of paragraph 2.|

### Titles of paragraphs
To write paragraph titles we will start with a period one line, and then the title of the paragraph.

**Encoding**

```asciidoc
.Title of the paragraph
Sentence 1 of paragraph 1.
Sentence 2 of paragraph 1.

Sentence 1 of paragraph 2.
```

**Rendered**

> <span style="color: red; font-style: italic">Paragraph title</span>
>
> Sentence 1 of paragraph 1. Sentence 2 of paragraph 1.
>
> Sentence 1 of paragraph 2.

## Lists of elements
Always be careful to separate the first item in the list in the previous paragraph with a blank line.

### Unsorted lists
Use the asterisk at the beginning of a line to mark an item in an unordered list.

**Encoding**

```asciidoc
Planets of the Solar System:

* Mercury.
* Venus.
* Earth
mars
```

**Rendered**

> Planets of the Solar System:
>
> * Mercury.
> * Venus.
> * Earth
> * Mars.

### Sorted lists
Use the period at the beginning of a line to mark an item in an unordered list.

**Encoding**

```asciidoc
Planets of the Solar System:

. mercury
. Venus
. earth
. mars
```

**Rendered**

> Planets of the Solar System:
>
> 1. Mercury.
> 2. Venus.
> 3. Earth.
> 4. Mars.

### Mixed lists
Mix ordered and unordered list markup characters.

To create a nesting of lists,
repeat the markup character(s) from the previous list level and add a new list markup character.

Use the period at the beginning of a line to mark an item in an unordered list.

**Encoding**

```asciidoc
Galaxies:

. Milky Way
.* Solar System.
.** Mercury.
.** Venus.
.** Earth.
.** Mars.
```

**Rendered**

> Galaxies:
>
> 1. Milky way.
> * Solar System.
> * Mercury.
> * Venus.
> * Earth
> * Mars.

## Text syntax and punctuation

### Bold
The bold style is indicated by delimiting the text to be highlighted between asterisks.

**Encoding**

```asciidoc
This is *example text* written in bold.
```

**Rendered**

> This is **sample text** written in bold.

### Italic

The italic style is indicated by delimiting the text to be highlighted between underscores.

**Encoding**

```asciidoc
This is a _sample text_ written in italics.
```

**Rendered**

> This is _example text_ written in italics.

### Monospace
All text contained between backslashes is rendered with a fixed-width font.

**Encoding**

```asciidoc
This is ``example text'' written as monospace.
```

**Rendered**

> This is `sample text' written in bold.

## Subscripts and superscripts
Superscripts are indicated delimited by the circumflex accent,
and subscripts by the tilde symbol.

**Encoding**

```asciidoc
This is an example of a superscript: E=mc^2^.

This is an example of a subscript: H~2~O.
```

**Rendered**

> This is an example of a superscript: E=mc<sup>2</sup>.
>
> This is an example of a sub-incidence: H<sub>2</sub>O.

### Text highlighting
To highlight text,
delimit it with the number sign

**Encoding**

```asciidoc
This is an example of #highlighted text#.
```

**Rendered**

> This is an example of <span style="background-color: lightyellow">highlighted text</span>.

## Tables
Tables are delimited by `|===`.
Try to restrict the use of tables to the minimum necessary,
and use as little syntax as possible to make the original wsdl documentation easier to read.

### Basic table
**Encoding**

```asciidoc
|===
|Cell 1,1|Cell 1,2
|Cell 2,1|Cell 2,2
|===
```

**Rendered**

> <table>
> <tr><td>Cell 1.1</td><td>Cell 1.2</td></tr>
> <tr><td>Cell 2.1</td><td>Cell 2.2</td></tr>
> </table>

### Table with header
**Encoding**
```asciidoc
[% header]
|===
|Cell 1.1|Cell 1.2
|Cell 2.1|Cell 2.2