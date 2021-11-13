SUMMARY = "Dreambox EGL and GLES2 libraries"
DEPENDS = "${LIBEGLDEP}"
PROVIDES = "virtual/egl virtual/libgles1 virtual/libgles2"
RPROVIDES_${PN} = "virtual/egl virtual/libgles1 virtual/libgles2"

COMPATIBLE_MACHINE = "^(dm900|dm920|dm7080|dm520|dm820)$"

LIBEGLDEP = ""
LIBEGLDEP_dm520 = "libv3ddriver"
LIBEGLDEP_dm820 = "libv3ddriver"
LIBEGLDEP_dm7080 = "libv3ddriver"
LIBEGLDEP_dm900 = "libvc5driver"
LIBEGLDEP_dm920 = "libvc5driver"

LIBEGLRDEP = ""
LIBEGLRDEP_dm820 = "libv3ddriver"
LIBEGLRDEP_dm7080 = "libv3ddriver"
LIBEGLRDEP_dm900 = "libvc5driver"
LIBEGLRDEP_dm920 = "libvc5driver"

RDEPENDS_${PN} = "${LIBEGLRDEP}"

RPROVIDES_${PN} = "libegl libegl1 libgles1 libgles2 libglesv2-2"
RPROVIDES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"

RCONFLICTS_${PN} = "libegl libegl1 libgles1 libgles2 libglesv2-2"
RCONFLICTS_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"

RREPLACES_${PN} = "libegl libegl1 libgles1 libgles2 libglesv2-2"
RREPLACES_${PN}-dev = "libegl-dev libgles1-dev libgles2-dev"

# As a workaround, keep .so symlinks in base package
# until all users were recompiled.
FILES_${PN} += "${libdir}/lib*${SOLIBSDEV}"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "already-stripped dev-so ldflags"

SRC_URI[cortexa15hf-neon-vfpv4.md5sum] = "ab90073665c58598fea18039c99b211a"
SRC_URI[cortexa15hf-neon-vfpv4.sha256sum] = "5f76330975369cd20ef9e6d4ed03daa60694aea3cdf2d425fdb1d434ff68b534"
SRC_URI[mips32el.md5sum] = "b72e651aaa6a67ccdc973b609b6451ca"
SRC_URI[mips32el.sha256sum] = "cb3568a58964e110531619a6012315ad70bb62f095c7ee4a0b2b20cf66b08fff"

inherit opendreambox-precompiled-binary3
