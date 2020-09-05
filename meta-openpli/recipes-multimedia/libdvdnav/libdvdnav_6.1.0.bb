SUMMARY = "library for DVD navigation features"
SECTION = "libs/multimedia"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"
DEPENDS = "libdvdread"

SRC_URI = "http://download.videolan.org/pub/videolan/${BPN}/${PV}/${BP}.tar.bz2"
SRC_URI[md5sum] = "8817ac12c13644df6ba2091c2dcaeed3"
SRC_URI[sha256sum] = "f697b15ea9f75e9f36bdf6ec3726308169f154e2b1e99865d0bbe823720cee5b"

inherit autotools lib_package binconfig pkgconfig

CONFIGUREOPTS_remove = "--disable-silent-rules"

CFLAGS_append_sh4 += "-std=gnu11"

