SUMMARY = "Dreambox EGL and GLES2 libraries"
DEPENDS = "${LIBEGLDEP}"
PROVIDES = "virtual/egl virtual/libgles2"
RPROVIDES:${PN} = "virtual/egl virtual/libgles2"

LIBEGLDEP = ""
LIBEGLDEP:bcm73625 = "libv3ddriver"
LIBEGLDEP:bcm7435 = "libv3ddriver"
LIBEGLDEP:bcm7252s = "libvc5driver"

LIBEGLRDEP = ""
LIBEGLRDEP:bcm7435 = "libv3ddriver"
LIBEGLRDEP:bcm7252s = "libvc5driver"

RDEPENDS:${PN} = "${LIBEGLRDEP}"

RPROVIDES:${PN} = "libegl libegl1 libgles2 libglesv2-2"
RPROVIDES:${PN}-dev = "libegl-dev libgles2-dev"

RCONFLICTS:${PN} = "libegl libegl1 libgles2 libglesv2-2"
RCONFLICTS:${PN}-dev = "libegl-dev libgles2-dev"

RREPLACES:${PN} = "libegl libegl1 libgles2 libglesv2-2"
RREPLACES:${PN}-dev = "libegl-dev libgles2-dev"

# As a workaround, keep .so symlinks in base package
# until all users were recompiled.
FILES:${PN} += "${libdir}/lib*${SOLIBSDEV}"
FILES:SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

SRC_URI[cortexa15hf-neon-vfpv4.md5sum] = "ab90073665c58598fea18039c99b211a"
SRC_URI[cortexa15hf-neon-vfpv4.sha256sum] = "5f76330975369cd20ef9e6d4ed03daa60694aea3cdf2d425fdb1d434ff68b534"
SRC_URI[mips32el.md5sum] = "b72e651aaa6a67ccdc973b609b6451ca"
SRC_URI[mips32el.sha256sum] = "cb3568a58964e110531619a6012315ad70bb62f095c7ee4a0b2b20cf66b08fff"

inherit opendreambox-precompiled-binary3
