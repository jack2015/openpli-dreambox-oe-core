#!/bin/sh

echo ""
echo "Updating from git, please wait ..."
echo ""
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
git pull
git submodule sync
git submodule update --init
git pull
echo ""
echo "Done!"
echo ""
echo ""
cd meta-python2
echo "Checking out meta-python2 master branch:"
git checkout master
git pull
echo ""
echo "Done!"
echo ""
echo ""
