SUMMARY = "Extended number zap addon for enigma2"
DESCRIPTION = "Extended number zap addon for enigma2"
HOMEPAGE = "https://github.com/Dima73/Extended-NumberZap"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=2996e0935082e0f3e6d91abf95e750a1"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/Extended-NumberZap;branch=master;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins
