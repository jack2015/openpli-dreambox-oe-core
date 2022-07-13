SUMMARY = "Compatibility for packages that link to older libcrypto or libssl 1.1"
require conf/license/license-gplv2.inc
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"
S = "${WORKDIR}"
PV = "1.0"
PR = "r0"
FILESEXTRAPATHS:prepend := "${THISDIR}/libcrypto-compat:"

SRC_URI = " \
	file://libcrypto.so.1.1.arm \
	file://libssl.so.1.1.arm \
	file://libcrypto.so.1.1.mipsel \
	file://libssl.so.1.1.mipsel \
"

RREPLACES:${PN} = "libcrypto1.1 libssl1.1"
RCONFLICTS:${PN} = "libcrypto1.1 libssl1.1"

do_install () {
    install -d ${D}${libdir}
    install -m 0755 ${S}/libcrypto.so.1.1.${TARGET_ARCH} ${D}${libdir}/libcrypto.so.1.1
    install -m 0755 ${S}/libssl.so.1.1.${TARGET_ARCH} ${D}${libdir}/libssl.so.1.1
}

FILES:${PN} = "${libdir}"

RPROVIDES:${PN} += "libcrypto.so.1.1 libssl.so.1.1 libcrypto1.1 libssl1.1"
