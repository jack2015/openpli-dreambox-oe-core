SRC_URI_remove = "git://chromium.googlesource.com/webm/libvpx;protocol=https;branch=master"
GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI_append = " ${GIT_SITE}/libvpx;protocol=https;branch=master"
