SUMMARY = "Driver for Ralink RT8070/3070/3370/5370/5372 USB 802.11abgn WiFi sticks"
inherit allarch

require conf/license/license-gplv2.inc

RRECOMMENDS:${PN} = "\
    rt3070 \
    firmware-rt3070 \
"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
