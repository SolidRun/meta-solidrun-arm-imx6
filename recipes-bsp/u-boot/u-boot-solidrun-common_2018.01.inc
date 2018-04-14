# Copyright (C) 2012-2018 O.S. Systems Software LTDA.
# Released under the MIT license (see COPYING.MIT for the terms)
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

require recipes-bsp/u-boot/u-boot.inc

PROVIDES += "u-boot"

SRC_URI = "git://github.com/SolidRun/u-boot.git;branch=${SRCBRANCH}"

SRCREV = "457cdd60c331e4beeded338123f6965f11780418"
SRCBRANCH = "v2018.01-solidrun-imx6"

PV = "v2018.01-solidrun-imx6+git${SRCPV}"

S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "(mx6|mx7)"
