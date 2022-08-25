DESCRIPTION = "Openpli branding lib DM500HD"
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

GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI="${GIT_SITE}/branding-module;protocol=https;branch=master"

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
    --with-visionversion="9.3" \
    --with-visionrevision="develop" \
    --with-developername="develop" \
    --with-boxbrand="dreambox" \
    --with-oever="PLi-OE" \
    --with-distro="openpli" \
    --with-boxtype="dm500hd" \
    --with-machinebuild="dm500hd" \
    --with-imageversion="9.3" \
    --with-imagebuild="dunfell" \
    --with-imagedevbuild="000" \
    --with-imagetype="develop" \
    --with-feedsurl="http://downloads.openpli.org/feeds/openpli-7-release" \
    --with-imagedir="" \
    --with-imagefs="jffs2.nfi" \
    --with-mtdbootfs="jffs2" \
    --with-mtdrootfs="jffs2" \
    --with-mtdkernel="mtd2" \
    --with-rootfile="rootfs.bin" \
    --with-kernelfile="kernel.bin" \
    --with-mkubifs="-m 512 -e 15KiB -c 3735 -x favor_lzo -X 1 -F -j 4MiB" \
    --with-ubinize="-m 512 -p 16KiB -s 512" \
    --with-arch="mips32el" \
    --with-tfpu="hard" \
    --with-display-type="bwlcd96" \
    --with-small-flash="True" \
    --with-kernelversion="3.2" \
    --with-transcoding="False" \
    --with-multitranscoding="False" \
    --with-middle-flash="False" \
    --with-multilib="False" \
    --with-hdmi="True" \
    --with-yuv="False" \
    --with-rca="False" \
    --with-av-jack="False" \
    --with-scart="True" \
    --with-dvi="False" \
    --with-svideo="False" \
    --with-hdmi-in-hd="False" \
    --with-hdmi-in-fhd="False" \
    --with-wol="False" \
    --with-ci="False" \
    --with-blindscanbinary="" \
    --with-socfamily="bcm7405" \
    --with-vfd-symbol="False" \
    --with-rctype="" \
    --with-rcname="" \
    --with-rcidnum="" \
    --with-fhdskin="False" \
    "

FILES_${PN} = "${libdir}/enigma2/python/*.so"
FILES_${PN}-dev += "${libdir}/enigma2/python/*.la"
FILES_${PN}-staticdev += "${libdir}/enigma2/python/*.a"
FILES_${PN}-dbg += "${libdir}/enigma2/python/.debug"
