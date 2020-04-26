SUMMARY = "UCL is a portable lossless data compression library written in ANSI C."
HOMEPAGE = "http://www.oberhumer.com/opensource/ucl/"
SECTION = "libs"
PRIORITY = "optional"

require conf/license/openpli-gplv2.inc

inherit autotools-brokensep

SRC_URI = " \
	http://www.oberhumer.com/opensource/ucl/download/ucl-1.03.tar.gz \
	file://ucl_configure_fix.patch;patch=1 \
	"


SRC_URI[md5sum] = "852bd691d8abc75b52053465846fba34"
SRC_URI[sha256sum] = "b865299ffd45d73412293369c9754b07637680e5c826915f097577cd27350348"

BBCLASSEXTEND += "native nativesdk"
