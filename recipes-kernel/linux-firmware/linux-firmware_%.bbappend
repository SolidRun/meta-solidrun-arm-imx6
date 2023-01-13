# Add this layer to SRC_URI search path
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

# Add NVRAM files for SolidRun Boards
SRC_URI:append = " file://brcmfmac4329-sdio.txt \
                   file://brcmfmac4330-sdio.txt \
                   file://wl18xx-conf.bin \
                   https://github.com/SolidRun/deb-pkg_cuboxi-firmware-wireless/raw/master/BCM4329B1.hcd;name=bcm4329b1 \
                   https://github.com/SolidRun/deb-pkg_cuboxi-firmware-wireless/raw/master/BCM4330B1.hcd;name=bcm4330b1"
SRC_URI[bcm4329b1.sha256sum] = "7fbab01012c909b9b2de0fda3a76c43fa1c5c150e08642d7d1dbaf4d5ebf887c"
SRC_URI[bcm4330b1.sha256sum] = "f790c0c981f003a684b2aaea0eb7e475ff33c3b7bc204678fee5efbbe21148a0"

do_install:append() {
	base=solidrun,imx6qdl-som

	install -v -m644 -D ${WORKDIR}/brcmfmac4329-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.$base.txt
	ln -sv brcmfmac4329-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.solidrun,cubox-i-dl.txt
	ln -sv brcmfmac4329-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.solidrun,cubox-i-q.txt
	ln -sv brcmfmac4329-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.solidrun,hummingboard-dl.txt
	ln -sv brcmfmac4329-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.solidrun,hummingboard-q.txt
	ln -sv brcmfmac4329-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.solidrun,hummingboard2-dl.txt
	ln -sv brcmfmac4329-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.solidrun,hummingboard2-q.txt

	install -v -m644 -D ${WORKDIR}/BCM4329B1.hcd ${D}${nonarch_base_libdir}/firmware/brcm/BCM4329B1.hcd

	install -v -m644 -D ${WORKDIR}/brcmfmac4330-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4330-sdio.$base.txt
	ln -sv brcmfmac4330-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4330-sdio.solidrun,cubox-i-dl.txt
	ln -sv brcmfmac4330-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4330-sdio.solidrun,cubox-i-q.txt
	ln -sv brcmfmac4330-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4330-sdio.solidrun,hummingboard-dl.txt
	ln -sv brcmfmac4330-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4330-sdio.solidrun,hummingboard-q.txt
	ln -sv brcmfmac4330-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4330-sdio.solidrun,hummingboard2-dl.txt
	ln -sv brcmfmac4330-sdio.$base.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac4330-sdio.solidrun,hummingboard2-q.txt

	install -v -m644 -D ${WORKDIR}/BCM4330B1.hcd ${D}${nonarch_base_libdir}/firmware/brcm/BCM4330B1.hcd

	# TODO: rename once Linux supports board-specific suffixes
	install -v -m644 -D ${WORKDIR}/wl18xx-conf.bin ${D}${nonarch_base_libdir}/firmware/ti-connectivity/wl18xx-conf.bin
}

FILES:${PN}-bcm4329:append = " ${nonarch_base_libdir}/firmware/brcm/BCM4329*.hcd"
FILES:${PN}-bcm4329:append = " ${nonarch_base_libdir}/firmware/brcm/brcmfmac4329-sdio.*.txt"
FILES:${PN}-bcm4330:append = " ${nonarch_base_libdir}/firmware/brcm/BCM4330*.hcd"
