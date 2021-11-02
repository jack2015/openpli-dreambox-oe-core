SUMMARY = "enigma2-plugin-extensions-yahooweather"
DESCRIPTION = "enigma2-plugin-extensions-yahooweather"
MAINTAINER = "original by m43c0 and mmark and mod by mogli123"
SECTION = "extra"
PRIORITY = "optional"

require conf/license/license-gplv2.inc

inherit autotools-brokensep gitpkgv

SRCREV = "${AUTOREV}"
PV = "1.2.+git${SRCPV}"
PKGV = "1.2.+git${GITPKGV}"
PR = "r0"


SRC_URI="git://github.com/oe-alliance/YahooWeather.git;protocol=${GIT_PROTOCOL}"

S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-po"
FILES:${PN} = "/usr/lib"
FILES:${PN}-src = "/usr/lib/enigma2/python/Plugins/Extensions/YahooWeather/*.py"
FILES:${PN}-po = "/usr/lib/enigma2/python/Plugins/Extensions/YahooWeather/locale/*/*/*.po"

inherit autotools gettext

EXTRA_OECONF = "\
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
"
CONFFILES:${PN} = " \
	/usr/lib/enigma2/python/Plugins/Extensions/YahooWeather/Config/Location_id \
	/usr/lib/enigma2/python/Plugins/Extensions/YahooWeather/Config/Region_id "

pkg_postinst:${PN}() {
#!/bin/sh 
echo ""
echo "YahooWeather successfully installed!"
echo ""
echo ""
exit 0
}

pkg_postrm:${PN}() {
#!/bin/sh
rm -r /usr/lib/enigma2/python/Plugins/Extensions/YahooWeather
echo " YahooWeather removed! You should restart enigma2 now!"
exit 0
}
