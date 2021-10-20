#!/bin/sh

# meta-python2
COMMIT1="c43c29e"

# bitbake
COMMIT2="efd026c"

# meta-openembedded
COMMIT3="2c7bbea"

# openembedded-core
COMMIT4="c2d9612"

echo ""
echo "Updating from git, please wait ..."
echo ""
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
cd meta-python2
echo "Checking out meta-python2 master branch: ${COMMIT1}"
git branch -D temp
git checkout ${COMMIT1} -b temp
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd bitbake
echo "Checking out bitbake master branch: ${COMMIT2}"
git branch -D temp
git checkout ${COMMIT2} -b temp
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd meta-openembedded
echo "Checking out meta-openembedded master branch: ${COMMIT3}"
git branch -D temp
git checkout ${COMMIT3} -b temp
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd openembedded-core
echo "Checking out openembedded-core master branch: ${COMMIT4}"
git branch -D temp
git checkout ${COMMIT4} -b temp
echo ""
echo "Done!"
echo ""
echo ""
