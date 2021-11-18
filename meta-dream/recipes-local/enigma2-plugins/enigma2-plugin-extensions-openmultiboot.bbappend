FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/jack2015/pli-openmultibootmanager.git;protocol=${GIT_PROTOCOL};branch=master"

SRC_URI += " \
	file://nfidump_mipsel_0.4.2 \
	file://nfidump_mipsel_1.0.0 \
	file://nfidump_mipsel_2.0.0 \
	"

FILES:${PN}:append:mipsel = " /usr/sbin"
NFINAME:dm7020hd = "nfidump_mipsel_1.0.0"
NFINAME:dm7020hdv2 = "nfidump_mipsel_1.0.0"
NFINAME:dm8000 = "nfidump_mipsel_1.0.0"
NFINAME:dm500hdv2 = "nfidump_mipsel_1.0.0"
NFINAME:dm800sev2 = "nfidump_mipsel_1.0.0"
NFINAME:dm500hd = "nfidump_mipsel_0.4.2"
NFINAME:dm800se = "nfidump_mipsel_0.4.2"
NFINAME:dm800 = "nfidump_mipsel_0.4.2"
NFINAME:dm820 = "nfidump_mipsel_2.0.0"
NFINAME:dm520 = "nfidump_mipsel_2.0.0"
NFINAME:dm7080 = "nfidump_mipsel_2.0.0"

do_install:append() {
    rm -rf ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/*.py
    rm -rf ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/open-multiboot-branding-helper.pyo
    cp ${S}/src/open-multiboot-branding-helper.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/open-multiboot-branding-helper.py
    chmod 0755 ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/install-nandsim.sh
}

do_install:append:mipsel() {
    install -d ${D}/usr/sbin
    install -m 0755 ${WORKDIR}/${NFINAME} ${D}/usr/sbin/nfidump
}

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"
