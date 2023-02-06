#!/bin/sh

###
# #%L
# wsdlit
# %%
# Copyright (C) 2021 - 2023 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
# %%
# This file is part of "wsdlit".
#
# "wsdlit" is free software: you can redistribute it and/or modify
# it under the terms of:
# European Union Public License, either Version 1.2 or – as soon
# they will be approved by the European Commission - subsequent versions of
# the EUPL;
#
# "wsdlit" is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# European Union Public License for more details.
#
# You may obtain a copy of tce European Union Public Licence at:
# http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
# #L%
###

# $1 = Target file
# $2 = URL
downloadExtensionLib() {
    file="${EXTENSION_LIB_PATH}/${1}"

    if [ ! -f "${file}" ]; then
        sudo wget --quiet -O "${file}" "${2}" \
        && echo "[  OK   ] Downloading ${1}" \
        || echo "[ ERROR ] Downloading ${1}"
    fi
}

# $1 = Command to check
# $2 = Ubuntu package name to install
installPackageIfNotExists() {
    echo "Checking '${1}' command in $OS_NAME"
    if ! [ -x "`command -v ${1}`" ]; then
        case $OS_NAME in
        'Linux')
            sudo apt-get install -qq "${2}" -y \
            && echo "[  OK   ] Installing '${2}' package" \
            || echo "[ ERROR ] Installing '${2}' package"
            ;;
        esac
    fi

    if ! [ -x "`command -v ${1}`" ]; then
        exit 1
    fi
}

determineLibExtensionDir() {
    path=`mvn help:evaluate -Dexpression="java.ext.dirs"  --non-recursive  --quiet -B -DforceStdout \
      | sed '1,5d' \
       | sed 's/:/\n/g' \
       | tail -1`

echo "path=$path"
    if [ "${path}" = "" ]; then
        EXTENSION_LIB_PATH=/usr/java/packages/lib/ext
    else
        EXTENSION_LIB_PATH=${path}
    fi

    mkdir -p "${EXTENSION_LIB_PATH}"
    ls -l "${EXTENSION_LIB_PATH}"
}

EXTENSION_LIB_PATH=
OS_NAME="`uname`"

determineLibExtensionDir

echo "EXTENSION_LIB_PATH=${EXTENSION_LIB_PATH}"

downloadExtensionLib "javagalician-java6.jar" "https://github.com/javagalician/javagalician-java6/releases/download/javagalician-java6-1.1/javagalician-java6-1.1.jar"

installPackageIfNotExists gs ghostscript
