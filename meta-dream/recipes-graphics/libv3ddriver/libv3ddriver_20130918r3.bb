SUMMARY = "Broadcom V3D driver"

COMPATIBLE_MACHINE = "^(dm7080|dm520|dm820)$"

FILES_${PN} = "${libdir}/lib*${SOLIBSDEV}"
FILES_${PN}-dev = "${includedir}"

EXCLUDE_FROM_WORLD = "1"

INSANE_SKIP_${PN} += "already-stripped dev-so ldflags"

SRC_URI[mips32el.md5sum] = "bc8608430b20ed65260be268b3db89a0"
SRC_URI[mips32el.sha256sum] = "3daa50dd2750770f727bf8599703ea3ff0f19f23f6623453051fc29d9a6ca5eb"

inherit opendreambox-precompiled-binary3
