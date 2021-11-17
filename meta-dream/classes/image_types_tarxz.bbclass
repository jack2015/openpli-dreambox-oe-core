inherit image_types

IMAGEDIR = "${MACHINE}"
IMAGEVERSION := "OPENPLI-${DISTRO_VERSION}-${MACHINE}-${MACHINESIMS}-${DATE}"
IMAGEVERSION[vardepsexclude] = "DATE"

IMAGE_CMD:tar = "tar --sort=name --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar -C ${IMAGE_ROOTFS} . || [ $? -eq 1 ]"

IMAGE_CMD:tar:prepend = " \
    mkdir -p ${IMAGE_ROOTFS}/tmp; \
    "

CONVERSION_CMD:xz = " \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    xz -f -k -c ${XZ_COMPRESSION_LEVEL} ${XZ_DEFAULTS} --check=${XZ_INTEGRITY_CHECK} ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar > ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar.xz; \
    mkdir -p ${IMAGEDIR}; \
    echo "${IMAGEVERSION}" > ${IMAGEDIR}/imageversion; \
    mv ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar.xz ${IMAGEDIR}/rootfs.tar.xz; \
    zip openpli-${DISTRO_VERSION}_${MACHINE}_${MACHINESIMS}_${DATE}.zip ${IMAGEDIR}/*; \
    rm -f *.manifest; \
    rm -rf ${IMAGEDIR}; \
    "
