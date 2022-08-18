DESCRIPTION = "DVB / MPEG stream analyzer"
SUMMARY = "DVB / MPEG stream analyzer"
MAINTAINER = "Open Vision Developers"
AUTHOR = "Rainer Scherg <rasc@users.sourceforge.net>"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit gitpkgv

PV = "1.4.55"
PKGV = "1.4.55+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/dvbsnoop;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit autotools
