require dreambox-dvb-modules.inc

SRC_URI[modules.md5sum] = "8907582110a9b782c9a5e54115a3d34d"
SRC_URI[modules.sha256sum] = "1874b0992eb09836463f96a70fb7f3c75ffab8954a80a050d296aa409ffcf1f6"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "^(dm500hd)$"
