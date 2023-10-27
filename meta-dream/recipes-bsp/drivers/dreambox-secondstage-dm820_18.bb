SUMMARY = "Dreambox second stage bootloader DM820"
SECTION = "base"
require conf/license/openpli-gplv2.inc
PROVIDES += "virtual/bootloader"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"

RPROVIDES:${PN} += "dreambox-secondstage"

COMPATIBLE_MACHINE = "^(dm820)$"

SRC_URI[dm820.md5sum] = "597f55dbc5b87133d305690db41c0c8e"
SRC_URI[dm820.sha256sum] = "26afa1d0922f95bedf3856b8ad7866453c2ffc1da2d2cee17b79f11fa077afe6"
MD5SUM = "${@d.getVarFlag('SRC_URI', 'dm820.md5sum', True)}"

SRC_URI = "https://source.mynonpublic.com/dreambox/dreambox-secondstage_${PV}_${MACHINE}.tar.xz;name=${MACHINE}"

S = "${WORKDIR}/dreambox-secondstage_${PV}_${MACHINE}"

do_install() {
    install -d ${D}/usr/share/dreambox-secondstage
    install -m 0644 ${S}/usr/share/dreambox-secondstage/ssbl.bin ${D}/usr/share/dreambox-secondstage/ssbl.bin
}

PACKAGES = "${PN}"
RDEPENDS:${PN} = "flash-scripts"
FILES:${PN} = "/usr/share/dreambox-secondstage/ssbl.bin"
