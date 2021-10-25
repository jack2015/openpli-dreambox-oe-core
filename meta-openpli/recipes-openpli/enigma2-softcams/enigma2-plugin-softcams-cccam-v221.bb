require conf/license/openpli-gplv2.inc
SUMMARY = "CCcam Softcam for DM800se Ver2.2.1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_PACKAGE_STRIP = "1"
INSANE_SKIP:${PN}:append = " already-stripped"
DEPENDS += "virtual/crypt"
RDEPENDS:${PN} += "libxcrypt-compat"

S = "${WORKDIR}"
PV = "2.2.1"

SRC_URI += " \
	file://CCcam221 \
	file://CCcam.cfg \
	file://CCcam.xml \
	file://softcam.CCcam221 \
"

CONFFILES = "/etc/CCcam.cfg /etc/ppanels/CCcam.xml"

FILES:${PN} = "/usr/bin /etc"

do_install() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/CCcam221 ${D}/usr/bin/CCcam221
	install -d ${D}/etc
	install -m 0644 ${S}/CCcam.cfg ${D}/etc/CCcam.cfg
	install -d ${D}/etc/ppanels
	install -m 0644 ${WORKDIR}/CCcam.xml ${D}/etc/ppanels/CCcam.xml
	install -d ${D}/etc/init.d
	install -m 0755 ${S}/softcam.CCcam221 ${D}/etc/init.d/softcam.CCcam221
}

CAMNAME = "CCcam221"
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
	else
		$D${CAMLINK} stop > /dev/null 2>&1
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
