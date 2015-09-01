RDEPENDS_${PN} += " libgbm libglapi " 

SRC_URI_remove_mx6 = " file://0004-Desktop-shell-Don-t-assume-there-is-a-pointer.patch "

PACKAGECONFIG_remove = "kms"

EXTRA_OECONF_remove = "--enable-drm-compositor"
EXTRA_OECONF += "--disable-drm-compositor"

FILES_${PN} += " ${libdir}/weston/*.so "
