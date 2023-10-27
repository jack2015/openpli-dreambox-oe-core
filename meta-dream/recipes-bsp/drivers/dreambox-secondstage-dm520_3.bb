SUMMARY = "Dreambox second stage bootloader DM520"
SECTION = "base"
require conf/license/openpli-gplv2.inc
PROVIDES += "virtual/bootloader"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RPROVIDES:${PN} += "dreambox-secondstage"

COMPATIBLE_MACHINE = "^(dm520)$"

SRC_URI[dm520.md5sum] = "e1b388c62396e57b3c359fcc922eedad"
SRC_URI[dm520.sha256sum] = "d2253ab36ee0871206d019126e15750ae1eaefccf4cb11b6dca834d16f81c415"
MD5SUM = "${@d.getVarFlag('SRC_URI', 'dm520.md5sum', True)}"

SRC_URI = "https://source.mynonpublic.com/dreambox/dreambox-secondstage_${PV}_${MACHINE}.tar.xz;name=${MACHINE}"

S = "${WORKDIR}/dreambox-secondstage_${PV}_${MACHINE}"

do_install() {
    install -d ${D}/usr/share/dreambox-secondstage
    install -m 0644 ${S}/usr/share/dreambox-secondstage/ssbl.bin ${D}/usr/share/dreambox-secondstage/ssbl.bin
}

PACKAGES = "${PN}"
RDEPENDS:${PN} = "flash-scripts"
FILES:${PN} = "/usr/share/dreambox-secondstage/ssbl.bin"
