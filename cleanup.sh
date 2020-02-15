#!/bin/sh
echo ""
RED='\033[0;31m'
NC='\033[0m' # No Color
GREEN='\033[0;32m'
echo "Welcome to Open Vision's OE cleanup script!"
echo "After using this script the size of the build folder will be reduced."
echo -e "First tell us what kind of cleanup do you want?"
echo -e "Answers are in ${GREEN}green:${NC}"
echo -e "${GREEN}Fast ${NC}- ${GREEN}Full"
echo -e ""
echo -e "${NC}Enter cleanup mode:${GREEN}"
echo -e ""
read CLEANMODE
echo -e "${NC}"
if [ $CLEANMODE != "Fast" -a $CLEANMODE != "Full" ]
then
	echo -e "${RED}Not a valid answer!${NC}"
	echo -e ""
	exit 0
fi
echo -e "Now check ${RED}Vision-metas.md ${NC}and enter a specific machine to cleanup:"
echo -e "${GREEN}"
read MACHINE
echo -e "${NC}"
echo -e "Removing ${GREEN}$MACHINE${NC} build folders, please wait ..."
echo ""
rm -rf build/tmp/buildstats/*
echo ""
echo "buildstats cleaned!"
echo ""
rm -rf build/tmp/cache/default-glibc/$MACHINE
echo ""
echo "cache/default-glibc cleaned!"
echo ""
rm -rf build/tmp/deploy/ipk/$MACHINE
echo ""
echo "deploy/ipk cleaned!"
echo ""
rm -rf build/tmp/deploy/images/$MACHINE
echo ""
echo "deploy/images cleaned!"
echo ""
rm -rf build/tmp/log/cooker/$MACHINE
echo ""
echo "log/cooker cleaned!"
echo ""
rm -rf build/tmp/pkgdata/$MACHINE
echo ""
echo "pkgdata cleaned!"
echo ""
rm -rf build/tmp/sstate-control/*$MACHINE*
echo ""
echo "sstate-control cleaned!"
echo ""
rm -rf build/tmp/stamps/$MACHINE-oe-linu*
echo ""
echo "stamps cleaned!"
echo ""
find build/tmp/stamps -name "*.$MACHINE" -type f -exec rm -f {} \;
echo ""
echo "packagedata_setscene cleaned!"
echo ""
rm -rf build/tmp/sysroots-components/$MACHINE
echo ""
echo "sysroots-components cleaned!"
echo ""
rm -rf build/tmp/work/$MACHINE-oe-linu*
echo ""
echo "work cleaned!"
echo ""
rm -rf build/tmp/work-shared/$MACHINE
echo ""
echo "work-shared cleaned!"
echo ""
if [ $CLEANMODE = "Full" ]
then
	find build/tmp/work/*/*/*/temp -type f -exec rm -f {} \;
	echo ""
	echo "temp cleaned!"
	echo ""
	cd build/tmp/work
	find \( -iname \*_$MACHINE_*.adb -o -iname \*_$MACHINE_*.ads \) -type f -exec rm -f {} \;
	cd ..
	cd ..
	cd ..
	echo ""
	echo "work's adb ads files cleaned!"
	echo ""
	cd build/tmp/work-shared
	find \( -iname \*_$MACHINE_*.adb -o -iname \*_$MACHINE_*.ads \) -type f -exec rm -f {} \;
	cd ..
	cd ..
	cd ..
	echo ""
	echo "work-shared's adb ads files cleaned!"
	echo ""
fi
echo "Done."
