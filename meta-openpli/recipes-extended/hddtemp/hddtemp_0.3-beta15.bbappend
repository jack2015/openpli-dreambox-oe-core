FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
		file://hddtemp.db \
"

PACKAGE_NO_LOCALE = "1"
