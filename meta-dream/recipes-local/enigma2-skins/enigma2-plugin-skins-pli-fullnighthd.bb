SUMMARY = "PLi-FullNightHD skin"
MAINTAINER = "littlesat"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://usr/share/enigma2/PLi-HD/skin.xml;beginline=3;endline=8;md5=1d560d35b9194281a488eb3a32d9c8bf"

inherit gitpkgv allarch

PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/jack2015/skin-PLiHD.git"

FILES_${PN} = "${datadir}/enigma2/"

S = "${WORKDIR}/git"

do_compile[noexec] = "1"

do_install() {
	install -d ${D}${datadir}/enigma2/PLi-FullNightHD
	cp -r ${S}${datadir}/enigma2/PLi-FullNightHD/* ${D}${datadir}/enigma2/PLi-FullNightHD
	chmod -R a+rX ${D}${datadir}/enigma2/
}
