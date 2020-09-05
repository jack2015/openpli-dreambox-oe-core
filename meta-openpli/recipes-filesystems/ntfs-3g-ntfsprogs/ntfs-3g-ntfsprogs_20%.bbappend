# Remove unneeded util-linux-mount from RDEPENDS and RRECOMMENDS
RRECOMMENDS_ntfs-3g = ""

RDEPENDS_ntfs-3g += "kernel-module-ntfs"
