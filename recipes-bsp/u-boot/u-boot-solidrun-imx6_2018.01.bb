# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017 NXP

DESCRIPTION = "U-Boot supporting SolidRun i.MX6 based boards."

require recipes-bsp/u-boot/u-boot-solidrun-common_2018.01.inc

S = "${WORKDIR}/git"

LOCALVERSION ?= "-${SRCBRANCH}"
