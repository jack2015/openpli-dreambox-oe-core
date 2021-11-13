require dreambox-dvb-modules.inc

SRC_URI[modules.md5sum] = "36c8b8d23cd80460b92eb76b88fb1a25"
SRC_URI[modules.sha256sum] = "71a3b3277d29f57e4949ccd9eed0a1bb18e83189fa184e417f8021a95a0aa812"
DREAMBOX_DVB_MODULES_MIRROR = "https://jack2015.github.io/files"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "^(dm800se)$"
