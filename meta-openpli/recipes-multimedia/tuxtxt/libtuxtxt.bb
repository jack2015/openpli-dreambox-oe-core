SUMMARY = "tuxbox libtuxtxt"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"
DEPENDS = "libpng freetype zlib"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit gitpkgv autotools pkgconfig

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/tuxtxt;protocol=https;branch=master \
	file://tuxtxt_getPressedKey.patch \
	file://0001-fix-secfault-w-use-wrong-line_length.patch \
	${@bb.utils.contains('DISTRO_FEATURES', 'tuxtxtfhd', 'file://libtuxtxt_FHD.patch', '', d)} \
	"

S = "${WORKDIR}/git/libtuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

EXTRA_OECONF = "--with-boxtype=generic DVB_API_VERSION=5"

do_configure_prepend() {
    touch ${S}/NEWS
    touch ${S}/README
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
}

FILES_${PN} = "${libdir}/libtuxtxt.so.*"
FILES_${PN}-dev = "/usr/include/ ${libdir}/libtuxtxt.la ${libdir}/libtuxtxt.so ${libdir}/pkgconfig/tuxbox-tuxtxt.pc"
