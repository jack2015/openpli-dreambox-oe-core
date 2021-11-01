SUMMARY = "Compatibility packages that link to older libcrypto and libssl"
require conf/license/license-gplv2.inc

RRECOMMENDS:${PN} = "libcrypto-compat"

PV = "1.0"
PR = "r0"

inherit allarch

PACKAGES = "${PN}"
ALLOW_EMPTY:${PN} = "1"

# We only need the packaging tasks - disable the rest
do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
