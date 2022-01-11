SUMMARY = "Realtek Bluetooth USB driver"
HOMEPAGE = "https://www.realtek.com/"
require conf/license/openpli-gplv2.inc

inherit module
SRCREV = "${AUTOREV}"
SRC_URI = "git://gitlab.com/jack2015/LINUX_BT_USB_DRIVER.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "LINUX_SRC=${STAGING_KERNEL_DIR} KDIR=${STAGING_KERNEL_DIR}"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bluetooth
    install -m 0644 ${S}/rtk_btusb.ko ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bluetooth
}
