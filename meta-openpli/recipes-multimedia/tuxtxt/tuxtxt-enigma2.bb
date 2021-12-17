SUMMARY = "tuxbox tuxtxt 32bit framebuffer for enigma2"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=393a5ca445f6965873eca0259a17f833"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "freetype libtuxtxt"

inherit autotools pkgconfig gitpkgv

GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/tuxtxt.git;protocol=${GIT_PROTOCOL} \
	${@bb.utils.contains('DISTRO_FEATURES', 'tuxtxtfhd', 'file://tuxtxt_FHD.patch', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'tuxtxtfhd', ' \
	file://tuxtxt.ttf \
	file://tuxtxt_nonbold.ttf \
	', '', d)} \
"

S = "${WORKDIR}/git/tuxtxt"

PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
PR = "r3"

EXTRA_OECONF = "--with-boxtype=generic --with-configdir=/etc \
	${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
	DVB_API_VERSION=5 \
	"

do_configure:prepend() {
    touch ${S}/NEWS
    touch ${S}/README
    touch ${S}/AUTHORS
    touch ${S}/ChangeLog
    if ${@bb.utils.contains('DISTRO_FEATURES', 'tuxtxtfhd', 'true', 'false', d)}; then
        sed 's/UseTTF 0/UseTTF 1/g' -i ${S}/data/tuxtxt2.conf
        sed 's/TTFWidthFactor16 28/TTFWidthFactor16 29/g' -i ${S}/data/tuxtxt2.conf
        sed 's/TTFHeightFactor16 16/TTFHeightFactor16 14/g' -i ${S}/data/tuxtxt2.conf
    fi
}

do_install:append() {
    # remove unused .pyc files
    find ${D}${libdir}/enigma2/python/ -name '*.pyc' -exec rm {} \;
    if ${@bb.utils.contains('DISTRO_FEATURES', 'tuxtxtfhd', 'true', 'false', d)}; then
        install -d ${D}/usr/share/fonts
        rm -f ${D}/usr/share/fonts/tuxtxt.ttf
        rm -f ${D}/usr/share/fonts/tuxtxt_nonbold.ttf
        cp -f ${WORKDIR}/tuxtxt.ttf ${D}/usr/share/fonts/tuxtxt.ttf
        cp -f ${WORKDIR}/tuxtxt_nonbold.ttf ${D}/usr/share/fonts/tuxtxt_nonbold.ttf
    fi
}

PACKAGES = "${PN}-src ${PN}-dbg ${PN}-dev ${PN}"
FILES:${PN}-src = "/usr/src ${libdir}/enigma2/python/Plugins/Extensions/Tuxtxt/*.py"
FILES:${PN} = "${libdir}/libtuxtxt32bpp.so.* ${datadir}/fonts ${libdir}/enigma2/python/Plugins/Extensions/Tuxtxt/*.pyo ${sysconfdir}/tuxtxt"
CONFFILES:${PN} = "${sysconfdir}/tuxtxt/tuxtxt2.conf"
