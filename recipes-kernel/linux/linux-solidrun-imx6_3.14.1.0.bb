include recipes-kernel/linux/linux-imx.inc
include recipes-kernel/linux/linux-dtb.inc

SUMMARY = "SolidRun 3.14.yy LTS kernel based on linux-fscl"
DESCRIPTION = "Linux kernel that is based on the Linux-fslc 3.14-1.0.x-mx6 \
branch with optimize support for SolidRun hardware."

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "3.14-1.0.x-mx6-sr"
SRCREV = "c720cc05fe29269f61504a22984fa38a2dfe0e44"
LOCALVERSION ?= "_1.0.0_mx6-sr"

SRC_URI = "git://github.com/SolidRun/linux-fslc.git;branch=${SRCBRANCH} \
           file://defconfig"

COMPATIBLE_MACHINE = "(solidrun-imx6)"
