SUMMARY = "Enigma2 Skin MX black"
MAINTAINER = "OBH"
require conf/license/openpli-gplv2.inc

RDEPENDS:${PN} = "enigma2-plugin-systemplugins-bh-skin-support enigma2-plugin-extensions-weatherplugin"

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
SRCREV = "${AUTOREV}"

SRC_URI= "git://github.com/jack2015/skin-BH-PLI.git;protocol=${GIT_PROTOCOL}"

S = "${WORKDIR}/git"

FILES:${PN} = "/usr/"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/enigma2/MX_black
    cp -r ${S}${datadir}/enigma2/MX_black/* ${D}${datadir}/enigma2/MX_black
    chmod -R a+rX ${D}${datadir}/enigma2
}
