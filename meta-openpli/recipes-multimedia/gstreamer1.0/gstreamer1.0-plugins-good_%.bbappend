FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG_SOUP = "soup2"
RDEPENDS:${PN}-soup += "libsoup-2.4"

SRC_URI:append = " \
           file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
           file://0002-Revert-souphttpsrc-Always-use-the-content-decoder.patch \
"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio x11', d)} \
    ${@bb.utils.contains('TUNE_FEATURES', 'm64', 'asm', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'novp9', '', 'vpx',d)} \
    bz2 cairo flac gdk-pixbuf gudev jpeg lame libpng \
    mpg123 soup2 speex taglib v4l2 wavpack \
"

PACKAGE_NO_LOCALE = "1"
PV = "1.22.5"

SRC_URI[md5sum] = "8c5a67f702ff5f30290be35ac27b3ac9"
SRC_URI[sha256sum] = "b67b31313a54c6929b82969d41d3cfdf2f58db573fb5f491e6bba5d84aea0778"
