SUMMARY = "Skin MetropolisHD by Taapat"
MAINTAINER = "Taapat"
require conf/license/license-gplv2.inc

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
SRCREV = "${AUTOREV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/skin-MetropolisHD;branch=master;protocol=https"

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
