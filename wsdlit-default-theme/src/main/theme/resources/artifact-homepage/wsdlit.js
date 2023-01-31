/*-
 * #%L
 * wsdlit-default-theme
 * %%
 * Copyright (C) 2021 - 2022 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
 * %%
 * This file is part of "wsdlit".
 * 
 * "wsdlit" is free software: you can redistribute it and/or modify
 * it under the terms of:
 * European Union Public License, either Version 1.2 or – as soon
 * they will be approved by the European Commission - subsequent versions of
 * the EUPL;
 * 
 * "wsdlit" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * European Union Public License for more details.
 * 
 * You may obtain a copy of tce European Union Public Licence at:
 * http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 * #L%
 */

function apiContainer_onLoad( iframe ) {
    document.title = iframe.contentDocument.title;
    iframe.title = document.title;
}

function nexusReadVersions() {
    getReleases( handleNexusResponse );
}

function handleNexusResponse( request ) {
    if (request.readyState === request.DONE && request.status === 200) {
        var versions = request.responseXML.getElementsByTagName( "text" );
        var nonDevelopmentVersions = getAllVersions( versions, 25 );
        console.log( nonDevelopmentVersions );
        console.log( request.responseXML );
        fillOptionsFromVersions( "versions", nonDevelopmentVersions );
        changeApiVersion( nonDevelopmentVersions[0].textContent );
    }
}

function newVersionSelected( element ) {
    changeApiVersion( element.selectedOptions[ 0 ].label );
}

function changeApiVersion( version ) {
    var newUrl = removeHomepage( window.location.href );
    var iFrame = document.getElementById( "apiContainer" );
    newUrl = newUrl + '/' + version + '/index.html';
    iFrame.src = newUrl;
}


function getReleases( callback ) {
    var request = new XMLHttpRequest();
    var normalizedUrl = removeHomepage( window.location.href );

    console.log( "NormalizedUrl = ", normalizedUrl );
    request.open('GET', normalizedUrl );
    request.responseType = 'document';
    request.overrideMimeType('text/xml');

    request.onreadystatechange = function() {
        if (request.readyState === request.DONE && request.status === 200) {
            callback( request );
        }
    };
    request.send();
    return request;
}

function removeHomepage( url ) {
    if ( url.endsWith( 'wsdlit.html' ) ||  url.endsWith( '/' ) ) {
        return removeLastComponent( url );
    }
    return url;
}

function removeLastComponent( url ) {
    var slash = url.lastIndexOf( '/' );
    return url.substring( 0, slash);
}    

function getAllVersions( versions, size ) {
    var index = versions.length - 1;
    var nonDevelopmentVersions = [];
    var versionRE = /^\d+(\.\d+){1,3}(-a\d+|-b\d+|-rc\d+)?(-SNAPSHOT)?$/
    
    while( index > -1 && nonDevelopmentVersions.length < size ) {
        var item = versions[ index ];
        var version = item.textContent;
        if ( versionRE.test( version ) ) {
            nonDevelopmentVersions.push( item );
        }
        index = index - 1;
    }
    return nonDevelopmentVersions;
}

function fillOptionsFromVersions( id, versions ) {
    var list = document.getElementById( id );
    
    for( var i = 0; i < versions.length; i++ ) {
        var version = new Option( versions[ i ].textContent, i );
        list.options.add( version );
    }
}
