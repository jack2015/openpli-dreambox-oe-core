IMAGE_INSTALL_remove = "distro-feed-configs"
IMAGE_INSTALL_remove = "hdparm"
IMAGE_INSTALL_remove = "3rd-party-feed-configs"
IMAGE_INSTALL_remove = "settings-autorestore"

#dm800se-only

IMAGE_INSTALL_append = " \
	bitratecalc \
	libcrypto-compat \
	busybox-cron \
	"

KERNEL_WIFI_DRIVERS = " \
	firmware-rtl8712u \
	kernel-module-r8712u \
	"

EXTERNAL_WIFI_DRIVERS = ""

ENIGMA2_PLUGINS = " \
	enigma2-plugin-language-en \
	enigma2-plugin-language-ru \
	enigma2-plugin-glibclocale-fake \
	enigma2-plugin-skins-pli-fullnighthd \
	enigma2-plugin-extensions-fancontrol2 \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
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
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	enigma2-plugin-systemplugins-wirelesslan \
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

rmpo() {
	for file2 in `ls -A $1`
	do
		if [ $file2 = "en" ]; then
			echo "do nothing"
		elif [ $file2 = "ru" ]; then
			echo "do nothing"
		elif [ $file2 = "it" ]; then
			echo "do nothing"
		else
			rm -rf $1/$file2
		fi
	done
}

upxall() {
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/ldconfig || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/udevd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/blindscan || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/bsdcat || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/dbus-daemon || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/enigma2 || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/mpg123 || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/ntfs-3g || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/openssl || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/vpxdec || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/vpxenc || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/udevadm || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/libexec/udevadm || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/sdparm || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/alsactl || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/avahi-daemon || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/exportfs || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/groupadd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/groupdel || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/groupmod || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/newusers || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/parted || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/rpc.mountd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/rpc.statd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/wpa_supplicant || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/ethtool || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/dropbearmulti || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/wpa_cli || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/sm-notify || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/useradd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/userdel || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/usermod || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/vsftpd || true
}

rootfs_myworks() {
	rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists
	rm -rf ${IMAGE_ROOTFS}/usr/lib/python2.7/site-packages/*egg-info*
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components
	rm -rf ${IMAGE_ROOTFS}/usr/share/locale/*
	rm -rf ${IMAGE_ROOTFS}/usr/share/mime/*
	rm -rf ${IMAGE_ROOTFS}/usr/share/ffmpeg/examples/*
	rm -f ${IMAGE_ROOTFS}/bin/bash.bash
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/bash
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/sh
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/AudioSync/locale
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/AutoBackup/locale
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/CacheFlush/locale
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/locale
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OscamStatus/locale
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/MovieCut/locale
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/NetworkBrowser/locale
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/SystemTime/locale
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-HD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullHD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullNightHD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/lib/locale/locale-archive
	cp -rf ${THISDIR}/files/dm800se-only/usr ${IMAGE_ROOTFS}/
	cp -rf ${THISDIR}/files/dm800se-only/etc ${IMAGE_ROOTFS}/
	rm -f ${IMAGE_ROOTFS}/usr/share/fonts/fallback.font
	rm -f ${IMAGE_ROOTFS}/usr/share/fonts/wqy-microhei.ttc
	upxall
}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_myworks; "
