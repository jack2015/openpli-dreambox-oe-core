# I hope it solves the PAK archive (application/x-pak) problem
PACKAGES =+ "${PN}-realpath"

FILES_${PN}-realpath = "${bindir}/realpath.${PN}"

RRECOMMENDS_${PN}_append_class-target = "${PN}-realpath"

ALTERNATIVE_${PN}_remove = "realpath"
ALTERNATIVE_${PN}-realpath = "realpath"
