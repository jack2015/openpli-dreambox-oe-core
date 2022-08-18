SUMMARY = "AiO screenshot grabber"
MAINTAINER = "PLi team"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"

DEPENDS = "jpeg libpng zlib"

inherit gitpkgv autotools pkgconfig

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

SRC_URI = "git://gitee.com/jackgee2021/openpli-aio-grab.git;branch=master;protocol=https"
SRC_URI:dm900 = "git://gitee.com/jackgee2021/aio-grab.git;branch=master;protocol=https"
SRC_URI:dm920 = "git://gitee.com/jackgee2021/aio-grab.git;branch=master;protocol=https"
SRC_URI:sh4 = "git://gitee.com/jackgee2021/aio-grab.git;branch=master;protocol=https"

S = "${WORKDIR}/git"

EXTRA_OECONF = "ac_cv_prog_c_openmp=-fopenmp"
