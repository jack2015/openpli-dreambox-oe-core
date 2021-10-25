KV = "3.14-1.17"
DRIVERDATE = "20200226"

require dreambox-dvb-modules-dm9x0.inc

SRC_URI[dm900.md5sum] = "ea92f82a7c4e09ef620999ca5365daf5"
SRC_URI[dm900.sha256sum] = "202685660e7d6cd6342e321639b41aa86cc7f5a178af04757716be7970542f73"
DREAMBOX_DVB_MODULES_MIRROR ?= "http://dreamboxupdate.com/download/opendreambox/2.5.0/dreambox-dvb-modules/${KV}-${MACHINE}-${DRIVERDATE}/${MACHINE}/${MD5SUM}"
SRC_URI:dm900 = "${DREAMBOX_DVB_MODULES_MIRROR}/dreambox-dvb-modules_${KV}-${MACHINE}-${DRIVERDATE}_${MACHINE}.tar.xz;name=${MACHINE}"
S = "${WORKDIR}/dreambox-dvb-modules_${KV}-${MACHINE}-${DRIVERDATE}_${MACHINE}"

COMPATIBLE_MACHINE = "^(dm900)$"

MD5SUM = "${@d.getVarFlag('SRC_URI', 'dm900.md5sum', True)}"
