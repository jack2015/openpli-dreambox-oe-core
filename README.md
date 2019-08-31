Build environment dreambox 8000 based on OpenPLi-homebuild(develop branch).

https://github.com/OpenPLi/openpli-oe-core/commits/develop

Thanks to OpenPLi to make this possible.

In comparison to OpenPLi this repository has:<br>
-Submodules bitbake, openembedded-core and meta-openembedded from master branch,<br>
&nbsp;with git snapshot of August 31th, 2019.<br>
-GCC 9.2.0<br>
-Glibc 2.30<br>
-GStreamer 1.17.0.1<br>
-Python 2.7.16<br>
-OpenSSL 1.1.1c<br>
-Busybox 1.31.0<br>
-Dreambox kernel 3.2.102<br>
and more.<br>
<br>
<br>
Feel free to send pull-request.

Tested on Ubuntu 19.04.
<br>
<br>
Dependencies:
```
sudo apt install autoconf automake bison bzip2 cvs diffstat flex g++ gawk gcc gettext git git-lfs gzip help2man ncurses-bin lib32ncurses5-dev libc6-dev libtool texinfo patch perl pkg-config subversion tar texi2html zlib1g-dev chrpath libxml2-utils xsltproc libglib2.0-dev python-setuptools libc6-i386 genromfs guile-2.0-libs quilt
```
To build image:
```
git clone https://github.com/Hains/openpli-dm8000-oe-core.git

cd openpli-dm8000-oe-core

make image
```
When the build is finished, the image openpli-enigma2-homebuild-dm8000.nfi is in the:
```
build/tmp/deploy/images/dm8000/
```
directory.

To build feed:
```
make feed
```

To update your box:

Install apache2:
```
sudo apt install apache2
```
Create symlinks to your build-environment:
```
cd /var/www/html

sudo mkdir feeds;cd feeds;sudo mkdir openpli-9.2;cd openpli-9.2;

sudo ln -s /home/<your username>/openpli-dm8000-oe-core/build/tmp/deploy/ipk/dm8000 dm8000 

sudo ln -s /home/<your username>/openpli-dm8000-oe-core/build/tmp/deploy/ipk/all all

sudo ln -s /home/<your username>/openpli-dm8000-oe-core/build/tmp/deploy/ipk/mips32el mips32el
```
Add hostname or ip address to the site.conf file (exist after make command), e.g. at the end of the file.
```
DISTRO_HOST = " <your ip address or hostname> "
```
To update the image, run:
```
make image                         // update image only.
```
or  
```
make feed                          // update image and feed.
```

To update build-environment including all submodules, run:
```
make update
```

Notice: 
* Run 'make feed' twice before you update the box!

==========================================================
