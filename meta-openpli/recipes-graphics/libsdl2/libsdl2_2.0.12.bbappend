FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://add-missing-keys.patch"

EXTRA_OECONF += " --disable-fcitx --disable-ibus"
