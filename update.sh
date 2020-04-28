#!/bin/sh

echo ""
echo "Updating from git, please wait ..."
echo ""
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
cd meta-python2
echo "Checking out meta-python2 dunfell branch:"
git checkout .
git checkout dunfell
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd bitbake
echo "Checking out bitbake 1.46 branch:"
git checkout .
git checkout 1.46
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd meta-openembedded
echo "Checking out meta-openembedded dunfell branch:"
git checkout .
git checkout dunfell
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd openembedded-core
echo "Checking out openembedded-core dunfell branch:"
git checkout .
git checkout dunfell
git pull
echo ""
echo "Done!"
echo ""
echo ""
