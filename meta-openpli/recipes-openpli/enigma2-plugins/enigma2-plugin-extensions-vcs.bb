SUMMARY = "Auto video clipping switcher for enigma2"
DESCRIPTION = "Auto video clipping switcher for enigma2"
HOMEPAGE = "https://github.com/Dima73/vcs"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=8eddbaffb25ee762a200b712d7242dbe"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/vcs;branch=master;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins
