#!/bin/sh

# meta-python2
COMMIT1="3fae17aece0e6d82f56965fe501bf7080c671df8"

# bitbake
COMMIT2="5d02c98489d3a5836676b9c3fb3bd0157449db2b"

# meta-openembedded
COMMIT3="f3f7a5f1a4713f145107bb043e0d14cb3a51c62f"

# openembedded-core
COMMIT4="fdae970656cc421c542af9856bc9ae038c61db13"

echo ""
echo "Updating from git, please wait ..."
echo ""
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd "${SCRIPTPATH}"
cd meta-python2
git checkout gatesgarth
echo "Checking out meta-python2 gatesgarth branch: ${COMMIT1}"
git branch -D temp103
git checkout ${COMMIT1} -b temp103
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd bitbake
git checkout 1.48
echo "Checking out bitbake 1.48 branch: ${COMMIT2}"
git branch -D temp103
git checkout ${COMMIT2} -b temp103
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd meta-openembedded
git checkout gatesgarth
echo "Checking out meta-openembedded gatesgarth branch: ${COMMIT3}"
git branch -D temp103
git checkout ${COMMIT3} -b temp103
echo ""
echo "Done!"
echo ""
echo ""
cd "${SCRIPTPATH}"
cd openembedded-core
git checkout gatesgarth
echo "Checking out openembedded-core gatesgarth branch: ${COMMIT4}"
git branch -D temp103
git checkout ${COMMIT4} -b temp103
echo ""
echo "Done!"
echo ""
echo ""
