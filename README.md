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

Will launch Field "inside" this project. All project.clj `dependencies` will be visible on Field's classpath, all Field sheets will be stored in this project. 

Options:

    :field-arguments ["-stereo" "1" "-opengl32" "1"] ; add arguments to Field's command line


Comming soon: Syntax Highlighting (borrowed from arthuredelstein/clooj) and completion (borrowed from alexander-yakushev/compliment).
