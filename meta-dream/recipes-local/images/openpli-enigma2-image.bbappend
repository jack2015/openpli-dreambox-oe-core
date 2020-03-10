IMAGE_INSTALL_remove = "distro-feed-configs"

#dm800se-big

DEPENDS += " \
	zip-native \
	upx-native \
	ucl-native \
	"

IMAGE_INSTALL_append += " \
	bitratecalc \
	"

KERNEL_WIFI_DRIVERS = " \
	firmware-rtl8712u \
	kernel-module-r8712u \
	"

EXTERNAL_WIFI_DRIVERS = ""

ENIGMA2_PLUGINS = " \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-backupsuite \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-cacheflush \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-moviecut \
	enigma2-plugin-extensions-oscamstatus \
	enigma2-plugin-extensions-pictureplayer \
	enigma2-plugin-extensions-ppanel \
	enigma2-plugin-softcams-oscam-dm800se \
	enigma2-plugin-extensions-openmultiboot \
	openmultiboot \
	\
	enigma2-plugin-systemplugins-cablescan \
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-mphelp \
	enigma2-plugin-systemplugins-hdmicec \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-osd3dsetup \
	enigma2-plugin-systemplugins-osdpositionsetup \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-systemtime \
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-wirelesslan \
	enigma2-plugin-systemplugins-serviceapp \
	"

rmpy() {
	rm -f $1/*.py
	for file2 in `ls -A $1`
	do
		if [ -d "$1/$file2" ];then
			if [ $file2 != "OpenMultiboot" ];then
				rmpy "$1/$file2"
			fi
		fi
	done
}

rootfs_myworks() {
	rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists
	rm -rf ${IMAGE_ROOTFS}/usr/lib/python2.7/site-packages/*egg-info*
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components
	mv -f ${IMAGE_ROOTFS}/usr/lib/bitratecalc.so ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-HD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullHD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullNightHD/picon_default.png
	cp -rf ${THISDIR}/files/dm800se-big/usr ${IMAGE_ROOTFS}/
	cp -rf ${THISDIR}/files/dm800se-big/etc ${IMAGE_ROOTFS}/
}

ROOTFS_POSTPROCESS_COMMAND_append_dm800se += "rootfs_myworks; "
