GST_VERSION_FULL = "1.18.6"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
        file://0002-revert-use-new-gst-adapter-get-buffer.patch \
"
