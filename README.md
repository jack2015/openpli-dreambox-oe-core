Build environment based on OpenPLi-homebuild(develop branch).

https://github.com/OpenPLi/openpli-oe-core/commits/develop <br>
https://github.com/Hains

Thanks to OpenPLi & Hains.

In comparison to OpenPLi this repository has:<br>
-Submodules bitbake, openembedded-core and meta-openembedded from master branch,<br>
-GCC 12.2.0<br>
-Glibc 2.36<br>
-GStreamer 1.20.5<br>
-Python 2.7.18<br>
-OpenSSL 3.0.7<br>
-Busybox 1.35.0<br>
-Linux OS 3.2/3.4/3.14

******************************************************

Tested with Ubuntu 18.04.06 & 22.04<br><br>

1. Dependencies:
```
sudo apt install dialog autoconf automake bison bzip2 cvs diffstat \
flex g++ gawk gcc gettext git git-lfs gzip help2man ncurses-bin lib32ncurses-dev \
libc6-dev libtool make texinfo patch perl pkg-config subversion tar texi2html \
zlib1g-dev chrpath libxml2-utils lz4 xsltproc libglib2.0-dev python-setuptools \
libc6-i386 genromfs guile-2.2-libs quilt zstd

```

2. Set python2 as preferred provider for python:
```
sudo update-alternatives --install /usr/bin/python python /usr/bin/python2 1
sudo update-alternatives --install /usr/bin/python python /usr/bin/python3 2
sudo update-alternatives --config python
↳ Select python2

```

3. Set your shell to /bin/bash:
```
sudo dpkg-reconfigure dash
↳ Select "NO" when asked "Install dash as /bin/sh?"
```

4. Modify max_user_watches:
```
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf
sudo sysctl -n -w fs.inotify.max_user_watches=524288
```

5. Install gcc11:
```
sudo apt-get install software-properties-common
sudo add-apt-repository -y ppa:ubuntu-toolchain-r/test
sudo apt-get -q update
sudo apt-get install gcc-11 g++-11
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-11 100 --slave /usr/bin/g++ g++ /usr/bin/g++-11 --slave /usr/bin/gcov gcov /usr/bin/gcov-11
```

6. Build image step & step:
```
git clone https://github.com/jack2015/openpli-dreambox-oe-core.git
cd openpli-dreambox-oe-core
make update
git checkout master
make update
./image.sh
```
