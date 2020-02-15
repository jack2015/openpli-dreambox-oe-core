IMAGE_INSTALL_remove = "distro-feed-configs"

#dm800se-cn

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
	enigma2-plugin-extensions-pluginskinmover \
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

upxall() {
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/enigma2
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/dbus-daemon
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/blindscan
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/bsdcat
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/mpg123
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/ntfs-3g
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/openssl
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/vpxdec
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/vpxenc
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/sdparm
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/wpa_supplicant
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/vsftpd
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/ethtool
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/dropbearmulti
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/avahi-daemon
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/useradd
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/userdel
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/usermod
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/iw
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/rpc.mountd
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/ldconfig
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/tune2fs.e2fsprogs
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/mkfs.ext3
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/mkfs.ext4
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/e2label
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/e2fsck
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/fsck.ext2
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/fsck.ext3
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/fsck.ext4
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/hdparm.hdparm
}

rootfs_myworks() {
	rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists
	rm -rf ${IMAGE_ROOTFS}/usr/lib/python2.7/site-packages/*egg-info*
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components
	rm -rf ${IMAGE_ROOTFS}/usr/share/locale/*
	rm -rf ${IMAGE_ROOTFS}/usr/share/mime/*
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/AudioSync/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/AutoBackup/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/CacheFlush/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OscamStatus/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/MovieCut/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/EPGImport/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/PluginSkinMover/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/NetworkBrowser/locale
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/SystemTime/locale
	mv -f ${IMAGE_ROOTFS}/usr/lib/bitratecalc.so ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components/Converter/
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-HD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullHD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullNightHD/picon_default.png
	rm -f ${IMAGE_ROOTFS}/usr/lib/locale/locale-archive
	cp -rf ${THISDIR}/files/dm800se-cn/usr ${IMAGE_ROOTFS}/
	cp -rf ${THISDIR}/files/dm800se-cn/etc ${IMAGE_ROOTFS}/
	rm -rf ${IMAGE_ROOTFS}/usr/share/enigma2/po/ru
	upxall
}

ROOTFS_POSTPROCESS_COMMAND_append_dm800se += "rootfs_myworks; "
