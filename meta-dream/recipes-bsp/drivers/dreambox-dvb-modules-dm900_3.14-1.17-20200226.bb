KV = "3.14-1.17"
DRIVERDATE = "20200226"

require dreambox-dvb-modules-new.inc

OLDDRIVERDATE = "20190830"
SRC_URI[dm900.md5sum] = "5136e1c8c29ce53885ab40fd4f07aa8c"
SRC_URI[dm900.sha256sum] = "0b8af55570b382444330c1818722a92af6be372f5b25928ab9c45562d0a3f883"
DREAMBOX_DVB_MODULES_MIRROR = "https://jack2015.github.io/files"
SRC_URI:dm900 = "${DREAMBOX_DVB_MODULES_MIRROR}/dreambox-dvb-modules_${KV}-${MACHINE}-${OLDDRIVERDATE}_${MACHINE}.tar.xz;name=${MACHINE}"
S = "${WORKDIR}/dreambox-dvb-modules_${KV}-${MACHINE}-${OLDDRIVERDATE}_${MACHINE}"

COMPATIBLE_MACHINE = "^(dm900)$"

MD5SUM = "${@d.getVarFlag('SRC_URI', 'dm900.md5sum', True)}"
