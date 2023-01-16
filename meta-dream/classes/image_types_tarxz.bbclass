inherit image_types

IMAGEDIR = "${MACHINE}"
IMAGEVERSION = "openpli-${DISTRO_VERSION}-${MACHINE}-${MACHINESIMS}-${DATE}"
IMAGEVERSION[vardepsexclude] += "DATE"

IMAGE_CMD:tar = "tar --sort=name --numeric-owner -cf ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar -C ${IMAGE_ROOTFS} . || [ $? -eq 1 ]"

IMAGE_CMD:tar:prepend = " \
    mkdir -p ${IMAGE_ROOTFS}/tmp; \
    mkdir -p ${IMAGE_ROOTFS}/var/lib/opkg/info; \
    echo "#!/bin/sh" > ${IMAGE_ROOTFS}/var/lib/opkg/info/dreambox-secondstage.postinst; \
    echo "[ -n \"\$D\" ] || flash-ssbl /usr/share/dreambox-secondstage/ssbl.bin" >> ${IMAGE_ROOTFS}/var/lib/opkg/info/dreambox-secondstage.postinst; \
    chmod 777 ${IMAGE_ROOTFS}/var/lib/opkg/info/dreambox-secondstage.postinst; \
    "

CONVERSION_CMD:xz = " \
    rm -f ${DEPLOY_DIR_IMAGE}/*.zip; \
    xz -f -k -c ${XZ_COMPRESSION_LEVEL} ${XZ_DEFAULTS} --check=${XZ_INTEGRITY_CHECK} ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar > ${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.tar.xz; \
    echo "${IMAGEVERSION}" > ./imageversion; \
    zip ${IMAGEVERSION}.zip ./imageversion ./*.xz; \
    rm -f ./*.manifest; \
    rm -f ./imageversion; \
    "
