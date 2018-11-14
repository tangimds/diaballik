#!/bin/sh

name1="Tangi-MENDES"
name2="Taha-YASSINE"
release="D1"-$name1-$name2

rm -r $release 2> /dev/null
mkdir $release
cd ..
zip -r diaballik-bundle/$release/diaballik-D1-$name1-$name2.zip diaballik-doc
