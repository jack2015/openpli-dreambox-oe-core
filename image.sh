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
def_path3="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.4"

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
BOX_9="dm820"
BOX_10="dm520-original"
BOX_11="dm520-clone"
BOX_12="dm500hd-original"
BOX_13="dm500hd-clone"

list=
for i in $(seq 1 13); do
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
    9)
    machinespecific="dm820"
    ;;
    10)
    machinespecific="dm520-original"
    ;;
    11)
    machinespecific="dm520-clone"
    ;;
    12)
    machinespecific="dm500hd-original"
    ;;
    13)
    machinespecific="dm500hd-clone"
    ;;
    *) clear && exit ;;
    esac

clear
## Menu Select build type ##
TYPE_1="image"
TYPE_2="feed"
TYPE_3="image_with_good_network"
TYPE_4="feed_with_good_network"
list=
for i in $(seq 1 4); do
    p="TYPE_$i"
    list="$list $i ${!p} "
done
list=($list)
build=$(dialog --stdout --clear --colors --menu "Select build type" 12 60 10 ${list[@]})
    case $build in
    1)
    echostr="Compiling $machinespecific image, please wait ..."
    MAKETYPE="image"
    DISTROSTR="local"
    ;;
    2)
    echostr="Compiling $machinespecific image and feed, please wait ..."
    MAKETYPE="feed"
    DISTROSTR="local"
    ;;
    3)
    echostr="Compiling $machinespecific image with good network, please wait ..."
    MAKETYPE="image"
    DISTROSTR="network"
    ;;
    4)
    echostr="Compiling $machinespecific image and feed with good network, please wait ..."
    MAKETYPE="feed"
    DISTROSTR="network"
    ;;
    *) clear && exit ;;
    esac

clear

########## HACK ###########
rm -f $def_path/defconfig
rm -f $def_path2/defconfig
rm -f $def_path3/defconfig

if [ ! -d meta-dream/recipes-local/images/ ]
then
    mkdir meta-dream/recipes-local/images/
fi

if [ ! -d meta-dream/recipes-local/drivers/ ]
then
    mkdir meta-dream/recipes-local/drivers/
fi
###########################
if [ "$machinespecific" = "dm800se-cn" ]; then
    cp -f backup/dm800se-cn/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm800se-en/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm800se-en-clone" ]; then
    cp -f backup/dm800se-en/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm800se-en/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm800se-en-original" ]; then
    cp -f backup/dm800se-en/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm800se-en/original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm8000" ]; then
    echo "$echostr"
    MACHINE=dm8000 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm900-clone" ]; then
    cp -f backup/dm9x0/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm900-clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm900 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm900-original" ]; then
    cp -f backup/dm9x0/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm900-original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm900 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm920" ]; then
    cp -f backup/dm9x0/*.bbappend meta-dream/recipes-local/images/
    echo "$echostr"
    MACHINE=dm920 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm800sev2-en-clone" ]; then
    cp -f backup/dm800sev2-en/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm800sev2-en/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800sev2 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm800sev2-en-original" ]; then
    cp -f backup/dm800sev2-en/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm800sev2-en/original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800sev2 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm820" ]; then
    cp -f backup/dm820/*.bbappend meta-dream/recipes-local/images/
    echo "$echostr"
    MACHINE=dm820 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm520-original" ]; then
    cp -f backup/dm520/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm520/original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm520 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm520-clone" ]; then
    cp -f backup/dm520/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm520/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm520 DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm500hd-original" ]; then
    cp -f backup/dm500hd/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm500hd/original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm500hd DISTRO=${DISTROSTR} make ${MAKETYPE}
elif [ "$machinespecific" = "dm500hd-clone" ]; then
    cp -f backup/dm500hd/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm500hd/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm500hd DISTRO=${DISTROSTR} make ${MAKETYPE}
else
    echo "Please enter a correct choice"
fi
