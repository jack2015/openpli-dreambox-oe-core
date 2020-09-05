SUMMARY = "E2iPlayer for Enigma2"
DESCRIPTION = "Watch Videos Online"
SECTION = "multimedia"
require conf/license/openpli-gplv2.inc

SRC_URI = "git://github.com/jack2015/e2iplayer.git;protocol=https"

S = "${WORKDIR}/git"

inherit gitpkgv allarch distutils-openplugins gettext rm_python_pyc

RREPLACES_${PN} = "enigma2-plugin-extensions-e2istream"
RCONFLICTS_${PN} = "enigma2-plugin-extensions-e2istream"

PV = "1.2+git${SRCPV}"
PKGV = "1.2+git${GITPKGV}"

DEPENDS = "gettext-native python"

RDEPENDS_${PN} = " \
	cmdwrapper \
	duktape \
	f4mdump \
	ffmpeg \
	hlsdl \
	lsdir \
	python-compression \
	python-core \
	python-html \
	python-json \
	python-pycurl \
	python-shell \
	python-subprocess \
	python-textutils \
	rtmpdump \
	uchardet \
	exteplayer3 \
	gst-ifdsrc \
	gstplayer \
	wget \
	"

RDEPENDS_{PN}-src = "${PN}"
FILES_${PN}-src = " \
        ${libdir}/enigma2/python/Plugins/*/*.py \
        ${libdir}/enigma2/python/Plugins/*/*/*.py \
        ${libdir}/enigma2/python/Plugins/*/*/*/*.py \
        ${libdir}/enigma2/python/Plugins/*/*/*/*/*.py \
        ${libdir}/enigma2/python/Plugins/*/*/*/*/*/*.py \
        ${libdir}/enigma2/python/Plugins/*-py2.7.egg-info/* \
        ${libdir}/enigma2/python/Plugins/*/locale/*/LC_MESSAGES/*.po \
        "

deltask package_qa

FILES_${PN} += "${sysconfdir}"

do_install_append() {
    install -d ${D}${sysconfdir}
    cp -r  --preserve=mode,links ${S}/vk ${D}${sysconfdir}/vk
    install -m 0755 ${S}/bin/mipsel/e2icjson.so ${D}${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/libs/e2icjson/e2icjson.so
    install -m 0755 ${S}/bin/mipsel/_subparser.so ${D}${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer/libs/iptvsubparser/_subparser.so
}

pkg_preinst_${PN}() {
#!/bin/sh
if [ -d "$D${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer" ]; then
    rm -rf $D${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer > /dev/null 2>&1
fi
}

pkg_postrm_${PN} () {
#!/bin/sh
if [ -d "$D${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer" ]; then
    rm -rf $D${libdir}/enigma2/python/Plugins/Extensions/IPTVPlayer > /dev/null 2>&1
fi
}
