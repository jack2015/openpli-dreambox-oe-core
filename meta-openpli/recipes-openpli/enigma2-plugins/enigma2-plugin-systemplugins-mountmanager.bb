SUMMARY = "Mount devices at your decision (label,uuid)"
DESCRIPTION = "Mount devices at your decision (label,uuid)"
HOMEPAGE = "https://github.com/Dima73/enigma2-plugin-mountmanager"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://README;md5=0e36b30b1a9303e9763901f55c05e558"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-mountmanager;branch=master;protocol=https"
S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins

RDEPENDS_${PN} = " \
	e2fsprogs-tune2fs \
	"
