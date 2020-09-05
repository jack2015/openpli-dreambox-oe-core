# Can you believe this? It's 2020 and still we need to remove .pyc files!

do_install_append() {
    find ${D}/ -name '*.py' -exec rm {} \;
    find ${D}/ -name '*.pyc' -exec rm {} \;
    find ${D}/ -name '*.po' -exec rm {} \;
    find ${D}/ -name '*.pot' -exec rm {} \;
    find ${D}/ -name '*.sh' -exec chmod a+x {} \;
    find ${D}/ -name '*.so' -exec chmod a+x {} \;
}
