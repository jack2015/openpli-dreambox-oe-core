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
