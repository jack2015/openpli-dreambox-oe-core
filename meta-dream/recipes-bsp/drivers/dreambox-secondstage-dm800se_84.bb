SUMMARY = "Dreambox second stage bootloader"
require conf/license/openpli-gplv2.inc
PROVIDES += "virtual/bootloader"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "dreambox-buildimage-native"
DATE = "${@time.strftime('%Y%m%d',time.gmtime())}"

COMPATIBLE_MACHINE = "^(dm800se)$"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped"

inherit deploy

do_configure[nostamp] = "1"

SRC_URI = "https://jack2015.github.io/files/secondstage-dm800se-84.bin"
SRC_URI[md5sum] = "79ce5d144684cf0338c3480a409c564b"
SRC_URI[sha256sum] = "e66dc359b3457827af4b3f1561f08732950113c475be43c2442b7b0f4bbb992b"

PACKAGES = "${PN}"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/tmp
	buildimage --arch ${MACHINE} --raw ${EXTRA_BUILDCMD} \
	--erase-block-size ${DREAMBOX_ERASE_BLOCK_SIZE} \
	--flash-size ${DREAMBOX_FLASH_SIZE} \
	--sector-size ${DREAMBOX_SECTOR_SIZE} \
	--boot-partition=${DREAMBOX_PART0_SIZE}:secondstage-${MACHINE}-${PV}.bin \
	> ${D}/tmp/secondstage-${MACHINE}.bin
}

# busybox nandwrite requires oob patch and is not working in every box
RDEPENDS:${PN} = "mtd-utils"
FILES:${PN} = "/tmp/secondstage-${MACHINE}.bin"

pkg_postinst:${PN}() {
	if [ -z "$D" ] && grep -q '\<${MACHINE}\>' /proc/stb/info/model; then
		if [ -f /tmp/secondstage-${MACHINE}.bin ]; then
			flash_erase /dev/mtd1 0 0 2>/dev/null
			nandwrite -m -n -o /dev/mtd1 /tmp/secondstage-${MACHINE}.bin 2>/dev/null
			rm /tmp/secondstage-${MACHINE}.bin
		fi
	fi
}

do_deploy() {
	install -d ${DEPLOYDIR}
	install -m 0644 secondstage-${MACHINE}-${PV}.bin ${DEPLOYDIR}/secondstage-${MACHINE}.bin
}

RDEPENDS:${PN} = ""

pkg_postinst:${PN}() {
	echo "Due to space limitations, this is now a dummy package!"
	echo "Nothing will happen when you try to update or reinstall it!"
	echo "The secondstage bootloader will remain the same!"
}

addtask deploy before do_package after do_install
