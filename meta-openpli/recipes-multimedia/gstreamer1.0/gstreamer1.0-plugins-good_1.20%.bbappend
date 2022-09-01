FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
           file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio x11', d)} \
    ${@bb.utils.contains('TUNE_FEATURES', 'm64', 'asm', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'novp9', '', 'vpx',d)} \
    bz2 cairo flac gdk-pixbuf gudev jpeg lame libpng \
    mpg123 soup speex taglib v4l2 wavpack \
"

PACKAGE_NO_LOCALE = "1"
