# split out gpgv from main package
RDEPENDS_${PN}_append = " gpgv"
PACKAGES =+ "gpgv"
FILES_gpgv = "${bindir}/gpgv*"

PACKAGE_NO_LOCALE = "1"
