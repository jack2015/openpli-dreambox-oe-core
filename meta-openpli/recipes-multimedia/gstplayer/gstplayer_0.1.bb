DESCRIPTION = "gstplayer by samsamsam"
SECTION = "multimedia"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

inherit pkgconfig

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/gstplayer;protocol=https;branch=master"

S = "${WORKDIR}/git/"

do_compile() {
    cd ${S}/gst-1.0
    ${CC} *.c ../common/*.c -I../common/ `pkg-config --cflags --libs gstreamer-1.0 gstreamer-pbutils-1.0` -o gstplayer_gst-1.0 ${LDFLAGS}
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/gst-1.0/gstplayer_gst-1.0 ${D}${bindir}/gstplayer
}

pkg_postinst_${PN}() {
    ln -sf gstplayer $D${bindir}/gstplayer_gst-1.0
}

pkg_prerm_${PN}() {
    rm -f $D${bindir}/gstplayer_gst-1.0
}
