#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mipsel = " file://fix_mips_build.patch"
