SUMMARY = "Wireless Firmware required by SolidRun MX6 based SOMs"
DESCRIPTION = "Provides firmware required by wireless hardware on \
SolidRun i.MX6 based SOMs."
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE.TIInit_12.8.32;md5=1c9961176d6529283e0d0c983be41b45 \
                    file://LICENSE.wl18xx-fw-4;md5=c5e02be633f1499c109d1652514d85ec"

SRC_URI = "git://github.com/mxOBS/deb-pkg_cuboxi-firmware-wireless.git;protocol=https "
SRCREV = "b2ab7f29f6a6d56147256887de47a2fd12622d2c"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${base_libdir}/firmware/brcm 
    install -d ${D}${base_libdir}/firmware/ti-connectivity 

    cp -fv ${S}/BCM*.hcd ${D}${base_libdir}/firmware/brcm
    cp -fv ${S}/brcm*sdio.* ${D}${base_libdir}/firmware/brcm

    cp -fv ${S}/TIInit_11.8.32.bts ${D}${base_libdir}/firmware/ti-connectivity
    cp -fv ${S}/wl18xx*.bin ${D}${base_libdir}/firmware/ti-connectivity
}

PACKAGES = "${PN}-brcm ${PN}-wilink8"

FILES_${PN}-brcm = "${base_libdir}/firmware/brcm/*"
FILES_${PN}-wilink8 = "${base_libdir}/firmware/ti-connectivity/*"

COMPATIBLE_MACHINE = "solidrun-imx6"
