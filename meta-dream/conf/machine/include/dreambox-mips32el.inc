# dreambox-mips32el.inc

BOX_BRAND = "dreambox"
TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"
MTD_KERNEL = "mtd2"

KERNEL_FILE = "kernel.bin"
ROOTFS_FILE = "rootfs.bin"

MACHINE_EXTRA_RRECOMMENDS += "\
    ${@bb.utils.contains_any("MACHINE", "dm500hd dm800se", "", "gstreamer1.0-libav", d)} \
    "

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    libpagecache \
    libpassthrough \
"

require conf/machine/include/dreambox.inc
require conf/machine/include/mips/tune-mips32.inc

MACHINE_KERNEL_PR = "r15"

DVBMEDIASINK_CONFIG = "--with-pcm --with-wma --with-wmv --with-dtsdownmix --with-eac3 --with-dts"
DVBMEDIASINK_CONFIG:dm8000 = "--with-pcm --with-wma --with-wmv --with-dtsdownmix --with-eac3 --with-dts --with-limited-mpeg4v2"
DVBMEDIASINK_CONFIG:dm7080 = "--with-pcm --with-wma --with-wmv --with-dtsdownmix --with-eac3 --with-dts --with-vb6 --with-vb8 --with-spark"
DVBMEDIASINK_CONFIG:dm820 = "--with-pcm --with-wma --with-wmv --with-dtsdownmix --with-eac3 --with-dts --with-vb6 --with-vb8 --with-spark"
DVBMEDIASINK_CONFIG:dm520 = "--with-pcm --with-wma --with-wmv --with-dtsdownmix --with-eac3 --with-dts --with-vb6 --with-vb8 --with-spark --with-h265"
