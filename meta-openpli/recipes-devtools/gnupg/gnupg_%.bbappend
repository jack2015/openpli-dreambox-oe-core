# split out gpgv from main package
RDEPENDS:${PN}:append = " gpgv"
PACKAGES =+ "gpgv"
FILES:gpgv = "${bindir}/gpgv*"

PACKAGE_NO_LOCALE = "1"
