SUMMARY = "Library that implements bitrate calculations from enigma2"
DESCRIPTION = "Library that implements bitrate calculations from enigma2"
HOMEPAGE = "https://github/Taapat/bitratecalc"
require conf/license/license-gplv2.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "enigma2"

inherit gitpkgv autotools pythonnative pkgconfig

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
SRCREV = "${AUTOREV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/bitratecalc;branch=master;protocol=https"

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/enigma2/python/Components/Converter/bitratecalc.so"

do_install_append() {
	install -d ${D}${libdir}/enigma2/python/Components/Converter/
	mv -f ${D}${libdir}/bitratecalc.so ${D}${libdir}/enigma2/python/Components/Converter/bitratecalc.so
	chmod 0755 ${D}${libdir}/enigma2/python/Components/Converter/bitratecalc.so
}
