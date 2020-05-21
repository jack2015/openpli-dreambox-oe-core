SUMMARY = "Movie Player for E2 using the ffmpeg solution"
require conf/license/license-gplv2.inc

RRECOMMENDS_${PN} = " \
    exteplayer3 \
    ffmpeg \
    "

PV = "1.0"
PR = "r0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES = "${PN}"
ALLOW_EMPTY_${PN} = "1"


