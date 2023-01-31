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

# Themes
`wsdlit` themes are nothing more than a zip file with a given structure,
containing a set of resources that define a visual appearance that is applied to the build of the documentation.
These resources can be documentation templates,
CSS style sheets for generation in **HTML5** format or more or less complex resources,
such as the configuration of the documentation in **PDF** format,
the images to be used or the resources needed to generate one index page per artifact,
which will allow you to select a version to view its documentation.

---
**Table of Contents**
<!-- MACRO{toc} -->
---

## Layout of a theme
It's very simple,
below we can see a preview of the default theme content,
`wsdlit-default-theme`.

```
/
|- artifact-homepage (1)
| |- wsdlit.css
| |- wsdlit.html
| |- wsdlit.js
| |- wsdlit.png
|- _asciidoc (2)
| |- _templates (3)
| | |- intro
| | | |- index.adoc
| | | |- intro-conventions.adoc
| | | |- intro-legal.adoc
| | |- resources
| | | |- resource-changelist.adoc
| | | |- resource-feedback.adoc
| | | |- resource-otherFormatsForThisDocument.adoc
| | | |- resource-project-authors.adoc
| | | |- resource-trademarks.adoc
| | |- additional-doc.adoc
| | |- docinfo.html
| | |- pde.adoc
|- html5 (4)
| |- css
| | |- font-awesome.css
| | |- font-awesome.min.css
| |- sources
| | |- FontAwesome.otf
| | |- fontawesome-webfont.eot
| | |- fontawesome-webfont.svg
| | |- fontawesome-webfont.ttf
| | |- fontawesome-webfont.woff
| | |- fontawesome-webfont.woff2
|- pdf (5)
| |- sources
| | |- mplus1mn-bold-ascii.ttf
| | |- mplus1mn-bold-italic-ascii.ttf
| | |- mplus1mn-italic-ascii.ttf
| | |- mplus1mn-regular-ascii-conums.ttf
| | |- mplus1mn-regular-fallback.ttf
| | |- notoserif-bold-subset.ttf
| | |- notoserif-bold_italic-subset.ttf
| | |- notoserif-italic-subset.ttf
| | |- notoserif-regular-subset.ttf
| |- images
| | |- bg-draft.svg
| | |- bg-title_page.jpg
| | |- ic-logo-business.png
| | |- ic-logo-header.svg
| | |- ic-logo-secondary.svg
| |- wsdlit-base-theme.yml (6)
| |- wsdlit-nowatermark-theme.yml (7)
| |- wsdlit-prepress-theme.yml (8)
| |- wsdlit-theme.yml (9)
```

1. The files responsible for rendering the documentation page of the artifact.
2. Asciidoc resources shared with all Maven projects.
3. Reusable templates in different Maven projects.
4. Resources for exclusive use in `html5` format.
5. Resources for exclusive use in `pdf` format.
6. **Asciidoctor** `pdf` theme used as a base for the other `wsdlit` themes.
7. **Asciidoctor** `pdf` theme without company watermark.
8. **Asciidoctor** `pdf` theme ready for pre-printing.
9. **Asciidoctor** `pdf` theme with company watermark.

## The default theme: wsdlit-default-theme
[wsdlit-default-theme](../../../wsdlit-default-theme/index.html) is the default theme of `wsdlit`,
whose appearance binds directly to `wsdlit`.
If the neutral theme defined in `wsdlit-default-theme` is sufficient for your needs,
you don't need to configure anything,
it is assumed by default.

You can use this theme:
* To layout your documentation, which will present a neutral but homogenized appearance.
* As a starting point for creating a new theme.

## How to use a theme

### Specify a custom theme
In companies, corporations, projects, Public Administrations...
a personalized visual aspect may be of interest,
which means a different theme.
Therefore,
a way is needed to indicate which artifact contains the theme to be applied to the documentation.
The way to indicate the artifact depends on whether we specified it in **Stage 1** or **Stage 2**.

#### During Stage 1
In this phase,
the `wsdlit-maven-plugin` plugin is used to generate the documentation sources in `.adoc` format that will be used in **Stage 2** to build the `html5`, `pdf`...
Therefore we will follow the format established for this plugin regarding the coordinates of the artifacts:
`groupId:artifactId[:extension[:classifier]]:version`.

We can configure the theme artifact either in the project properties, or in the plugin itself or as a user property.

```xml
<project>
     <!-- ... -->
     <properties>
         <!-- Here we specify it as a user property. -->
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
                     <!-- Here we specify it as a configuration property in the plugin itself. -->
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

And this is how we set it as a user property, on the command line used to generate the documentation sources in **Stage 1**:

```shell
mvn ... -Dwsdlit.theme.artifact=groupId:artifactId[:extension[:classifier]]:version
```

* Note the order of coordinates: `groupId:artifactId[:extension[:classifier]]:version`.

#### During Stage 2
We do not recommend indicating the topic at this point,
since this type of building will disappear in the long run,
when replacing `wsdlit-guide-parent` with a lifecycle of its own for this kind of project.

However,
it may be of interest for quick checks of the visual appearance of a theme.

In this case,
since `wsdlit-guide-parent` is used to configure the documentation build process and that uses
the `maven-dependencies-plugin` plugin to unzip the theme content,
it is necessary to indicate the coordinates of the subject following the format `groupId:artifactId:version[:packaging[:classifier]]`.

```shell
mvn ... -Dwsdlit.theme.artifact=groupId:artifactId:version[:packaging[:classifier]]
```

## Links of interest
* [Asciidoctor PDF Theming](https://docs.asciidoctor.org/pdf-converter/latest/theme/)
  How to create PDF themes for Asciidoctor.
* HTML [Default Style Sheet](https://docs.asciidoctor.org/asciidoctor/latest/html-backend/default-stylesheet/)
  Default CSS for HTML.
