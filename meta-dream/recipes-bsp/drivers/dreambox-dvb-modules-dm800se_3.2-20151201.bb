SUMMARY = "Hardware drivers for Dreambox"
require conf/license/openpli-gplv2.inc
DEPENDS += "virtual/kernel"
PRIORITY = "required"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "^(dm800se)$"
DRIVERDATE = "20151201"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"


SRC_URI = " https://jack2015.github.io/files/dreambox-dvb-modules-dm800se-3.2-dm800se-20151201.tar.bz2;name=modules \
	file://modules \
"

SRC_URI[modules.md5sum] = "36c8b8d23cd80460b92eb76b88fb1a25"
SRC_URI[modules.sha256sum] = "71a3b3277d29f57e4949ccd9eed0a1bb18e83189fa184e417f8021a95a0aa812"
DM_LOCALVERSION = "3.2-dm800se"

inherit module-base

do_compile() {
}

do_install() {
	install -d ${D}${sysconfdir}/modules-load.d
	install -m 0644 ${WORKDIR}/modules ${D}${sysconfdir}/modules-load.d/${PN}.conf
	install -d ${D}/lib/modules/${DM_LOCALVERSION}/extra
	install -m 0644 ${WORKDIR}/LICENSE ${D}/lib/modules/${DM_LOCALVERSION}/extra
	install -m 0644 ${WORKDIR}/*.ko ${D}/lib/modules/${DM_LOCALVERSION}/extra
}

PACKAGES = "${PN}"
FILESEXTRAPATHS:prepend := "${THISDIR}/dreambox-dvb-modules:"
RDEPENDS:${PN} += "kernel-module-stv0299 dreambox-secondstage-${MACHINE} kernel-${DM_LOCALVERSION} kernel-vmlinux"
FILES:${PN} += "${sysconfdir}/modules-load.d/${PN}.conf /lib/modules/${DM_LOCALVERSION}/extra"
