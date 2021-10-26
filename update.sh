#!/bin/sh

echo ""
echo "Updating from git, please wait ..."
echo ""
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
cd meta-python2
echo "Checking out meta-python2 gatesgarth branch:"
git checkout gatesgarth
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd bitbake
echo "Checking out bitbake 1.48 branch:"
git checkout 1.48
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd meta-openembedded
echo "Checking out meta-openembedded gatesgarth branch:"
git checkout gatesgarth
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd openembedded-core
echo "Checking out openembedded-core gatesgarth branch:"
git checkout gatesgarth
git pull
echo ""
echo "Done!"
echo ""
echo ""
