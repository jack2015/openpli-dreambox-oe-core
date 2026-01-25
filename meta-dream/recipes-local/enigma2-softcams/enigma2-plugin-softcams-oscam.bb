SUMMARY = "Oscam Softcam for ${MACHINE}"
require conf/license/openpli-gplv2.inc
require oscam-version.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"
PACKAGES = "${PN}"
CAMNAME = "oscam-nx111"

DEPENDS:mipsel = "openssl libusb libdvbcsa openssl-native upx-native"
RDEPENDS:${PN}:mipsel += "libdvbcsa"
DEPENDS:arm = "openssl libusb pcsc-lite ccid openssl-native upx-native libdvbcsa"
RDEPENDS:${PN}:arm += "libusb1 pcsc-lite pcsc-lite-lib ccid libdvbcsa"
LDFLAGS:prepend = "-ldvbcsa "
GLIBC_64BIT_TIME_FLAGS = ""

inherit cmake gitpkgv

SRCREV = "${AUTOREV}"
SRC_URI = "${CODEWEBSITE}/oscam-nx111.git;protocol=https;branch=master"

S = "${WORKDIR}/git"
B = "${S}"

SRC_URI += " \
    file://oscam.conf \
    file://softcam.${CAMNAME} \
    file://fix-some-warning.patch \
    "

EXTRA_OECMAKE:mipsel += " \
    -DOSCAM_SYSTEM_NAME=Tuxbox \
    -DWEBIF=1 \
    -DWITH_STAPI=0 \
    -DWITH_SSL=1 \
    -DHAVE_LIBUSB=1 \
    -DSTATIC_LIBUSB=1 \
    -DCLOCKFIX=0 \
    -DCW_CYCLE_CHECK=1 \
    -DCS_CACHEEX=1 \
    -DMODULE_CONSTCW=1 \
    -DLCDSUPPORT=1 \
    -DCARDREADER_SMARGO=1 \
    -DMODULE_STREAMRELAY=1 \
    -DHAVE_LIBDVBCSA=1 \
    "

EXTRA_OECMAKE:arm += " \
    -DOSCAM_SYSTEM_NAME=Tuxbox \
    -DWEBIF=1 \
    -DWITH_STAPI=0 \
    -DWITH_SSL=1 \
    -DHAVE_LIBUSB=1 \
    -DSTATIC_LIBUSB=0 \
    -DHAVE_PCSC=1 \
    -DSTATIC_LIBPCSC=0 \
    -DCLOCKFIX=0 \
    -DCW_CYCLE_CHECK=1 \
    -DCS_CACHEEX=1 \
    -DMODULE_CONSTCW=1 \
    -DLCDSUPPORT=1 \
    -DCARDREADER_SMARGO=1 \
    -DCARDREADER_PCSC=1 \
    -DMODULE_STREAMRELAY=1 \
    -DHAVE_LIBDVBCSA=1 \
    "

do_configure:prepend () {
	rm -rf ${S}/certs
	${S}/config.sh --create-cert ecdsa prime256v1 ca "OpenPLi OSCam Distribution"
}

do_install() {
    install -d ${D}${sysconfdir}/tuxbox/config
    install -m 0644 ${WORKDIR}/oscam.conf ${D}${sysconfdir}/tuxbox/config
    install -d ${D}${bindir}
    install -m 0755 ${B}/oscam ${D}${bindir}/${CAMNAME}
    install -d ${D}/etc/init.d
    install -m 0755 ${WORKDIR}/softcam.${CAMNAME} ${D}/etc/init.d
}

do_install:append:dm800se() {
    upx --best --ultra-brute ${D}/usr/bin/${CAMNAME}
}

do_install:append:dm500hd() {
    upx --best --ultra-brute ${D}/usr/bin/${CAMNAME}
}

CONFFILES = "/etc/tuxbox/config/oscam.conf"
FILES:${PN} = "/usr /etc"

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
