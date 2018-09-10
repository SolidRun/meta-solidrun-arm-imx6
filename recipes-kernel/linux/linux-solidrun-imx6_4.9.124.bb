include recipes-kernel/linux/linux-imx.inc

SUMMARY = "SolidRun 4.9.yy LTS kernel based on NXPs ga source"
DESCRIPTION = "Linux kernel that is based on the NXP's imx_4.9.x_1.0.0_ga \
branch with optimize support for SolidRun hardware."

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "solidrun-imx_4.9.x_1.0.0_ga"
SRCREV = "3b4f1a2b7c57f198641c0a45e23fe7255a164daf"
LOCALVERSION = "-mx6-sr"

SRC_URI = "git://github.com/SolidRun/linux-fslc.git;branch=${SRCBRANCH} \
           file://defconfig"

COMPATIBLE_MACHINE = "solidrun-imx6"
