FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "0.6.2+git${SRCPV}"

SRC_URI += "  \
			file://smbnetfs.common.conf \
			file://smbnetfs.user.conf \
			file://init \
			"

FILES:${PN} += "${sysconfdir}/*.conf ${sysconfdir}/init.d"
CONFFILES:${PN} = "${sysconfdir}/smbnetfs.user.conf"
inherit update-rc.d

INITSCRIPT_NAME = "${PN}.sh"

do_install:append() {
	install -d ${D}${sysconfdir}
	install -m 600 ${WORKDIR}/smbnetfs.common.conf ${D}${sysconfdir}/
	install -m 600 ${WORKDIR}/smbnetfs.user.conf ${D}${sysconfdir}/
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}.sh
}
