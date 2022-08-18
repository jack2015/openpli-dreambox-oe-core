SUMMARY = "Plugin for managing autofs files, mount and umount"
DESCRIPTION = "Plugin for managing autofs files, mount and umount"
MAINTAINER = "ims"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9432c1f3d564948269193fd19a0ad0fd"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/ManagerAutofs;protocol=https;branch=master"

S="${WORKDIR}/git"

inherit distutils-openplugins
