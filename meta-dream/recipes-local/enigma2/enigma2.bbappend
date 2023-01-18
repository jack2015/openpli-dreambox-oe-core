ENIGMA2_BRANCH = "develop"
ENIGMA2_BRANCH:dm800se = "dm800se"
ENIGMA2_BRANCH:dm800sev2 = "dm800se"

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"

SRC_URI = "${GIT_SITE}/enigma2-openpli.git;protocol=https;branch=${ENIGMA2_BRANCH} \
	file://001-use-mallinfo2.patch \
	file://002-fix-build-gcc11.patch \
	file://003-get-rid-of-register-keyword.patch \
	file://005-suppress-compile-errors.patch \
	"

SRC_URI:append:arm = " file://006-remove-rca-and-hdmi-pc-ports.patch \
	"

SRC_URI:dm800se = "${GIT_SITE}/enigma2-openpli.git;protocol=https;branch=${ENIGMA2_BRANCH} \
	file://001-use-mallinfo2.patch \
	file://002-fix-build-gcc11.patch \
	file://003-get-rid-of-register-keyword.patch \
	file://004-suppress-compile-errors.patch \
	"

SRC_URI:dm800sev2 = "${GIT_SITE}/enigma2-openpli.git;protocol=https;branch=${ENIGMA2_BRANCH} \
	file://001-use-mallinfo2.patch \
	file://002-fix-build-gcc11.patch \
	file://003-get-rid-of-register-keyword.patch \
	file://004-suppress-compile-errors.patch \
	"

do_install:append() {
	find ${D}/usr/share/enigma2/rc_models/ -name '*.png' -exec rm {} \;
	find ${D}/usr/share/enigma2/rc_models/ -name '*.xml' -exec rm {} \;
	install -m 0644 ${S}/data/rc_models/dmm.png ${D}/usr/share/enigma2/rc_models/dmm.png
	install -m 0644 ${S}/data/rc_models/dmmadv.png ${D}/usr/share/enigma2/rc_models/dmmadv.png
	install -m 0644 ${S}/data/rc_models/dmm.xml ${D}/usr/share/enigma2/rc_models/dmm.xml
	install -m 0644 ${S}/data/rc_models/dmmadv.xml ${D}/usr/share/enigma2/rc_models/dmmadv.xml
}

SUMMARY:enigma2-plugin-language-en = "British English"
SUMMARY:enigma2-plugin-language-ru = "Russian"
SUMMARY:enigma2-plugin-language-ar = "Arabic"
SUMMARY:enigma2-plugin-language-de = "German"
SUMMARY:enigma2-plugin-language-es = "Spanish"
SUMMARY:enigma2-plugin-language-it = "Italian"
SUMMARY:enigma2-plugin-language-tr = "Turkish"
SUMMARY:enigma2-plugin-language-bg = "Bulgarian"
SUMMARY:enigma2-plugin-language-ca = "Catalan"
SUMMARY:enigma2-plugin-language-cs = "Czech"
SUMMARY:enigma2-plugin-language-da = "Danish"
SUMMARY:enigma2-plugin-language-el = "Greek"
SUMMARY:enigma2-plugin-language-et = "Estonian"
SUMMARY:enigma2-plugin-language-fa = "Persian"
SUMMARY:enigma2-plugin-language-fi = "Finnish"
SUMMARY:enigma2-plugin-language-fr = "French"
SUMMARY:enigma2-plugin-language-fy = "Frisian"
SUMMARY:enigma2-plugin-language-gl = "Galicia"
SUMMARY:enigma2-plugin-language-he = "Hebrew"
SUMMARY:enigma2-plugin-language-hr = "Croatian"
SUMMARY:enigma2-plugin-language-hu = "Hungarian"
SUMMARY:enigma2-plugin-language-id = "Indonesian"
SUMMARY:enigma2-plugin-language-is = "Icelandic"
SUMMARY:enigma2-plugin-language-ku = "Kurdish"
SUMMARY:enigma2-plugin-language-lt = "Lithuanian"
SUMMARY:enigma2-plugin-language-lv = "Latvian"
SUMMARY:enigma2-plugin-language-mk = "Macedonian"
SUMMARY:enigma2-plugin-language-nb = "Norwegian Bokm"
SUMMARY:enigma2-plugin-language-nl = "Dutch"
SUMMARY:enigma2-plugin-language-nn = "Norwegian Nynorsk"
SUMMARY:enigma2-plugin-language-pl = "Polish"
SUMMARY:enigma2-plugin-language-pt = "Portuguese-Portugal"
SUMMARY:enigma2-plugin-language-pt-br = "Portuguese-Brazil"
SUMMARY:enigma2-plugin-language-ro = "Romanian"
SUMMARY:enigma2-plugin-language-sk = "Slovak"
SUMMARY:enigma2-plugin-language-sl = "Slovenian"
SUMMARY:enigma2-plugin-language-sr = "Serbian"
SUMMARY:enigma2-plugin-language-sv = "Swedish"
SUMMARY:enigma2-plugin-language-th = "Thailand-Thai"
SUMMARY:enigma2-plugin-language-uk = "Ukrainian"
SUMMARY:enigma2-plugin-language-vi = "Vietnamese"
SUMMARY:enigma2-plugin-language-zh-cn = "Chinese-China"
SUMMARY:enigma2-plugin-language-zh-hk = "Chinese-Hong Kong"
SUMMARY:enigma2-plugin-font-wqy-microhei = "wqy-microhei font supports Chinese EPG"
