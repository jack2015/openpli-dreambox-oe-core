FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
        file://0002-revert-use-new-gst-adapter-get-buffer.patch \
"
