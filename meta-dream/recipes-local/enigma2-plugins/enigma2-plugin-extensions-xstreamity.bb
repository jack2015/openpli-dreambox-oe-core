DESCRIPTION = "IPTV Xtream Codes playlists player by KiddaC"
HOMEPAGE = "https://www.linuxsat-support.com"
MAINTAINER = "kiddac"
PRIORITY = "optional"
require conf/license/license-gplv2.inc

RDEPENDS_${PN} = "python-argparse python-image python-imaging python-multiprocessing python-requests"

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
SRCREV = "${AUTOREV}"

inherit gitpkgv allarch rm_python_pyc compile_python_pyo

SRC_URI = "git://github.com/jack2015/XStreamity.git"

S = "${WORKDIR}/git"

FILES_${PN} = " ${libdir}/enigma2/python/Components/Converter/* \
                ${libdir}/enigma2/python/Components/Renderer/* \
		${sysconfdir}/enigma2/xstreamity \
                ${libdir}/enigma2/python/Plugins/Extensions/XStreamity/*"

do_install() {
    install -d ${D}${libdir}/enigma2/python/Components/Converter
    install -d ${D}${libdir}/enigma2/python/Components/Renderer
    install -d ${D}${libdir}/enigma2/python/Plugins/Extensions/XStreamity
    install -d ${D}${sysconfdir}/enigma2/xstreamity
    touch ${D}${sysconfdir}/enigma2/xstreamity/playlists.txt
    cp -rf ${S}/XStreamity/usr/lib/enigma2/python/Components/Converter/* ${D}${libdir}/enigma2/python/Components/Converter/
    cp -rf ${S}/XStreamity/usr/lib/enigma2/python/Components/Renderer/* ${D}${libdir}/enigma2/python/Components/Renderer/
    rm -f ${S}/XStreamity/usr/lib/enigma2/python/Plugins/Extensions/XStreamity/owibranding.pyo
    cp -rf ${S}/XStreamity/usr/lib/enigma2/python/Plugins/Extensions/XStreamity/* ${D}${libdir}/enigma2/python/Plugins/Extensions/XStreamity/
}

pkg_preinst_${PN}() {
#!/bin/sh
if [ -f "$D${sysconfdir}/enigma2/X-Streamity/playlists.json" ]
    then
    rm -f $D${sysconfdir}/enigma2/X-Streamity/playlists.json > /dev/null 2>&1
fi

if [ -f "$D${sysconfdir}/enigma2/xstreamity/playlists.json" ]
    then
    rm -f $D${sysconfdir}/enigma2/xstreamity/playlists.json > /dev/null 2>&1
fi
}

pkg_postrm_${PN} () {
#!/bin/sh
rm -rf $D${libdir}/enigma2/python/Plugins/Extensions/XStreamity > /dev/null 2>&1
rm -f $D${libdir}/enigma2/python/Components/Renderer/xRunningText.pyo > /dev/null 2>&1
rm -f $D${libdir}/enigma2/python/Components/Converter/xServiceInfo.pyo > /dev/null 2>&1
rm -rf $D${sysconfdir}/enigma2/X-Streamity
rm -rf $D${sysconfdir}/enigma2/xstreamity
}
