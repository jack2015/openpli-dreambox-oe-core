FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/jack2015/pli-openmultibootmanager.git;protocol=${GIT_PROTOCOL};branch=master"

SRC_URI += " \
	file://nfidump_mipsel_0.4.2 \
	file://nfidump_mipsel_1.0.0 \
	"

FILES:${PN}:append = " /usr/sbin"
NFINAME = "nfidump_mipsel_1.0.0"
NFINAME:dm500hd = "nfidump_mipsel_0.4.2"
NFINAME:dm800se = "nfidump_mipsel_0.4.2"
NFINAME:dm800 = "nfidump_mipsel_0.4.2"

do_install:append() {
    install -d ${D}/usr/sbin
    install -m 0755 ${WORKDIR}/${NFINAME} ${D}/usr/sbin/nfidump
    rm -rf ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/*.py
    rm -rf ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/open-multiboot-branding-helper.pyo
    cp ${S}/src/open-multiboot-branding-helper.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/open-multiboot-branding-helper.py
    chmod 0755 ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/install-nandsim.sh
}

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"
