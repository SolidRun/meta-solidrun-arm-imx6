DESCRIPTION = "Firmware for CYW43455"
SECTION = "kernel"
LICENSE = "Proprietary"

# this is a hack, our repository does not contain the license file ...
LIC_FILES_CHKSUM = "file://overlay/lib/firmware/brcm/brcmfmac43455-sdio.bin;md5=2cb4b58cddaf9b4c2a0ac4ad79b4d252 \
                    file://overlay/lib/firmware/brcm/brcmfmac43455-sdio.clm_blob;md5=d66abc1f26d8073150237ef4f6967bd1 \
                    file://overlay/lib/firmware/brcm/brcmfmac43455-sdio.txt;md5=c6e43a135a69b9bde8736a4eb05aaf99"

SRCREV = "fbc8635647fd4c4fb832e9d59a7d251b6f81ac8b"
SRC_URI = "git://github.com/SolidRun/imx6_buildroot;protocol=http;branch=master"
S = "${WORKDIR}/git"

do_install () {
	install -d "${D}/lib/firmware/brcm"
	install -v -m644 overlay/lib/firmware/brcm/brcmfmac43455-sdio.bin "${D}/lib/firmware/brcm/"
	install -v -m644 overlay/lib/firmware/brcm/brcmfmac43455-sdio.clm_blob "${D}/lib/firmware/brcm/"
	install -v -m644 overlay/lib/firmware/brcm/brcmfmac43455-sdio.txt "${D}/lib/firmware/brcm/"
}

FILES_${PN} = "/lib/firmware/brcm/*"
