SUMMARY = "Firmware for rtl8192cufw_TMSC"
require conf/license/license-gplv2.inc
PACKAGES = "${PN}"

inherit allarch

SRC_URI = " \
    file://rtl8192cufw_TMSC.zip \
"

S = "${WORKDIR}"

FILES_${PN} += "${nonarch_base_libdir}/firmware"

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware
    install -d ${D}${nonarch_base_libdir}/firmware/rtlwifi
    install -m 0644 rtl8192cufw_TMSC.bin ${D}${nonarch_base_libdir}/firmware/rtlwifi
}
