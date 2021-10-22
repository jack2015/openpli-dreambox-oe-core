SUMMARY = "Allow writes to NTFS file systems"

require conf/license/license-gplv2.inc

RRECOMMENDS:${PN} = "ntfs-3g kernel-module-ntfs"

PV = "1.0"
PR = "r0"

ALLOW_EMPTY:${PN} = "1"
