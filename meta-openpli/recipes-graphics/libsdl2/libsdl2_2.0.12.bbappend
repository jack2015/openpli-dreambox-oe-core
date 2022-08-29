FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://add-missing-keys.patch"

EXTRA_OECONF_append = " --disable-fcitx --disable-ibus"
