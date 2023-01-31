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

# Temas
Os temas `wsdlit` non son máis que un arquivo zip cunha estrutura determinada,
que contén un conxunto de recursos que definen un aspecto visual que se aplica á construción da documentación.
Estes recursos poden ser plantillas de documentación,
follas de estilo CSS para a xeración en formato **HTML5** ou recursos máis ou menos complexos,
coma a configuración da documentación en formato **PDF**,
as imaxes que se usarán ou os recursos necesarios para xerar unha páxina índice por artefacto,
que permitirá seleccionar unha versión para ver a súa documentación.

---
**Táboa de contido**
<!-- MACRO{toc} -->
---

## Layout dun tema
É moi simple,
a continuación podemos ver un vistazo do contido do tema por defecto,
`wsdlit-default-theme`.

```
/
|- artifact-homepage (1)
|  |- wsdlit.css
|  |- wsdlit.html
|  |- wsdlit.js
|  |- wsdlit.png
|- _asciidoc (2)
|  |- _templates (3)
|  |  |- intro
|  |  |  |- index.adoc
|  |  |  |- intro-conventions.adoc
|  |  |  |- intro-legal.adoc
|  |  |- resources
|  |  |  |- resource-changelist.adoc
|  |  |  |- resource-feedback.adoc
|  |  |  |- resource-otherFormatsForThisDocument.adoc
|  |  |  |- resource-project-authors.adoc
|  |  |  |- resource-trademarks.adoc
|  |  |- additional-doc.adoc
|  |  |- docinfo.html
|  |  |- pde.adoc
|- html5 (4)
|  |- css
|  |  |- font-awesome.css
|  |  |- font-awesome.min.css
|  |- fonts
|  |  |- FontAwesome.otf
|  |  |- fontawesome-webfont.eot
|  |  |- fontawesome-webfont.svg
|  |  |- fontawesome-webfont.ttf
|  |  |- fontawesome-webfont.woff
|  |  |- fontawesome-webfont.woff2
|- pdf (5)
|  |- fonts
|  |  |- mplus1mn-bold-ascii.ttf
|  |  |- mplus1mn-bold-italic-ascii.ttf
|  |  |- mplus1mn-italic-ascii.ttf
|  |  |- mplus1mn-regular-ascii-conums.ttf
|  |  |- mplus1mn-regular-fallback.ttf
|  |  |- notoserif-bold-subset.ttf
|  |  |- notoserif-bold_italic-subset.ttf
|  |  |- notoserif-italic-subset.ttf
|  |  |- notoserif-regular-subset.ttf
|  |- images
|  |  |- bg-draft.svg
|  |  |- bg-title_page.jpg
|  |  |- ic-logo-business.png
|  |  |- ic-logo-header.svg
|  |  |- ic-logo-secondary.svg
|  |- wsdlit-base-theme.yml (6)
|  |- wsdlit-nowatermark-theme.yml (7)
|  |- wsdlit-prepress-theme.yml (8)
|  |- wsdlit-theme.yml (9)
```

1. Os arquivos que se encargan de renderizar a páxina de documentación do artefacto.
2. Recursos asciidoc compartidos con todos os proxectos Maven.
3. Plantillas reutilizables nos distintos proxectos Maven.
4. Recursos de uso exclusivo no formato `html5`.
5. Recursos de uso exclusivo no formato `pdf`.
6. Tema **Asciidoctor** `pdf` usado como base para os demáis temas de `wsdlit`.
7. Tema **Asciidoctor** `pdf` sen a marca de auga da empresa.
8. Tema **Asciidoctor** `pdf` listo para pre impresión.
9. Tema **Asciidoctor** `pdf` coa marca de auga da empresa.

## O tema por defecto: wsdlit-default-theme
[wsdlit-default-theme](../../../wsdlit-default-theme/index.html) é o tema por defecto de `wsdlit`,
cuxo aspecto vincúlase directamente a `wsdlit`.
Se o aspecto neutro definido en `wsdlit-default-theme` é suficiente para as súas necesidades,
non precisa configurar nada,
se asume por defecto.

Pode usar este tema:
* Para maquetar a súa documentación, que presentará un aspecto neutro pero homoxeneizado.
* Como punto de partida para crear un novo tema.

## Como usar un tema

### Especificar un tema persoalizado
Nas empresas, corporacións, proxectos, Administracións Públicas...
pode resultar de interese un aspecto visual persoalizado,
o que significa un tema distinto.
Polo tanto,
é preciso un xeito de indicar que artefacto contén o tema a aplicar á documentación.
A forma de indicar o artefacto depende de si o especificamos na **Etapa 1** ou na **Etapa 2**.

#### Durante a Etapa 1
Nesta fase,
se usa o plugin `wsdlit-maven-plugin` para xerar as fontes de documentación en formato `.adoc` que se usarán na **Etapa 2** para construir os formatos `html5`, `pdf`...
Polo tanto seguiremos o formato estabrecida para este plugin respecto das coordenadas dos artefactos:
`groupId:artifactId[:extension[:classifier]]:version`.

Podemos configurar o artefacto do tema ben nas propiedades do proxecto, ben no propio plugin ou ben como propiedade de usuario.

```xml
<project>
    <!-- ... -->
    <properties>
        <!-- Aquí o especificamos como propiedade de usuario. -->
        <wsdlit.theme.artifact>groupId:artifactId[:extension[:classifier]]:version</wsdlit.theme.artifact>
    </properties>
    <!-- ... -->
    <build>
        <!-- ... -->
        <plugins>
            ...
            <plugin>
                <groupId>gal.xunta.amtega.wsdlit</groupId>
                <artifactId>wsdlit-maven-plugin</artifactId>
                <!-- ... -->
                <configuration>
                    <!-- Aquí o especificamos como propiedade de configuración no propio plugin. -->
                    <theme>groupId:artifactId[:extension[:classifier]]:version</theme>
                </configuration>
                <!-- ... -->
            </plugin>
            <!-- ... -->
        </plugins>
    </build>
    <!-- ... -->
</project>
```

E así o configuramos como propiedade de usuario, na liña de comandos usada para xerar as fontes da documentación na **Etapa 1**:

```shell
mvn ... -Dwsdlit.theme.artifact=groupId:artifactId[:extension[:classifier]]:version
```

* Nótese a orde das coordenadas: `groupId:artifactId[:extension[:classifier]]:version`.

#### Durante a Etapa 2
Non recomendamos indicar neste punto o tema,
posto que este tipo de construción desaparecerá a longo prazo,
cando se substitúa `wsdlit-guide-parent` por un lifecycle propio para este tipo de proxectos.

Sen embargo,
pode resultar de interese para comprobacións rápidas do aspecto visual dun tema.

Neste caso,
xa que se usa `wsdlit-guide-parent` para configurar o proceso de construción da documentación e aquel usa
o plugin `maven-dependencies-plugin` para descomprimir o contido do tema,
é necesario indicar as coordenadas do tema seguindo o formato `groupId:artifactId:version[:packaging[:classifier]]`.

```shell
mvn ... -Dwsdlit.theme.artifact=groupId:artifactId:version[:packaging[:classifier]]
```

## Enlaces de interese
* [Asciidoctor PDF Theming](https://docs.asciidoctor.org/pdf-converter/latest/theme/)
  Como crear temas PDF para Asciidoctor.
* HTML [Default Style Sheet](https://docs.asciidoctor.org/asciidoctor/latest/html-backend/default-stylesheet/)
  CSS por defecto para HTML.
