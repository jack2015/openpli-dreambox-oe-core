SUMMARY = "Expand your flash using an USB stick or an NFS share"
MAINTAINER = "Openpli Developers"
require conf/license/openpli-gplv2.inc
PACKAGES = "${PN}"

inherit gitpkgv allarch

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://gitlab.com/jack2015/flashexpander.git;protocol=https;branch=master"

FILES:${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
    python2 -O -m compileall ${S}
}

do_install() {
    install -d ${D}/usr
    cp -rf ${S}/usr/* ${D}/usr/
    find ${D}/ -name '*.py' -exec rm {} \;
    find ${D}/ -name '*.po' -exec rm {} \;
    find ${D}/ -name '*.sh' -exec chmod a+x {} \;
}
