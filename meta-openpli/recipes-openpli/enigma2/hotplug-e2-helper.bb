DESCIPTION = "helper tool to deliver hotplug events to e2"
MAINTAINER = "PLi team"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://bdpoll.c;startline=9;endline=20;md5=d903287e43d72c0f608fd5b718e88450"

inherit gitpkgv

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/hotplug-e2-helper;branch=master;protocol=https"

S = "${WORKDIR}/git"

inherit autotools
