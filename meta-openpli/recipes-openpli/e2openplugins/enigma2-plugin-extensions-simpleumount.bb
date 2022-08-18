MODULE = "SimpleUmount"
SUMMARY = "Unmounter for mass storage devices"
DESCRIPTION = "Unmounter for mass storage devices"
MAINTAINTER = "ambrosa"

inherit gitpkgv
PV = "0.1+git${SRCPV}"
PKGV = "0.1+git${GITPKGV}"
PR = "r0"

require conf/license/license-gplv2.inc
require openplugins-distutils.inc

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/e2openplugin-SimpleUmount;protocol=https;branch=master"

