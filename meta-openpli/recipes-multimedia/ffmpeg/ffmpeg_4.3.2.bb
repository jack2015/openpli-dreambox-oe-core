SUMMARY = "A complete, cross-platform solution to record, convert and stream audio and video."
DESCRIPTION = "FFmpeg is the leading multimedia framework, able to decode, encode, transcode, \
               mux, demux, stream, filter and play pretty much anything that humans and machines \
               have created. It supports the most obscure ancient formats up to the cutting edge."
HOMEPAGE = "https://www.ffmpeg.org/"
SECTION = "libs"

LICENSE = "BSD & GPLv2+ & LGPLv2.1+ & MIT"
LICENSE_${PN} = "GPLv2+"
LICENSE_libavcodec = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavdevice = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavfilter = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavformat = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavresample = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libavutil = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libpostproc = "GPLv2+"
LICENSE_libswresample = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_libswscale = "${@bb.utils.contains('PACKAGECONFIG', 'gpl', 'GPLv2+', 'LGPLv2.1+', d)}"
LICENSE_FLAGS = "commercial"

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02"

MIPSFPU = "${@bb.utils.contains("TARGET_FPU", "soft", "--disable-mipsfpu", "--enable-mipsfpu", d)}"

SRC_URI = "\
	git://github.com/FFmpeg/FFmpeg.git;branch=release/4.3 \
	file://01-mips64_cpu_detection.patch \
	file://0001-libavutil-include-assembly-with-full-path-from-sourc.patch \
	file://02-fix-hls.patch \
	file://03-buffer-size.patch \
	file://04-aac.patch \
	file://05-fix-mpegts.patch \
	file://06-allow_to_choose_rtmp_impl_at_runtime.patch \
	file://07-fix-edit-list-parsing.patch \
	file://08-hls-replace-key-uri.patch \
	file://09-Define-soft-mips-variables.patch \
	file://10_rtsp_patch \
	file://11_dxva2_patch \
	"

S = "${WORKDIR}/git"

inherit gitpkgv autotools pkgconfig

PV = "4.3.2+git${SRCPV}"
PKGV = "4.3.2+git${GITPKGV}"

# Build fails when thumb is enabled: https://bugzilla.yoctoproject.org/show_bug.cgi?id=7717
ARM_INSTRUCTION_SET_armv4 = "arm"
ARM_INSTRUCTION_SET_armv5 = "arm"
ARM_INSTRUCTION_SET_armv6 = "arm"

# Should be API compatible with libav (which was a fork of ffmpeg)
# libpostproc was previously packaged from a separate recipe
PROVIDES = "libav libpostproc"

RDEPENDS_${PN} = "libbluray rtmpdump libxml2 openssl"
DEPENDS = "alsa-lib zlib libogg nasm-native libxml2"

PACKAGECONFIG ??= "avdevice avfilter avcodec avformat swresample swscale postproc avresample \
		alsa bzlib gpl lzma pic pthreads shared theora x264 zlib \
		openjpeg openssl libass libbluray libfreetype librtmp mp3lame theora libvorbis libv4l2 lzma vpx wavpack \
		${@bb.utils.contains('AVAILTUNES', 'mips32r2', 'mips32r2', '', d)} \
		${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xv xcb', '', d)}"

# libraries to build in addition to avutil
PACKAGECONFIG[avdevice] = "--enable-avdevice,--disable-avdevice"
PACKAGECONFIG[avfilter] = "--enable-avfilter,--disable-avfilter"
PACKAGECONFIG[avcodec] = "--enable-avcodec,--disable-avcodec"
PACKAGECONFIG[avformat] = "--enable-avformat,--disable-avformat"
PACKAGECONFIG[swresample] = "--enable-swresample,--disable-swresample"
PACKAGECONFIG[swscale] = "--enable-swscale,--disable-swscale"
PACKAGECONFIG[postproc] = "--enable-postproc,--disable-postproc"
PACKAGECONFIG[avresample] = "--enable-avresample,--disable-avresample"

# features to support
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[libass] = "--enable-libass,--disable-libass,libass"
PACKAGECONFIG[altivec] = "--enable-altivec,--disable-altivec,"
PACKAGECONFIG[bzlib] = "--enable-bzlib,--disable-bzlib,bzip2"
PACKAGECONFIG[fdk-aac] = "--enable-libfdk-aac --enable-nonfree,--disable-libfdk-aac,fdk-aac"
PACKAGECONFIG[gpl] = "--enable-gpl,--disable-gpl"
PACKAGECONFIG[gsm] = "--enable-libgsm,--disable-libgsm,libgsm"
PACKAGECONFIG[jack] = "--enable-indev=jack,--disable-indev=jack,jack"
PACKAGECONFIG[libbluray] = "--enable-libbluray --enable-protocol=bluray,--disable-libbluray,libbluray"
PACKAGECONFIG[libfontconfig] = "--enable-libfontconfig,--disable-libfontconfig,fontconfig"
PACKAGECONFIG[libfreetype] = "--enable-libfreetype,--disable-libfreetype,freetype"
PACKAGECONFIG[librtmp] = "--enable-librtmp,--disable-librtmp,librtmp rtmpdump"
PACKAGECONFIG[libvorbis] = "--enable-libvorbis,--disable-libvorbis,libvorbis"
PACKAGECONFIG[lzma] = "--enable-lzma,--disable-lzma,xz"
PACKAGECONFIG[mfx] = "--enable-libmfx,--disable-libmfx,intel-mediasdk"
PACKAGECONFIG[mp3lame] = "--enable-libmp3lame,--disable-libmp3lame,lame"
PACKAGECONFIG[openssl] = "--enable-openssl,--disable-openssl,openssl"
PACKAGECONFIG[sdl2] = "--enable-sdl2,--disable-sdl2,virtual/libsdl2"
PACKAGECONFIG[speex] = "--enable-libspeex,--disable-libspeex,speex"
PACKAGECONFIG[srt] = "--enable-libsrt,--disable-libsrt,srt"
PACKAGECONFIG[theora] = "--enable-libtheora,--disable-libtheora,libtheora libogg"
PACKAGECONFIG[vaapi] = "--enable-vaapi,--disable-vaapi,libva"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,libvdpau"
PACKAGECONFIG[vpx] = "--enable-libvpx,--disable-libvpx,libvpx"
PACKAGECONFIG[x264] = "--enable-libx264,--disable-libx264,x264"
PACKAGECONFIG[x265] = "--enable-libx265,--disable-libx265,x265"
PACKAGECONFIG[xcb] = "--enable-libxcb,--disable-libxcb,libxcb"
PACKAGECONFIG[xv] = "--enable-outdev=xv,--disable-outdev=xv,libxv"
PACKAGECONFIG[zlib] = "--enable-zlib,--disable-zlib,zlib"
PACKAGECONFIG[openjpeg] = "--enable-libopenjpeg,--disable-libopenjpeg,openjpeg"
PACKAGECONFIG[wavpack] = "--enable-libwavpack,--disable-libwavpack,wavpack"
PACKAGECONFIG[libv4l2] = "--enable-libv4l2,--disable-libv4l2,v4l-utils"
PACKAGECONFIG[gnutls] = "--enable-gnutls,--disable-gnutls"

# other configuration options
PACKAGECONFIG[mips32r2] = ",--disable-mipsdsp --disable-mipsdspr2"
PACKAGECONFIG[pic] = "--enable-pic"
PACKAGECONFIG[pthreads] = "--enable-pthreads,--disable-pthreads"
PACKAGECONFIG[shared] = "--enable-shared"
PACKAGECONFIG[strip] = ",--disable-stripping"

# Check codecs that require --enable-nonfree
USE_NONFREE = "${@bb.utils.contains_any('PACKAGECONFIG', [ 'openssl' ], 'yes', '', d)}"

def cpu(d):
    for arg in (d.getVar('TUNE_CCARGS') or '').split():
        if arg.startswith('-mcpu='):
            return arg[6:]
    return 'generic'

EXTRA_OECONF = " \
	${@bb.utils.contains('USE_NONFREE', 'yes', '--enable-nonfree', '', d)} \
	\
	--cross-prefix=${TARGET_PREFIX} \
	\
	--ld="${CCLD}" \
	--cc="${CC}" \
	--cxx="${CXX}" \
	--arch=${TARGET_ARCH} \
	--target-os="linux" \
	--enable-cross-compile \
	--extra-cflags="${CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}" \
	--extra-ldflags="${LDFLAGS}" \
	--sysroot="${STAGING_DIR_TARGET}" \
	--enable-hardcoded-tables \
	${EXTRA_FFCONF} \
	--libdir=${libdir} \
	--shlibdir=${libdir} \
	--datadir=${datadir}/ffmpeg \
	--cpu=${@cpu(d)} \
	--pkg-config=pkg-config \
	"

EXTRA_OECONF_append_linux-gnux32 = " --disable-asm"
# gold crashes on x86, another solution is to --disable-asm but thats more hacky
# ld.gold: internal error in relocate_section, at ../../gold/i386.cc:3684

LDFLAGS_append_x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

do_configure() {
	${S}/configure ${EXTRA_OECONF}
}

PACKAGES =+ "libavcodec \
	libavdevice \
	libavfilter \
	libavformat \
	libavresample \
	libavutil \
	libpostproc \
	libswresample \
	libswscale"

FILES_libavcodec = "${libdir}/libavcodec${SOLIBS}"
FILES_libavdevice = "${libdir}/libavdevice${SOLIBS}"
FILES_libavfilter = "${libdir}/libavfilter${SOLIBS}"
FILES_libavformat = "${libdir}/libavformat${SOLIBS}"
FILES_libavresample = "${libdir}/libavresample${SOLIBS}"
FILES_libavutil = "${libdir}/libavutil${SOLIBS}"
FILES_libpostproc = "${libdir}/libpostproc${SOLIBS}"
FILES_libswresample = "${libdir}/libswresample${SOLIBS}"
FILES_libswscale = "${libdir}/libswscale${SOLIBS}"

# ffmpeg disables PIC on some platforms (e.g. x86-32)

EXTRA_FFCONF = " \
	--prefix=${prefix} \
	--disable-runtime-cpudetect \
	--disable-ffplay \
	--disable-ffprobe \
	\
	--disable-doc \
	--disable-htmlpages \
	--disable-manpages \
	--disable-podpages \
	--disable-txtpages \
	\
	--disable-altivec \
	--disable-amd3dnow \
	--disable-amd3dnowext \
	--disable-mmx \
	--disable-mmxext \
	--disable-sse \
	--disable-sse2 \
	--disable-sse3 \
	--disable-ssse3 \
	--disable-sse4 \
	--disable-sse42 \
	--disable-avx \
	--disable-xop \
	--disable-fma3 \
	--disable-fma4 \
	--disable-avx2 \
	--disable-inline-asm \
	--disable-yasm \
	--disable-x86asm \
	--disable-fast-unaligned \
	\
	--disable-dxva2 \
	--disable-vaapi \
	--disable-vdpau \
	\
	--disable-muxers \
	--enable-muxer=apng \
	--enable-muxer=flac \
	--enable-muxer=mp3 \
	--enable-muxer=h261 \
	--enable-muxer=h263 \
	--enable-muxer=h264 \
	--enable-muxer=h265 \
	--enable-muxer=hevc \
	--enable-muxer=image2 \
	--enable-muxer=image2pipe \
	--enable-muxer=m4v \
	--enable-muxer=matroska \
	--enable-muxer=mjpeg \
	--enable-muxer=mp4 \
	--enable-muxer=mpeg1video \
	--enable-muxer=mpeg2video \
	--enable-muxer=mpegts \
	--enable-muxer=ogg \
	\
	--disable-parsers \
	--enable-parser=aac \
	--enable-parser=aac_latm \
	--enable-parser=ac3 \
	--enable-parser=dca \
	--enable-parser=dvbsub \
	--enable-parser=dvd_nav \
	--enable-parser=dvdsub \
	--enable-parser=flac \
	--enable-parser=h264 \
	--enable-parser=h265 \
	--enable-parser=hevc \
	--enable-parser=mjpeg \
	--enable-parser=mpeg4video \
	--enable-parser=mpegvideo \
	--enable-parser=mpegaudio \
	--enable-parser=png \
	--enable-parser=vc1 \
	--enable-parser=vorbis \
	--enable-parser=vp8 \
	--enable-parser=vp9 \
	\
	--disable-encoders \
	--enable-encoder=aac \
	--enable-encoder=h261 \
	--enable-encoder=h263 \
	--enable-encoder=h263p \
	--enable-encoder=jpeg2000 \
	--enable-encoder=jpegls \
	--enable-encoder=ljpeg \
	--enable-encoder=mjpeg \
	--enable-encoder=mpeg1video \
	--enable-encoder=mpeg2video \
	--enable-encoder=mpeg4 \
	--enable-encoder=png \
	--enable-encoder=rawvideo \
	\
	--disable-decoders \
	--enable-decoder=aac \
	--enable-decoder=aac_latm \
	--enable-decoder=adpcm_ct \
	--enable-decoder=adpcm_g722 \
	--enable-decoder=adpcm_g726 \
	--enable-decoder=adpcm_g726le \
	--enable-decoder=adpcm_ima_amv \
	--enable-decoder=adpcm_ima_oki \
	--enable-decoder=adpcm_ima_qt \
	--enable-decoder=adpcm_ima_rad \
	--enable-decoder=adpcm_ima_wav \
	--enable-decoder=adpcm_ms \
	--enable-decoder=adpcm_sbpro_2 \
	--enable-decoder=adpcm_sbpro_3 \
	--enable-decoder=adpcm_sbpro_4 \
	--enable-decoder=adpcm_swf \
	--enable-decoder=adpcm_yamaha \
	--enable-decoder=alac \
	--enable-decoder=ape \
	--enable-decoder=atrac1 \
	--enable-decoder=atrac3 \
	--enable-decoder=atrac3p \
	--enable-decoder=ass \
	--enable-decoder=cook \
	--enable-decoder=dca \
	--enable-decoder=dsd_lsbf \
	--enable-decoder=dsd_lsbf_planar \
	--enable-decoder=dsd_msbf \
	--enable-decoder=dsd_msbf_planar \
	--enable-decoder=dvbsub \
	--enable-decoder=dvdsub \
	--enable-decoder=eac3 \
	--enable-decoder=evrc \
	--enable-decoder=flac \
	--enable-decoder=g723_1 \
	--enable-decoder=g729 \
	--enable-decoder=h261 \
	--enable-decoder=h263 \
	--enable-decoder=h263i \
	--enable-decoder=h264 \
	--enable-decoder=h265 \
	--enable-decoder=hevc \
	--enable-decoder=iac \
	--enable-decoder=imc \
	--enable-decoder=jpeg2000 \
	--enable-decoder=jpegls \
	--enable-decoder=mace3 \
	--enable-decoder=mace6 \
	--enable-decoder=metasound \
	--enable-decoder=mjpeg \
	--enable-decoder=mlp \
	--enable-decoder=movtext \
	--enable-decoder=mp1 \
	--enable-decoder=mp2 \
	--enable-decoder=mp3 \
	--enable-decoder=mp3adu \
	--enable-decoder=mp3on4 \
	--enable-decoder=mpeg1video \
	--enable-decoder=mpeg2video \
	--enable-decoder=mpeg4 \
	--enable-decoder=nellymoser \
	--enable-decoder=opus \
	--enable-decoder=pcm_alaw \
	--enable-decoder=pcm_bluray \
	--enable-decoder=pcm_dvd \
	--enable-decoder=pcm_f32be \
	--enable-decoder=pcm_f32le \
	--enable-decoder=pcm_f64be \
	--enable-decoder=pcm_f64le \
	--enable-decoder=pcm_lxf \
	--enable-decoder=pcm_mulaw \
	--enable-decoder=pcm_s16be \
	--enable-decoder=pcm_s16be_planar \
	--enable-decoder=pcm_s16le \
	--enable-decoder=pcm_s16le_planar \
	--enable-decoder=pcm_s24be \
	--enable-decoder=pcm_s24daud \
	--enable-decoder=pcm_s24le \
	--enable-decoder=pcm_s24le_planar \
	--enable-decoder=pcm_s32be \
	--enable-decoder=pcm_s32le \
	--enable-decoder=pcm_s32le_planar \
	--enable-decoder=pcm_s8 \
	--enable-decoder=pcm_s8_planar \
	--enable-decoder=pcm_u16be \
	--enable-decoder=pcm_u16le \
	--enable-decoder=pcm_u24be \
	--enable-decoder=pcm_u24le \
	--enable-decoder=pcm_u32be \
	--enable-decoder=pcm_u32le \
	--enable-decoder=pcm_u8 \
	--enable-decoder=pcm_zork \
	--enable-decoder=pgssub \
	--enable-decoder=png \
	--enable-decoder=qcelp \
	--enable-decoder=qdm2 \
	--enable-decoder=ra_144 \
	--enable-decoder=ra_288 \
	--enable-decoder=ralf \
	--enable-decoder=s302m \
	--enable-decoder=sipr \
	--enable-decoder=shorten \
	--enable-decoder=sonic \
	--enable-decoder=srt \
	--enable-decoder=ssa \
	--enable-decoder=subrip \
	--enable-decoder=subviewer \
	--enable-decoder=subviewer1 \
	--enable-decoder=tak \
	--enable-decoder=text \
	--enable-decoder=truehd \
	--enable-decoder=truespeech \
	--enable-decoder=tta \
	--enable-decoder=vorbis \
	--enable-decoder=wmalossless \
	--enable-decoder=wmapro \
	--enable-decoder=wmav1 \
	--enable-decoder=wmav2 \
	--enable-decoder=wmavoice \
	--enable-decoder=wavpack \
	--enable-decoder=xsub \
	\
	--disable-demuxers \
	--enable-demuxer=aac \
	--enable-demuxer=ac3 \
	--enable-demuxer=apng \
	--enable-demuxer=ass \
	--enable-demuxer=avi \
	--enable-demuxer=dts \
	--enable-demuxer=dash \
	--enable-demuxer=ffmetadata \
	--enable-demuxer=flac \
	--enable-demuxer=flv \
	--enable-demuxer=h264 \
	--enable-demuxer=h265 \
	--enable-demuxer=hls \
	--enable-demuxer=image2 \
	--enable-demuxer=image2pipe \
	--enable-demuxer=image_bmp_pipe \
	--enable-demuxer=image_jpeg_pipe \
	--enable-demuxer=image_jpegls_pipe \
	--enable-demuxer=image_png_pipe \
	--enable-demuxer=m4v \
	--enable-demuxer=matroska \
	--enable-demuxer=mjpeg \
	--enable-demuxer=mov \
	--enable-demuxer=mp3 \
	--enable-demuxer=mpegts \
	--enable-demuxer=mpegtsraw \
	--enable-demuxer=mpegps \
	--enable-demuxer=mpegvideo \
	--enable-demuxer=mpjpeg \
	--enable-demuxer=ogg \
	--enable-demuxer=pcm_s16be \
	--enable-demuxer=pcm_s16le \
	--enable-demuxer=realtext \
	--enable-demuxer=rawvideo \
	--enable-demuxer=rm \
	--enable-demuxer=rtp \
	--enable-demuxer=rtsp \
	--enable-demuxer=srt \
	--enable-demuxer=vc1 \
	--enable-demuxer=wav \
	--enable-demuxer=webm_dash_manifest \
	\
	--disable-filters \
	--enable-filter=scale \
	--enable-filter=drawtext \
	--enable-filter=overlay \
	\
	--enable-zlib \
	--enable-bzlib \
	--enable-openssl \
	--enable-libass \
	--enable-bsfs \
	--disable-xlib \
	--disable-libxcb \
	--disable-libxcb-shm \
	--disable-libxcb-xfixes \
	--disable-libxcb-shape \
	\
	--enable-shared \
	--enable-network \
	--enable-nonfree \
	--enable-small \
	--enable-stripping \
	--disable-static \
	--disable-debug \
	--disable-runtime-cpudetect \
	--enable-pic \
	--enable-pthreads \
	--enable-hardcoded-tables \
	\
    ${@bb.utils.contains("TARGET_ARCH", "mipsel", "${MIPSFPU} --disable-vfp --disable-neon --disable-mipsdsp --disable-mipsdspr2", "", d)} \
    ${@bb.utils.contains("TARGET_ARCH", "arm", "--enable-armv6 --enable-armv6t2 --enable-vfp --enable-neon", "", d)} \
    ${@bb.utils.contains("TUNE_FEATURES", "aarch64", "--enable-armv8 --enable-vfp --enable-neon", "", d)} \
	${@bb.utils.contains("TARGET_ARCH", "sh4", "--disable-vfp --disable-neon", "", d)} \
    --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS} -ffunction-sections -fdata-sections -fno-aggressive-loop-optimizations" \
    --extra-ldflags="${TARGET_LDFLAGS},--gc-sections -Wl,--print-gc-sections,-lrt" \
"
