SUMMARY = "UPX is a free, portable, extendable, high-performance executable packer for several executable formats."
HOMEPAGE = "https://upx.github.io/"
SECTION = "base"
PRIORITY = "optional"

require conf/license/openpli-gplv2.inc

DEPENDS = "zlib ucl"

inherit gitpkgv
PV = "3.94+git${SRCPV}"
PKGV = "3.94+git${GITPKGV}"
SRCREV_upx = "d31947e1f016e87f24f88b944439bbee892f0429"
SRCREV_lzmasdk = "426fe82d122e2cf140a86751055ee523378fe2ef"
SRCREV_FORMAT = "upx"

SRC_URI = " \
	git://github.com/upx/upx.git;name=upx \
	git://github.com/upx/upx-lzma-sdk.git;name=lzmasdk;destsuffix=git/src/lzma-sdk \
	http://www.oberhumer.com/opensource/ucl/download/ucl-1.03.tar.gz;subdir=git;name=ucl \
	file://ucl_configure_fix.patch;patch=1 \
	file://fix-build-with-gcc8.patch \
	"

S = "${WORKDIR}/git"
UPX_UCLDIR = "${S}/ucl-1.03"

SRC_URI[ucl.md5sum] = "852bd691d8abc75b52053465846fba34"
SRC_URI[ucl.sha256sum] = "b865299ffd45d73412293369c9754b07637680e5c826915f097577cd27350348"

do_compile () {
	oe_runmake all
}

do_install () {
	install -d  "${D}${bindir}"
	install -m 0755 "${S}/src/upx.out" "${D}${bindir}/upx"
}

BBCLASSEXTEND += "native nativesdk"
