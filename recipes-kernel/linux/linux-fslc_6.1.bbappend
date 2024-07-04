# Add this layer to SRC_URI search path
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Enable non-default kernel configs required for Docker
SRC_URI:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'virtualization', ' file://docker.scc', '', d)}"

# Enable non-default kernel configs required for ir receiver
SRC_URI:append = " file://ir-receiver.scc"

# Enable non-default kernel configs required for bluetooth
SRC_URI:append = " file://bluetooth.scc"

# Enable non-default kernel configs required for ethernet phy
SRC_URI:append = " file://ethernet-phy.scc"

# TODO: add kernel modules to image by default:
# - br_netfilter
# - bridge
# - ip_tables
# - iptable_filter
# - iptable_nat
# - llc
# - nf_nat
# - stp
# - x_tables
# - xt_MASQUERADE
# - xt_addrtype
# - xt_conntrack

# Add SolidRun patches
SRC_URI += "file://0001-arm-dts-Add-support-for-SolidRun-HummingBoard-CBi.patch \
            file://0002-ARM-dts-solidsense-add-miscelaneous-changes-from-lin.patch \
            file://0003-ARM-dts-hummingboard-disable-can-interface-for-micro.patch \
            file://0004-ARM-dts-imx6qdl-sr-som-brcm-add-bluetooth-nodes.patch \
            file://0005-HACK-ARM-dts-imx6qdl-sr-som-brcm-enable-BCM4330-blue.patch"
