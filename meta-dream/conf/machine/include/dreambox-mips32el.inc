# dreambox-mips32el.inc

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

MTD_KERNEL = "mtd2"

require conf/machine/include/dreambox.inc
require conf/machine/include/mips/tune-mips32.inc

MACHINE_KERNEL_PR = "r14"

KERNEL_SPLIT_MODULES = "1"
EXTENDPKGV = ""
