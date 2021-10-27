SUMMARY = "OpenVpn Verbindungen verwalten"
MAINTAINER = "murxer <support@boxpirates.to>"
require conf/license/openpli-gplv2.inc
HOMEPAGE = "https://www.boxpirates.to"

inherit gitpkgv allarch
RDEPENDS:${PN} = "python-requests python-json openvpn openresolv"
RCONFLICTS:${PN} = "enigma2-plugin-extensions-goldenpanel, enigma2-plugin-extensions-satvenus-panel, enigma2-plugin-extensions-goldenfeed, enigma2-plugin-extensions-persiandreambox, enigma2-plugin-extensions-dreamosat-downloader"

PV = "1.1.4+git${SRCPV}"
PKGV = "1.1.4+git${GITPKGV}"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/jack2015/vpnmanager.git"

FILES:${PN} = "/usr/"

S = "${WORKDIR}/git"

do_compile() {
    python2 -O -m compileall ${S}/
}

do_install() {
    install -d ${D}${libdir}/enigma2/python/Plugins/Extensions
    cp -rf ${S}${libdir}/enigma2/python/Plugins/Extensions/* ${D}${libdir}/enigma2/python/Plugins/Extensions
}

pkg_preinst:${PN}() {
    [[ -d $D/etc/openvpn ]] || mkdir $D/etc/openvpn
}

pkg_prerm:${PN}() {
    $D/etc/init.d/openvpn stop
}

pkg_postrm:${PN}() {
    update-rc.d -f openvpn remove
}

python populate_packages:prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/[a-zA-Z0-9_]+.*$', 'enigma2-plugin-%s', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
