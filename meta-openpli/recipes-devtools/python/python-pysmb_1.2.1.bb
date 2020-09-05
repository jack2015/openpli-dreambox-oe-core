SUMMARY  = "pysmb is an experimental SMB/CIFS library written in Python"
DESCRIPTION = "pysmb is an experimental SMB/CIFS library written in Python to support file sharing between Windows and Linux machines. It implements the client-side SMB/CIFS protocol which allows your Python application to access and transfer files to/from SMB/CIFS shared folders like your Windows file sharing and Samba folders."
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=06f070053828db68be36bd1c8c95ca5e"

inherit setuptools

SRCNAME = "pysmb"
SRC_URI = "https://files.pythonhosted.org/packages/7a/90/7afb3db088a171f7e425855fab65a182503d205b234eb6bf2e36fd6aa5a5/pysmb-${PV}.zip"

S = "${WORKDIR}/${SRCNAME}-${PV}"

SRC_URI[md5sum] = "27fc12a0f95ea7543f72c8eec4e1b811"
SRC_URI[sha256sum] = "ab721bbdcf27a4a8a77e2f82dfc6d5e33b4db88f5075160a8bc065722a1270be"

include python-package-split.inc
