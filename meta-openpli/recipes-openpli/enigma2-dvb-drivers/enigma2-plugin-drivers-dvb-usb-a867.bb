SUMMARY = "USB DVB driver for Afatech 867 chipset"

require dvb-usb-drivers-meta.inc

RRECOMMENDS:${PN} = " \
    kernel-module-dvb-usb-a867 \
    firmware-dvb-usb-af9035-02 \
    "

PV = "1.0"

