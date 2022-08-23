#
FILESEXTRAPATHS_prepend := "${THISDIR}/nodejs:"

SRC_URI_append_mipsel = " file://fix_mips_build.patch"
