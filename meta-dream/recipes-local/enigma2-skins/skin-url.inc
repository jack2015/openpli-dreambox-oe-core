GIT_SITE = "${@ 'git://gitlab.com/jack2015/skin-PLiHD' if d.getVar('CODEWEBSITE') else 'git://git.code.sf.net/p/skin-plihd/code'}"
SRC_URI = "${GIT_SITE};branch=master;protocol=https"
SRCREV = "${AUTOREV}"
