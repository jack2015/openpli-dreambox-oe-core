SUMMARY = "Firmware for rt3070"
require conf/license/license-gplv2.inc
inherit allarch

PACKAGES = "${PN}"
FILES:${PN} += "${nonarch_base_libdir}/firmware"
RDEPENDS:${PN} = "firmware-rt2870"

do_install() {
        install -d ${D}${nonarch_base_libdir}/firmware
	ln -sfn "rt2870.bin" "${D}${nonarch_base_libdir}/firmware/rt3070.bin"
}
