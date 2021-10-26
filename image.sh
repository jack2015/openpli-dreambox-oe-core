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

rm -f build/bitbake.lock

def_path="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.2"
def_path2="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.14"

clear
## Menu Select Boxes ##
BOX_1="dm800se-cn"
BOX_2="dm800se-en"
BOX_3="dm800sev2-en"
BOX_4="dm900-clone"
BOX_5="dm900-original"
BOX_6="dm920"
list=
for i in $(seq 1 6); do
    p="BOX_$i"
    list="$list $i ${!p} "
done
list=($list) #00ff2525
box=$(dialog --stdout --clear --colors --menu "Build Dreambox Image" 22 70 10 ${list[@]})
    case $box in
    1)
    echo "($box) Select DM800se chinese language"
    machinespecific="dm800se-cn"
    ;;
    2)
    echo "($box) Select DM800se english language"
    machinespecific="dm800se-en"
    ;;
    3)
    echo "($box) Select DM800sev2 english language"
    machinespecific="dm800sev2-en"
    ;;
    4)
    echo "($box) Select DM900 CLONE"
    machinespecific="dm900-clone"
    ;;
    5)
    echo "($box) Select DM900 ORIGINAL"
    machinespecific="dm900-original"
    ;;
    6)
    echo "($box) Select DM920"
    machinespecific="dm920"
    ;;
    *) clear && exit ;;
    esac

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
