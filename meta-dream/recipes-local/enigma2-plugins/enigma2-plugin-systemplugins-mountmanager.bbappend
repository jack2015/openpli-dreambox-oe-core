SRC_URI = "git://gitlab.com/jack2015/enigma2-plugin-mountmanager.git;protocol=https;branch=master"
do_install:append() {
    find ${D}/ -name '*.py' -exec rm {} \;
    find ${D}/ -name '*.po' -exec rm {} \;
    find ${D}/ -name '*.sh' -exec chmod a+x {} \;
}
