# Don't configure udev by default since it will cause a circular
# dependecy with udev package, which depends on libusb
# EXTRA_OECONF = "--libdir=${base_libdir} --disable-udev"

PACKAGECONFIG:class-target = ""

PACKAGE_ARCH = "${MACHINE_ARCH}"
