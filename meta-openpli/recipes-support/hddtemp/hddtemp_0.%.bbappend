FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://hddtemp.db \
	file://init \
"

# Latest hddtemp.db: https://www.guzu.net/linux/hddtemp.db
# Tool to add new devices: https://github.com/noegodinho/ssdadd

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/hddtemp
}
