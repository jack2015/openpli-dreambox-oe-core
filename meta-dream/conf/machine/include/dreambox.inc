# dreambox.inc
# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED += "rtc"

OPTIONAL_BSP_PACKAGES += "dreambox-buildimage"

EXTRA_IMAGEDEPENDS += "dreambox-secondstage-${MACHINE}"

# KERNEL_MODULE_AUTOLOAD += "xfs"

MACHINE_EXTRA_RRECOMMENDS += " \
	${@bb.utils.contains_any("MACHINE", "dm500hd dm800se", "", "gstreamer1.0-libav", d)} \
	gstreamer1.0-plugin-multibox-dvbmediasink \
	kernel-module-cifs \
	kernel-module-hmac \
	kernel-module-md4 \
	kernel-module-ecb \
	dreambox-boot-progress \
	"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += " \
	dreambox-dvb-modules-${MACHINE} \
	kernel-module-stv0299 \
	libpagecache \
	libpassthrough \
	"

GST_VERSION = "1.0"
	
MACHINE_FEATURES += "alsa usbhost dreambox"
# Add "dreambox" override for recipes
MACHINEOVERRIDES .= ":dreambox"

PREFERRED_PROVIDER_virtual/bootloader = "dreambox-secondstage-${MACHINE}"
PREFERRED_PROVIDER_virtual/kernel = "linux-dreambox"

PREFERRED_PROVIDER_virtual/blindscan-dvbs = "dreambox-blindscan-utils"
PREFERRED_PROVIDER_virtual/blindscan-dvbc = "dreambox-blindscan-utils"

PREFERRED_VERSION_linux-dreambox = "3.2"

KERNEL_IMAGETYPE = "vmlinux"
KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"
KERNEL_OUTPUT_DIR = "."
KERNEL_CONSOLE = "ttyS0,115200"

DREAMBOX_BUILDIMAGE = "buildimage --arch ${MACHINE} ${EXTRA_BUILDCMD} \
	--erase-block-size ${DREAMBOX_ERASE_BLOCK_SIZE} \
	--sector-size ${DREAMBOX_SECTOR_SIZE}"
