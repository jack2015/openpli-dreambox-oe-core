FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
           file://0002-subparse-set-need_segment-after-sink-pad-received-GS.patch \
           file://0003-riff-media-added-fourcc-to-all-ffmpeg-mpeg4-video-caps.patch \
           file://0009-glimagesink-Downrank-to-marginal.patch \
"

PACKAGECONFIG = " \
    ${GSTREAMER_ORC} \
    ${PACKAGECONFIG_GL} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'alsa x11', d)} \
    jpeg ogg pango png theora vorbis \
    cdparanoia gio opus tremor \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland egl', '', d)} \
"

PACKAGECONFIG[gio] = "-Dgio=enabled,-Dgio=disabled,glib-2.0"

PACKAGE_NO_LOCALE = "1"
PV = "1.22.5"

SRC_URI[md5sum] = "cc16aeaefdfd3917e009412a22d07fac"
SRC_URI[sha256sum] = "edd4338b45c26a9af28c0d35aab964a024c3884ba6f520d8428df04212c8c93a"
