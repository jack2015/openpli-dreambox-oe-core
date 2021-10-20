SUMMARY  = "UDF reader"
SECTION = "misc"
HOMEPAGE = "http://videolan.org"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM="file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://github.com/jack2015/libudfread.git"

inherit gitpkgv autotools-brokensep pkgconfig

PV = "1.1.2+git${SRCPV}"
PKGV = "1.1.2+git${GITPKGV}"

S="${WORKDIR}/git"
