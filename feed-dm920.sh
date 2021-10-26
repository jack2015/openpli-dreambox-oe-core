#!/bin/sh

gcc --version | sed -nr '/Ubuntu [0-9]+/ s/.*Ubuntu +([0-9]+).*/\1/p' > /tmp/vision-gcc-version
VISIONGCCVERSION=`cat /tmp/vision-gcc-version`
if [ "${VISIONGCCVERSION}" != '11' ]; then
	echo -e "${RED}GCC version is wrong!"
	echo -e "It means you need to choose version 11 of GCC!"
	sudo update-alternatives --config gcc
	gcc --version | sed -nr '/Ubuntu [0-9]+/ s/.*Ubuntu +([0-9]+).*/\1/p' > /tmp/vision-gcc-version
	VISIONGCCVERSION=`cat /tmp/vision-gcc-version`
	echo -e "Done, now GCC version is: ${VISIONGCCVERSION} ${NC}"
	echo -e ""
	exit 0
fi

SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
def_path2="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.14"

cp -pf $def_path2/dm920/defconfig $def_path2/defconfig
cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
echo "Compiling DM920 image & feed, please wait ..."
MACHINE=dm920 make feed
