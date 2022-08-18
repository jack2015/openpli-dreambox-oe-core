SUMMARY = "Enigma2 plugin to manage your youtube account and watch videos"
DESCRIPTION = "Small plugin to manage your account, search and watch videos \
from youtube"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-youtube"
SECTION = "multimedia"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-youtube;branch=master;protocol=https \
        file://0001-Add_option_to_choose_style_of_VirtualKeyBoard.patch \
"

S = "${WORKDIR}/git"

inherit gitpkgv
PV = "1+git${SRCPV}"
PKGV = "1+git${GITPKGV}"

inherit distutils-openplugins

RDEPENDS_${PN} = " \
	python-core \
	python-codecs \
	python-json \
	python-netclient \
	python-pyopenssl \
	python-zlib \
	python-twisted-web \
	"

RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src = "${libdir}/enigma2/python/Plugins/Extensions/YouTube/*.py"
