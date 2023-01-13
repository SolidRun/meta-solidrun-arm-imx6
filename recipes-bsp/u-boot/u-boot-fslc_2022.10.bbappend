# add custom boot-script
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:${THISDIR}/u-boot:"
SRC_URI:append = " file://boot.txt"
SRC_URI += "file://0001-autoboot-detect-usb-boot-before-preboot.patch"

#do_configure:append() {
#	${S}/scripts/config --file ${B}/.config --enable SPL_USB_GADGET
#	${S}/scripts/config --file ${B}/.config --enable SPL_USB_SDP_SUPPORT
#}
