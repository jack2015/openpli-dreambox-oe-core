Build environment based on OpenPLi-homebuild(develop branch).

https://github.com/OpenPLi/openpli-oe-core/commits/develop <br>
https://github.com/Hains

Thanks to OpenPLi & Hains.

In comparison to OpenPLi this repository has:<br>
-Submodules bitbake, openembedded-core and meta-openembedded from honister branch,<br>
-GCC 11.2.0<br>
-Glibc 2.34<br>
-GStreamer 1.18.6<br>
-Python 2.7.18<br>
-OpenSSL 1.1.1o<br>
-Busybox 1.34.1<br>

******************************************************

Tested with Ubuntu 18.04.06<br><br>
Dependencies:
```
sudo apt install autoconf automake bison bzip2 cvs diffstat flex g++ gawk gcc gettext git git-lfs gzip help2man ncurses-bin lib32ncurses-dev libc6-dev libtool make texinfo patch perl pkg-config subversion tar texi2html zlib1g-dev chrpath libxml2-utils lz4 xsltproc libglib2.0-dev python-setuptools libc6-i386 genromfs guile-2.2-libs quilt zstd
```

Install gcc11
```
sudo apt-get install software-properties-common
sudo add-apt-repository -y ppa:ubuntu-toolchain-r/test
sudo apt-get -q update
sudo apt-get install gcc-11 g++-11
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-11 100 --slave /usr/bin/g++ g++ /usr/bin/g++-11 --slave /usr/bin/gcov gcov /usr/bin/gcov-11
```

Build image step & step:<br>
git clone https://github.com/jack2015/openpli-dreambox-oe-core.git<br>
cd openpli-dreambox-oe-core<br>
make update<br>
./image.sh
