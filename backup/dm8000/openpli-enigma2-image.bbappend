IMAGE_INSTALL:remove = "distro-feed-configs"
IMAGE_INSTALL:remove = "hdparm"
IMAGE_INSTALL:remove = "3rd-party-feed-configs"
IMAGE_INSTALL:remove = "settings-autorestore"

#dm8000

IMAGE_INSTALL:append = " \
	bitratecalc \
	ffmpeg \
	exteplayer3 \
	gstplayer \
	busybox-cron \
	"

KERNEL_WIFI_DRIVERS = ""

EXTERNAL_WIFI_DRIVERS = ""

ENIGMA2_PLUGINS = " \
	enigma2-plugin-drivers-ntfs-3g \
	enigma2-plugin-softcams-libcrypto-compat \
	enigma2-plugin-language-zh-cn \
	enigma2-plugin-language-de \
	enigma2-plugin-language-en \
	enigma2-plugin-language-lt \
	enigma2-plugin-language-lv \
	enigma2-plugin-language-pl \
	enigma2-plugin-language-ru \
	enigma2-plugin-language-tr \
	enigma2-plugin-language-uk \
	enigma2-plugin-font-wqy-microhei \
	enigma2-plugin-skins-pli-fullnighthd \
	enigma2-plugin-extensions-fancontrol2 \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-cacheflush \
	enigma2-plugin-extensions-epgimport \
	enigma2-plugin-extensions-e2iplayer \
	enigma2-plugin-extensions-filecommander \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-moviecut \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-oscamstatus \
	enigma2-plugin-extensions-pictureplayer \
	enigma2-plugin-extensions-ppanel \
	enigma2-plugin-softcams-oscam \
	enigma2-plugin-extensions-openmultiboot \
	openmultiboot \
	\
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
	enigma2-plugin-systemplugins-serviceapp \
	"

rmpy() {
	rm -f $1/*.py
	rm -f $1/*.pyc
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
	rm -f ${IMAGE_ROOTFS}/bin/bash.bash
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/bash
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/sh
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-HD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullHD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullNightHD/picon_default.png
	cp -rf ${THISDIR}/files/dm800sev2-en/usr ${IMAGE_ROOTFS}/
	cp -rf ${THISDIR}/files/dm800sev2-en/etc ${IMAGE_ROOTFS}/
}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_myworks; "
