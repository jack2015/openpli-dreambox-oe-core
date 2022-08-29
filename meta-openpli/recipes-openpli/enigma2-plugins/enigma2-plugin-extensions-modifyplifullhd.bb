SUMMARY = "Modify colors anf font in PLi-FullHD and PLi-HD1"
DESCRIPTION = "Modify colors anf font in PLi-FullHD and PLi-HD1"
MAINTAINER = "ims"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=534862957bf314f95d85e0c07632f84d"

inherit gitpkgv
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"

SRC_URI = "git://gitlab.com/jack2015/ModifyPliFullHD.git;protocol=https;branch=master"

S="${WORKDIR}/git"

inherit distutils-openplugins
