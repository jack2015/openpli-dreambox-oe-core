DESCRIPTION = "Library that implements bitrate calculations from enigma2"
HOMEPAGE = "https://github/Taapat/bitratecalc"
require conf/license/openpli-gplv2.inc
LICENSE = "CLOSED"

DEPENDS = "enigma2"

inherit gitpkgv autotools pythonnative pkgconfig

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/jack2015/bitratecalc.git"

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/bitratecalc.so"
