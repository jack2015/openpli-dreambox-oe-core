SUMMARY = "Enigma2 plugin to play Blu-ray discs"
DESCRIPTION = "Small plugin to to play Blu-ray discs"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-blurayplayer"
SECTION = "multimedia"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "libbluray libudfread"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"
BRANCH = "openpli"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-blurayplayer;branch=${BRANCH};protocol=https"

S = "${WORKDIR}/git"

inherit distutils-openplugins

FILES_${PN}-dbg += "${libdir}/enigma2/python/Plugins/Extensions/BlurayPlayer/.debug"
