SUMMARY = "Compatibility for packages that link to older libcrypto or libssl"
require conf/license/license-gplv2.inc
INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"
S = "${WORKDIR}"

SRC_URI = " \
	file://libcrypto.so.1.0.2.arm \
	file://libssl.so.1.0.2.arm \
	file://libcrypto.so.1.0.2.mipsel \
	file://libssl.so.1.0.2.mipsel \
"

RREPLACES:${PN} = "libcrypto1.0.2 libssl1.0.2 libcrypto1.0.0 libssl1.0.0 libcrypto0.9.8 libssl0.9.8 libcrypto0.9.7 libssl0.9.7"
RCONFLICTS:${PN} = "libcrypto1.0.2 libssl1.0.2 libcrypto1.0.0 libssl1.0.0 libcrypto0.9.8 libssl0.9.8 libcrypto0.9.7 libssl0.9.7"

do_install () {
    install -d ${D}${libdir}
    install -m 0755 ${S}/libcrypto.so.1.0.2.${TARGET_ARCH} ${D}${libdir}/libcrypto.so.1.0.2
    install -m 0755 ${S}/libssl.so.1.0.2.${TARGET_ARCH} ${D}${libdir}/libssl.so.1.0.2
    ln -sf libcrypto.so.1.0.2 ${D}${libdir}/libcrypto.so.0.9.7
    ln -sf libssl.so.1.0.2 ${D}${libdir}/libssl.so.0.9.7
    ln -sf libcrypto.so.1.0.2 ${D}${libdir}/libcrypto.so.0.9.8
    ln -sf libssl.so.1.0.2 ${D}${libdir}/libssl.so.0.9.8
    ln -sf libcrypto.so.1.0.2 ${D}${libdir}/libcrypto.so.1.0.0
    ln -sf libssl.so.1.0.2 ${D}${libdir}/libssl.so.1.0.0
}

FILES:${PN} = "${libdir}"

RPROVIDES:${PN} += "libcrypto.so.1.0.2 libssl.so.1.0.2 libcrypto.so.1.0.0 libssl.so.1.0.0 libcrypto.so.0.9.8 libssl.so.0.9.8 libcrypto.so.0.9.7 \
	libssl.so.0.9.7 libcrypto1.0.2 libssl1.0.2 libcrypto1.0.0 libssl1.0.0 libcrypto0.9.8 libssl0.9.8 libcrypto0.9.7 libssl0.9.7"
