#FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://github.com/jack2015/enigma2-openpli.git;branch=develop"

RRECOMMENDS_${PN}_remove = "enigma2-plugin-skins-octetfhd"

PYTHON_RDEPS_append += " \
	python-mmap \
	"

RDEPENDS_${PN}_remove = "openvision-branding"

RDEPENDS_${PN}_append += " \
	dm800se-branding \
	"

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
	cp -f ${D}/usr/share/enigma2/po/en/LC_MESSAGES/enigma2.mo ${WORKDIR}/en-enigma2.mo
	cp -f ${D}/usr/share/enigma2/po/ru/LC_MESSAGES/enigma2.mo ${WORKDIR}/ru-enigma2.mo
	cp -f ${D}/usr/share/enigma2/po/zh_CN/LC_MESSAGES/enigma2.mo ${WORKDIR}/zh_CN-enigma2.mo
	rm -rf /media/jack/dm800se/build/dm800se/po
	mkdir /media/jack/dm800se/build/dm800se/po
	cp -rf ${D}/usr/share/enigma2/po/* /media/jack/dm800se/build/dm800se/po/
	rm -rf ${D}/usr/share/enigma2/po
	install -d ${D}/usr/share/enigma2/po/en/LC_MESSAGES
	install -m 0644 ${WORKDIR}/en-enigma2.mo ${D}/usr/share/enigma2/po/en/LC_MESSAGES/enigma2.mo
	install -d ${D}/usr/share/enigma2/po/zh_CN/LC_MESSAGES
	install -m 0644 ${WORKDIR}/zh_CN-enigma2.mo ${D}/usr/share/enigma2/po/zh_CN/LC_MESSAGES/enigma2.mo
	install -d ${D}/usr/share/enigma2/po/ru/LC_MESSAGES
	install -m 0644 ${WORKDIR}/ru-enigma2.mo ${D}/usr/share/enigma2/po/ru/LC_MESSAGES/enigma2.mo
}
