inherit image_types

IMAGEDIR = "${MACHINE}"
IMAGEVERSION := "OPENPLI-${DISTRO_VERSION}-${MACHINE}-${DATE}"
IMAGEVERSION[vardepsexclude] = "DATE"

CONVERSION_CMD:bz2 = " \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    pbzip2 -f -k ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}; \
    mkdir -p ${IMAGEDIR}; \
    cp ${DEPLOY_DIR_IMAGE}/zImage ${IMAGEDIR}/${KERNEL_FILE}; \
    echo "${IMAGEVERSION}" > ${IMAGEDIR}/imageversion; \
    mv ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar.bz2 ${IMAGEDIR}/rootfs.tar.bz2; \
    zip openpli-${DISTRO_VERSION}_${MACHINE}_${MACHINESIMS}_${DATE}.zip ${IMAGEDIR}/*; \
    rm -f *.manifest; \
    rm -rf ${IMAGEDIR}; \
    "
