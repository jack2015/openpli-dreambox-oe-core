#!/bin/sh

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
BOX_4="dm900-clone"
BOX_5="dm900-original"
BOX_6="dm920"
BOX_7="dm500hd"

list=
for i in $(seq 1 7); do
    p="BOX_$i"
    list="$list $i ${!p} "
done
list=($list) #00ff2525
box=$(dialog --stdout --clear --colors --menu "Build Dreambox Image" 22 70 10 ${list[@]})
    case $box in
    1)
    machinespecific="dm800se-cn"
    boxsim="clone"
    ;;
    2)
    machinespecific="dm800se-en-clone"
    boxsim="clone"
    ;;
    3)
    machinespecific="dm800se-en-original"
    boxsim="original"
    ;;
    4)
    machinespecific="dm900-clone"
    boxsim="clone"
    ;;
    5)
    machinespecific="dm900-original"
    boxsim="original"
    ;;
    6)
    machinespecific="dm920"
    boxsim="original"
    ;;
    7)
    machinespecific="dm500hd"
    boxsim="original"
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
    MACHINE=dm800se MACHINESIM=${boxsim} make ${MAKETYPE}
elif [ "$machinespecific" = "dm800se-en-clone" ]; then
    cp -f backup/dm800se-en/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm800se-en/clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se MACHINESIM=${boxsim} make ${MAKETYPE}
elif [ "$machinespecific" = "dm800se-en-original" ]; then
    cp -f backup/dm800se-en/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm800se-en/original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm800se MACHINESIM=${boxsim} make ${MAKETYPE}
elif [ "$machinespecific" = "dm900-clone" ]; then
    cp -f backup/dm9x0/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm900-clone/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm900 MACHINESIM=${boxsim} make ${MAKETYPE}
elif [ "$machinespecific" = "dm900-original" ]; then
    cp -f backup/dm9x0/*.bbappend meta-dream/recipes-local/images/
    cp -f backup/dm900-original/* meta-dream/recipes-local/drivers/
    echo "$echostr"
    MACHINE=dm900 MACHINESIM=${boxsim} make ${MAKETYPE}
elif [ "$machinespecific" = "dm920" ]; then
    cp -f backup/dm9x0/*.bbappend meta-dream/recipes-local/images/
    echo "$echostr"
    MACHINE=dm920 MACHINESIM=${boxsim} make ${MAKETYPE}
elif [ "$machinespecific" = "dm500hd" ]; then
    cp -f backup/dm500hd/*.bbappend meta-dream/recipes-local/images/
    echo "$echostr"
    MACHINE=dm500hd MACHINESIM=${boxsim} make ${MAKETYPE}
else
	echo "Please enter a correct choice"
fi


