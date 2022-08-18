SUMMARY  = "Interface to the sendfile syscall"
HOMEPAGE = "https://github.com/giampaolo/pysendfile"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8eec2f0885ebe1362d0bdb1617f61b5"

inherit setuptools gitpkgv

# Version 2.0.1 is actually over three years old, we're using "master".
PV = "2.0.1+git${SRCPV}"
PKGV = "2.0.1+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/pysendfile;branch=master;protocol=https"
S = "${WORKDIR}/git"

include python-package-split.inc
