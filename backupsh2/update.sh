#!/bin/sh

echo ""
echo "Updating hardknott OE-A from git, please wait ..."
echo ""
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
cd meta-python2
echo "Checking out meta-python2 hardknott branch:"
git checkout hardknott
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd bitbake
echo "Checking out bitbake 1.50 branch:"
git checkout 1.50
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd meta-openembedded
echo "Checking out meta-openembedded hardknott branch:"
git checkout hardknott
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd openembedded-core
echo "Checking out openembedded-core hardknott branch:"
git checkout hardknott
git pull
echo ""
echo "Done!"
echo ""
echo ""
