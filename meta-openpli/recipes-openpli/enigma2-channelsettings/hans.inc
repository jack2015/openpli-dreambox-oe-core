LICENSE = "CLOSED"

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://gitlab.openpli.org/openpli/hanssettings.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

RDEPENDS:${PN} = "xz"

inherit gitpkgv allarch

FILES:${PN} = "${sysconfdir}/enigma2/"

do_install () {
	install -d ${D}${sysconfdir}/enigma2
	cp -r ${S}/${SETTINGS_FILENAME}/* ${D}${sysconfdir}/enigma2
	if [ -e ${D}/etc/enigma2/satellites.xml ]; then
		rm -f ${D}/etc/enigma2/satellites.xml
	fi
	if [ -e ${D}/etc/enigma2/satellites.xml ]; then
	        rm -f ${D}/etc/enigma2/userbouquet.favourites.tv
	fi
	if [ -e ${D}/etc/enigma2/satellites.xml ]; then
		rm -f ${D}/etc/enigma2/userbouquet.favourites.radio
	fi
}
