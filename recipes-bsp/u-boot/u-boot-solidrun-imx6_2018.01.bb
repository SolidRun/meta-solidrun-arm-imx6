#
# Copyright 2022 Josua Mayer <josua@solid-run.com>
#
require recipes-bsp/u-boot/u-boot.inc
UBOOT_INITIAL_ENV = ""
FILESEXTRAPATHS:prepend := "${THISDIR}/u-boot-solidrun-imx6:${THISDIR}/u-boot:"

LOCALVERSION = "+solidrun-imx6"
inherit fsl-u-boot-localversion

DESCRIPTION = "SolidRun U-Boot fork for i.MX6 based products."
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
SRCREV = "a6e3032a725a443ff8701fca3635613a4d1c8275"
SRCBRANCH = "v2018.01-solidrun-imx6"
SRC_URI = "git://github.com/SolidRun/u-boot.git;branch=${SRCBRANCH};protocol=https \
           file://boot.txt \
           file://0001-mx6cuboxi-Support-build-time-selection-of-board-vari.patch"
PV = "2018.01+git${SRCPV}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
DEPENDS += "bc-native bison-native flex-native python3-setuptools-native"
PROVIDES += "u-boot"

do_configure:append:imx6qdlhummingboardcbi() {
	sed -i "s;^.*\(CONFIG_SYS_BOARD_SR_AUTO\)=.*$;# \1 is not set;g" ${B}/.config
	sed -i "s;^.*\(SYS_BOARD_SR_SOLIDSENSE_N6\)=.*$;# \1 is not set;g" ${B}/.config
	sed -i "s;^.*\(CONFIG_SYS_BOARD_SR_HUMMINGBOARD_CBI\).*$;\1=y;g" ${B}/.config
}

do_configure:append:imx6qdlsolidsense() {
	sed -i "s;^.*\(CONFIG_SYS_BOARD_SR_AUTO\)=.*$;# \1 is not set;g" ${B}/.config
	sed -i "s;^.*\(SYS_BOARD_SR_SOLIDSENSE_N6\)=.*$;\1=y;g" ${B}/.config
	sed -i "s;^.*\(CONFIG_SYS_BOARD_SR_HUMMINGBOARD_CBI\).*$;# \1 is not set;g" ${B}/.config
}
