inherit gitpkgv

PV = "1.1+git${SRCPV}"
PKGV = "1.1+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/linux-firmware;protocol=https;branch=master"
S = "${WORKDIR}/git"

require linux-firmware-local.inc
