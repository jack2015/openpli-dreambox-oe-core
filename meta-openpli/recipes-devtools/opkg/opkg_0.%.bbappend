FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://opkg-configure.service \
	file://opkg.conf \
	file://0001-reuse-the-installed_files-list-when-possible.patch \
	file://0002-opkg-symlink-to-directory-workarounds.patch \
	file://modprobe \
	"
