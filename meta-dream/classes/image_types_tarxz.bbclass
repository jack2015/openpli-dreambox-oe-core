inherit image_types

IMAGEDIR = "${MACHINE}"
IMAGEVERSION := "OPENPLI-${DISTRO_VERSION}-${MACHINE}-${MACHINESIMS}-${DATE}"
IMAGEVERSION[vardepsexclude] = "DATE"

IMAGE_CMD_tar = "${IMAGE_CMD_TAR} --sort=name --format=gnu --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar -C ${IMAGE_ROOTFS} . || [ $? -eq 1 ]"

CONVERSION_CMD_xz_dm820 = " \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    xz -f -k -c ${XZ_COMPRESSION_LEVEL} ${XZ_DEFAULTS} --check=${XZ_INTEGRITY_CHECK} ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type} > ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.xz; \
    mkdir -p ${IMAGEDIR}; \
    cp ${DEPLOY_DIR_IMAGE}/vmlinux.gz ${IMAGEDIR} 2>/dev/null; \
    cp ${DEPLOY_DIR_IMAGE}/vmlinux.bin ${IMAGEDIR} 2>/dev/null; \
    echo "${IMAGEVERSION}" > ${IMAGEDIR}/imageversion; \
    mv ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar.xz ${IMAGEDIR}/rootfs.tar.xz; \
    zip openpli-${DISTRO_VERSION}_${MACHINE}_${MACHINESIMS}_${DATE}.zip ${IMAGEDIR}/*; \
    rm -f *.manifest; \
    rm -rf ${IMAGEDIR}; \
    "

CONVERSION_CMD_xz_dm520 = " \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    xz -f -k -c ${XZ_COMPRESSION_LEVEL} ${XZ_DEFAULTS} --check=${XZ_INTEGRITY_CHECK} ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type} > ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.xz; \
    mkdir -p ${IMAGEDIR}; \
    cp ${DEPLOY_DIR_IMAGE}/vmlinux.gz ${IMAGEDIR} 2>/dev/null; \
    echo "${IMAGEVERSION}" > ${IMAGEDIR}/imageversion; \
    mv ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar.xz ${IMAGEDIR}/rootfs.tar.xz; \
    zip openpli-${DISTRO_VERSION}_${MACHINE}_${MACHINESIMS}_${DATE}.zip ${IMAGEDIR}/*; \
    rm -f *.manifest; \
    rm -rf ${IMAGEDIR}; \
    "
