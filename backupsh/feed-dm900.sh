#!/bin/sh

gcc --version | sed -nr '/Ubuntu [0-9]+/ s/.*Ubuntu +([0-9]+).*/\1/p' > /tmp/vision-gcc-version
VISIONGCCVERSION=`cat /tmp/vision-gcc-version`
if [ "${VISIONGCCVERSION}" != '10' ]; then
	echo -e "${RED}GCC version is wrong!"
	echo -e "It means you need to choose version 10 of GCC!"
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

cp -pf $def_path2/dm900/defconfig $def_path2/defconfig
cp -pf backup/dm900-original/* meta-dream/recipes-bsp/drivers/
cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
cp -pf backup/serviceapp/dm9x0/enigma2.bbappend meta-dream/recipes-local/enigma2/
cp -pf backup/serviceapp/dm9x0/enigma2-plugin-systemplugins-serviceapp_0.5.bbappend meta-dream/recipes-local/enigma2-plugins/
echo "Compiling $machinespecific image & feed, please wait ..."
MACHINE=dm900 make feed
