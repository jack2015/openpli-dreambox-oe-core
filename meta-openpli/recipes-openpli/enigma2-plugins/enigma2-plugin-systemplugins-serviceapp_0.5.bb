SUMMARY = "serviceapp service for enigma2"
DESCRIPTION = "serviceapp service for enigma2"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "enigma2 uchardet openssl"
RDEPENDS_${PN} = "enigma2 uchardet openssl python-json"
RRECOMMENDS_${PN} = "exteplayer3 gstplayer"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/serviceapp;branch=develop;protocol=https"

S = "${WORKDIR}/git"

inherit autotools gitpkgv pythonnative pkgconfig

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

EXTRA_OECONF = "\
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

do_install_append() {
	rm ${D}${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/*.pyc
}

FILES_${PN} = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/*.pyo \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/locale/*/LC_MESSAGES/ServiceApp.mo \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/serviceapp.so"

FILES_${PN}-dev = "\
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/*.py \
	${libdir}/enigma2/python/Plugins/SystemPlugins/ServiceApp/serviceapp.la"

