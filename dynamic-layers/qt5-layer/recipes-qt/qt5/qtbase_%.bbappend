# Copyright (C) 2013 Eric Bénard - Eukréa Electromatique
# Copyright (C) 2016 Freescale Semiconductor
# Copyright (C) 2016, 2017 O.S. Systems Software LTDA.
# Copyright (C) 2017 NXP
# Copyright (C) 2018 SolidRun Ltd.

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_imxgpu2d = "file://0014-Add-IMX-GPU-support.patch \
                           file://0001-egl.prf-Fix-build-error-when-egl-headers-need-platfo.patch \
"

SRC_URI_append_imxgpu3d = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
                         'file://0015-Add-eglfs_viv_wl-to-IMX-GPU.patch ', \
                         'file://0015-Add-eglfs_viv-to-IMX-GPU.patch ', d)} \
"

SRC_URI_append_imxgpu3d = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', \
                         'file://0016-Configure-eglfs-with-egl-pkg-config.patch \
                          file://0017-Cast-EGL-displays-and-windows-to-the-proper-type.patch \
                          file://0018-Switch-FB_MULTI_BUFFERS-to-default-of-triple-bufferi.patch \
                         ', d)} \
"

PACKAGECONFIG_GL_imxpxp   = "gles2"
PACKAGECONFIG_GL_imxgpu2d = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', ' gl', '', d)}"
PACKAGECONFIG_GL_imxgpu3d = "gles2"
PACKAGECONFIG_GL_use-mainline-bsp  = "gbm gles2 kms"

QT_CONFIG_FLAGS_APPEND = ""
QT_CONFIG_FLAGS_APPEND_imxpxp = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '-no-eglfs', '-eglfs -qpa eglfs', d)}"
QT_CONFIG_FLAGS_APPEND_imxgpu2d = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '-no-eglfs', '-no-opengl -linuxfb -no-eglfs', d)}"
QT_CONFIG_FLAGS_APPEND_imxgpu3d = "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '-no-eglfs', '-eglfs -qpa eglfs', d)}"
QT_CONFIG_FLAGS_APPEND_use-mainline-bsp =  "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '-no-eglfs', '-eglfs -qpa eglfs', d)}"
QT_CONFIG_FLAGS_append = " ${QT_CONFIG_FLAGS_APPEND}"

RDEPENDS_qtbase_append = " tslib-conf tslib-calibrate"

PACKAGECONFIG_MULTIMEDIA_append = " alsa"
PACKAGECONFIG_DEFAULT_append = " tslib"

PACKAGECONFIG_FONTS = "fontconfig"

#input devices
PACKAGECONFIG_append = " libinput libevdev mtdev"
PACKAGECONFIG_DEFAULT = "dbus udev libs freetype"
