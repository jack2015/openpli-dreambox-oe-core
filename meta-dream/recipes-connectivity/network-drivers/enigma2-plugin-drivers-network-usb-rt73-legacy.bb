SUMMARY = "Driver for Ralink USB devices RT2571W, RT2573 & RT2671 (legacy)"
inherit allarch

require conf/license/license-gplv2.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS:${PN} = " \
    rt73 \
    firmware-rt73 \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
