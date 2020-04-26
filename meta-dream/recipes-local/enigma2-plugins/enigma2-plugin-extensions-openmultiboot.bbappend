SRC_URI = "git://github.com/jack2015/pli-openmultibootmanager.git"
do_install_append() {
    rm -rf ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/*.py
    rm -rf ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/open-multiboot-branding-helper.pyo
    cp ${S}/src/open-multiboot-branding-helper.py ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/open-multiboot-branding-helper.py
    chmod 0755 ${D}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/install-nandsim.sh
}
