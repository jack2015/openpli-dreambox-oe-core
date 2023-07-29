#
GIT_SITE = "${@ 'git://gitlab.com/jack2015' if d.getVar('CODEWEBSITE') else 'git://gitee.com/jackgee2021'}"
SRC_URI:remove = "git://github.com/vim/vim.git;branch=master;protocol=https"
SRC_URI:append = " ${GIT_SITE}/vim;branch=master;protocol=https"
