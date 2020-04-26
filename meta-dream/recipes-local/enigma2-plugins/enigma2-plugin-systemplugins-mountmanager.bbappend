SRC_URI = "git://github.com/jack2015/enigma2-plugin-mountmanager.git;branch=master"
do_install_append() {
	rm -f ${D}${libdir}/enigma2/python/Plugins/SystemPlugins/MountManager/*.py
	chmod 0755 ${D}${libdir}/enigma2/python/Plugins/SystemPlugins/MountManager/*.sh
}
