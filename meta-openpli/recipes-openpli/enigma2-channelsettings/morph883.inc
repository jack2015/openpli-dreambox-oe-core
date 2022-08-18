LICENSE = "CLOSED"

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/Enigma2-Settings;branch=master;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv allarch

FILES_${PN} = "/etc/enigma2/"

do_install () {
	install -d ${D}/etc/enigma2
	cp -r ${S}/${SETTINGS_FILENAME}/* ${D}/etc/enigma2
	if [ -e ${D}/etc/enigma2/satellites.xml ]; then
		rm -f ${D}/etc/enigma2/satellites.xml
	fi
}
