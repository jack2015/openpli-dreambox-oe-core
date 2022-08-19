FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_mipsel = "file://Revert-mke2fs-enable-the-metadata_csum.patch"

PACKAGE_NO_LOCALE = "1"
