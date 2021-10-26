require dreambox-secondstage.inc

COMPATIBLE_MACHINE = "^(dm800sev2)$"

SRC_URI[md5sum] = "aa4b8a7042d64fcc268427503106cbef"
SRC_URI[sha256sum] = "41166f89d6f63ab088c12bf46dfdca164bf5bcff9d4ddb703650dbaa7f82a17d"
SRC_URI_dm800sev2 = "https://jack2015.github.io/files/secondstage-dm800sev2-89.bin"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"
