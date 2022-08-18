SUMMARY = "tuxbox tuxtxt for 32bit framebuffer"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
DEPENDS = "freetype libtuxtxt"
DESCRIPTION = "tuxbox tuxtxt for enigma2"

inherit gitpkgv

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/tuxtxt;branch=master;protocol=https"

S = "${WORKDIR}/git/tuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r3"

PACKAGES = "${PN}-src ${PN}-dbg ${PN}-dev ${PN}"
FILES_${PN}-src = "/usr/src ${libdir}/enigma2/python/Plugins/Extensions/Tuxtxt/*.py"
FILES_${PN} = "${libdir}/libtuxtxt32bpp.so.* ${datadir}/fonts ${libdir}/enigma2/python/Plugins/Extensions/Tuxtxt/*.pyo ${sysconfdir}/tuxtxt"
CONFFILES_${PN} = "${sysconfdir}/tuxtxt/tuxtxt2.conf"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-boxtype=generic --with-configdir=/etc \
	DVB_API_VERSION=5\
	"

do_install_append() {
	# remove unused .pyc files
	find ${D}${libdir}/enigma2/python/ -name '*.pyc' -exec rm {} \;
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
