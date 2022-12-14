SUMMARY = "WiFi devices for QCA9377 chipsets."
inherit allarch

require conf/license/license-gplv2.inc

RRECOMMENDS:${PN} = " \
    wlan-qcacld-20 \
    firmware-qca9377 \
    "

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"
