SRC_URI = "git://github.com/jack2015/enigma2-plugin-mountmanager.git;protocol=${GIT_PROTOCOL};branch=master"
do_install:append() {
    find ${D}/ -name '*.py' -exec rm {} \;
    find ${D}/ -name '*.po' -exec rm {} \;
    find ${D}/ -name '*.sh' -exec chmod a+x {} \;
}
