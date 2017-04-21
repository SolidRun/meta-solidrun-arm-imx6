require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "u-boot which includes support for SolidRun boards such as Cubox-i."
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=bc069111b5e5b1ed8bed98ae73b596ec"

UBOOT_KCONFIG_SUPPORT = "1"
inherit resin-u-boot

PROVIDES += "u-boot"

PV = "v2013.10+git${SRCPV}"

SRCREV = "56c055a72841c9598d13209696a040d42fc0077c"
SRC_URI = " \
    git://github.com/SolidRun/u-boot-imx6.git;branch=imx6-next \
    ${@bb.utils.contains('DISTRO_FEATURES','development-image','file://uEnv-devel.txt','file://uEnv.txt',d)} \
    "

S = "${WORKDIR}/git"

UENV_FILENAME ?= "uEnv-${MACHINE}.txt"

do_deploy_append () {
    if ${@bb.utils.contains('DISTRO_FEATURES','development-image','true','false',d)}; then 
        install ${WORKDIR}/uEnv-devel.txt ${DEPLOYDIR}/${UENV_FILENAME}
    else
        install ${WORKDIR}/uEnv.txt ${DEPLOYDIR}/${UENV_FILENAME}
    fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "solidrun-imx6"
