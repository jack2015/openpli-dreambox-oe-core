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
def_path2="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.14"

echo "Please enter: (1)dm800se-cn (2)dm800se-en (3)dm800sev2-en (4)dm900-clone (5)dm900-original (6)dm920"

read machinespecific

if [ "$machinespecific" != "dm900-clone" -a "$machinespecific" != "dm900-original" -a "$machinespecific" != "dm920" -a "$machinespecific" != "dm8000" -a "$machinespecific" != "dm800se-cn" -a "$machinespecific" != "dm800se-en" -a "$machinespecific" != "dm800sev2-en" -a "$machinespecific" != "dm800se-big" -a "$machinespecific" != "dm800se-only" ]; then
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
elif [ "$machinespecific" = "dm900-clone" ]; then
        cp -pf $def_path2/dm900/defconfig $def_path2/defconfig
	cp -pf backup/dm900-clone/* meta-dream/recipes-bsp/drivers/
	cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
	echo "Compiling $machinespecific image, please wait ..."
        MACHINE=dm900 make image
elif [ "$machinespecific" = "dm900-original" ]; then
        cp -pf $def_path2/dm900/defconfig $def_path2/defconfig
	cp -pf backup/dm900-original/* meta-dream/recipes-bsp/drivers/
	cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
	echo "Compiling $machinespecific image, please wait ..."
        MACHINE=dm900 make image
elif [ "$machinespecific" = "dm920" ]; then
        cp -pf $def_path2/dm920/defconfig $def_path2/defconfig
	cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
	echo "Compiling $machinespecific image, please wait ..."
        MACHINE=dm920 make image
elif [ "$machinespecific" = "dm800sev2-en" ]; then
        cp -pf $def_path/dm800sev2/defconfig $def_path/defconfig
	cp -pf backup/dm800sev2-en/* meta-dream/recipes-local/images/
	echo "Compiling $machinespecific image, please wait ..."
        MACHINE=dm800sev2 make image
else
	echo "Please enter a correct choice"
fi


