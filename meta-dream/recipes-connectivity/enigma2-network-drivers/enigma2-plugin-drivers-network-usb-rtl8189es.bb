SUMMARY = "Ralink 8189es v1.0"
inherit allarch

require conf/license/license-gplv2.inc

RRECOMMENDS_${PN} = " \
    rtl8189es \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
