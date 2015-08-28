include recipes-kernel/linux/linux-imx.inc
include recipes-kernel/linux/linux-dtb.inc

SUMMARY = "SolidRun 3.14.yy LTS kernel based on linux-fscl"
DESCRIPTION = "Linux kernel that is based on the Linux-fslc 3.14-1.0.x-mx6 \
branch with optimize support for SolidRun hardware."

DEPENDS += "lzop-native bc-native"

SRCBRANCH ?= "3.14-1.0.x-mx6-sr"
SRCREV = "cd17e70255ec6daac0aa46b7314d30eebf3b7220"
LOCALVERSION ?= "-${SRCBRANCH}"

SRC_URI = "git://github.com/SolidRun/linux-fslc.git;branch=${SRCBRANCH} \
           file://defconfig"

COMPATIBLE_MACHINE = "(solidrun-imx6)"
