#
# Copyright 2022 Josua Mayer <josua@solid-run.com>
#
require recipes-bsp/u-boot/u-boot.inc
UBOOT_INITIAL_ENV = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/u-boot:"

LOCALVERSION = "+solidrun-imx6"
inherit fsl-u-boot-localversion

DESCRIPTION = "SolidRun U-Boot fork for i.MX6 based products."
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
SRCREV = "a6e3032a725a443ff8701fca3635613a4d1c8275"
SRCBRANCH = "v2018.01-solidrun-imx6"
SRC_URI = "git://github.com/SolidRun/u-boot.git;branch=${SRCBRANCH};protocol=https \
           file://boot.txt"
PV = "2018.01+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
COMPATIBLE_MACHINE:imx6qdlcubox = "imx6qdlcubox"
DEPENDS += "bc-native bison-native flex-native python3-setuptools-native"
PROVIDES += "u-boot"
