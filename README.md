Build environment based on OpenPLi-homebuild(develop branch).

https://github.com/OpenPLi/openpli-oe-core/commits/develop <br>
https://github.com/Hains

Thanks to OpenPLi & Hains.

In comparison to OpenPLi this repository has:<br>
-Submodules bitbake, openembedded-core and meta-openembedded from master branch,<br>
-GCC 12.1.0<br>
-Glibc 2.35<br>
-GStreamer 1.18.6<br>
-Python 2.7.18<br>
-OpenSSL 3.0.5<br>
-Busybox 1.35.0<br>

******************************************************

Tested with Ubuntu 22.04<br><br>

1. Dependencies:
```
sudo apt install dialog upx-ucl autoconf automake bison bzip2 cvs diffstat flex g++ gawk gcc gettext git git-lfs gzip help2man ncurses-bin lib32ncurses-dev libc6-dev libtool make texinfo patch perl pkg-config subversion tar texi2html zlib1g-dev chrpath libxml2-utils lz4 xsltproc libglib2.0-dev python-setuptools libc6-i386 genromfs guile-2.2-libs quilt zstd
```

2. Set python2 as preferred provider for python
```
sudo update-alternatives --install /usr/bin/python python /usr/bin/python2 1

sudo update-alternatives --install /usr/bin/python python /usr/bin/python3 2

sudo update-alternatives --config python
↳ Select python2
```

3. Set your shell to /bin/bash
```
sudo dpkg-reconfigure dash
↳ Select "NO" when asked "Install dash as /bin/sh?"
```

Build image step & step:<br>
git clone https://github.com/jack2015/openpli-dreambox-oe-core.git<br>
cd openpli-dreambox-oe-core<br>
make update<br>
./image.sh
