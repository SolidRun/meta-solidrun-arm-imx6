include recipes-kernel/linux/linux-imx.inc

SUMMARY = "SolidRun 4.9.yy LTS kernel based on NXPs ga source"
DESCRIPTION = "Linux kernel that is based on the NXP's imx_4.9.x_1.0.0_ga \
branch with optimize support for SolidRun hardware."

DEPENDS += "lzop-native bc-native"

SRCBRANCH = "solidrun-imx_4.9.x_1.0.0_ga"
SRCREV = "457f3ded3371686c15786fd8e934158625647a88"
LOCALVERSION = "-mx6-sr"

SRC_URI = "git://github.com/SolidRun/linux-fslc.git;branch=${SRCBRANCH} \
           file://defconfig"

COMPATIBLE_MACHINE = "solidrun-imx6"
