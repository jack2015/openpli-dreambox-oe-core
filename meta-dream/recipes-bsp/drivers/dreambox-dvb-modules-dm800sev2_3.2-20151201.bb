require dreambox-dvb-modules.inc

DRIVERDATE = "20151201"
SRC_URI[modules.md5sum] = "36840e97de9997d54d85f6639ba221c9"
SRC_URI[modules.sha256sum] = "588dfe928ab52b4ab2bbc6e8fd5d05e0f0ce4336c322f40a282d281674680e28"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

RDEPENDS_${PN} += "kernel-module-stv0299"

COMPATIBLE_MACHINE = "^(dm800sev2)$"
