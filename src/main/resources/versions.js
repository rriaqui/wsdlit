/*-
 * #%L
 * wsdlit
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

function generateVersionSelector() {
    const removeChildren = (parent) => {
        while ( parent.lastChild ) {
            parent.removeChild( parent.lastChild );
        }
    };
    const versionElement = document.getElementById( "projectVersion" );
    const versionButton = document.createElement( "button" );
    const versionButtonLabel = document.createTextNode( versionElement.innerText.split( ":" ) [0] + ": " );
    const versionMenu = createVersionMenu();

    removeChildren( versionElement );
    versionButton.innerText = "2.0-SNAPSHOT";
    versionButton.id = "selectProjectVersion";
    versionElement.appendChild( versionButtonLabel );
    versionElement.appendChild( versionButton );
    versionElement.appendChild( versionMenu );

    createLanguageBadges();
}

function createVersionMenu() {
    const versions = VERSIONS;
    const nav = document.createElement( "nav" );
    const ul = document.createElement( "ul" );

    nav.classList.add( "versionMenu" );
    nav.appendChild( ul );

    for ( let i = 0; i < versions.length; i++ ) {
        const version = VERSIONS[ i ];
        const li = document.createElement( "li" );
        const button = document.createElement( "button" );
        button.value = version;
        button.innerText = version;
        button.onclick = function() { changeVersion( version ); };

        li.appendChild( button );
        ul.appendChild( li );
    }
    return nav;
}

function createLanguageBadges() {
    const locales = "en,gl".split( "," ).filter( locale => locale != document.documentElement.lang );
    const labels = {
        en : [ 'english', 'Language' ],
        gl : [ 'galego', 'Idioma' ]
    };
    const bodyColumn = document.getElementById( 'bodyColumn' );
    const title = bodyColumn.getElementsByTagName( 'h1' )[ 0 ];
    const separation = document.createElement( 'span' );

    separation.innerHTML = " ";
    title.append( separation );

    for( var i = 0; i < locales.length; i++ ) {
        const locale = locales[ i ];
        const localeItem = labels[ locale ];
        const badgeLabel = localeItem[ 1 ];
        const badgeValue = localeItem[ 0 ];
        const a = document.createElement( "a" );
        const img = document.createElement( "img" );

        img.src = 'https://img.shields.io/badge/' + badgeLabel + '-' + badgeValue + '-blue';
        a.appendChild( img );
        a.title = badgeLabel + ' ' + badgeValue;
        a.href = "javascript:changeLanguage( '" + locale + "');";

        title.appendChild( a );
    }
}

function changeVersion( newVersion ) {
    var newUrl = window.location.href.replace( "2.0-SNAPSHOT", newVersion );
    window.location.href = newUrl;
}

function changeLanguage( language ) {
    const defaultLocale = "${locales}".split( ',' )[ 0 ];
    if( document.documentElement.lang != language ) {
        var newUrl = window.location.href.replace( /2.0-SNAPSHOT.*/, '2.0-SNAPSHOT' );
        if ( language != defaultLocale ) {
            newUrl = newUrl + '/' + language + '/index.html';
        } else {
            newUrl = newUrl + '/index.html';
        }
    }
    window.location.href = newUrl;
}
