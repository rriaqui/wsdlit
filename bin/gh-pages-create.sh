#!/bin/sh

###
# #%L
# wsdlit
# %%
# Copyright (C) 2021 - 2022 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
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

BASEDIR=$( readlink -f "$( dirname "$0" )" )
TARGET_PATH=$( readlink -f "${BASEDIR}/../target" )
GH_PAGES_PATH=${TARGET_PATH}/gh-pages
SITE_PATH=${TARGET_PATH}/site
STAGING_PATH=${TARGET_PATH}/staging

echo "Deleting 'target/gh-pages', 'target/site' and 'target/staging'"
[ -d "${GH_PAGES_PATH}" ] && rm -Rf "${GH_PAGES_PATH}"
[ -d "${SITE_PATH}" ] && rm -Rf "${SITE_PATH}"
[ -d "${STAGING_PATH}" ] && rm -Rf "${STAGING_PATH}"

echo 'Building target/site'
mvn clean site site:stage

echo ''
echo 'Building target/gh-pages'
mvn post-site
