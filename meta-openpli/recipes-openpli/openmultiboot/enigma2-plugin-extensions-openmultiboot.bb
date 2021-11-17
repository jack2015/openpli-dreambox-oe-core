DESCRIPTION = "Multi boot loader manager for enigma2 box"
HOMEPAGE = "https://github.com/Dima73/pli-openmultibootmanager"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

RDEPENDS:${PN} = "python-subprocess mtd-utils mtd-utils-ubifs kernel-module-nandsim openmultiboot"
inherit gitpkgv distutils-openplugins
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://github.com/Dima73/pli-openmultibootmanager.git;protocol=${GIT_PROTOCOL}"
S = "${WORKDIR}/git"

pkg_preinst:${PN}() {
#!/bin/sh
if mountpoint -q ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot; then
    echo "openMultiBoot will only install on main image."
    echo "Child image is running - canceling installation!"
    sleep 3
    exit 1
else
    echo "Main image is running - proceeding installation..."
    exit 0
fi
}

pkg_postrm:${PN}() {
#!/bin/sh

if mountpoint -q ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot; then
    echo "openMultiBoot will only remove on main image."
    exit 0
else
    echo "Main image is running - proceeding removing..."
fi

rm -rf /sbin/init
ln -s /sbin/init.sysvinit /sbin/init
rm -rf /sbin/open-multiboot-branding-helper.py

chown -Rh root:root ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot
rm -rf ${libdir}/enigma2/python/Plugins/Extensions/OpenMultiboot
exit 0

}
