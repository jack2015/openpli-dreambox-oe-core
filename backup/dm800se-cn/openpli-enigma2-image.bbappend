IMAGE_INSTALL:remove = "distro-feed-configs"
IMAGE_INSTALL:remove = "hdparm"
IMAGE_INSTALL:remove = "3rd-party-feed-configs"
IMAGE_INSTALL:remove = "settings-autorestore"

IMAGE_INSTALL += " \
	bitratecalc \
	busybox-cron \
	"

KERNEL_WIFI_DRIVERS = ""

EXTERNAL_WIFI_DRIVERS = ""

ENIGMA2_PLUGINS = " \
	enigma2-plugin-drivers-ntfs-3g \
	enigma2-plugin-language-en \
	enigma2-plugin-language-zh-cn \
	enigma2-plugin-font-wqy-microhei \
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
	enigma2-plugin-extensions-openmultiboot \
	openmultiboot \
	enigma2-plugin-softcams-oscam \
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
		if [ $file2 = "zh_CN" ]; then
			echo "do nothing"
		else
			rm -rf $1/$file2
		fi
	done
}

upxall() {
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/ldconfig || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/iwconfig || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/sbin/tune2fs.e2fsprogs || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/blindscan || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/bsdcat || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/dbus-daemon || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/chage || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/enigma2 || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/gpasswd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/mpg123 || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/openssl || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/out123 || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/pzstd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/sdparm || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/bin/zstd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/alsactl || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/avahi-daemon || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/chgpasswd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/dropbearmulti || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/ethtool || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/exportfs || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/groupadd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/groupdel || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/groupmod || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/grpck || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/newusers || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/parted || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/rpc.mountd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/rpc.statd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/sm-notify || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/useradd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/userdel || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/usermod || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/vsftpd || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/wpa_cli || true
	upx --best --ultra-brute ${IMAGE_ROOTFS}/usr/sbin/wpa_supplicant || true
}

rootfs_myworks() {
	rm -rf ${IMAGE_ROOTFS}/var/lib/opkg/lists || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/python2.7/site-packages/*egg-info* || true
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins || true
	rmpy ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Components || true
	rm -rf ${IMAGE_ROOTFS}/usr/share/locale/* || true
	rm -rf ${IMAGE_ROOTFS}/usr/share/enigma2/countries/* || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/locale/* || true
	rm -rf ${IMAGE_ROOTFS}/usr/share/mime/* || true
	rm -f ${IMAGE_ROOTFS}/bin/bash.bash || true
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/bash || true
	ln -sf busybox.nosuid ${IMAGE_ROOTFS}/bin/sh || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/AudioSync/locale || true
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/AutoBackup/locale || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/BackupSuite/locale || true
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/CacheFlush/locale || true
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/FanControl2/locale || true
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenWebif/locale || true
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OpenMultiboot/locale || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/OscamStatus/locale || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/Extensions/MovieCut/locale || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/NetworkBrowser/locale || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/ServiceApp/locale || true
	rm -rf ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/SystemTime/locale || true
	rmpo ${IMAGE_ROOTFS}/usr/lib/enigma2/python/Plugins/SystemPlugins/MountManager/locale || true
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-HD/picon_default.png || true
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullHD/picon_default.png || true
	rm -f ${IMAGE_ROOTFS}/usr/share/enigma2/PLi-FullNightHD/picon_default.png || true
	cp -rf ${THISDIR}/files/dm800se-cn/usr ${IMAGE_ROOTFS}/ || true
	cp -rf ${THISDIR}/files/dm800se-cn/etc ${IMAGE_ROOTFS}/ || true
	upxall || true
}

ROOTFS_POSTPROCESS_COMMAND += "rootfs_myworks;"
