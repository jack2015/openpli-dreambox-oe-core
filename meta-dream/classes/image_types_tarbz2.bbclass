inherit image_types

IMAGEDIR = "${MACHINE}"
IMAGEVERSION = "openpli-${DISTRO_VERSION}-${MACHINE}-${MACHINESIMS}-${DATE}"
IMAGEVERSION[vardepsexclude] += "DATE"

IMAGE_CMD:tar = "tar --sort=name --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar -C ${IMAGE_ROOTFS} . || [ $? -eq 1 ]"

IMAGE_CMD:tar:prepend = " \
    mkdir -p ${IMAGE_ROOTFS}/tmp; \
    "

CONVERSION_CMD:bz2 = " \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.json; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.tar.bz2; \
    bzip2 -f -k ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar; \
    mkdir -p ${IMAGEDIR}; \
    cp ${DEPLOY_DIR_IMAGE}/zImage ${IMAGEDIR}/${KERNEL_FILE}; \
    echo "${IMAGEVERSION}" > ${IMAGEDIR}/imageversion; \
    cp ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar.bz2 ${IMAGEDIR}/rootfs.tar.bz2; \
    zip ${IMAGEVERSION}.zip ${IMAGEDIR}/*; \
    rm -f *.manifest; \
    rm -rf ${IMAGEDIR}; \
    "

REPRODUCIBLE_TIMESTAMP_ROOTFS = "${@time.strftime('%s')}"
