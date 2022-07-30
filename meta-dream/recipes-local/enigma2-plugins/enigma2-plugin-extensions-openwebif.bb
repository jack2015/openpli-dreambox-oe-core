SUMMARY = "Control your receiver with a browser"
MAINTAINER = "Openpli Developers"
require conf/license/license-gplv2.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"
DEPENDS = "python-cheetah-native"

RDEPENDS:${PN} = "\
	aio-grab \
	python-pprint \
	python-cheetah \
	python-compression\
	python-ipaddress\
	python-json \
	python-misc \
	python-numbers \
	python-pyopenssl \
	python-shell \
	python-six \
	python-twisted-web \
	python-unixadmin \
	"

inherit gittag distutils-openplugins gettext

DISTUTILS_INSTALL_ARGS = "--root=${D} --install-lib=${libdir}/enigma2/python/Plugins"

#ver 1.5.2
SRCREV = "750dee9e557cc8ef053eabb7da5ff827b3f609a4"

PV = "git${SRCPV}"
PKGV = "${GITPKGVTAG}"
PR = "r8"
SRC_URI = "git://gitee.com/jackgee2021/e2openplugin-OpenWebif.git;protocol=https;branch=master \
	file://dm800sev2.png"

#ver 1.3.9
SRCREV:dm800se = "fcf12a42d446022a90b7617a297ba676fc6cfcfe"
SRCREV:dm500hd = "fcf12a42d446022a90b7617a297ba676fc6cfcfe"

PV:dm800se = "1.3.9+git${SRCPV}"
PKGV:dm800se = "1.3.9+git${GITPKGV}"
SRC_URI:dm800se = "git://gitee.com/jackgee2021/e2openplugin-OpenWebif.git;protocol=https;branch=NoSix \
	file://dm800sev2.png"

PV:dm500hd = "1.3.9+git${SRCPV}"
PKGV:dm500hd = "1.3.9+git${GITPKGV}"
SRC_URI:dm500hd = "git://gitee.com/jackgee2021/e2openplugin-OpenWebif.git;protocol=https;branch=NoSix \
	file://dm800sev2.png"

S="${WORKDIR}/git"

PLUGINPATH = "${libdir}/enigma2/python/Plugins/Extensions/OpenWebif"

do_compile() {
    cheetah-compile -R --nobackup ${S}/plugin
    python2 -O -m compileall ${S}
}

do_install:append() {
    install -d ${D}${PLUGINPATH}
    cp -rf ${S}/plugin/* ${D}${PLUGINPATH}
    cp -f ${WORKDIR}/dm800sev2.png ${D}${PLUGINPATH}/public/images/boxes/
    chmod a+rX ${D}${PLUGINPATH}
}

python do_cleanup () {

    import os

    pluginpath = "%s%s" % (d.getVar('D', True), d.getVar('PLUGINPATH', True))
    images = "%s/public/images/" % pluginpath
    keymaps = "%s/public/static/" % pluginpath

    mybox_name = d.getVar('MACHINE', True)

    if mybox_name == 'dm500hd':
        target_box = 'dm500hd.png'
        target_remote = 'dmm1.png'
        target_keymap = 'dmm1.html'
    if mybox_name == 'dm8000':
        target_box = 'dm8000.png'
        target_remote = 'dmm1.png'
        target_keymap = 'dmm1.html'
    if mybox_name == 'dm800se':
        target_box = 'dm800se.png'
        target_remote = 'dmm1.png'
        target_keymap = 'dmm1.html'
    if mybox_name == 'dm800sev2':
        target_box = 'dm800sev2.png'
        target_remote = 'dmm1.png'
        target_keymap = 'dmm1.html'
    if mybox_name == 'dm500hdv2':
        target_box = 'dm500hdv2.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm900':
        target_box = 'dm900.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm920':
        target_box = 'dm920.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm820':
        target_box = 'dm820.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm520':
        target_box = 'dm520.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm525':
        target_box = 'dm525.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm7080':
        target_box = 'dm7080.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm7020hd':
        target_box = 'dm7020hd.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'
    if mybox_name == 'dm7020hdv2':
        target_box = 'dm7020hdv2.png'
        target_remote = 'dmm2.png'
        target_keymap = 'dmm2.html'

    exception = ''

    for root, dirs, files in os.walk(images + 'boxes', topdown=False):
        for name in files:
            if target_box != name and name != 'unknown.png' and exception != name:
                os.remove(os.path.join(root, name))

    for root, dirs, files in os.walk(images + 'remotes', topdown=False):
        for name in files:
            if target_remote != name and name != 'ow_remote.png' and exception != name:
                os.remove(os.path.join(root, name))

    for root, dirs, files in os.walk(keymaps + 'remotes', topdown=False):
        for name in files:
            if target_keymap != name:
                os.remove(os.path.join(root, name))
}

addtask do_cleanup after do_populate_sysroot before do_package

FILES:${PN} = "${PLUGINPATH}"
PACKAGES =+ "${PN}-terminal ${PN}-themes ${PN}-webtv ${PN}-vxg"
FILES:${PN}-themes = "${libdir}/enigma2/python/Plugins/Extensions/OpenWebif/public/themes"
FILES:${PN}-webtv = "${libdir}/enigma2/python/Plugins/Extensions/OpenWebif/public/webtv"
FILES:${PN}-vxg = "${libdir}/enigma2/python/Plugins/Extensions/OpenWebif/public/vxg"
RDEPENDS:${PN}-terminal = "${PN} shellinabox"
RDEPENDS:${PN}-themes = "${PN}"
RDEPENDS:${PN}-webtv = "${PN}"
RDEPENDS:${PN}-vxg = "${PN}"
ALLOW_EMPTY:${PN}-terminal = "1"
ALLOW_EMPTY:${PN}-themes = "1"
ALLOW_EMPTY:${PN}-webtv = "1"
ALLOW_EMPTY:${PN}-vxg = "1"

FILES:${PN}-dbg += "\
    /usr/lib/enigma2/python/Plugins/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/*/*/.debug \
    /usr/lib/enigma2/python/Plugins/*/*/*/*/*/*/.debug \
    "

FILES:${PN}-src = "\
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

FILES:${PN}-src += "${PLUGINPATH}/controllers/views/*.tmpl ${PLUGINPATH}/controllers/views/*/*.tmpl ${PLUGINPATH}/controllers/views/*/*/*.tmpl"

python populate_packages:prepend() {
    enigma2_plugindir = bb.data.expand('${libdir}/enigma2/python/Plugins', d)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/.*\.py$', 'enigma2-plugin-%s-src', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
    do_split_packages(d, enigma2_plugindir, '^(\w+/\w+)/(.*/)?\.debug/.*$', 'enigma2-plugin-%s-dbg', 'Enigma2 Plugin: %s', recursive=True, match_path=True, prepend=True)
}
