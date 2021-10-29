require dreambox-secondstage.inc

COMPATIBLE_MACHINE = "^(dm800se)$"

SRC_URI_dm800se = "https://jack2015.github.io/files/secondstage-dm800se-84.bin"

SRC_URI[md5sum] = "79ce5d144684cf0338c3480a409c564b"
SRC_URI[sha256sum] = "e66dc359b3457827af4b3f1561f08732950113c475be43c2442b7b0f4bbb992b"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"
