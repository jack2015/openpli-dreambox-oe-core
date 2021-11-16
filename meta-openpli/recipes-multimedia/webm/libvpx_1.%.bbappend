SRC_URI:remove = "git://chromium.googlesource.com/webm/libvpx;protocol=https;branch=master"
SRC_URI:append = " git://github.com/webmproject/libvpx.git;protocol=${GIT_PROTOCOL};branch=master"
