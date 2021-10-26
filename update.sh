#!/bin/sh

echo ""
echo "Updating from git, please wait ..."
echo ""
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
cd meta-python2
echo "Checking out meta-python2 honister branch:"
git checkout honister
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd bitbake
echo "Checking out bitbake 1.52 branch:"
git checkout 1.52
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd meta-openembedded
echo "Checking out meta-openembedded honister branch:"
git checkout honister
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd openembedded-core
echo "Checking out openembedded-core honister branch:"
git checkout honister
git pull
echo ""
echo "Done!"
echo ""
echo ""
