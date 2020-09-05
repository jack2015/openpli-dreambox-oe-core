SRC_URI = "git://github.com/jack2015/enigma2-openpli.git;branch=dm800se"

PYTHON_RDEPS_append += " \
	python-mmap \
	python-six \
	python-netifaces \
	"

RDEPENDS_enigma2-plugin-systemplugins-wirelesslan = "wpa-supplicant wireless-tools python-wifi"

RDEPENDS_${PN}-build-dependencies_remove = "iw"

RDEPENDS_${PN}-build-dependencies_append += " \
	wireless-tools \
	"

RDEPENDS_${PN}_remove = "openvision-branding"

RDEPENDS_${PN}_append += " \
	dm800se-branding \
	"

RRECOMMENDS_enigma2-plugin-extensions-dvdplayer = "kernel-module-udf"
RRECOMMENDS_enigma2-plugin-extensions-dvdburn = "kernel-module-pktcdvd"
RRECOMMENDS_${PN}-build-dependencies = "kernel-module-udf kernel-module-pktcdvd"

EXTRA_OECONF = "\
	--with-libsdl=no --with-boxtype=${MACHINE} \
	--enable-dependency-tracking \
	ac_cv_prog_c_openmp=-fopenmp \
	${@get_crashaddr(d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "7segment", "--with-7segment" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "7seg", "--with-7segment" , "", d)} \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

do_install_append_dm800se() {
	find ${D}/usr/share/enigma2/rc_models/ -name '*.png' -exec rm {} \;
	find ${D}/usr/share/enigma2/rc_models/ -name '*.xml' -exec rm {} \;
	install -m 0644 ${S}/data/rc_models/dmm.png ${D}/usr/share/enigma2/rc_models/dmm.png
	install -m 0644 ${S}/data/rc_models/dmmadv.png ${D}/usr/share/enigma2/rc_models/dmmadv.png
	install -m 0644 ${S}/data/rc_models/dmm.xml ${D}/usr/share/enigma2/rc_models/dmm.xml
	install -m 0644 ${S}/data/rc_models/dmmadv.xml ${D}/usr/share/enigma2/rc_models/dmmadv.xml
}

SUMMARY_enigma2-plugin-language-en = "British English"
SUMMARY_enigma2-plugin-language-ru = "Russian"
SUMMARY_enigma2-plugin-language-ar = "Arabic"
SUMMARY_enigma2-plugin-language-de = "German"
SUMMARY_enigma2-plugin-language-es = "Spanish"
SUMMARY_enigma2-plugin-language-it = "Italian"
SUMMARY_enigma2-plugin-language-tr = "Turkish"
SUMMARY_enigma2-plugin-language-bg = "Bulgarian"
SUMMARY_enigma2-plugin-language-ca = "Catalan"
SUMMARY_enigma2-plugin-language-cs = "Czech"
SUMMARY_enigma2-plugin-language-da = "Danish"
SUMMARY_enigma2-plugin-language-el = "Greek"
SUMMARY_enigma2-plugin-language-et = "Estonian"
SUMMARY_enigma2-plugin-language-fa = "Persian"
SUMMARY_enigma2-plugin-language-fi = "Finnish"
SUMMARY_enigma2-plugin-language-fr = "French"
SUMMARY_enigma2-plugin-language-fy = "Frisian"
SUMMARY_enigma2-plugin-language-he = "Hebrew"
SUMMARY_enigma2-plugin-language-hr = "Croatian"
SUMMARY_enigma2-plugin-language-hu = "Hungarian"
SUMMARY_enigma2-plugin-language-id = "Indonesian"
SUMMARY_enigma2-plugin-language-is = "Icelandic"
SUMMARY_enigma2-plugin-language-ku = "Kurdish"
SUMMARY_enigma2-plugin-language-lt = "Lithuanian"
SUMMARY_enigma2-plugin-language-lv = "Latvian"
SUMMARY_enigma2-plugin-language-nb = "Norwegian Bokm"
SUMMARY_enigma2-plugin-language-nl = "Dutch"
SUMMARY_enigma2-plugin-language-nn = "Norwegian Nynorsk"
SUMMARY_enigma2-plugin-language-pl = "Polish"
SUMMARY_enigma2-plugin-language-pt = "Portuguese-Portugal"
SUMMARY_enigma2-plugin-language-pt-br = "Portuguese-Brazil"
SUMMARY_enigma2-plugin-language-ro = "Romanian"
SUMMARY_enigma2-plugin-language-sk = "Slovak"
SUMMARY_enigma2-plugin-language-sl = "Slovenian"
SUMMARY_enigma2-plugin-language-sr = "Serbian"
SUMMARY_enigma2-plugin-language-sv = "Swedish"
SUMMARY_enigma2-plugin-language-th = "Thailand-Thai"
SUMMARY_enigma2-plugin-language-uk = "Ukrainian"
SUMMARY_enigma2-plugin-language-vi = "Vietnamese"
SUMMARY_enigma2-plugin-language-zh-cn = "Chinese-China"
SUMMARY_enigma2-plugin-language-zh-hk = "Chinese-Hong Kong"

SUMMARY_enigma2-plugin-font-wqy-microhei = "wqy-microhei font supports Chinese EPG"
PACKAGES =+ "enigma2-plugin-font-wqy-microhei"
FILES_enigma2-plugin-font-wqy-microhei = "${datadir}/fonts/wqy-microhei.ttc ${datadir}/fonts/fallback.font"
ALLOW_EMPTY_enigma2-plugin-font-wqy-microhei = "1"
