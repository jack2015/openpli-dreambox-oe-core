SUMMARY = "Advanced history zap selector"
DESCRIPTION = "Advanced history zap selector"
HOMEPAGE = "https://github.com/Dima73/enigma2-plugin-extensions-historyzapselector"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=b5f3c7ed409cbcc6d849e694f25729ba"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-extensions-historyzapselector;branch=master;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins
