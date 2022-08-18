DESCRIPTION = "A template for writing your own GStreamer plug-in"
MAINTAINER = "samsamsam"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

require conf/license/openpli-gplv2.inc
inherit gitpkgv autotools pkgconfig

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r0"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/gst-ifdsrc;protocol=https;branch=master"

S = "${WORKDIR}/git/"

FILES_${PN} += "${libdir}/gstreamer-1.0"
