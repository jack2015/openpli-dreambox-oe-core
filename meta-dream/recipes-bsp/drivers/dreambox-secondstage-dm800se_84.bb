require dreambox-secondstage.inc

COMPATIBLE_MACHINE = "^(dm800se)$"

SRC_URI[md5sum] = "79ce5d144684cf0338c3480a409c564b"
SRC_URI[sha256sum] = "e66dc359b3457827af4b3f1561f08732950113c475be43c2442b7b0f4bbb992b"
SRC_URI_dm800se = "https://jack2015.github.io/files/secondstage-dm800se-84.bin"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

RDEPENDS_${PN} = ""

pkg_postinst_${PN}() {
	echo "Due to space limitations, this is now a dummy package!"
	echo "Nothing will happen when you try to update or reinstall it!"
	echo "The secondstage bootloader will remain the same!"
}
