SUMMARY = "Timezone data"
HOMEPAGE = "http://www.iana.org/time-zones"
SECTION = "base"
LICENSE = "PD & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef1a352b901ee7b75a75df8171d6aca7"

DEPENDS = "tzcode-native"

SRC_URI = "http://www.iana.org/time-zones/repository/releases/tzdata${PV}.tar.gz;name=tzdata"
UPSTREAM_CHECK_URI = "http://www.iana.org/time-zones"

SRC_URI[tzdata.md5sum] = "50dc0dc50c68644c1f70804f2e7a1625"
SRC_URI[tzdata.sha256sum] = "f8242a522ea3496b0ce4ff4f2e75a049178da21001a08b8e666d8cbe07d18086"

inherit allarch

RCONFLICTS:${PN} = "timezones timezone-africa timezone-america timezone-antarctica \
             timezone-arctic timezone-asia timezone-atlantic \
             timezone-australia timezone-europe timezone-indian \
             timezone-iso3166.tab timezone-pacific timezone-zone.tab"

S = "${WORKDIR}"

DEFAULT_TIMEZONE ?= "Universal"
INSTALL_TIMEZONE_FILE ?= "1"

TZONES= "africa antarctica asia australasia europe northamerica southamerica  \
         factory etcetera backward systemv \
        "
# pacificnew 

do_compile () {
        for zone in ${TZONES}; do \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}${datadir}/zoneinfo -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}${datadir}/zoneinfo/posix -L /dev/null \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
            ${STAGING_BINDIR_NATIVE}/zic -d ${WORKDIR}${datadir}/zoneinfo/right -L ${S}/leapseconds \
                -y ${S}/yearistype.sh ${S}/${zone} ; \
        done
}

do_install () {
        install -d ${D}/$exec_prefix ${D}${datadir}/zoneinfo
        cp -pPR ${S}/$exec_prefix ${D}/
        # libc is removing zoneinfo files from package
        cp -pP "${S}/zone.tab" ${D}${datadir}/zoneinfo
        cp -pP "${S}/zone1970.tab" ${D}${datadir}/zoneinfo
        cp -pP "${S}/iso3166.tab" ${D}${datadir}/zoneinfo

        # Install default timezone
        if [ -e ${D}${datadir}/zoneinfo/${DEFAULT_TIMEZONE} ]; then
            install -d ${D}${sysconfdir}
            if [ "${INSTALL_TIMEZONE_FILE}" = "1" ]; then
                echo ${DEFAULT_TIMEZONE} > ${D}${sysconfdir}/timezone
            fi
            ln -s ${datadir}/zoneinfo/${DEFAULT_TIMEZONE} ${D}${sysconfdir}/localtime
        else
            bberror "DEFAULT_TIMEZONE is set to an invalid value."
            exit 1
        fi

        chown -R root:root ${D}
}

pkg_postinst:${PN} () {
	etc_lt="$D${sysconfdir}/localtime"
	src="$D${sysconfdir}/timezone"

	if [ -e ${src} ] ; then
		tz=$(sed -e 's:#.*::' -e 's:[[:space:]]*::g' -e '/^$/d' "${src}")
	fi
	
	if [ -z "${tz}" ] ; then
		exit 0
	fi
	
	if [ ! -e "$D${datadir}/zoneinfo/${tz}" ] ; then
		echo "You have an invalid TIMEZONE setting in ${src}"
		echo "Your ${etc_lt} has been reset to Universal; enjoy!"
		tz="Universal"
		echo "Updating ${etc_lt} with $D${datadir}/zoneinfo/${tz}"
		if [ -L ${etc_lt} ] ; then
			rm -f "${etc_lt}"
		fi
		ln -s "${datadir}/zoneinfo/${tz}" "${etc_lt}"
	fi
}

# Packages primarily organized by directory with a major city
# in most time zones in the base package

PACKAGES = "tzdata tzdata-misc tzdata-posix tzdata-right tzdata-africa \
    tzdata-americas tzdata-antarctica tzdata-arctic tzdata-asia \
    tzdata-atlantic tzdata-australia tzdata-europe tzdata-pacific"

FILES:tzdata-africa += "${datadir}/zoneinfo/Africa/*"
RPROVIDES:tzdata-africa = "tzdata-africa"

FILES:tzdata-americas += "${datadir}/zoneinfo/America/*  \
                ${datadir}/zoneinfo/US/*                \
                ${datadir}/zoneinfo/Brazil/*            \
                ${datadir}/zoneinfo/Canada/*            \
                ${datadir}/zoneinfo/Mexico/*            \
                ${datadir}/zoneinfo/Chile/*"
RPROVIDES:tzdata-americas = "tzdata-americas"

FILES:tzdata-antarctica += "${datadir}/zoneinfo/Antarctica/*"
RPROVIDES:tzdata-antarctica = "tzdata-antarctica"

FILES:tzdata-arctic += "${datadir}/zoneinfo/Arctic/*"
RPROVIDES:tzdata-arctic = "tzdata-arctic"

FILES:tzdata-asia += "${datadir}/zoneinfo/Asia/*        \
                ${datadir}/zoneinfo/Indian/*            \
                ${datadir}/zoneinfo/Mideast/*"
RPROVIDES:tzdata-asia = "tzdata-asia"

FILES:tzdata-atlantic += "${datadir}/zoneinfo/Atlantic/*"
RPROVIDES:tzdata-atlantic = "tzdata-atlantic"

FILES:tzdata-australia += "${datadir}/zoneinfo/Australia/*"
RPROVIDES:tzdata-australia = "tzdata-australia"

FILES:tzdata-europe += "${datadir}/zoneinfo/Europe/*"
RPROVIDES:tzdata-europe = "tzdata-europe"

FILES:tzdata-pacific += "${datadir}/zoneinfo/Pacific/*"
RPROVIDES:tzdata-pacific = "tzdata-pacific"

FILES:tzdata-posix += "${datadir}/zoneinfo/posix/*"
RPROVIDES:tzdata-posix = "tzdata-posix"

FILES:tzdata-right += "${datadir}/zoneinfo/right/*"
RPROVIDES:tzdata-right = "tzdata-right"


FILES:tzdata-misc += "${datadir}/zoneinfo/Cuba           \
                ${datadir}/zoneinfo/Egypt                \
                ${datadir}/zoneinfo/Eire                 \
                ${datadir}/zoneinfo/Factory              \
                ${datadir}/zoneinfo/GB-Eire              \
                ${datadir}/zoneinfo/Hongkong             \
                ${datadir}/zoneinfo/Iceland              \
                ${datadir}/zoneinfo/Iran                 \
                ${datadir}/zoneinfo/Israel               \
                ${datadir}/zoneinfo/Jamaica              \
                ${datadir}/zoneinfo/Japan                \
                ${datadir}/zoneinfo/Kwajalein            \
                ${datadir}/zoneinfo/Libya                \
                ${datadir}/zoneinfo/Navajo               \
                ${datadir}/zoneinfo/Poland               \
                ${datadir}/zoneinfo/Portugal             \
                ${datadir}/zoneinfo/Singapore            \
                ${datadir}/zoneinfo/Turkey"
RPROVIDES:tzdata-misc = "tzdata-misc"


FILES:${PN} += "${datadir}/zoneinfo/Pacific/Honolulu     \
                ${datadir}/zoneinfo/America/Anchorage    \
                ${datadir}/zoneinfo/America/Los_Angeles  \
                ${datadir}/zoneinfo/America/Denver       \
                ${datadir}/zoneinfo/America/Chicago      \
                ${datadir}/zoneinfo/America/New_York     \
                ${datadir}/zoneinfo/America/Caracas      \
                ${datadir}/zoneinfo/America/Sao_Paulo    \
                ${datadir}/zoneinfo/Europe/London        \
                ${datadir}/zoneinfo/Europe/Paris         \
                ${datadir}/zoneinfo/Africa/Cairo         \
                ${datadir}/zoneinfo/Europe/Moscow        \
                ${datadir}/zoneinfo/Asia/Dubai           \
                ${datadir}/zoneinfo/Asia/Karachi         \
                ${datadir}/zoneinfo/Asia/Dhaka           \
                ${datadir}/zoneinfo/Asia/Bankok          \
                ${datadir}/zoneinfo/Asia/Hong_Kong       \
                ${datadir}/zoneinfo/Asia/Tokyo           \
                ${datadir}/zoneinfo/Australia/Darwin     \
                ${datadir}/zoneinfo/Australia/Adelaide   \
                ${datadir}/zoneinfo/Australia/Brisbane   \
                ${datadir}/zoneinfo/Australia/Sydney     \
                ${datadir}/zoneinfo/Pacific/Noumea       \
                ${datadir}/zoneinfo/CET                  \
                ${datadir}/zoneinfo/CST6CDT              \
                ${datadir}/zoneinfo/EET                  \
                ${datadir}/zoneinfo/EST                  \
                ${datadir}/zoneinfo/EST5EDT              \
                ${datadir}/zoneinfo/GB                   \
                ${datadir}/zoneinfo/GMT                  \
                ${datadir}/zoneinfo/GMT+0                \
                ${datadir}/zoneinfo/GMT-0                \
                ${datadir}/zoneinfo/GMT0                 \
                ${datadir}/zoneinfo/Greenwich            \
                ${datadir}/zoneinfo/HST                  \
                ${datadir}/zoneinfo/MET                  \
                ${datadir}/zoneinfo/MST                  \
                ${datadir}/zoneinfo/MST7MDT              \
                ${datadir}/zoneinfo/NZ                   \
                ${datadir}/zoneinfo/NZ-CHAT              \
                ${datadir}/zoneinfo/PRC                  \
                ${datadir}/zoneinfo/PST8PDT              \
                ${datadir}/zoneinfo/ROC                  \
                ${datadir}/zoneinfo/ROK                  \
                ${datadir}/zoneinfo/UCT                  \
                ${datadir}/zoneinfo/UTC                  \
                ${datadir}/zoneinfo/Universal            \
                ${datadir}/zoneinfo/W-SU                 \
                ${datadir}/zoneinfo/WET                  \
                ${datadir}/zoneinfo/Zulu                 \
                ${datadir}/zoneinfo/zone.tab             \
                ${datadir}/zoneinfo/zone1970.tab         \
                ${datadir}/zoneinfo/iso3166.tab          \
                ${datadir}/zoneinfo/Etc/*"

CONFFILES:${PN} += "${@ "${sysconfdir}/timezone" if bb.utils.to_boolean(d.getVar('INSTALL_TIMEZONE_FILE')) else "" }"
CONFFILES:${PN} += "${sysconfdir}/localtime"
