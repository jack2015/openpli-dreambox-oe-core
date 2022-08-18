DESCRIPTION = "mount UPnP server content as a linux filesystem"
HOMEPAGE = "http://djmount.sourceforge.net/"
LICENSE = "GPLv2+"
DEPENDS = "libupnp fuse"
RDEPENDS_${PN} = "libupnp fuse"

LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

INITSCRIPT_NAME = "djmount"
INITSCRIPT_PARAMS = "defaults"
SRCREV = "02d7d47c4f04054a8a1c174b75839ee38682af86"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"

SRC_URI = "${GIT_SITE}/djmount;protocol=https;branch=fixes"

SRC_URI_append +=" \
	file://init \
	file://04-support-fstab-mounting.patch \
	file://missing_header.patch \
	file://06-fix-build-with-gettext-0.20.x.patch \
	"
EXTRA_OECONF = "--with-external-libupnp-prefix='${STAGING_LIBDIR}' --with-fuse-prefix='${STAGING_LIBDIR}'"

do_configure_prepend() {
	mkdir ${S}/libupnp/config.aux/
	cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}/libupnp/config.aux/config.rpath
}

S = "${WORKDIR}/git"

inherit autotools update-rc.d pkgconfig gettext

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/djmount
}

CFLAGS_append_sh4 += "-std=gnu11"
