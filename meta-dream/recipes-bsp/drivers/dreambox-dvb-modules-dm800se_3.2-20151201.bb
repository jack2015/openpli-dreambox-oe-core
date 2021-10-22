SUMMARY = "Hardware drivers for Dreambox"
SECTION = "base"
LICENSE = "CLOSED"
DEPENDS += "virtual/kernel"
PRIORITY = "required"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "^(dm800se)$"

SRC_URI = " \
			https://jack2015.github.io/files/dreambox-dvb-modules-${MACHINE}-3.2-${MACHINE}-${DRIVERDATE}.tar.bz2;name=modules \
			file://modules \
"

SRC_URI[modules.md5sum] = "36c8b8d23cd80460b92eb76b88fb1a25"
SRC_URI[modules.sha256sum] = "71a3b3277d29f57e4949ccd9eed0a1bb18e83189fa184e417f8021a95a0aa812"

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

RDEPENDS:${PN} += "kernel-module-stv0299 dreambox-secondstage-${MACHINE} kernel-${DM_LOCALVERSION}"

# We don't use KERNEL_VERSION in this recipe, because the
# precompiled modules depend on a specific version.
DM_LOCALVERSION = "${@'-'.join('${PV}'.split('-')[:-1])}-${MACHINE}"
DRIVERDATE = "${@'${PV}'.split('-')[-1]}"

FILESEXTRAPATHS:prepend := "${THISDIR}/dreambox-dvb-modules:"

FILES:${PN} += "${sysconfdir}/modules-load.d/${PN}.conf /lib/modules/${DM_LOCALVERSION}/extra"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped"
