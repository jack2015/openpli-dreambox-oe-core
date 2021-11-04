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
BOX_2="dm800se-en-clone"
BOX_3="dm800se-en-original"
BOX_4="dm800sev2-en-clone"
BOX_5="dm800sev2-en-original"
BOX_6="dm900-clone"
BOX_7="dm900-original"
BOX_8="dm920"

list=
for i in $(seq 1 8); do
    p="BOX_$i"
    list="$list $i ${!p} "
done
list=($list) #00ff2525
box=$(dialog --stdout --clear --colors --menu "Build Dreambox Image" 22 70 10 ${list[@]})
    case $box in
    1)
    machinespecific="dm800se-cn"
    ;;
    2)
    machinespecific="dm800se-en-clone"
    ;;
    3)
    machinespecific="dm800se-en-original"
    ;;
    4)
    machinespecific="dm800sev2-en-clone"
    ;;
    5)
    machinespecific="dm800sev2-en-original"
    ;;
    6)
    machinespecific="dm900-clone"
    ;;
    7)
    machinespecific="dm900-original"
    ;;
    8)
    machinespecific="dm920"
    ;;
    *) clear && exit ;;
    esac

clear
## Menu Select build type ##
TYPE_1="image"
TYPE_2="feed"
list=
for i in $(seq 1 2); do
    p="TYPE_$i"
    list="$list $i ${!p} "
done
list=($list)
build=$(dialog --stdout --clear --colors --menu "Select build type" 12 60 10 ${list[@]})
    case $build in
    1)
    echostr="Compiling $machinespecific image, please wait ..."
    MAKETYPE="image"
    ;;
    2)
    echostr="Compiling $machinespecific image and feed, please wait ..."
    MAKETYPE="feed"
    ;;
    *) clear && exit ;;
    esac

clear
if [ "$machinespecific" = "dm800se-cn" ]; then
    cp -pf $def_path/dm800se/defconfig $def_path/defconfig
    cp -pf backup/dm800se-cn/* meta-dream/recipes-local/images/
    cp -pf backup/dm800se-en/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se make ${MAKETYPE}
elif [ "$machinespecific" = "dm800se-en-clone" ]; then
    cp -pf $def_path/dm800se/defconfig $def_path/defconfig
    cp -pf backup/dm800se-en/openpli-enigma2-image.bbappend meta-dream/recipes-local/images/
    cp -pf backup/dm800se-en/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se make ${MAKETYPE}
elif [ "$machinespecific" = "dm800se-en-original" ]; then
    cp -pf $def_path/dm800se/defconfig $def_path/defconfig
    cp -pf backup/dm800se-en/openpli-enigma2-image.bbappend meta-dream/recipes-local/images/
    cp -pf backup/dm800se-en/original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se make ${MAKETYPE}
elif [ "$machinespecific" = "dm8000" ]; then
    cp -pf $def_path/dm8000/defconfig $def_path/defconfig
    echo "$echostr"
    MACHINE=dm8000 make ${MAKETYPE}
elif [ "$machinespecific" = "dm900-clone" ]; then
    cp -pf $def_path2/dm900/defconfig $def_path2/defconfig
    cp -pf backup/dm900-clone/* meta-dream/recipes-local/drivers/
    cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
    echo "$echostr"
    MACHINE=dm900 make ${MAKETYPE}
elif [ "$machinespecific" = "dm900-original" ]; then
    cp -pf $def_path2/dm900/defconfig $def_path2/defconfig
    cp -pf backup/dm900-original/* meta-dream/recipes-local/drivers/
    cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
    echo "$echostr"
    MACHINE=dm900 make ${MAKETYPE}
elif [ "$machinespecific" = "dm920" ]; then
    cp -pf $def_path2/dm920/defconfig $def_path2/defconfig
    cp -pf backup/dm9x0/* meta-dream/recipes-local/images/
    echo "$echostr"
    MACHINE=dm920 make ${MAKETYPE}
elif [ "$machinespecific" = "dm800sev2-en-clone" ]; then
    cp -pf $def_path/dm800sev2/defconfig $def_path/defconfig
    cp -pf backup/dm800sev2-en/openpli-enigma2-image.bbappend meta-dream/recipes-local/images/
    cp -pf backup/dm800sev2-en/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800sev2 make ${MAKETYPE}
elif [ "$machinespecific" = "dm800sev2-en-original" ]; then
    cp -pf $def_path/dm800sev2/defconfig $def_path/defconfig
    cp -pf backup/dm800sev2-en/openpli-enigma2-image.bbappend meta-dream/recipes-local/images/
    cp -pf backup/dm800sev2-en/original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800sev2 make ${MAKETYPE}
else
    echo "Please enter a correct choice"
fi
