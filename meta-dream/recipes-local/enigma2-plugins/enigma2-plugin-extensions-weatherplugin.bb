SUMMARY = "Five Days Weather Plugin & Skin Component"
DESCRIPTION = "WeatherPlugin by Dr.Best modified by RAED"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

inherit gitpkgv distutils-openplugins gettext pythonnative

PV = "2.1+git${SRCPV}"
PKGV = "2.1+git${GITPKGV}"
SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/jack2015/WeatherPlugin2.git;protocol=git"

FILES_${PN} = "${libdir}"

S = "${WORKDIR}/git"

do_compile_append() {
    python2 -O -m compileall ${S}/usr
}

do_install_append() {
    install -d ${D}/usr/lib/enigma2/python/Components
    cp -r ${S}/usr/lib/enigma2/python/Components/* ${D}/usr/lib/enigma2/python/Components
    install -d ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins
    cp -r ${S}/usr/lib/enigma2/python/Plugins/SystemPlugins/* ${D}/usr/lib/enigma2/python/Plugins/SystemPlugins
    install -d ${D}/usr/lib/enigma2/python/Plugins/SkinComponents
    cp -r ${S}/usr/lib/enigma2/python/Plugins/SkinComponents/* ${D}/usr/lib/enigma2/python/Plugins/SkinComponents
    find ${D}/ -name '*.pyc' -exec rm {} \;
    find ${D}/ -name '*.py' -exec rm {} \;
}

pkg_preinst_${PN}() {
    rm -rf $D/usr/lib/enigma2/python/Plugins/Extensions/WeatherPlugin > /dev/null 2>&1
    rm -rf $D/usr/lib/enigma2/python/Plugins/SkinComponents/WeatherComponent > /dev/null 2>&1
    rm -rf $D/usr/lib/enigma2/python/Plugins/SystemPlugins/WeatherComponentHandler > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/WeatherMSN.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Sources/MSNWeather.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Converter/MSNWeather.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Renderer/MSNWeatherPixmap.py > /dev/null 2>&1
}

pkg_postrm_${PN}() {
    rm -rf $D/usr/lib/enigma2/python/Plugins/Extensions/WeatherPlugin > /dev/null 2>&1
    rm -rf $D/usr/lib/enigma2/python/Plugins/SkinComponents/WeatherComponent > /dev/null 2>&1
    rm -rf $D/usr/lib/enigma2/python/Plugins/SystemPlugins/WeatherComponentHandler > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/WeatherMSN.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Sources/MSNWeather.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Converter/MSNWeather.py > /dev/null 2>&1
    rm -f $D/usr/lib/enigma2/python/Components/Renderer/MSNWeatherPixmap.py > /dev/null 2>&1
}
