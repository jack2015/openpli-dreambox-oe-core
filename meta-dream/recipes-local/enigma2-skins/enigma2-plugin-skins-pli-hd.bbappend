#SRC_URI="git://github.com/jack2015/Hains-PLiHD-skin.git;protocol=git"
SRC_URI="git://github.com/jack2015/skin-PLiHD.git;protocol=git"

do_install_append_dm800se() {
        rm -rf ${D}/usr/share/enigma2/PLi-HD
	rm -rf ${D}/usr/share/enigma2/PLi-HD1
	rm -rf ${D}/usr/share/enigma2/PLi-HD2
	chmod -R a+rX ${D}/usr/share/enigma2/
}
