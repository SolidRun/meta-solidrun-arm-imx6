# Copyright (C) 2017 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Linux Backports"
HOMEPAGE = "https://backports.wiki.kernel.org"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/projects/backports/stable/v5.10.110/backports-${PV}.tar.xz \
	   file://yocto_defconfig \
           file://backports-5.10.110-1_hack-makefile-for-yocto.patch;apply=yes \
           file://backports-5.10.110-1_linux-3.14.y-fslc-sr.patch;apply=yes \
"
SRC_URI[md5sum] = "add7d0b8e2a89016894792930380d0d2"
SRC_URI[sha256sum] = "3d958154080c059adaf26512430fd1a8888d65a2228e5e70e48d028201e148b1"

S = "${WORKDIR}/backports-${PV}"

DEPENDS += "coreutils-native flex-native virtual/kernel"

inherit module-base

addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"

do_configure() {
	unset CC CFLAGS CPPFLAGS CXX CXXFLAGS LDFLAGS
	oe_runmake \
		LEX=flex \
		KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} \
		KLIB=${base_libdir}/modules/${KERNEL_VERSION} \
		mrproper
	install -v -m644 "${WORKDIR}/yocto_defconfig" defconfigs/yocto
	oe_runmake \
		LEX=flex \
		KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} \
		KLIB=${base_libdir}/modules/${KERNEL_VERSION} \
		defconfig-yocto
	oe_runmake \
		LEX=flex \
		KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} \
		KLIB=${base_libdir}/modules/${KERNEL_VERSION} \
		savedefconfig
}

do_compile() {
	unset CC CFLAGS CPPFLAGS CXX CXXFLAGS LDFLAGS
	oe_runmake \
		LEX=flex \
		KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} \
		KLIB=${base_libdir}/modules/${KERNEL_VERSION} \
		modules
}

do_install() {
	unset CC CFLAGS CPPFLAGS CXX CXXFLAGS LDFLAGS
	oe_runmake \
		LEX=flex \
		KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} \
		KLIB=${base_libdir}/modules/${KERNEL_VERSION} \
		INSTALL_MOD_PATH="${D}" \
		modules_install
}

FILES_${PN} = ""
FILES_${PN} += "${nonarch_base_libdir}/udev \
                ${sysconfdir}/udev \
		${nonarch_base_libdir} \
               "
EXCLUDE_FROM_WORLD = "1"
