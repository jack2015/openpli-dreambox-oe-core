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

def_path="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.2"

echo "Please enter: (1)dm800se-cn (2)dm800se-en"

read machinespecific

if [ "$machinespecific" != "dm8000" -a "$machinespecific" != "dm800se-cn" -a "$machinespecific" != "dm800se-en" ]; then
	echo -e "${RED}Not a valid answer!${NC}"
	echo -e ""
	exit 0
fi

if [ "$machinespecific" = "dm800se-cn" ]; then
	cp -pf $def_path/dm800se/defconfig $def_path/defconfig
	cp -pf backup/dm800se-cn/* meta-dream/recipes-local/images/
	echo "Compiling $machinespecific image, please wait ..."
	MACHINE=dm800se make image
elif [ "$machinespecific" = "dm800se-en" ]; then
	cp -pf $def_path/dm800se/defconfig $def_path/defconfig
	cp -pf backup/dm800se-en/* meta-dream/recipes-local/images/
	echo "Compiling $machinespecific image, please wait ..."
	MACHINE=dm800se make image
elif [ "$machinespecific" = "dm8000" ]; then
	cp -pf $def_path/dm8000/defconfig $def_path/defconfig
	echo "Compiling $machinespecific image, please wait ..."
	MACHINE=dm8000 make image
else
	echo "Please enter a correct choice"
fi


