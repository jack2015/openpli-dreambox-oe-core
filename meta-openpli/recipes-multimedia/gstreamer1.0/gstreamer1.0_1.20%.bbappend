GST_VERSION_FULL = "1.20.3"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
           file://0001-revert-use-new-gst-adapter-get-buffer.patch \
"
