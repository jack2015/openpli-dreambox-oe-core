DESCRIPTION = "OpenBlackHole skin files required for rendering the analog clock"
MAINTAINER = "OpenBlackHole"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb723b61539feef013de476e68b5c50a"

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/jack2015/enigma2-plugin-systemplugins-bh-skin-support.git"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
	python2 -O -m compileall ${S}/
}

do_install() {
	install -d ${D}/${libdir}/enigma2/python/Components/Converter
	install -d ${D}/${libdir}/enigma2/python/Components/Renderer
	cp ${S}/BhAnalogic.pyo ${D}/${libdir}/enigma2/python/Components/Converter/
	cp ${S}/Bhclock.pyo ${D}/${libdir}/enigma2/python/Components/Renderer/
}

pkg_preinst_${PN}() {
    rm -f $D/usr/lib/enigma2/python/Components/Converter/BhAnalogic.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Renderer/Bhclock.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Converter/BhAnalogic.pyo > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Renderer/Bhclock.pyo > /dev/null 2>&1
}
