IMAGE_INSTALL_remove = "distro-feed-configs"
IMAGE_INSTALL_remove = "hdparm"
IMAGE_INSTALL_remove = "3rd-party-feed-configs"
IMAGE_INSTALL_remove = "settings-autorestore"

#dm9x0

IMAGE_INSTALL += " \
	bitratecalc \
	ofgwrite \
	busybox-cron \
	ffmpeg \
	exteplayer3 \
	gstplayer \
	"

KERNEL_WIFI_DRIVERS = ""
EXTERNAL_WIFI_DRIVERS = ""

ENIGMA2_PLUGINS = " \
	enigma2-plugin-drivers-ntfs-3g \
	enigma2-plugin-softcams-libcrypto-compat \
	enigma2-plugin-language-zh-cn \
	enigma2-plugin-language-de \
	enigma2-plugin-language-en \
	enigma2-plugin-language-it \
	enigma2-plugin-language-ru \
	enigma2-plugin-language-tr \
	enigma2-plugin-language-fr \
	enigma2-plugin-language-es \
	enigma2-plugin-font-wqy-microhei \
	enigma2-plugin-skins-pli-fullnighthd \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-backupsuite \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-cacheflush \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-oscamstatus \
	enigma2-plugin-extensions-pictureplayer \
	enigma2-plugin-extensions-youtube \
	enigma2-plugin-extensions-e2iplayer \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-extensions-openmultiboot \
	openmultiboot \
	enigma2-plugin-systemplugins-cablescan \
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-mphelp \
	enigma2-plugin-systemplugins-hdmicec \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-mountmanager \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-osd3dsetup \
	enigma2-plugin-systemplugins-osdpositionsetup \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-wirelesslan \
	enigma2-plugin-systemplugins-commoninterfaceassignment \
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

export PATH_ETC = "${@bb.utils.contains("MACHINE", "dm900", "${THISDIR}/files/dm9x0/etc", "${THISDIR}/files/dm9x0/etc-920/etc", d)}"

rootfs_myworks() {
	rm -rf ${IMAGE_ROOTFS}/usr/lib/python2.7/site-packages/*egg-info*
	rm -f ${IMAGE_ROOTFS}/bin/bash.bash
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/bash
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/sh
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-HD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullHD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullNightHD/picon_default.png
	cp -rf ${THISDIR}/files/dm9x0/usr ${IMAGE_ROOTFS}/
	cp -rf ${PATH_ETC} ${IMAGE_ROOTFS}/
}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_myworks; "
