DESCRIPTION = "gstreamer subsink plugin"
SECTION = "multimedia"
PRIORITY = "optional"
DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base glib-2.0 glib-2.0-native"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/gst-plugin-subsink;branch=master;protocol=https"

S = "${WORKDIR}/git"

inherit gitpkgv

GSTVERSION = "1.0"

PV = "${GSTVERSION}+git${SRCPV}"
PKGV = "${GSTVERSION}+git${GITPKGV}"

EXTRA_OECONF = "--with-gstversion=${GSTVERSION}"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/gstreamer-${GSTVERSION}/*.so*"
FILES_${PN}-dev += "${libdir}/gstreamer-${GSTVERSION}/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-${GSTVERSION}/*.a"
FILES_${PN}-dbg += "${libdir}/gstreamer-${GSTVERSION}/.debug"

