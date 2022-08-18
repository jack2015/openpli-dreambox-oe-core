SRC_URI = "git://gitee.com/jackgee2021/enigma2-plugin-mountmanager.git;branch=master;protocol=https"
do_install_append() {
	rm -f ${D}${libdir}/enigma2/python/Plugins/SystemPlugins/MountManager/*.py
	chmod 0755 ${D}${libdir}/enigma2/python/Plugins/SystemPlugins/MountManager/*.sh
}
