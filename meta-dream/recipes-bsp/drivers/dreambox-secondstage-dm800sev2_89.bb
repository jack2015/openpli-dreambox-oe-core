SUMMARY = "Dreambox second stage bootloader"
require conf/license/openpli-gplv2.inc
PROVIDES += "virtual/bootloader"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "dreambox-buildimage-native"

COMPATIBLE_MACHINE = "^(dm800sev2)$"
do_configure[nostamp] = "1"
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"

inherit deploy

INSANE_SKIP:${PN} = "empty-dirs"

SRC_URI = "https://jack2015.github.io/files/secondstage-dm800sev2-89.bin"
SRC_URI[md5sum] = "aa4b8a7042d64fcc268427503106cbef"
SRC_URI[sha256sum] = "41166f89d6f63ab088c12bf46dfdca164bf5bcff9d4ddb703650dbaa7f82a17d"

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

addtask deploy before do_package after do_install
