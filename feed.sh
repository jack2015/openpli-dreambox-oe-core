#!/bin/sh

SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"

def_path="${SCRIPTPATH}/meta-dream/recipes-bsp/linux/linux-dreambox-3.2"

cp -pf $def_path/dm800se/defconfig $def_path/defconfig
cp -pf backup/dm800se-en/* meta-dream/recipes-local/images/
cp -pf backup/image-size/64m/* meta-dream/conf/machine/include/
cp -pf backup/serviceapp/normal/enigma2.bbappend meta-dream/recipes-local/enigma2/
cp -pf backup/serviceapp/normal/enigma2-plugin-systemplugins-serviceapp_0.5.bbappend meta-dream/recipes-local/enigma2-plugins/
echo "Compiling dm800se english image & feed, please wait ..."

MACHINE=dm800se make feed

