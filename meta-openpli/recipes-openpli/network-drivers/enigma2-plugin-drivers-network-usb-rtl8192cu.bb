SUMMARY = "new universal rt18xxx kernel driver"
inherit allarch

require conf/license/license-gplv2.inc

RRECOMMENDS:${PN} = " \
    rtl8192cu \
    firmware-rtl8192cu \
    firmware-rtl8192cufw \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
