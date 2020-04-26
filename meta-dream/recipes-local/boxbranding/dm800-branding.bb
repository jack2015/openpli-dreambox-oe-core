DESCRIPTION = "Openpli branding lib DM800"
MAINTAINER = "Openpli Developers"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "python"

require conf/license/openpli-gplv2.inc

inherit autotools-brokensep gitpkgv pythonnative

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

PR[vardepsexclude] += "DATE"

do_configure[nostamp] = "1"

SRC_URI="git://github.com/jack2015/branding-module.git;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-visionversion="7.2" \
    --with-visionrevision="develop" \
    --with-boxbrand="dreambox" \
    --with-oever="PLi-OE" \
    --with-distro="openpli" \
    --with-boxtype="dm800" \
    --with-brandoem="dreambox" \
    --with-machinebrand="dreambox" \
    --with-machinename="dm800" \
    --with-machinebuild="dm800" \
    --with-machinemake="dm800" \
    --with-imageversion="7.2" \
    --with-imagebuild="rc" \
    --with-imagedevbuild="000" \
    --with-imagetype="develop" \
    --with-feedsurl="http://downloads.openpli.org/feeds/openpli-7-release" \
    --with-imagedir="" \
    --with-imagefs="jffs2nfi" \
    --with-mtdbootfs="jffs2" \
    --with-mtdrootfs="jffs2" \
    --with-mtdkernel="mtd2" \
    --with-rootfile="rootfs.bin" \
    --with-kernelfile="kernel.bin" \
    --with-mkubifs="-m 512 -e 15KiB -c 3735 -x favor_lzo -X 1 -F -j 4MiB" \
    --with-ubinize="-m 512 -p 16KiB -s 512" \
    --with-arch="mips32el-nf" \
    --with-display-type="bwlcd96" \
    --with-small-flash="smallflash" \
    --with-transcoding="" \
    --with-multitranscoding="" \
    "

FILES_${PN} = "${libdir}/enigma2/python/*.so"
FILES_${PN}-dev += "${libdir}/enigma2/python/*.la"
FILES_${PN}-staticdev += "${libdir}/enigma2/python/*.a"
FILES_${PN}-dbg += "${libdir}/enigma2/python/.debug"
