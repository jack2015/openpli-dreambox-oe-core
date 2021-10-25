SUMMARY = "Broadcom driver"
require conf/license/openpli-gplv2.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "*"
FILES:${PN}-dev = ""
FILES:${PN}-dbg = ""

ALLOW_EMPTY:${PN}-dev = "1"
ALLOW_EMPTY:${PN}-dbg = "1"

EXCLUDE_FROM_WORLD = "1"
