SUMMARY = "periodicaly flushing cache"
MAINTAINER = "Openvix Developers"
require conf/license/openpli-gplv2.inc

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/e2openplugin-CacheFlush;branch=master;protocol=https"

inherit gitpkgv distutils-openplugins gettext

S = "${WORKDIR}/git"
SRCREV = "${AUTOREV}"
PV = "1.17+git${SRCPV}"
PKGV = "1.17+git${GITPKGV}"

python populate_packages_prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
