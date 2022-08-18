DESCRIPTION = "SAT>IP server"
MAINTAINER = "PLi team"
require conf/license/openpli-gplv2.inc

inherit gitpkgv

PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/minisatip;protocol=https;branch=master"

FILES_${PN} = "${sbindir}/minisatip"

S = "${WORKDIR}/git"
BUILD = "${WORKDIR}/build"

inherit autotools

do_install() {
	install -m 755 -d ${D}${sbindir}
	install -m 755 ${BUILD}/minisatip ${D}${sbindir}
}
