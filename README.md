lein-field
==========

Leiningen plugin for launching Field.

Build & install:

lein install

Usage:

export FIELD=/home/marc/fieldwork/field/Contents/linux/field_linux64_8.sh

lein new testproject
cd testproject
lein field

Will launch Field "inside" this project. All dependancies will be visible on Field's classpath, all Field sheets will be stored in this project. 


