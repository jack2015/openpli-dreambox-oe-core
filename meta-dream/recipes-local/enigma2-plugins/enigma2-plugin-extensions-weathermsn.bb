SUMMARY = "Weather MSN - Weather forecast for five days"
DESCRIPTION = "Weather forecast for 5 days"
MAINTAINER = "Sirius"
HOMEPAGE = "www.gisclub.tv"
require conf/license/license-gplv2.inc

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/jack2015/enigma2-plugins-weathermsn.git"
SRCREV = "${AUTOREV}"
FILES:${PN} = "${libdir}/enigma2/"

S = "${WORKDIR}/git"

do_compile() {
    python2 -O -m compileall ${S}
}

do_install() {
    install -d ${D}${libdir}/enigma2/python/Plugins/Extensions
    cp -r --preserve=mode,links ${S}/python/Plugins/Extensions/WeatherMSN ${D}${libdir}/enigma2/python/Plugins/Extensions/
    chmod -R a+rX ${D}${libdir}/enigma2/
    find ${D}/ -name '*.pyc' -exec rm {} \;
    find ${D}/ -name '*.po' -exec rm {} \;
    find ${D}/ -name '*.py' -exec rm {} \;
}
