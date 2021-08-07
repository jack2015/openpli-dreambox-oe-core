Build environment dreambox 8000 based on OpenPLi-homebuild(develop branch).

https://github.com/OpenPLi/openpli-oe-core/commits/develop

Thanks to OpenPLi.

In comparison to OpenPLi this repository has:<br>
-Submodules bitbake, openembedded-core and meta-openembedded from master branch,<br>
&nbsp;with git snapshot of August 7, 2021.<br>
-GCC 11.2.0<br>
-Glibc 2.33<br>
-GStreamer 1.19.1.1<br>
-Python 2.7.18<br>
-OpenSSL 1.1.1k<br>
-Busybox 1.33.1<br>
-Dreambox kernel 3.2.102<br>
and more.<br>
<br>
<br>
Feel free to send pull-request.

Tested with Ubuntu 21.04.
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
When the build is finished, the openpli-enigma2-11.2-dm8000.nfi image file is in the:
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

sudo mkdir feeds;cd feeds;sudo mkdir openpli-11.2;cd openpli-11.2;

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
