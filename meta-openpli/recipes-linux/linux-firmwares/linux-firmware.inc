inherit gitpkgv

PV = "1.1+git${SRCPV}"
PKGV = "1.1+git${GITPKGV}"

SRC_URI = "git://gitlab.com/jack2015/linux-firmware.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

require linux-firmware-local.inc
