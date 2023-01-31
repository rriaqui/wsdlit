<#--
 #%L
 wsdlit-core
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

= ${document.title}
{wsdlit-api-author} <{wsdlit-api-email}>
${document.version}, ${document.date?date?iso_utc}
//
:attachmentdir: assets/attachments
:bl: pass:[ +]
// data-uri provoca que as imaxes se embeban no HTML, en lugar de referenciarse.
// :data-uri: images
//:front-cover-image: image:cover.png[Carátula,595,736]
:globaldir: _global
:icons: font
// TODO Pendiente aún de integrar
// Deshabilitar los fonts remotos de Google
//:!webfonts:
:imagesdir: images
:keywords: refactorización,servizos,versión,versionado
:numbered!:
:page-break:
:sectanchors:
:source-highlighter: rouge
:stem:
//
:xrefstyle: full
//
// Axustes por defecto para os backends.
// HTML5, epub3, mobi e pdf comparten o doctype e o media
:doctype: book
// Activamos docinfo: https://docs.asciidoctor.org/asciidoctor/latest/docinfo/
:docinfo: shared
:media: screen
//
ifdef::backend-pdf[]
:page-layout:
:pdf-page-size: A4
:show-link-uri: false
endif::[]
//
// Idioma galego
:appendix-caption: Apéndice
:appendix-refsig: {appendix-caption}
:appendix-refsig: Apéndice
:caution-caption: Precaución
:chapter-label:
:chapter-refsig: {chapter-label}
:chapter-refsig: Capítulo
:example-caption: Exemplo
:figure-caption: Figura
:important-caption: Importante
:last-update-label: Ultima actualización
:listing-caption: Lista
:manname-title: Nome
:note-caption: Nota
:page-caption: Páxina
//:part-refsig: ???
:partnums:
:preface-title: Prefacio
:section-refsig: Sección
:table-caption: Táboa
:tip-caption: Suxerencia
:toc:
:toc-title: Táboa de contido
:toclevels: 3
:untitled-label: Sen título
:version-label: Versión
:warning-caption: Aviso
//
:sectnums:
//
:sectnumlevels: 4
//
:PDE: PENDENTE DE ESTUDO
// ICONS
:icon-disabled: icon:minus-circle[role=red,set=fas]
:icon-enabled: icon:check-circle[role=green,set=far]
:icon-true: icon:check-circle[role=green,set=far]
:icon-false: icon:times-circle[role=red,set=far]
:icon-unsetted: icon:question-circle[role=silver,set=far]
:icon-bug: icon:bug[role=green,set=fas,title=Bug]
:icon-delete: icon:trash-alt[role=red,set=fas,title=Eliminación]
:icon-new: icon:plus[role=green,set=fas,title=Novo]
:icon-modify: icon:edit[role=red,set=far,title=Modificación]
:icon-rename: icon:file[role=red,set=far,title=Cambio de nome]
:icon-version: icon:code-branch[role=red,set=fas,title="Cambio de versión do esquema"]
:icon-cyclic-reference: icon:sync[role=red,set=fas,title="Referencia cíclica"]

include::intro/index.adoc[leveloffset=+1]

<#list document.services as service>
include::api/${service.name}/index.adoc[leveloffset=+1]
</#list>

include::additional-doc.adoc[leveloffset=+1]
