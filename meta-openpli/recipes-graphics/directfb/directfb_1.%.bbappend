FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += " ${@bb.utils.contains("MACHINE_FEATURES", "sdl", "libsdl", "", d)} ${@bb.utils.contains("TARGET_ARCH", "sh4", "libmme-host sh4-dvb-modules", "", d)}"

SRC_URI_append_sh4 += "\
    file://DirectFB-1.7.7.stm.fixed.patch;patch=1 \
"

EXTRA_OECONF_sh4 = " \
  --enable-freetype=yes \
  --with-gfxdrivers=none \
  ${@bb.utils.contains("MACHINE_FEATURES", "sdl", "--enable-sdl --disable-imlib2 --disable-mesa", "--disable-sdl", d)} \
  --enable-zlib \
  --disable-vnc \
  --disable-x11 \
  ${@bb.utils.contains("TARGET_ARCH", "sh4", "--enable-stmfbdev=yes --enable-mme=yes --enable-hwjpeg --enable-rle --enable-hwpng", "", d)} \
"

RRECOMMENDS_${PN}_append_sh4 += " directfb-stgfx2 "

FILES_${PN} += "\
  ${libdir}/directfb-${RV}/gfxdrivers/*.so \
"

FILES_${PN}-dev += "\
  ${libdir}/directfb-${RV}/gfxdrivers/*.la \
"
