SUMMARY = "Create Dreambox eMMC boot BLOBs"
SECTION = "console/utils"
require conf/license/openpli-gplv2.inc

SRCREV = "a461d5195083a3867a3ff8b67883c294c2b5ed0d"

inherit autotools opendreambox-git

BBCLASSEXTEND = "native"
