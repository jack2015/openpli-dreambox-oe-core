SUMMARY = "Movie Player for E2 using the ffmpeg solution"
require conf/license/license-gplv2.inc

RRECOMMENDS:${PN} = " \
	exteplayer3 \
	ffmpeg \
	"

PV = "1.0"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES = "${PN}"
ALLOW_EMPTY:${PN} = "1"


