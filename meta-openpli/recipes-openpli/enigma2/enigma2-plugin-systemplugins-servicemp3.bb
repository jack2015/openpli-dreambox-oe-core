DESCRIPTION = "servicemp3 for enigma2"
AUTHOR = "OpenPLi team <info@openpli.org>"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PROVIDES += "virtual/enigma2-mediaservice"
RPROVIDES:${PN} += "virtual/enigma2-mediaservice"

GST_BASE_RDEPS = "\
	gstreamer1.0-plugins-base-alsa \
	gstreamer1.0-plugins-base-app \
	gstreamer1.0-plugins-base-audioconvert \
	gstreamer1.0-plugins-base-audioresample \
	gstreamer1.0-plugins-base-audiorate \
	gstreamer1.0-plugins-base-videoconvert \
	gstreamer1.0-plugins-base-ivorbisdec \
	gstreamer1.0-plugins-base-ogg \
	gstreamer1.0-plugins-base-playback \
	gstreamer1.0-plugins-base-subparse \
	gstreamer1.0-plugins-base-typefindfunctions \
	gstreamer1.0-plugins-base-vorbis \
	gstreamer1.0-plugins-base-rawparse \
	${@bb.utils.contains_any("TARGET_ARCH", "arm aarch64", "gstreamer1.0-plugins-base-opus", "", d)} \
	"

GST_GOOD_RDEPS = "\
	gstreamer1.0-plugins-good-apetag \
	gstreamer1.0-plugins-good-audioparsers \
	gstreamer1.0-plugins-good-autodetect \
	gstreamer1.0-plugins-good-avi \
	gstreamer1.0-plugins-good-flac \
	gstreamer1.0-plugins-good-flv \
	gstreamer1.0-plugins-good-icydemux \
	gstreamer1.0-plugins-good-id3demux \
	gstreamer1.0-plugins-good-isomp4 \
	gstreamer1.0-plugins-good-matroska \
	gstreamer1.0-plugins-good-mpg123 \
	gstreamer1.0-plugins-good-rtp \
	gstreamer1.0-plugins-good-rtpmanager \
	gstreamer1.0-plugins-good-rtsp \
	gstreamer1.0-plugins-good-udp \
	gstreamer1.0-plugins-good-wavparse \
	gstreamer1.0-plugins-good-wavpack \
	${@bb.utils.contains_any("TARGET_ARCH", "arm aarch64", "gstreamer1.0-plugins-good-vpx", "", d)} \
	"

GST_BAD_RDEPS = "\
	gstreamer1.0-plugins-bad-autoconvert \
	gstreamer1.0-plugins-bad-dash \
	gstreamer1.0-plugins-bad-mpegpsdemux \
	gstreamer1.0-plugins-bad-mpegtsdemux \
	gstreamer1.0-plugins-bad-rtmp \
	gstreamer1.0-plugins-bad-smoothstreaming \
	gstreamer1.0-plugins-bad-faad \
	gstreamer1.0-plugins-bad-hls \
	gstreamer1.0-plugins-bad-videoparsersbad \
	gstreamer1.0-plugins-bad-neonhttpsrc \
	${@bb.utils.contains_any("TARGET_ARCH", "arm aarch64", "gstreamer1.0-plugins-bad-opusparse", "", d)} \
	"

GST_UGLY_RDEPS = "\
	gstreamer1.0-plugins-ugly-amrnb \
	gstreamer1.0-plugins-ugly-amrwbdec \
	gstreamer1.0-plugins-ugly-asf \
	gstreamer1.0-plugins-ugly-cdio \
	gstreamer1.0-plugins-ugly-dvdsub \
	"

DEPENDS = "\
	enigma2 \
	gstreamer1.0-plugins-base gstreamer1.0 \
	"

RDEPENDS:${PN} = "\
	enigma2 \
	"

RRECOMMENDS:${PN} = "\
	glib-networking \
	gstreamer1.0-plugin-subsink \
	virtual/gstreamer1.0-dvbmediasink \
	${GST_BASE_RDEPS} \
	${GST_GOOD_RDEPS} \
	${GST_BAD_RDEPS} \
	${GST_UGLY_RDEPS} \
	"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"

SRC_URI = "${GIT_SITE}/servicemp3.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit autotools gitpkgv pythonnative pkgconfig

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

EXTRA_OECONF = "\
	--with-gstversion=1.0 \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

FILES:${PN} = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceMP3/*.pyo \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceMP3/servicemp3.so"

FILES:${PN}-dev = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceMP3/*.py \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceMP3/servicemp3.la"
