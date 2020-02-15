#!/bin/sh

SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"

if [ ! -d $SCRIPTPATH/build/tmp/sysroots-components/x86_64/upx-native/usr/bin ]; then
	mkdir -p $SCRIPTPATH/build/tmp/sysroots-components/x86_64/upx-native/usr/bin
fi
ln -sfn /usr/bin/upx $SCRIPTPATH/build/tmp/sysroots-components/x86_64/upx-native/usr/bin/upx

def_path="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.2"

echo "Please enter: (1)dm800se-cn (2)dm800se-en"

read MACHINESPECIFIC

if [ $MACHINESPECIFIC != "dm8000" -a $MACHINESPECIFIC != "dm800se-cn" -a $MACHINESPECIFIC != "dm800se-en" ]
then
	echo -e "${RED}Not a valid answer!${NC}"
	echo -e ""
	exit 0
fi

if [ $MACHINESPECIFIC = "dm800se-cn" ]; then
        cp -pf $def_path/dm800se/defconfig $def_path/defconfig
	cp -pf ../backup/dm800se-cn/* meta-dream/recipes-local/images/
	echo "Compiling $MACHINESPECIFIC image, please wait ..."
        MACHINE=dm800se make image
elif [ $MACHINESPECIFIC = "dm800se-en" ]; then
        cp -pf $def_path/dm800se/defconfig $def_path/defconfig
	cp -pf ../backup/dm800se-en/* meta-dream/recipes-local/images/
	echo "Compiling $MACHINESPECIFIC image, please wait ..."
        MACHINE=dm800se make image
elif [ $MACHINESPECIFIC = "dm8000" ]; then
        cp -pf $def_path/dm8000/defconfig $def_path/defconfig
	echo "Compiling $MACHINESPECIFIC image, please wait ..."
        MACHINE=dm8000 make image
else
	echo "Please enter a correct choice"
fi


