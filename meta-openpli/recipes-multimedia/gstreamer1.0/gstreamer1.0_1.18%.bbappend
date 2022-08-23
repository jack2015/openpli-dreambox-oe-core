FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
        file://0002-revert-use-new-gst-adapter-get-buffer.patch \
"
