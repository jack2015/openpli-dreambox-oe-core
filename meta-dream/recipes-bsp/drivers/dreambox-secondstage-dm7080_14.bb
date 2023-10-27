SUMMARY = "Dreambox second stage bootloader DM7080"
SECTION = "base"
require conf/license/openpli-gplv2.inc
PROVIDES += "virtual/bootloader"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RPROVIDES:${PN} += "dreambox-secondstage"

COMPATIBLE_MACHINE = "^(dm7080)$"

SRC_URI[dm7080.md5sum] = "ef12410e7944e23cffaa6753531d7bdd"
SRC_URI[dm7080.sha256sum] = "c50354e66d6f247ab533a518b9df42c2067f6711cd210f9e25983233018df016"
MD5SUM = "${@d.getVarFlag('SRC_URI', 'dm7080.md5sum', True)}"

SRC_URI = "https://source.mynonpublic.com/dreambox/dreambox-secondstage_${PV}_${MACHINE}.tar.xz;name=${MACHINE}"

S = "${WORKDIR}/dreambox-secondstage_${PV}_${MACHINE}"

do_install() {
    install -d ${D}/usr/share/dreambox-secondstage
    install -m 0644 ${S}/usr/share/dreambox-secondstage/ssbl.bin ${D}/usr/share/dreambox-secondstage/ssbl.bin
}

PACKAGES = "${PN}"
RDEPENDS:${PN} = "flash-scripts"
FILES:${PN} = "/usr/share/dreambox-secondstage/ssbl.bin"
