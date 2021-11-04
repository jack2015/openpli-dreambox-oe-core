#  12 Jan, 2019 1 commit e365183ffd99f113286908d7a33e2f02c6368e47
SUMMARY = "A template for writing your own GStreamer plug-in"
MAINTAINER = "samsamsam"
HOMEPAGE = "https://gitlab.com/e2i/gst-ifdsrc"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

require conf/license/openpli-gplv2.inc
inherit autotools pkgconfig

PV = "1.0"

SRC_URI = "file://gst-ifdsrc.zip"

SRC_URI[md5sum] = "3de0436d62ed308a84426a41cfbd9104"
SRC_URI[sha256sum] = "c7c52dcdf6236ae807aaa00a50c0bb7c1c4d3dd2acada2c53296fcca81111b66"

S = "${WORKDIR}"

FILES:${PN} += "${libdir}/gstreamer-1.0"
