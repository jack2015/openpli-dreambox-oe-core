DESCRIPTION = "DPF access extension module"
MAINTAINER = "https://sourceforge.net/projects/pydpf/"
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://PKG-INFO;md5=6a256fd20875b5cf06888bbcbe1a21aa"

SRC_URI = "git://gitlab.com/jack2015/pydpflib.git;protocol=https"

DEPENDS = "libusb"

S = "${WORKDIR}/git"

inherit gitpkgv setuptools

PV = "0.14+git${SRCPV}"
PKGV = "0.14+git${GITPKGV}"
PR = "r0"

do_compile:prepend() {
    $MAKE -C ./dpf-ax/dpflib all
}
