SUMMARY = "Skin MetropolisHD by Taapat"
MAINTAINER = "Taapat"
require conf/license/license-gplv2.inc

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/jack2015/skin-MetropolisHD.git"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
	python2 -O -m compileall ${S}/usr/lib/enigma2/python/Components/
}

do_install() {
    install -d ${D}/usr
    cp -r ${S}/usr/* ${D}/usr/
    find ${D}/ -name '*.py' -exec rm {} \;
}
