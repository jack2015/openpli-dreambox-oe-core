#
LICENSE = "Apache-2.0 | BSD-3-Clause"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://partly-support-openssl300.patch \
	file://get-rid-of-get-func.patch \
	file://get-rid-of-crypto-mem.patch \
	"

include python-package-split.inc
