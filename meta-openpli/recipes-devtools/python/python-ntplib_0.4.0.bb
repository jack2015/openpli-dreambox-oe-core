SUMMARY = "Python NTP library"
DESCRIPTION = "This module offers a simple interface to query NTP servers from Python"
HOMEPAGE = "https://pypi.python.org/pypi/ntplib/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://ntplib.py;beginline=1;endline=23;md5=afa07338a9595257e94c205c3e72224d"

SRCNAME = "ntplib"
SRC_URI = "https://files.pythonhosted.org/packages/b4/14/6b018fb602602d9f6cc7485cbad7c1be3a85d25cea18c233854f05284aed/${SRCNAME}-${PV}.tar.gz"

SRC_URI[md5sum] = "4c2118f6ef0d8c2c8a4f53d6318b2245"
SRC_URI[sha256sum] = "899d8fb5f8c2555213aea95efca02934c7343df6ace9d7628a5176b176906267"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils setuptools

RDEPENDS:${PN} = "python-core"

include python-package-split.inc
