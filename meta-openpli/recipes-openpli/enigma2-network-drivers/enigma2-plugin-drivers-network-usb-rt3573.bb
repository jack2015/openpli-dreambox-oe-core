SUMMARY = "rt3573 802.11a/b/g/n 2.4/5 GHz USB Single Chip"
inherit allarch

require conf/license/license-gplv2.inc

RRECOMMENDS_${PN} = " \
    rt3573 \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
