SUMMARY = "Driver for Ralink RT2501PCI/mPCI/CB (RT61:RT2561/RT2561S/RT2661)"
inherit allarch

require conf/license/license-gplv2.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS_${PN} = " \
    rt61 \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
