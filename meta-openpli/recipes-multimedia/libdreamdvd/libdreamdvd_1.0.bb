SUMMARY = "libdvdnav wrapper for enigma2 based stbs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libdvdnav"
RDEPENDS:${PN} = "liba52"

PR = "r4"

inherit autotools pkgconfig git-project

SRC_URI = " git://gitlab.com/jack2015/libdreamdvd.git;protocol=https;branch=master \
	file://move-function-pointer-away-from-header.patch"

CFLAGS += " -std=gnu11"
