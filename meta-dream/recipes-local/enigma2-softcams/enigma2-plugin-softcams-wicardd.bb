require conf/license/openpli-gplv2.inc
SUMMARY = "Wicardd Softcam for DM800se Ver1.19"
PACKAGE_ARCH = "${DEFAULTTUNE}"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN} += "already-stripped"
DEPENDS += "libcrypto-compat virtual/crypt"
RDEPENDS:${PN} += "libcrypto-compat libxcrypt-compat"

S = "${WORKDIR}"
PV = "1.19"

SRC_URI += " \
	file://softcam.wicardd \
	file://wicardd \
	file://wicardd.conf \
	file://wicardd_sample.conf \
"

CONFFILES = "/etc/tuxbox/config/wicardd.conf"

FILES:${PN} = "/usr/bin /etc"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/wicardd ${D}/usr/bin/wicardd
	install -d ${D}/etc
	touch ${D}/etc/clist.list
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${S}/wicardd.conf ${D}/etc/tuxbox/config/wicardd.conf
	install -m 0644 ${S}/wicardd_sample.conf ${D}/etc/tuxbox/config/wicardd_sample.conf
	install -d ${D}/etc/init.d
	install -m 0755 ${S}/softcam.wicardd ${D}/etc/init.d/softcam.wicardd
}

CAMNAME = "wicardd"
CAMPATH = "/etc/init.d/softcam.${CAMNAME}"
CAMLINK = "/etc/init.d/softcam"
# If no cam selected yet, install and start this cam (and don't start it on the build host).
pkg_postinst:${PN}() {
	if [ ! -e "$D/etc/rcS.d/S96softcam" ]
	then
		ln -s "../init.d/softcam" "$D/etc/rcS.d/S96softcam"
	fi

	if [ ! -e "$D${CAMLINK}" ] || [ "/etc/init.d/softcam.None" = "`readlink -f $D${CAMLINK}`" ] || [ "softcam.None" = "`readlink -f $D${CAMLINK}`" ]
	then
		ln -sf "softcam.${CAMNAME}" "$D${CAMLINK}"
		$D${CAMPATH} restart > /dev/null 2>&1
	fi
}

# Stop this cam (if running), and move softlink to None if we're the current cam
pkg_prerm:${PN}:prepend() {
	if  [ "/etc/init.d/softcam.${CAMNAME}" = "`readlink -f $D${CAMLINK}`" ] || [ "softcam.${CAMNAME}" = "`readlink -f $D${CAMLINK}`" ]
	then
		$D${CAMPATH} stop > /dev/null 2>&1
		ln -sf "softcam.None" "$D${CAMLINK}"
	fi
}
