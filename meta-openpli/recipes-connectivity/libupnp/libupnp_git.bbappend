PV = "1.6.x+git${SRCPV}"
SRCREV = "9cfe6b0197ffadb4e224463953056132f9a863dc"
GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI = "${GIT_SITE}/pupnp-backup;protocol=https;branch=branch-1.6.x"
