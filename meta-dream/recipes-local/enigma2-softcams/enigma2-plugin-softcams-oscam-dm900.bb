require conf/license/openpli-gplv2.inc
require oscam-version.inc
SUMMARY = "Oscam Softcam for DM900"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP_${PN}_append = " already-stripped"

S = "${WORKDIR}"

SRC_URI += " \
	file://oscam \
	file://oscam.conf \
	file://softcam.oscam"

CONFFILES = "/etc/tuxbox/config/oscam.conf"

FILES_${PN} = "/usr/bin /etc"

do_install() {
	install -d ${D}/etc/tuxbox/config
	install -m 0644 ${S}/oscam.conf ${D}/etc/tuxbox/config/oscam.conf
	install -d ${D}/usr/bin
	install -m 0755 ${S}/oscam ${D}/usr/bin/oscam
	install -d ${D}/etc/init.d
	install -m 0755 ${S}/softcam.oscam ${D}/etc/init.d/softcam.oscam
}

CAMNAME = "oscam"
CAMPATH = "/etc/init.d/softcam.${CAMNAME}"
CAMLINK = "/etc/init.d/softcam"
# If no cam selected yet, install and start this cam (and don't start it on the build host).
pkg_postinst_${PN}() {
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
pkg_prerm_${PN}_prepend() {
	if  [ "/etc/init.d/softcam.${CAMNAME}" = "`readlink -f $D${CAMLINK}`" ] || [ "softcam.${CAMNAME}" = "`readlink -f $D${CAMLINK}`" ]
	then
		$D${CAMPATH} stop > /dev/null 2>&1
		ln -sf "softcam.None" "$D${CAMLINK}"
	fi
}

