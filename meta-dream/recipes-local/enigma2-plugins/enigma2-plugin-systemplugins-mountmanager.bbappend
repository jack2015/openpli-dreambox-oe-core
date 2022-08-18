GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/enigma2-plugin-mountmanager;branch=master;protocol=https"
do_install_append() {
	rm -f ${D}${libdir}/enigma2/python/Plugins/SystemPlugins/MountManager/*.py
	chmod 0755 ${D}${libdir}/enigma2/python/Plugins/SystemPlugins/MountManager/*.sh
}
