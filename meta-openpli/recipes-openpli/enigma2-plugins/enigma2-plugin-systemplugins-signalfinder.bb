SUMMARY = "Signal finder for DVB-S2 tuners"
DESCRIPTION = "Signal finder for DVB-S2 tuners"
HOMEPAGE = "https://github.com/Dima73/enigma2-plugin-signalfinder"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://src/__init__.py;md5=7ac668f257efb8bfb222b04dc0c847ff"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-signalfinder;branch=master;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins
