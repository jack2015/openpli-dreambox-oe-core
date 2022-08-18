DESCRIPTION = "Skin SimpleGrayHD by Taapat"
MAINTAINER = "Taapat"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

inherit gitpkgv allarch

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/skin-SimpleGrayHD;branch=master;protocol=https"

FILES_${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
	python2 -O -m compileall ${S}/usr/lib/enigma2/python/Components/
}

do_install() {
	install -d ${D}/usr
	cp -r ${S}/usr/* ${D}/usr/
}
