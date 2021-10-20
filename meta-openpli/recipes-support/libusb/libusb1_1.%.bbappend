# Don't configure udev by default since it will cause a circular
# dependecy with udev package, which depends on libusb

PACKAGECONFIG_class-target ??= ""

EXTRA_OECONF = "--libdir=${base_libdir} --disable-udev"
