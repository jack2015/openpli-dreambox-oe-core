SUMMARY = "Weather forecast for the upcoming 10 days"
DESCRIPTION = "Weather forecast for the upcoming 10 days"
require conf/license/license-gplv2.inc

RDEPENDS_${PN} = "python-html"

inherit gitpkgv distutils-openplugins pkgconfig
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

FILES_${PN} += "${sysconfdir}/enigma2/Foreca"
CONFFILES_${PN} = "${sysconfdir}/enigma2/Foreca/City.cfg ${sysconfdir}/enigma2/Foreca/fav1.cfg ${sysconfdir}/enigma2/Foreca/fav2.cfg ${sysconfdir}/enigma2/Foreca/startservice.cfg ${sysconfdir}/enigma2/Foreca/Filter.cfg "

SRC_URI = "git://gitee.com/jackgee2021/e2openplugin-Foreca.git;branch=master;protocol=https"

S="${WORKDIR}/git"

FILES_${PN}-dbg += "\
    /usr/lib/enigma2/python/Plugins/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/*/*/*/.debug \
    "

FILES_${PN}-src = "\
    /usr/lib/enigma2/python/*/*.py \
    /usr/lib/enigma2/python/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*/*/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*/*.py \
    /usr/lib/enigma2/python/*/*/*/*/*/*/*/*/*/*/*.py \
    "

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
