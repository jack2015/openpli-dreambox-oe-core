SUMMARY = "Hardware drivers for Dreambox DM820"
SECTION = "base"
require conf/license/openpli-gplv2.inc
DEPENDS += "virtual/kernel"
PRIORITY = "required"
PACKAGE_ARCH = "${MACHINE_ARCH}"

KV = "3.4-4.0"
DRIVERDATE = "20181018"

SRC_URI[dm820.md5sum] = "f219ccdae57db1d2602fa57abe3f80cc"
SRC_URI[dm820.sha256sum] = "52261e5f574226a88d9528151ec60952ddb9d1f401824729897cba82c7ba0cd7"

COMPATIBLE_MACHINE = "^(dm820)$"

MD5SUM = "${@d.getVarFlag('SRC_URI', 'dm820.md5sum', True)}"

BCMNUMBER = "bcm7435"

DREAMBOX_DVB_MODULES_MIRROR ?= "http://dreamboxupdate.com/download/opendreambox/2.5.0/dreambox-dvb-modules/${KV}-${MACHINE}-${DRIVERDATE}/${MACHINE}/${MD5SUM}"

SRC_URI = "${DREAMBOX_DVB_MODULES_MIRROR}/dreambox-dvb-modules_${KV}-${MACHINE}-${DRIVERDATE}_${MACHINE}.tar.xz;name=${MACHINE}"

S = "${WORKDIR}/dreambox-dvb-modules_${KV}-${MACHINE}-${DRIVERDATE}_${MACHINE}"

inherit module-base

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_SYSROOT_STRIP = "1"

INSANE_SKIP:${PN}:append = " already-stripped"

do_install() {
    find . -depth -not -path "./patches*" -not -path "./.pc*" -print0 | cpio --null -pdlu ${D}
    chown -hR root:root ${D}
}

do_install:append() {
    install -d ${D}${sysconfdir}/modules-load.d
    echo stb-core > ${D}${sysconfdir}/modules-load.d/01-${BPN}-stb-core.conf
    chmod 0644 ${D}${sysconfdir}/modules-load.d/01-${BPN}-stb-core.conf
    if [ -f ${D}/lib/modules/${DM_LOCALVERSION}/extra/lcd.ko ]; then
        echo lcd > ${D}${sysconfdir}/modules-load.d/02-${BPN}-lcd.conf
        chmod 0644 ${D}${sysconfdir}/modules-load.d/02-${BPN}-lcd.conf
    fi
    for module in ${BCMNUMBER} dreambox_keyboard ble; do
        if [ -f ${D}/lib/modules/${DM_LOCALVERSION}/extra/$module.ko ]; then
            echo $module >> ${D}${sysconfdir}/modules-load.d/10-${BPN}.conf
        fi
    done
    chmod 0644 ${D}${sysconfdir}/modules-load.d/10-${BPN}.conf
}

PACKAGES =+ "${PN}-lcd ${PN}-stb-core"

RDEPENDS:${PN} = "dreambox-secondstage-${MACHINE} kernel-${DM_LOCALVERSION} ${PN}-stb-core"
RDEPENDS:${PN}-lcd = "${PN}-stb-core"
RRECOMMENDS:${PN} = "${PN}-lcd"

FILES:${PN} = "${sysconfdir}/modules-load.d/10-${BPN}.conf \
               /lib/modules/${DM_LOCALVERSION}/extra/"

FILES:${PN}-lcd = "${sysconfdir}/modules-load.d/02-${BPN}-lcd.conf \
                   /lib/modules/${DM_LOCALVERSION}/extra/lcd.ko"

FILES:${PN}-stb-core = "${sysconfdir}/modules-load.d/01-${BPN}-stb-core.conf \
                        /lib/modules/${DM_LOCALVERSION}/extra/stb-core.ko"

# We don't use KERNEL_VERSION in this recipe, because the
# precompiled modules depend on a specific version.
DM_LOCALVERSION = "${@'-'.join('${PV}'.split('-')[:-1])}-${MACHINE}"

pkg_postinst:${PN} () {
if [ -z "$D" ]; then
	depmod -a ${DM_LOCALVERSION}
fi
}
pkg_postinst:${PN}-lcd () {
if [ -z "$D" ]; then
	depmod -a ${DM_LOCALVERSION}
fi
}
pkg_postinst:${PN}-stb-core () {
if [ -z "$D" ]; then
	depmod -a ${DM_LOCALVERSION}
fi
}
