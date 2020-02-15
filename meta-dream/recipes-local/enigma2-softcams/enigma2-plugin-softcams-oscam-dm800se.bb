require conf/license/openpli-gplv2.inc
require oscam-version.inc
SUMMARY = "Softcam for DM800se"
SECTION = "base"
LICENSE = "CLOSED"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

S = "${WORKDIR}"

SRC_URI += " \
	file://oscam \
	file://oscam.conf \
	file://softcam.oscam"

CONFFILES = "/etc/tuxbox/config/oscam.conf"

FILES_${PN} = "/usr/bin /etc"

do_install() {
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${S}/oscam.conf ${D}/etc/tuxbox/config/oscam.conf
	install -d ${D}/usr/bin
	install -m 0755 ${S}/oscam ${D}/usr/bin/oscam
	install -d ${D}/etc/init.d
	install -m 0755 ${S}/softcam.oscam ${D}/etc/init.d/softcam.oscam
	ln -sfn "softcam.oscam" "${D}/etc/init.d/softcam"
	install -d ${D}/etc/rcS.d
	ln -sfn "../init.d/softcam" "${D}/etc/rcS.d/S96softcam"
}
