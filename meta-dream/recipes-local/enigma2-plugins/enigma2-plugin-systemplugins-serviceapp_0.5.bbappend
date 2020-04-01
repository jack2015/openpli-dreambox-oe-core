FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "git://github.com/mx3L/serviceapp.git;branch=develop \
    file://remove-deprecated-sFileSize.patch \
    "
RRECOMMENDS_${PN} = "gstplayer exteplayer3"
