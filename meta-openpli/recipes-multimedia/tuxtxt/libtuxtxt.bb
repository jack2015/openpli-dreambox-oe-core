SUMMARY = "tuxbox libtuxtxt"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "libpng freetype zlib"

inherit gitpkgv autotools pkgconfig

SRC_URI = "git://gitlab.com/jack2015/tuxtxt.git;protocol=https \
	file://tuxtxt_getPressedKey.patch \
	file://0001-fix-secfault-w-use-wrong-line_length.patch \
	${@bb.utils.contains('DISTRO_FEATURES', 'tuxtxtfhd', 'file://libtuxtxt_FHD.patch', '', d)} \
	"

S = "${WORKDIR}/git/libtuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

EXTRA_OECONF = "--with-boxtype=generic DVB_API_VERSION=5"

do_configure:prepend() {
    touch ${S}/NEWS
    touch ${S}/README
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
}

FILES:${PN} = "${libdir}/libtuxtxt.so.*"
FILES:${PN}-dev = "/usr/include/ ${libdir}/libtuxtxt.la ${libdir}/libtuxtxt.so ${libdir}/pkgconfig/tuxbox-tuxtxt.pc"
