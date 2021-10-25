DESCRIPTION = "Transmission is a BitTorrent client w/ a built-in Ajax-Powered Webif GUI."
SECTION = "network"
HOMEPAGE = "www.transmissionbt.com/"
DEPENDS = "gnutls openssl gettext libtool intltool-native curl glib-2.0-native libevent librtmp"
LICENSE = "GPLv2 & GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=0dd9fcdc1416ff123c41c785192a1895"

SRC_URI = "https://github.com/transmission/transmission-releases/raw/master/transmission-${PV}.tar.xz;name=archive \
           file://init \
           file://config"

INITSCRIPT_NAME = "transmission.sh"
INITSCRIPT_PARAMS = "defaults 60 "

inherit autotools update-rc.d gettext

do_install:append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/transmission.sh
	install -d ${D}${sysconfdir}/default
	install -m 0755 ${WORKDIR}/config ${D}${sysconfdir}/default/transmission-daemon
	install -d ${D}${localstatedir}/lib/transmission-daemon
}

PACKAGES += "${PN}-gui ${PN}-client"

FILES:${PN} = "${bindir}/transmission-daemon ${datadir}/transmission ${sysconfdir} ${localstatedir}/lib/${PN}-daemon"
FILES:${PN}-client = "${bindir}/transmission-remote ${bindir}/transmission-cli ${bindir}/transmission-create ${bindir}/transmission-show ${bindir}/transmission-edit"
FILES:${PN}-gui += "${bindir}/transmission-gtk ${datadir}/icons ${datadir}/applications ${datadir}/pixmaps"
CONFFILES:${PN} = "${sysconfdir}/default/transmission-daemon"
