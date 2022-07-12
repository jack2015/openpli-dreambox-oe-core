SUMMARY = "Hardware drivers for Dreambox"
require conf/license/openpli-gplv2.inc
DEPENDS += "virtual/kernel"
PRIORITY = "required"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "^(dm800sev2)$"
DRIVERDATE = "20151201"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"

SRC_URI = " https://jack2015.github.io/files/dreambox-dvb-modules-dm800sev2-3.2-dm800sev2-20151201.tar.bz2;name=modules \
	file://modules \
"

SRC_URI[modules.md5sum] = "36840e97de9997d54d85f6639ba221c9"
SRC_URI[modules.sha256sum] = "588dfe928ab52b4ab2bbc6e8fd5d05e0f0ce4336c322f40a282d281674680e28"
DM_LOCALVERSION = "3.2-dm800sev2"

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
