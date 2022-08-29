# Just a comment line to avoid PAK archive (application/x-pak)
PACKAGECONFIG = "openssl ${@bb.utils.contains('PTEST_ENABLED', '1', 'tests', '', d)}"
PACKAGE_NO_LOCALE = "1"
