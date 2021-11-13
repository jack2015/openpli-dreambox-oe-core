# I hope it solves the PAK archive (application/x-pak) problem
PACKAGES =+ "${PN}-realpath"

FILES:${PN}-realpath = "${bindir}/realpath.${PN}"

RRECOMMENDS:${PN}:append:class-target = "${PN}-realpath"

# ALTERNATIVE:${PN}:remove = "realpath"
ALTERNATIVE:${PN}-realpath = "realpath"
