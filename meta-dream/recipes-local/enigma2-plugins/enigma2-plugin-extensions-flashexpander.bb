SUMMARY = "Expand your flash using an USB stick or an NFS share"
DESCRIPTION = "Expand your flash using an USB stick or an NFS share"
MAINTAINER = "Openpli Developers"
require conf/license/openpli-gplv2.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

inherit gitpkgv pythonnative pkgconfig

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://gitee.com/jackgee2021/flashexpander.git;protocol=https;branch=master"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
	python2 -O -m compileall ${S}/usr/lib/enigma2/python/Plugins/Extensions/Flashexpander/*.py
}

do_install() {
	install -d ${D}/usr
	cp -r ${S}/usr/* ${D}/usr/
	rm -rf ${D}/usr/lib/enigma2/python/Plugins/Extensions/Flashexpander/*.py
}
