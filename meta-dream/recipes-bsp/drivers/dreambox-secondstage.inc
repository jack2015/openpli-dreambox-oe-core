SUMMARY = "Dreambox second stage bootloader"
require conf/license/openpli-gplv2.inc
PROVIDES += "virtual/bootloader"
SECTION = "base"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "dreambox-buildimage-native"

inherit deploy

INSANE_SKIP:${PN} = "empty-dirs"

do_configure[nostamp] = "1"

SRC_URI = "https://source.mynonpublic.com/dreambox/secondstage-${MACHINE}-${PV}.bin"

PACKAGES = "${PN}"

S = "${WORKDIR}"

do_install() {
	install -d ${D}/tmp
	buildimage --arch ${MACHINE} --raw ${EXTRA_BUILDCMD} \
	--erase-block-size ${DREAMBOX_ERASE_BLOCK_SIZE} \
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
